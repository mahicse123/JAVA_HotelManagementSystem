package main;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AccessDataBase {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/client";
	
	static final String USER = "root";
	static final String PASS = "";
	
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	
	public AccessDataBase() {}
	
	public void insertData(String sql) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);	
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void updatetData(String sql) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);	
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void deleteData(String sql) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);	
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
public ResultSet searchbyname(String sql) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);	
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultSet;
	}
	
public ResultSet login(String sql) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);	
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultSet;
	}
}
	
