package com.service;

import com.model.User;

public interface IUserService {

	public User getUserById(int userId);
	public User getUserByUserNameAndPassword(String UserName,String Password);
}
