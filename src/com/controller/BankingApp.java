package com.controller;

import com.dao.UserDao;
import com.dto.User;
import com.service.UserService;

public class BankingApp {

	public static void main(String[] args) {
		UserService userService = new UserService();
		User user = new User("test name4", "test04@gmail.com", "pass@123");
		userService.saveUserService(user);
		
//		String email = "test@gmail.com";
//		String password = "pass@123";
//		
//		UserDao userDao = new UserDao();
//		
//		userDao.userLogin(email, password);
	}

}
