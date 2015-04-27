package com.yanoda.rbac.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yanoda.rbac.domain.Role;
import com.yanoda.rbac.domain.User;
import com.yanoda.rbac.inputcheck.CheckAccountInfo;
import com.yanoda.rbac.service.LimitService;
import com.yanoda.rbac.service.RbacInitializeService;
import com.yanoda.rbac.service.UserService;

@Controller
@RequestMapping("user")
public class UserLoginController {

	@Autowired
	private UserService userService;
	@Autowired
	private LimitService limitService;
	@Autowired
	private RbacInitializeService rbacInitializeService;
	@Autowired
	private ServletContext context;

	@RequestMapping("login")
	public ModelAndView loginMethod(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		String checkLogin = "重试";

		int judge = userService.doCheckPassword(username, password);

		/*
		 * String ip_address; if (request.getHeader("x-forwarded-for") == null)
		 * { ip_address = request.getRemoteAddr(); } else { ip_address =
		 * request.getHeader("x-forwarded-for"); }
		 */

		if (username != null && password != null) {

			/* Limit limit = limitService.getLimitByUsername(username); */

			if (judge == 0 || judge == -1) {
				request.setAttribute("checkLogin", checkLogin);
				return new ModelAndView("index");

				/*
				 * if(limit == null) { //如果限制数据库中没有我
				 * response.sendRedirect("frontend/nworkform"); return; }else if
				 * (limit.getNumber() < 5) { //如果小于5次
				 * response.sendRedirect("frontend/nworkform");
				 * limitService.deleteLimit(username, ip_address); return; }else
				 * { checkLogin = "已锁定"; }
				 */
			} else {

				/*
				 * if (limit != null) { limitService.updateNumber(username,
				 * ip_address); checkLogin = "重试（" + (4 - limit.getNumber()) +
				 * "）"; if (limit.getNumber() >= 4) { checkLogin = "已锁定"; } }
				 * else { D_LoginLimit.doCreate(ipaddress, username, 1); }
				 */

			}
		} else {
			session.invalidate();
			checkLogin = "登入";
		}

		// login success
		HashMap<Integer, User> rbac_users = rbacInitializeService
				.doRbacUserInit();
		HashMap<Integer, Role> rbac_roles = rbacInitializeService
				.doRbacRoleInit();
		HashMap<Integer, Role> rbac_actions = rbacInitializeService
				.doRbacActionInit();
		checkLogin = "登出";
		context.setAttribute("rbac_users", rbac_users);
		context.setAttribute("rbac_roles", rbac_roles);
		context.setAttribute("rbac_actions", rbac_actions);
		session.setAttribute("id", judge);
		if (rbac_users.get(judge) != null) {
			session.setAttribute("userDepartment", rbac_users.get(judge)
					.getDepartment_id());
		}
		request.setAttribute("checkLogin", checkLogin);
		return new ModelAndView("redirect:/workform/nworkform/grid.do");
	}

	@RequestMapping("logout")
	public ModelAndView loginOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return new ModelAndView("index");
	}

	@RequestMapping("center")
	public ModelAndView center(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		int id = (Integer) request.getSession().getAttribute("id");
		User user = userService.getUserById(id);
		
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String fullname = request.getParameter("fullname");

		if (password == null || email == null || fullname == null) {
			map.put("userInfo", user);
			return new ModelAndView("/WEB-INF/frontend/userinfo", map);
		}

		if (password != null) {
			if (password.trim().equals("")) {
				password = user.getPassword();
			}
		}

		String checked = CheckAccountInfo
				.doCheckNull(password, fullname, email);

		if (checked.equals("ok")) {
			password = password.trim();
			email = email.trim();
			fullname = fullname.trim();
			checked = CheckAccountInfo.doMatch(email, fullname);
		}

		if (checked.equals("ok")) {

			if (password.equals(user.getPassword())) {
				int count = userService.updateUserById(password, email,
						fullname, id);
				if (count == 0) {
					checked = "数据库操作失败";
				} else {
					HashMap<Integer, User> rbac_users = rbacInitializeService
							.doRbacUserInit();
					HashMap<Integer, Role> rbac_roles = rbacInitializeService
							.doRbacRoleInit();
					HashMap<Integer, Role> rbac_actions = rbacInitializeService
							.doRbacActionInit();
					context.setAttribute("rbac_users", rbac_users);
					context.setAttribute("rbac_roles", rbac_roles);
					context.setAttribute("rbac_actions", rbac_actions);
					checked = "数据库操作成功";
					map.put("checked", checked);
					User new_user = userService.getUserById(id);
					map.put("userInfo", new_user);
					return new ModelAndView("/WEB-INF/frontend/userinfo", map);
				}
			}
			checked = "原始密码错误";
			map.put("checked", checked);
			map.put("userInfo", user);
			return new ModelAndView("/WEB-INF/frontend/userinfo", map);
		}
		checked = "填写信息有误";
		map.put("checked", checked);;
		return new ModelAndView("/WEB-INF/frontend/userinfo", map);
		
	}
}
