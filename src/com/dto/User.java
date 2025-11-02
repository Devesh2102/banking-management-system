package com.dto;
public class User {
	private int userId;
	private String userName;
	private String email;
	private String password;
		
	public User() {
		super();
	}

	public User(String userName, String email, String password) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
	}
		
	public User(int userId, String userName, String email) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
	}

	public User(int userId, String userName, String email, String password) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.password = password;
	}

	public String getuserName() {
		return userName;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setuserName(String userName) {
		this.userName = userName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
