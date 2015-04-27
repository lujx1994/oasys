package com.yanoda.rbac.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yanoda.rbac.domain.FormType;
import com.yanoda.rbac.service.FormService;
import com.yanoda.rbac.service.FormTypeService;

@Controller
@RequestMapping("workform")
public class WorkFormController {
	
	@Autowired
	private FormTypeService formTypeService;
	@Autowired
	private FormService formService;
	
	@RequestMapping("nworkform/{type}")
	public ModelAndView createWorkForm(@PathVariable String type, HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (type.equals("list")) {
			HttpSession session = request.getSession();
			List<FormType> formTypeList = formTypeService.getFormType();
			map.put("formTypeList", formTypeList);
			session.removeAttribute("checkMessage");
			return new ModelAndView("/WEB-INF/frontend/nworkform", map);
		} else {
			HttpSession session = request.getSession();
			map.put("allForm", formService.getAllForm());
			session.removeAttribute("checkMessage");
			return new ModelAndView("/WEB-INF/frontend/gworkform", map);
		}
	}
	
	/*@RequestMapping("workform/{formId}")
	public ModelAndView createWorkFormById(@PathVariable int formId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<FormType> formTypeList = formTypeService.getFormType();
		for (FormType formType : formTypeList) {
			List<Form> formList = formType.getFormList();
			for (Form form : formList) {
				if (form.getId() == formId) {
					map.put("form", form);
				}
			}
		}
		return new ModelAndView("/WEB-INF/frontend/nworkflow", map);
	}*/

}
