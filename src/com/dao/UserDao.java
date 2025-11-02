package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.connection.DBConnection;
import com.dto.Account;
import com.dto.User;
import com.dto.UserDetails;

public class UserDao {
	public User saveUserDao(User user) {
		String query = "INSERT INTO USERS(USER_NAME, EMAIL, PASSWORD) VALUES(?, ?, ?)";
		try (Connection connection = DBConnection.getDBConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, user.getuserName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getPassword());
			int rowAffected = preparedStatement.executeUpdate();
			if(rowAffected > 0) {
				System.out.println("User registered successfully....");
				return user;
			} else {
				System.out.println("User is not registered....");
				return null;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public boolean userExists(String email) {
		String query = "SELECT EMAIL FROM USERS WHERE EMAIL = ?";
		try (Connection connection = DBConnection.getDBConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return true;
			}
			//return false;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public int userLogin(String email, String password) {
		String query = "SELECT * FROM USERS WHERE EMAIL = ? AND PASSWORD = ?";
		try (Connection connection = DBConnection.getDBConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int id = resultSet.getInt("ID");				
				return id;
			} else {
				System.out.println("User not found!!!!!");
				return 0;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}
	
	public UserDetails fetchUserDetails(String email, String password) {
		String query = "SELECT * FROM USERS INNER JOIN ACCOUNTS ON USERS.ID = ACCOUNTS.USER_ID WHERE ACCOUNTS.USER_ID = (SELECT ID FROM USERS WHERE EMAIL = ?) AND USERS.PASSWORD = ?";
		
		try (Connection connection = DBConnection.getDBConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {				
				UserDetails userDetails = new UserDetails(new User(resultSet.getInt("ID"), resultSet.getString("USER_NAME"), resultSet.getString("EMAIL")), new Account(resultSet.getString("ACCOUNT_NUMBER"), resultSet.getString("SECRET_PIN"), resultSet.getDouble("AMOUNT")));
				return userDetails;
			} else {
				query = "SELECT * FROM USERS WHERE EMAIL = ? AND PASSWORD = ?";
				try (PreparedStatement preparedStatementElse = connection.prepareStatement(query)){
					preparedStatementElse.setString(1, email);
					preparedStatementElse.setString(2, password);
					resultSet = preparedStatementElse.executeQuery();
					if (resultSet.next()) {				
						UserDetails userDetails = new UserDetails(new User(resultSet.getInt("ID"), resultSet.getString("USER_NAME"), resultSet.getString("EMAIL")));
						return userDetails;
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				
				System.out.println("User not found!!!!!");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
}
