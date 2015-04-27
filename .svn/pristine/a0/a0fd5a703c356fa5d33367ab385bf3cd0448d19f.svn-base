package com.yanoda.rbac.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanoda.rbac.domain.Role;
import com.yanoda.rbac.domain.User;
import com.yanoda.rbac.mapper.RbacMapper;
import com.yanoda.rbac.service.RbacInitializeService;

@Service
public class RbacInitializeServiceImpl implements RbacInitializeService {
	@Autowired
	private RbacMapper rbacMapper;
	
	public HashMap<Integer, User> doRbacUserInit() {
		List<User> users = this.rbacMapper.doRbacUserInit();
		HashMap<Integer, User> rbac_users = new HashMap<Integer, User>();
		for (User user : users) {
			rbac_users.put(user.getId(), user);
		}
		return rbac_users;
	}

	@Override
	public HashMap<Integer, Role> doRbacRoleInit() {
		List<Role> roles = this.rbacMapper.doRbacRoleInit();
		HashMap<Integer, Role> rbac_roles = new HashMap<Integer, Role>();
		for (Role role : roles) {
			rbac_roles.put(role.getId(), role);
		}
		return rbac_roles;
	}

	@Override
	public HashMap<Integer, Role> doRbacActionInit() {
		List<Role> roles = this.rbacMapper.doRbacActionInit();
		HashMap<Integer, Role> rbac_role_permissions = new HashMap<Integer, Role>();
		for (Role role : roles) {
			rbac_role_permissions.put(role.getId(), role);
		}
		return rbac_role_permissions;
	}

}
