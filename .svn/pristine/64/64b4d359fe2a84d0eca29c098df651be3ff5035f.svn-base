package com.yanoda.rbac.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanoda.rbac.domain.Delegate;
import com.yanoda.rbac.mapper.DelegateMapper;
import com.yanoda.rbac.service.DelegateService;

@Service
public class DelegateServiceImpl implements DelegateService{
	
	@Autowired
	private DelegateMapper delegateMapper;
	
	@Override
	public List<Delegate> selectDelegate(int user_id) {
		return this.delegateMapper.selectDelegate(user_id);
	}
}
