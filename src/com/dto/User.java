package com.dto;

import java.sql.Connection;

import com.connection.Conn;

public class User {
	
	public static void main(String[] args) {
		try {
			Connection conn = Conn.getConn();
			if(!conn.isClosed()) {
				System.out.println("connected");
			}else {
				System.out.println("not connected");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
