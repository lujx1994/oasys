package com.yanoda.rbac.mapper;

import com.yanoda.rbac.domain.Limit;

public interface LimitMapper {
	Limit getLimitByUsername(String username);
	void deleteLimit(String username, String ip_address);
	void updateNumber(String username, String ip_address);
}
