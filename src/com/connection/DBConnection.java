package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/banking_system";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "password";
	private static Connection dbConnection; 

	public static Connection getDBConnection() {	
		try {
			if(dbConnection == null || dbConnection.isClosed()) {			
				Class.forName("com.mysql.cj.jdbc.Driver");
				dbConnection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			}
		} catch (ClassNotFoundException classNotFoundException) {
			System.out.println("JDBC Driver not found: " + classNotFoundException.getMessage());
			return null;
		} catch (SQLException sqlException) {
			System.out.println("Failed to connect to database: " + sqlException.getMessage());
			return null;
		}
		return dbConnection;
	}
}
