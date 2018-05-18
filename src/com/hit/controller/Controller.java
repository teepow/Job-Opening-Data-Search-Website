package com.hit.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * Controller superclass.
 *
 */
public class Controller
{
	
	/**
	 * Gets a prepared statement from a SQL query
	 * 
	 * @param sql
	 * @return
	 */
	protected static PreparedStatement getPreparedStatement(String sql)
	{
		DbController Dbc = new DbController();
		PreparedStatement preparedStatement = Dbc.getPreparedStatement(sql);
		
		return preparedStatement;
	}
	
	/**
	 * Sets the placeholders in the SQL to the desired value. Done to prevent SQL injection.
	 * 
	 * @param values String array of the values, in order, used to replace placeholders
	 * @param preparedStatement
	 */
	protected static void setPlaceholderValues(String[] values, PreparedStatement preparedStatement)
	{
		int valuesLength = values.length;
		
		for(int i = 0; i < valuesLength; i++) {
			try {
				preparedStatement.setString(i + 1, values[i]);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return;
	}
	
	/**
	 * Gets a ResultObject from a PreparedStatement object
	 * 
	 * @param preparedStatement
	 * @return ResultSet
	 */
	protected static ResultSet getResultSet(PreparedStatement preparedStatement)
	{
		ResultSet resultSet = null;
		
		try {
		    resultSet = preparedStatement.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return resultSet;
	}
	
	/**
	 * Exececutes an insert query from a Prepared Statement object
	 * 
	 * @param preparedStatement
	 * 
	 * @return true if successful; false if failed
	 */
	protected static boolean executeUpdate(PreparedStatement preparedStatement)
	{ 
		try {
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
		
	}

	/**
	 * Returns true if ResultSet object has more than one row; false otherwise
	 * 
	 * @param resultSet
	 * @return boolean
	 */
	protected boolean resultHasMoreThanOneRow(ResultSet resultSet) 
	{
		int i = 0;
		try {
			while(resultSet.next()) {
				i++;
			}	
			//set the row pointer back to the first row
			resultSet.beforeFirst();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return i > 1;	
	}
	
	/**
	 * Gets the string representation of a query result in a given column for the current row.
	 * It then resets the ResultSet row pointer back to its original position (before first).
	 * 
	 * @param columnName
	 * @param resultSet
	 * @return result
	 */
	protected String getStringResultByColNameAndReset(String columnName, ResultSet resultSet)
	{	
		String result = "";
		try {
			//advance the row pointer to the first row
			resultSet.next();
			
			result = resultSet.getString(columnName);
			
			//set the row pointer back to the first row
			resultSet.beforeFirst();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	/**
	 * Gets the integer representation of a query result in a given column for the current row.
	 * It then resets the ResultSet row pointer back to its original position (before first).
	 * 
	 * @param columnName
	 * @param resultSet
	 * @return result
	 */
	protected int getIntResultByColNameAndReset(String columnName, ResultSet resultSet)
	{	
		int result = -1;
		try {
			//advance the row pointer to the first row
			resultSet.next();
			
			result = resultSet.getInt(columnName);
			
			//set the row pointer back to the first row
			resultSet.beforeFirst();
		} catch (Exception e) {
			System.out.println(columnName);
			System.out.println(e.getMessage());
		}
		return result;
	}

	
	/**
	 * Gets the integer representation of a query result in a given column for the current row.
	 * It does not reset the ResultSet row pointer back to its original position.
	 * 
	 * @param columnName
	 * @param resultSet
	 * @return result
	 */
	protected static int getIntResultByColNameNoReset(String columnName, ResultSet resultSet)
	{	
		int result = -1;
		try {
			//advance the row pointer to the first row
			resultSet.next();
			
			result = resultSet.getInt(columnName);
		} catch (Exception e) {
			System.out.println(columnName);
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	/**
	 * Gets the string representation of a query result in a given column for the current row.
	 * It does not reset the ResultSet row pointer back to its original position (before first).
	 * 
	 * @param columnName
	 * @param resultSet
	 * @return result
	 */
	protected static String getStringResultByColNameNoReset(String columnName, ResultSet resultSet)
	{	
		String result = "";
		try {
			//advance the row pointer to the first row
			resultSet.next();
			
			result = resultSet.getString(columnName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * gets size of ResultSet object
	 * 
	 * @param resultSet
	 * @return size of ResultSet
	 */
	protected static int getSizeOfResultSet(ResultSet resultSet)
	{
		int size = 0;
		
		try {
			if(resultSet.last()) {
				size = resultSet.getRow();
				
				//set the row pointer back to the first row
				resultSet.beforeFirst();
			}			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return size;
	}
	
	/**
	 * Sets the row pointer to the specified rowNum
	 * 
	 * @param resultSet
	 * @param rowNum row at which to point the pointer
	 */
	protected static void setRow(ResultSet resultSet, int rowNum)
	{
		try {
			//ResultSet.absolute cannot absolute position to row 0, so we do it ourself
			if(rowNum == 0) {
				resultSet.beforeFirst();
				return;
			}
			resultSet.absolute(rowNum);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Gets a prepared statement which maintains the auto incremented id of the tuple to be inserted,
	 * which can be requested later by calling getGeneratedKeys on the PreparedStatement object
	 * 
	 * @param sql
	 * 
	 * @return PreparedStatement object
	 */
	protected static PreparedStatement getPreparedStatementPersistentId(String sql)
	{
		DbController Dbc = new DbController();
		PreparedStatement preparedStatement = Dbc.getPreparedStatementWithLastInsertId(sql);
		
		return preparedStatement;
	}

	/**
	 * Gets the auto incremented id result of the last value inserted tuple, given
	 * that the insert was prepared with the getPreparedStatementPersistentId
	 * method above
	 * 
	 * @param preparedStatement
	 * @return
	 */
	protected int getIdForLastInsert(PreparedStatement preparedStatement)
	{
		ResultSet resultSet = null;
		int id = -1;
		try {
			resultSet = preparedStatement.getGeneratedKeys();
			
			resultSet.next();
			
			id = resultSet.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return id;
	}
	
	/**
	 * Returns a string to tell the user their query did not produce any results. 
	 * 
	 * @return String
	 */
	protected String getNoResultString() 
	{
		return "Sorry, your search did not produce any results.";
	}

}
