package com.yanoda.rbac.service;

import java.util.HashMap;
import java.util.List;

import com.yanoda.rbac.domain.Page;

public interface PaginationService {
	public Page PaginationUtil(Integer nowPage, Integer pageSize,
			String tableName, String where);

	public List<HashMap<String, Object>> getRows(String[] columns,
			String tableName, String where);
}
