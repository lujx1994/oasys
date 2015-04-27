package com.yanoda.rbac.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanoda.rbac.domain.DefaultFlow;
import com.yanoda.rbac.mapper.DefaultFlowMapper;
import com.yanoda.rbac.service.DefaultFlowService;

@Service
public class DefaultFlowServiceImpl implements DefaultFlowService {
	
	@Autowired
	private DefaultFlowMapper defaultFlowMapper;
	
	@Override
	public List<DefaultFlow> getSelectActive(int workform_id, int department) {
		return this.defaultFlowMapper.getSelectActive(workform_id, department);
		
	}
}
