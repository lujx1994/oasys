package com.yanoda.rbac.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanoda.rbac.mapper.PermissionMapper;
import com.yanoda.rbac.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {
	
	@Autowired
	private PermissionMapper permissionMapper;
	
	@Override
	public void addPermission() {
		// TODO Auto-generated method stub
		this.permissionMapper.addPermission();
		
	}
	
}
