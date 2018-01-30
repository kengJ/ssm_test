package com.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.UserMapper;
import com.model.User;
import com.service.IUserService;

@Service("userService")
public class UserService implements IUserService{

	@Resource
	private UserMapper userDao;
	
	@Override
	public User getUserById(int userId) {
		return this.userDao.selectByPrimaryKey(userId);
	}

}
