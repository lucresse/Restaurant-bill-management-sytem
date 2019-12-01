package com.rbms.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	// CONNECTION A LA BASE DE DONNEE
	private static final String user="root";
	private static final String password=null;
	private static final String dbName="bd_rbms";
	private static  final String serveurUrl="jdbc:mysql://localhost:3306/"+dbName;
	private Connection con;
	public ConnectionDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(serveurUrl, user, password);	
			/// System.out.println("Connection ... réussit");
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}
		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	public Connection getCon() {
		return con;
	}
	
	
	
}
