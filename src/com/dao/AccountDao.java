package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.connection.DBConnection;
import com.dto.Account;

public class AccountDao {
	public void saveAccount(Account account) {
		String query = "INSERT INTO ACCOUNT (ACCOUNTNUMBER, SECRETPIN, USERID) VALUES(?, ?, ?)";
		try(Connection connection = DBConnection.getDBConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setLong(1, account.getAccountNumber());
			preparedStatement.setString(2, account.getSecretPin());
			preparedStatement.setInt(3, account.getUserID());
			
			int rowAffected = preparedStatement.executeUpdate();
			
			if(rowAffected > 0)
				System.out.println("Account created successfully, Your account number is: " + account.getAccountNumber());
			else
				System.out.println("Account is not created. Try again!!");			
		} catch (SQLException sqlException) {
			System.out.println("Account is not created.");
			System.out.println(sqlException.getMessage());
		}
	}
	
	public boolean accountExists(long accountNumber) {
		String query = "SELECT * FROM ACCOUNT WHERE accountNumber = ?";
		try(Connection connection = DBConnection.getDBConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)){
			preparedStatement.setLong(1, accountNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				System.out.println("account already exists");
				return true;
			}
			return false;
		} catch(SQLException sqlException) {
			System.out.println(sqlException.getMessage());
			return false;
		}
	}

}
