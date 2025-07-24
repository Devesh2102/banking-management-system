package com.dto;

public class Account {
	private long accountNumber;
	private String secretPin;
	private int userID;
	
	public Account() {
		super();
	}
	
	public Account(long accountNumber, String secretPin, int userId){
		this.accountNumber = accountNumber;
		this.secretPin = secretPin;
		this.userID = userId;
	}
	
	public long getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
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
	
}

