package com.yanoda.rbac.service;

import java.util.List;

import com.yanoda.rbac.domain.DefaultFlow;

public interface DefaultFlowService {
	List<DefaultFlow> getSelectActive(int workform_id, int department_id);
}
