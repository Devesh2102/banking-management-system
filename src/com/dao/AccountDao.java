package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.connection.DBConnection;
import com.controller.BankingApp;
import com.dto.Account;
import com.dto.UserDetails;

public class AccountDao {
	public void saveAccount(UserDetails currentUser, Account account) {
		String query = "INSERT INTO ACCOUNTS (SECRET_PIN, USER_ID, AMOUNT) VALUES(?, ?, ?)";
		try(Connection connection = DBConnection.getDBConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, account.getSecretPin());
			preparedStatement.setInt(2, currentUser.getUserId());
			preparedStatement.setDouble(3, account.getAmount());
			
			int rowAffected = preparedStatement.executeUpdate();
			
			if(rowAffected > 0) {
				System.out.println("Account created successfully, Your account number is: " + fetchAccountNumber(account.getUserID()));
				BankingApp.bankLogin(currentUser);
			}
			else
				System.out.println("Account is not created. Try again!!");			
		} catch (SQLException sqlException) {
			System.out.println("Account is not created.");
			System.out.println(sqlException.getMessage());
		}
	}
	
	public static long fetchAccountNumber(int userId) {
		String query = "SELECT ACCOUNT_NUMBER FROM ACCOUNTS WHERE USER_ID = ?";
		try(Connection connection = DBConnection.getDBConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setInt(1, userId);
				ResultSet resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) {
					long accountNumber = resultSet.getLong(1);
					return accountNumber;
				}
				return 0;
		}catch(SQLException sqlException) {
			System.out.println(sqlException.getMessage());
			return 0;
		}
	}
	
	public boolean creditAccount(double amount) {
		System.out.println("Account credited...." + amount);
		return true;
	}

}
