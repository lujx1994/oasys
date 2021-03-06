package com.yanoda.rbac.inputcheck;

import com.yanoda.rbac.utils.RegexUtil;

public class PermissionCheck {
	public static String doMatch(String name,String alias,String pid) {
		String value="ok";
		
		if(!RegexUtil.isName(name)) {
			return "操作名格式有误";
		}
		
		if(!RegexUtil.isFullname(alias)) {
			return "别名格式有误";
		}
		
		if(!RegexUtil.isDigital(pid)) {
			return "上层操作格式有误";
		}
	    return value;
	    
	}
	
	public static String doCheckNull(String name,String alias,String pid) {
		String value="ok";
		if(pid==null || name== null || alias == null) {
			return "";
		}else if(name.trim().length()<2 || name.trim().length()>25 ) {
			return "操作名长度不符";
		}else if(alias.trim().length()<2 || alias.trim().length()>40) {
			return "别名长度不符";
		}
		
		return value;
	}
}
