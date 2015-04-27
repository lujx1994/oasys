package com.yanoda.rbac.service;

import com.yanoda.rbac.domain.Limit;

public interface LimitService {
	
	public Limit getLimitByUsername(String username);
}
