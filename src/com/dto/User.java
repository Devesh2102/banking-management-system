package com.dto;
public class User {
	private String full_name;
	private String email;
	private String password;
		
	public User() {
		super();
	}

	public User(String full_name, String email, String password) {
		super();
		this.full_name = full_name;
		this.email = email;
		this.password = password;
	}

	public String getFull_name() {
		return full_name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
