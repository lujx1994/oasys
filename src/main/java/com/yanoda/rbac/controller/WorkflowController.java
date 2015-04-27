package com.yanoda.rbac.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yanoda.rbac.domain.DefaultFlow;
import com.yanoda.rbac.domain.Delegate;
import com.yanoda.rbac.domain.Form;
import com.yanoda.rbac.domain.Page;
import com.yanoda.rbac.domain.Role;
import com.yanoda.rbac.domain.User;
import com.yanoda.rbac.domain.Workflow;
import com.yanoda.rbac.inputcheck.WorkflowCheck;
import com.yanoda.rbac.service.DefaultFlowService;
import com.yanoda.rbac.service.DelegateService;
import com.yanoda.rbac.service.FormService;
import com.yanoda.rbac.service.DepartmentService;
import com.yanoda.rbac.service.PaginationService;
import com.yanoda.rbac.service.PermissionCheckService;
import com.yanoda.rbac.service.RoleService;
import com.yanoda.rbac.service.WorkflowService;

@Controller
@RequestMapping("workflow")
public class WorkflowController {
	@Autowired
	private PaginationService paginationService;
	@Autowired
	private FormService formService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private WorkflowService workflowService;
	@Autowired
	private DefaultFlowService defaultFlowService;
	@Autowired
	private DelegateService delegateService;
	@Autowired
	private PermissionCheckService permissionCheckService;
	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private ServletContext context;

	@RequestMapping("nworkflow/{formId}")
	public ModelAndView createWorkflow(@PathVariable int formId,
			RedirectAttributes attr, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		int department_id = (int) request.getSession().getAttribute("userDepartment");
		Form form = formService.getForm(formId);
		List<Role> roleList = roleService.getRole();
		List<DefaultFlow> defaultFlows = defaultFlowService
				.getSelectActive(formId, department_id);
		for (DefaultFlow defaultFlow : defaultFlows) {
			StringBuffer stringBuffer = new StringBuffer();
			String[] defaultWorkflow = defaultFlow.getParticipate().split(",");
			for (int i = 0; i < defaultWorkflow.length; i++) {
				String roleInfo = roleService
						.getRoleNameById(defaultWorkflow[i]);
				int j = roleInfo.indexOf("-");
				if (i == defaultWorkflow.length - 1) {
					stringBuffer.append(roleInfo.substring(j + 1));
				} else {
					stringBuffer.append(roleInfo.substring(j + 1) + "---->>");
				}
			}
			defaultFlow.setName(stringBuffer.toString());
		}

		map.put("roleList", roleList);
		map.put("form", form);
		map.put("defaultFlows", defaultFlows);

		return new ModelAndView("/WEB-INF/frontend/nworkflow", map);
	}

	@RequestMapping("cworkflow/{formId}")
	public ModelAndView commitWorkflow(@PathVariable int formId,
			HttpServletRequest request, RedirectAttributes attr) {
		HttpSession session = request.getSession();
		int id = (int) session.getAttribute("id");
		if (session.getAttribute("checkMessage") != null) {
			attr.addAttribute("checked", 2);
			return new ModelAndView("redirect:/workflow/nworkflow/" + formId
					+ ".do");
		}
		HashMap<Integer, User> rbac_users = (HashMap<Integer, User>) context.getAttribute("rbac_users");
		HashMap<Integer, Role> rbac_actions = (HashMap<Integer, Role>) context.getAttribute("rbac_actions");
		if (!permissionCheckService.doCheckPermissionRole(id, rbac_users, rbac_actions)) {
			return new ModelAndView("/error");
		}
		
		String workformName = request.getParameter("workformName");
		String mWorkflow = request.getParameter("mWorkflow");
		String content = request.getParameter("editor1");

		// test string -> bute[], byte[] -> string

		/*
		 * byte[] demo = null; demo = content.getBytes();
		 * System.out.println(demo); String afterdemo = null; afterdemo = new
		 * String(demo); System.out.println(afterdemo);
		 */

		String checked = WorkflowCheck.doCheckNull(mWorkflow, workformName,
				content);

		if (checked.equals("ok")) {
			mWorkflow = mWorkflow.trim();
			workformName = workformName.trim();
			content = content.trim();
			checked = WorkflowCheck.doMatch(mWorkflow, workformName);
		}

		String roleflow;
		String custom;
		if (checked.equals("ok")) {
			if (mWorkflow.substring(0, 1).equals("0")) {
				roleflow = mWorkflow.substring(2);
				custom = "t";
			} else {
				// in roleflow, for example 1, 3, default flow is 4, 5 when i
				// have 3, it will get 4, 5.
				String workflow[] = mWorkflow.split(",");
				roleflow = workflow[workflow.length - 1];
				for (int i = workflow.length - 2; i >= 0; i--) {
					List<Role> roleList = rbac_users.get(id).getRoleList();
					for (Role role : roleList) {
						if (role.getId() == Integer.valueOf(workflow[i])) {
							break;
						}
					}
					roleflow = workflow[i] + "," + roleflow;
				}
				custom = "f";
			}

			Date date = new Date();
			String dateString = date.toString();
			System.out.println(dateString);
			int count = workflowService.createWorkflow(workformName, roleflow,
					content.getBytes(), custom, id, dateString);
			if (count != 0) {
				session.setAttribute("checkMessage", "success");
				attr.addAttribute("checked", 1);
				return new ModelAndView("redirect:/workflow/nworkflow/"
						+ formId + ".do");
			}
		}
		attr.addAttribute("checked", -1);
		return new ModelAndView("redirect:/workflow/nworkflow/" + formId
				+ ".do");
	}

