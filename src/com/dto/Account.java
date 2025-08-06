package com.dto;

public class Account {
	private String secretPin;
	private int userID;
	private double amount;
	
	public Account() {
		super();
	}
	
	public Account(String secretPin, int userId, double amount){
		this.secretPin = secretPin;
		this.userID = userId;
		this.amount = amount;
	}
	
	public String getSecretPin() {
		return secretPin;
	}
	
	public void setSecretPin(String secretPin) {
		this.secretPin = secretPin;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}	
}

