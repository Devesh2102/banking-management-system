package com.dto;

public class Account {
	private String accountNumber;
	private String secretPin;
	private int userID;
	private double amount;

	public Account() {
		super();
	}

	public Account(String secretPin, int userId, double amount) {
		this.secretPin = secretPin;
		this.userID = userId;
		this.amount = amount;
	}
	
	public Account(String accountNumber, String secretPin,  double amount) {
		super();
		this.secretPin = secretPin;
		this.accountNumber = accountNumber;
		this.amount = amount;
	}

	public Account(String accountNumber, String secretPin, int userID,  double amount) {
		super();
		this.secretPin = secretPin;
		this.userID = userID;
		this.accountNumber = accountNumber;
		this.amount = amount;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setAmount(double amount) {
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