	@RequestMapping("pending")
	public ModelAndView getPendingWorkflow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int id = (Integer) request.getSession().getAttribute("id");

		HashMap<Integer, User> rbac_users = (HashMap<Integer, User>) context
				.getAttribute("rbac_users");

		System.out.println("getPendingFlow----" + rbac_users);

		HashMap<String, Object> map = new HashMap<String, Object>();

		List<Workflow> workflows = workflowService.getAllPendingWorkflowList();

		StringBuilder readyFor = new StringBuilder();
		boolean check = false;

		// who delegate to me
		List<Delegate> DelegateList = delegateService.selectDelegate(id);

		for (Workflow workflow : workflows) {
			check = permissionCheckService.doCheckPermisson(workflow, id,
					rbac_users);
			System.out.println(check);
			if (check) {
				int flowId = workflow.getId();
				readyFor.append(flowId + ",");
			} else if (DelegateList != null) {
				for (Delegate delegate : DelegateList) {
					check = permissionCheckService.doCheckPermisson(workflow,
							delegate.getUser_id(), rbac_users);
					if (check) {
						int flowId = workflow.getId();
						readyFor.append(flowId + ",");
						break;
					}
				}
			}
		}

		String myReadyFor = null;

		if (readyFor.length() != 0) {
			myReadyFor = readyFor.toString();
			myReadyFor = myReadyFor.substring(0, readyFor.length() - 1);
		}

		// to find the relative flow
		System.out.println("relative flow----" + myReadyFor);

		if (myReadyFor != null) {
			myReadyFor = " WHERE id IN (" + myReadyFor + ")";

			int pageNumber;
			if (request.getParameter("pageNumber") == null) {
				pageNumber = 1;
			} else {
				pageNumber = Integer
						.valueOf(request.getParameter("pageNumber"));
			}

			Page page = this.paginationService.PaginationUtil(pageNumber, 10,
					"workflow", myReadyFor);
			if (page.getTotal() != 0) {
				String[] columns = { "id", "name", "create_user_id",
						"create_time", "status" };
				List<HashMap<String, Object>> rows = this.paginationService
						.getRows(columns, page.getTableName(), page.getWhere());
				System.out.println(rows.get(0).get("name"));
				map.put("rows", rows);
				map.put("page", page);
			}
		}

