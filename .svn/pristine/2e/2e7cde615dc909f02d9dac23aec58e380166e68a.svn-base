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
import com.yanoda.rbac.service.PaginationService;

@Controller
@RequestMapping("application")
public class MyApplicationController {
	
	@Autowired
	private PaginationService pageService;
	
	@RequestMapping("all")
	public ModelAndView getMyAllApplication(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		int id=(Integer)request.getSession().getAttribute("id");
		
		int pageNumber;
		if (request.getParameter("pageNumber") == null) {
			pageNumber = 1;
		} else {
			pageNumber = Integer.valueOf(request.getParameter("pageNumber"));
		}

		String condition="WHERE create_user_id = "+id+" ORDER BY id DESC";
		Page page = this.pageService.PaginationUtil(pageNumber, 10, "workflow", condition);
		if (page.getTotal() != 0) {
			String[] columns = { "id", "name", "create_user_id", "create_time", "status"};
			List<HashMap<String, Object>> rows = this.pageService.getRows(columns, page.getTableName(), page.getWhere());
			map.put("rows", rows);
			map.put("page", page);
		}
		
		return new ModelAndView("/WEB-INF/frontend/rmyapplication", map);
		
	}
	
	@RequestMapping("finished")
	public ModelAndView getMyFinishedApplication(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		int user_id=(Integer)request.getSession().getAttribute("id");
		
		int pageNumber;
		if (request.getParameter("pageNumber") == null) {
			pageNumber = 1;
		} else {
			pageNumber = Integer.valueOf(request.getParameter("pageNumber"));
		}

		String condition=" WHERE account_flow REGEXP '^" + user_id + "(,[[:digit:]]+)*$' AND status != 0 ORDER BY id DESC";
		Page page = this.pageService.PaginationUtil(pageNumber, 10, "workflow", condition);
		if (page.getTotal() != 0) {
			String[] columns = { "id", "name", "create_user_id", "create_time", "status"};
			List<HashMap<String, Object>> rows = this.pageService.getRows(columns, page.getTableName(), page.getWhere());
			map.put("rows", rows);
			map.put("page", page);
		}
		
		return new ModelAndView("/WEB-INF/frontend/rfinishedflow", map);
		
	}

	

}
