package com.yanoda.rbac.service;

import com.yanoda.rbac.domain.User;

public interface UserService {
	
	public int doCheckPassword(String username, String password);
	public User getUserById(int id);
	public int updateUserById(String password, String email, String fullname, int id);
}
