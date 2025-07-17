package com.service;

import com.dao.UserDao;
import com.dto.User;

public class UserService {
	UserDao userDao = new UserDao();
	
	public User saveUserService(User user) {
		if(!userDao.userExists(user.getEmail())) {			
			return userDao.saveUserDao(user);
		}
		System.out.println("User already exists....");
		return null;
	}

}
