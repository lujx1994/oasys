package com.yanoda.rbac.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanoda.rbac.domain.User;
import com.yanoda.rbac.mapper.UserMapper;
import com.yanoda.rbac.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	
	@Override
	public int doCheckPassword(String username, String password){
		try {
			User user = this.userMapper.getPassword(username);
			if (user != null) {
				if (user.getPassword().equals(password)) {
					return user.getId();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return this.userMapper.getUserById(id);
	}

	@Override
	public int updateUserById(String password, String email, String fullname,
			int id) {
		// TODO Auto-generated method stub
		return this.userMapper.updateUserById(password, email, fullname, id);
	}
	
}
