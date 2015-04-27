package com.yanoda.rbac.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanoda.rbac.domain.Department;
import com.yanoda.rbac.mapper.GroupMapper;
import com.yanoda.rbac.service.DepartmentService;

@Service
public class GroupServiceImpl implements DepartmentService{
	
	@Autowired
	private GroupMapper groupMapper;

	@Override
	public Department getGroupById(int group_id) {
		// TODO Auto-generated method stub
		return this.groupMapper.getGroupById(group_id);
	}

}
