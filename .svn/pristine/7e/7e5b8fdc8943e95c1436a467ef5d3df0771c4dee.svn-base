package com.yanoda.rbac.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yanoda.rbac.domain.Department;

public interface GroupMapper {
	
	@Select("SELECT * FROM department WHERE id=#{id}")
	Department getGroupById(@Param("id") int id);

}
