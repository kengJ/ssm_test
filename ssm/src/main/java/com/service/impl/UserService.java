package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.UserMapper;
import com.model.User;
import com.model.UserExample;
import com.model.UserExample.Criteria;
import com.service.IUserService;

@Service("userService")
public class UserService implements IUserService{

	@Resource
	private UserMapper userDao;
	
	@Override
	public User getUserById(int userId) {
		return this.userDao.selectByPrimaryKey(userId);
	}

	@Override
	public User getUserByUserNameAndPassword(String UserName, String Password) {
		// TODO Auto-generated method stub
		UserExample  Example = new UserExample();
		Criteria Criteria = Example.createCriteria();
		Criteria.andUserNameEqualTo(UserName);
		Criteria.andPasswordEqualTo(Password);
		List<User> list = userDao.selectByExample(Example);
		return list.size()==0?null:list.get(0);
	}

}
