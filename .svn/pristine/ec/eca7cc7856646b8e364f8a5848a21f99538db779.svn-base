package com.yanoda.rbac.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yanoda.rbac.domain.Role;

public interface RoleMapper {
	List<Role> getRole();
	
	@Select("SELECT name FROM role where id = #{id}")
	String getRoleNameById(int id);
}
