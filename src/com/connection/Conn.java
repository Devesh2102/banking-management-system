package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conn {
	private static final String URL = "jdbc:mysql://localhost:3306/banking_system";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "password";
	static Connection conn = null; 

	public static Connection getConn() {		
		if(conn == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				return null;
			}			
		}
		return conn;
	}
}
