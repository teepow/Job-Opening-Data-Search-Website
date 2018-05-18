package com.hit.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

/**
 * 
 * Controller for the database. Uses mysql and jdbc
 *
 */
public class DbController
{
	
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://cs-336-db.ctiqsy52jvgm.us-east-2.rds.amazonaws.com:3306/hitdb";
	private static final String DB_USER = "";
	private static final String DB_PASSWORD = "";
	
	/**
	 * Gets a connection to the database
	 * 
	 * @return DB connection
	 */
	public Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}

	/**
	 * Gets a prepared statement from a SQL query
	 * 
	 * @param sql
	 * @return
	 */
	public PreparedStatement getPreparedStatement(String sql)
	{
		Connection conn = getDBConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement =  conn.prepareStatement(sql);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return preparedStatement;
	}
	
	/**
	 * Gets a prepared statement which maintains the auto incremented id of the tuple to be inserted,
	 * which can be requested later by calling getGeneratedKeys on the PreparedStatement object
	 * 
	 * @param sql
	 * 
	 * @return PreparedStatement object
	 */	
	public PreparedStatement getPreparedStatementWithLastInsertId(String sql)
	{
		Connection conn = getDBConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return preparedStatement;
	}

}
