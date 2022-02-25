package com.learning.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC {
	public static void main(String[] args) {
		String jdbcUrl="jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false&serverTimezone=UTC";
		String user="hbstudent";
		String password="hbstudent";		
		try {
			System.out.println("connecting to db: "+jdbcUrl);
			Connection mycon = DriverManager.getConnection(jdbcUrl, user, password);
			System.out.println("connection successful");
		}catch(Exception e) {
			
		}
	}
}
