package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.connection.DBConnection;
import com.dto.User;

public class UserDao {
	public User saveUserDao(User user) {
		String query = "INSERT INTO USER(full_name, email, password) values(?, ?, ?)";
		try (Connection connection = DBConnection.getDBConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, user.getFull_name());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getPassword());	//need to store encrypted password
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
		String query = "SELECT EMAIL FROM USER WHERE EMAIL = ?";
		try (Connection connection = DBConnection.getDBConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public int userLogin(String email, String password) {
		String query = "SELECT * FROM USER WHERE EMAIL = ? AND PASSWORD = ?";
		try (Connection connection = DBConnection.getDBConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int id = resultSet.getInt("id");				
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
}
