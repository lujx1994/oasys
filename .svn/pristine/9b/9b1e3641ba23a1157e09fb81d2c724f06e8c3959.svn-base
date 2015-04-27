package com.yanoda.rbac.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yanoda.rbac.domain.User;

public interface UserMapper {
	User getPassword(String username);
	
	@Select("SELECT * FROM user WHERE id=#{id}")
	User getUserById(int id);
	
	@Update("UPDATE user set password = #{0}, "
			+ "email = #{1}, truename = #{2} "
			+ "WHERE id = #{3}")
	int updateUserById(String password, String email, String fullname, int id);
}
