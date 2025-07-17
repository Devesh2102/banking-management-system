package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/banking_system";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "devesh@123";
	static Connection dbConnection = null; 

	public static Connection getDBConnection() {		
		if(dbConnection == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				dbConnection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return null;
			}			
		}
		return dbConnection;
	}
}
