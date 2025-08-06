package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.connection.DBConnection;
import com.controller.BankingApp;
import com.dto.Account;

public class AccountDao {
	public void saveAccount(Account account) {
		String query = "INSERT INTO ACCOUNT (SECRETPIN, USERID, AMOUNT) VALUES(?, ?, ?)";
		try(Connection connection = DBConnection.getDBConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, account.getSecretPin());
			preparedStatement.setInt(2, account.getUserID());
			preparedStatement.setDouble(3, account.getAmount());
			
			int rowAffected = preparedStatement.executeUpdate();
			
			if(rowAffected > 0) {
				System.out.println("Account created successfully, Your account number is: " + fetchAccountNumber(account.getUserID()));
				BankingApp.bankLogin(account.getUserID());
			}
			else
				System.out.println("Account is not created. Try again!!");			
		} catch (SQLException sqlException) {
			System.out.println("Account is not created.");
			System.out.println(sqlException.getMessage());
		}
	}
	
	public static long fetchAccountNumber(int userId) {
		String query = "SELECT ACCOUNTNUMBER FROM ACCOUNT WHERE USERID = ?";
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
	
	public boolean accountExists(int userId) {
		String query = "SELECT * FROM ACCOUNT WHERE userid = ?";
		try(Connection connection = DBConnection.getDBConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)){
			preparedStatement.setLong(1, userId);
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
	
	public ResultSet accountDetails(int userId) {
		String query = "SELECT * FROM ACCOUNT WHERE userid = ?";
		try(Connection connection = DBConnection.getDBConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)){
			preparedStatement.setLong(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();			
				return resultSet;
		} catch(SQLException sqlException) {
			System.out.println(sqlException.getMessage());
			return null;
		}
	}

}
