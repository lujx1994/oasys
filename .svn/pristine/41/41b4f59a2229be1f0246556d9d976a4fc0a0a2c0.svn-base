package com.yanoda.rbac.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PageMapper {
	int getCountSize(@Param("tableName")String tableName, @Param("where")String where);
	List<HashMap<String, Object>> getRows(@Param("selectColumns")String selectColumns, @Param("tableName")String tableName, @Param("where")String where);
}
