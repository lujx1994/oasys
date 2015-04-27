package com.yanoda.rbac.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanoda.rbac.domain.Role;
import com.yanoda.rbac.mapper.RoleMapper;
import com.yanoda.rbac.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public List<Role> getRole() {
		// TODO Auto-generated method stub
		return this.roleMapper.getRole();
	}

	@Override
	public String getRoleNameById(String id) {
		int role_id = Integer.parseInt(id);
		return this.roleMapper.getRoleNameById(role_id);
	}
	
	
	
	
	
}
