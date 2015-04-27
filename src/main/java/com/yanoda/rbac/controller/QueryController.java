package com.yanoda.rbac.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yanoda.rbac.domain.Page;
import com.yanoda.rbac.inputcheck.QueryCheck;
import com.yanoda.rbac.service.PaginationService;

@Controller
@RequestMapping("query")
public class QueryController {

	@Autowired
	private PaginationService pageService;

	@RequestMapping("main")
	public ModelAndView main(HttpServletRequest request,
			HttpServletResponse response) {

		// get the id from session
		int id = (Integer) request.getSession().getAttribute("id");

		// prepare map for saving data
		HashMap<String, Object> map = new HashMap<String, Object>();

		String myMonth = request.getParameter("month");
		String myYear = request.getParameter("year");
		String myType = request.getParameter("mytype");

		String checked = QueryCheck.doCheckNull(myYear, myMonth);

		if (checked.equals("ok")) {
			myYear = myYear.trim();
			myMonth = myMonth.trim();
			checked = QueryCheck.doMatch(myYear, myMonth);
		}

		if (checked.equals("ok")) {

			int pageNumber;
			if (request.getParameter("pageNumber") == null) {
				pageNumber = 1;
			} else {
				pageNumber = Integer
						.valueOf(request.getParameter("pageNumber"));
			}

			String condition;

			if (myType.equals("myapplication")) {
				condition = " WHERE create_user_id=" + id
						+ " ORDER BY id DESC";
				request.setAttribute("mytype", "detailmyapplication");
			} else {
				condition = " WHERE user_flow REGEXP '^" + id
						+ "(,[[:digit:]]+)*$' AND status != 0"
						+ " ORDER BY id DESC";
				request.setAttribute("mytype", "detailfinishedflow");
			}

			Page page = this.pageService.PaginationUtil(pageNumber, 10,
					"workflow", condition);
			if (page.getTotal() != 0) {
				String[] columns = { "id", "name", "create_user_id",
						"create_time", "status" };
				List<HashMap<String, Object>> rows = this.pageService.getRows(
						columns, page.getTableName(), page.getWhere());
				map.put("rows", rows);
				map.put("page", page);
				map.put("myYear", myYear);
				map.put("myMonth", myMonth);
				map.put("pType", myType);
				
			}
		}
		map.put("checked", checked);
		System.out.println(checked);
		return new ModelAndView("/WEB-INF/frontend/fquery", map);
	}

}
