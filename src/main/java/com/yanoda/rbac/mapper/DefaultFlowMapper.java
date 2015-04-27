package com.yanoda.rbac.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yanoda.rbac.domain.DefaultFlow;


public interface DefaultFlowMapper {
	
	//Get the active default flow related to workform_id
	List<DefaultFlow> getSelectActive(@Param("workform_id") int workform_id, @Param("department") int department);
}
