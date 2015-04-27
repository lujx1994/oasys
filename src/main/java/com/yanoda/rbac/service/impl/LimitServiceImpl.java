package com.yanoda.rbac.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanoda.rbac.domain.Limit;
import com.yanoda.rbac.mapper.LimitMapper;
import com.yanoda.rbac.service.LimitService;

@Service
public class LimitServiceImpl implements LimitService {
	@Autowired
	private LimitMapper limitMapper;
	
	@Override
	public Limit getLimitByUsername(String username) {
		return this.limitMapper.getLimitByUsername(username);
	}

}
