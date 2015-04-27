package com.yanoda.rbac.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class LoginAndRbacInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		HttpSession session = request.getSession();
		if (session != null) {
			if (session.getAttribute("id") != null) {
				int judge = (int) session.getAttribute("id");
				if (judge == -1 || judge == 0) {
					return false;
				}
				if (session.getServletContext().getAttribute("rbac_roles") != null &&  
						session.getServletContext().getAttribute("rbac_users") != null && 
								session.getServletContext().getAttribute("rbac_actions") != null) {
					return true;
				}
			}
		}
		return false;
	}

}
