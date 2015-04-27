package com.yanoda.rbac.service;

import java.util.List;


import com.yanoda.rbac.domain.Role;

public interface RoleService {
	
	List<Role> getRole();
	
	String getRoleNameById(String id);

}
