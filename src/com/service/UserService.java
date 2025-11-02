package com.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dao.UserDao;
import com.dto.User;
import com.dto.UserDetails;

public class UserService {
	UserDao userDao = new UserDao();
	
	public static boolean validateName(String userName) {
		Pattern namePattern = Pattern.compile("^[a-zA-z]{3,}");
		Matcher nameMatcher = namePattern.matcher(userName);
		if(nameMatcher.find())
			return true;
		else
			return false;
	}
	
	public static boolean validateEmail(String userEmail) {
		Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9][a-zA-Z0-9#._]*@[a-zA-Z]+(\\.[a-zA-Z]{2,})+$");
		Matcher emailMatcher = emailPattern.matcher(userEmail);
		if(emailMatcher.find())
			return true;
		else
			return false;
	}
	
	public User saveUserService(User user) {
		if(!userDao.userExists(user.getEmail())) {		
			return userDao.saveUserDao(user);
		}
		System.out.println("User already exists....");
		return null;
	}
	
	public UserDetails fetchUserDetailsService(String email, String password) {
		UserDetails userDetails = userDao.fetchUserDetails(email, password);		
		return userDetails;
	}

}