		return new ModelAndView("/WEB-INF/frontend/rworkflow", map);
	}

	@RequestMapping("modifyflow/{flowId}")
	public ModelAndView modifyWorkflow(@PathVariable int flowId,
			HttpServletRequest request, HttpServletResponse response) {
		/*
		 * int user_id = (Integer) request.getSession().getAttribute("user_id");
		 * HttpSession session = request.getSession();
		 * 
		 * @SuppressWarnings("unchecked") HashMap<Integer, User> rbac =
		 * (HashMap<Integer, User>) session
		 * .getServletContext().getAttribute("rbac");
		 */

		HashMap<Integer, User> rbac_users = (HashMap<Integer, User>) context
				.getAttribute("rbac_users");
		int id = (Integer) request.getSession().getAttribute("id");

		// delegate function
		/*
		 * ArrayList<Delegate> DelegateList =
		 * D_Delegate.doSelectDelegate(accountid);
		 */

		Workflow workflow = null;
		int count = 0;

		// check permission
		boolean check = false;
		/* int delegateAccountId = accountid; */

		workflow = workflowService.getWorkflowDetail(flowId);

		if (workflow != null) {
			check = permissionCheckService.doCheckPermisson(workflow, id,
					rbac_users);
			/*
			 * if(!check) { for (Delegate delegate : DelegateList) {
			 * check=CheckPermission.doCheckPermisson(workflow,
			 * delegate.getAccountId(), rbac); if(check) {
			 * delegateAccountId=delegate.getAccountId(); break; } } }
			 */
		}

		String decision = request.getParameter("decision");
		String content = request.getParameter("editor1");

		if (decision != null && content != null && workflow != null && check) {
			if (decision.equals("agree") || decision.equals("reject")) {

				String accountflow = "#";
				String roleflow;
				String roleflowArray[];
				// status = 0-success, 1-finished, 2-rejected
				int status = 0;

				StringBuilder sb = new StringBuilder();

				/*
				 * if(workflow.getUser_flow()==null) { if(delegateAccountId !=
				 * accountid) {
				 * accountflow=delegateAccountId+"-"+String.valueOf(accountid);
				 * }else { accountflow=String.valueOf(accountid); } }else{
				 * if(delegateAccountId != accountid) {
				 * accountflow=delegateAccountId
				 * +"-"+String.valueOf(accountid)+","+workflow.getAccountflow();
				 * }else {
				 * accountflow=String.valueOf(accountid)+","+workflow.getAccountflow
				 * (); } }
				 */

				roleflowArray = workflow.getRole_flow().split(",");
				if (roleflowArray.length == 1) {
					status = 1;
					// save the last role
					roleflow = String.valueOf(rbac_users.get(id)
							.getDepartment_id());
				} else {
					for (int i = 1; i < roleflowArray.length; i++) {
						sb.append(roleflowArray[i] + ",");
					}
					roleflow = sb.toString();
					roleflow = roleflow.substring(0, roleflow.length() - 1);
				}

				if (decision.equals("reject")) {
					status = 2;
					roleflow = String.valueOf(rbac_users.get(id)
							.getDepartment_id());
				}
				count = workflowService.doModifyWorkflow(flowId,
						content.getBytes(), accountflow, roleflow, status);
				String roleflowCheck[];
				roleflowCheck = workflowService.getWorkflowDetail(flowId)
						.getRole_flow().split(",");
				if (roleflowCheck.length == 1) {
					try {
						workflowService.updateStatusById(flowId);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				if (count == 1) {
					request.setAttribute("message", "success");
					return new ModelAndView("redirect:/workflow/pending.do");
				}
			}
		}
		return new ModelAndView("rworkflow");
	}

	@RequestMapping("detailflow/{flowId}")
	public ModelAndView detailWorkflow(@PathVariable int flowId,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		HashMap<Integer, Role> rbac_roles = (HashMap<Integer, Role>) context
				.getAttribute("rbac_roles");
		HashMap<Integer, User> rbac_users = (HashMap<Integer, User>) context
				.getAttribute("rbac_users");
		System.out.println(rbac_roles);
		int id = (Integer) session.getAttribute("id");
		/* List<Delegate> DelegateList = delegateService.selectDelegate(id); */

		// check permission
		Workflow workflow = null;
		boolean check = false;

		System.out.println(flowId);
		workflow = workflowService.getWorkflowDetail(flowId);
		System.out.println("workflowdetail" + id);
		System.out.println("workflowdetail" + rbac_users);
		if (workflow != null) {
			check = permissionCheckService.doCheckPermisson(workflow, id,
					rbac_users);
			/*
			 * if(!check) { for (Delegate delegate : DelegateList) { check =
			 * permissionCheckService.doCheckPermisson(workflow,
			 * delegate.getUser_id(), rbac_users); if(check) break; } }
			 */
		}

		ArrayList<String> finishInfo = null;
		System.out.println("detailflow" + check);
		if (check) {
			if (workflow.getUser_flow() != null
					&& !workflow.getUser_flow().equals("")
					&& !workflow.getUser_flow().equals("#")) {
				String userFlow[] = workflow.getUser_flow().split(",");
				finishInfo = new ArrayList<String>();
				String userInfo = null;

				for (String user : userFlow) {
					// 如果是委托的
					/*
					 * if(user.contains("-")) { String userIdInfo[] =
					 * user.split("-"); //委托人和经办人信息 int
					 * userId=Integer.valueOf(userIdInfo[0]); int
					 * delegateId=Integer.valueOf(userIdInfo[1]);
					 * 
					 * userInfo =
					 * departmentService.getGroupById(rbac_users.get(userId
					 * ).getDepartment_id()).getName()+
					 * "-"+rbac_roles.get(rbac_users
					 * .get(userId).getDefault_role_id()).getName()+
					 * "-"+rbac_users.get(userId).getTruename() +"-->"+
					 * D_Department
					 * .doSelect(rbac.get(delegateId).getDepartmentId
					 * ()).getAlias()+
					 * "-"+roles.get(rbac.get(delegateId).getDefault_roleid
					 * ()).getAlias()+ "-"+rbac.get(delegateId).getFullname();
					 * 
					 * finishInfo.add(accountInfo); }else {
					 */
					int userId = Integer.valueOf(user);
					userInfo = departmentService.getGroupById(
							rbac_users.get(userId).getDepartment_id())
							.getName()
							+ "-"
							+ rbac_roles
									.get(rbac_users.get(userId)
											.getDefault_role_id()).getName()
							+ "-" + rbac_users.get(userId).getTruename();
					finishInfo.add(userInfo);
					/* } */
				}
			}
		} else {
			return new ModelAndView("redirect:/workflow/pending.do");
		}
		System.out.println(rbac_roles);
		System.out.println(workflow.getCreate_user_id()
				+ "workflow.getCreate_user_id()");
		System.out.println(rbac_users.get(workflow.getCreate_user_id())
				+ "rbac_users.get(workflow.getCreate_user_id())");
		System.out
				.println(rbac_users.get(workflow.getCreate_user_id())
						.getDefault_role_id()
						+ "rbac_users.get(workflow.getCreate_user_id()).getDefault_role_id()");
		System.out
				.println(rbac_roles.get(rbac_users.get(
						workflow.getCreate_user_id()).getDefault_role_id())
						+ "rbac_roles.get(rbac_users.get(workflow.getCreate_user_id()).getDefault_role_id())");
		System.out
				.println(rbac_roles.get(
						rbac_users.get(workflow.getCreate_user_id())
								.getDefault_role_id()).getName()
						+ "rbac_roles.get(rbac_users.get(workflow.getCreate_user_id()).getDefault_role_id()).getName()");
		System.out.println(rbac_roles.get(
				rbac_users.get(workflow.getCreate_user_id())
						.getDefault_role_id()).getName()
				+ "rbac_roles.get(1)");
		System.out.println(rbac_roles.get(
				rbac_users.get(workflow.getCreate_user_id())
						.getDefault_role_id()).getName()
				+ "rbac_roles.get(1).getName()");
		String create_userInfo = departmentService
				.getGroupById(
						rbac_users.get(workflow.getCreate_user_id())
								.getDepartment_id()).getName()
				+ "-"
				+ rbac_roles.get(
						rbac_users.get(workflow.getCreate_user_id())
								.getDefault_role_id()).getName()
				+ "-"
				+ rbac_users.get(workflow.getCreate_user_id()).getTruename();

		request.setAttribute("userInfo", create_userInfo);
		request.setAttribute("finishInfo", finishInfo);
		String aftercontent = null;
		aftercontent = new String(workflow.getContent());
		request.setAttribute("content", aftercontent);
		request.setAttribute("flowid", flowId);

		return new ModelAndView("/WEB-INF/frontend/detailflow");
	}

	@ResponseBody
	@RequestMapping("countworkflow")
	public String countWorkflow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int count = 0;
		HttpSession session = request.getSession();
		if (session.getAttribute("id") != null) {
			int id = (int) session.getAttribute("id");

			List<Workflow> workflows = workflowService
					.getAllPendingWorkflowList();
			HashMap<Integer, User> rbac_users = (HashMap<Integer, User>) context
					.getAttribute("rbac_users");
			boolean check = false;

			for (Workflow workflow : workflows) {
				check = permissionCheckService.doCheckPermisson(workflow, id,
						rbac_users);
				if (check) {
					count++;
				} /*
				 * else if (DelegateList != null) { for (Delegate delegate :
				 * DelegateList) { check =
				 * CheckPermission.doCheckPermisson(workflow,
				 * delegate.getAccountId(), rbac); if (check) { count++; break;
				 * } } }
				 */
			}
		}

		System.out.println(count);
		System.out.println("ajax is ok");
		/* return String.valueOf(count); */
		return String.valueOf(count);
	}
}