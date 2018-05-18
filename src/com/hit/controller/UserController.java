package com.hit.controller;
import com.hit.json.JobOpeningJSON;
import com.hit.json.UserJSON;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.http.HttpSession;

import java.sql.ResultSet;

/**
 * 
 * Controller for the User entity
 *
 */
public class UserController extends Controller {

	/**
	 * Checks login of the User
	 * 
	 * @param userJSON
	 * 
	 * @return
	 */
	public static int checkLogin(UserJSON userJSON) {
	    int id=0;
	    
	    Connection conn = null;
	    try {
	    	DbController Dbc = new DbController();
	        conn = Dbc.getDBConnection();

	        Statement stmt = null;
		    ResultSet rs = null;
		    try {
		        stmt = conn.createStatement();
		        String query="select id from User where username= '"+userJSON.getUserName() + "' and password='"+userJSON.getPassword()+"'";
		        System.out.println(query);
		        rs = stmt.executeQuery(query);
		        while (rs.next()) {
		        	  id = rs.getInt("id");
		        }
		   
		    }
		    catch (SQLException ex){
		        // handle any errors
		        System.out.println("SQLException: " + ex.getMessage());
		        System.out.println("SQLState: " + ex.getSQLState());
		        System.out.println("VendorError: " + ex.getErrorCode());
		    }
		    finally {


		        if (rs != null) {
		            try {
		                rs.close();
		            } catch (SQLException sqlEx) { } // ignore

		            rs = null;
		        }

		        if (stmt != null) {
		            try {
		                stmt.close();
		            } catch (SQLException sqlEx) { } // ignore

		            stmt = null;
		        }
		        if (conn != null) {
					conn.close();
				}
		    }

	      
	    } catch (SQLException ex) {
	        // handle any errors
	        System.out.println("SQLException: " + ex.getMessage());
	        System.out.println("SQLState: " + ex.getSQLState());
	        System.out.println("VendorError: " + ex.getErrorCode());
	    } 

		return id;
	}
	
	/**
	 * Checks if user exists
	 * 
	 * @param userJSON
	 * 
	 * @return
	 */
	public static int isUser(UserJSON userJSON) {
	    int id=0;
	    
	    Connection conn = null;
	    try {
	    	DbController Dbc = new DbController();
	        conn = Dbc.getDBConnection();
	        
	        Statement stmt = null;
		    ResultSet rs = null;
		    try {
		        stmt = conn.createStatement();
		        String query="select id from User where username= '"+userJSON.getUserName() + "'";
		        System.out.println(query);
		        rs = stmt.executeQuery(query);
		        while (rs.next()) {
		        	  id = rs.getInt("id");
		        }
		   
		    }
		    catch (SQLException ex){
		        // handle any errors
		        System.out.println("SQLException: " + ex.getMessage());
		        System.out.println("SQLState: " + ex.getSQLState());
		        System.out.println("VendorError: " + ex.getErrorCode());
		    }
		    finally {

		        if (rs != null) {
		            try {
		                rs.close();
		            } catch (SQLException sqlEx) { } // ignore

		            rs = null;
		        }

		        if (stmt != null) {
		            try {
		                stmt.close();
		            } catch (SQLException sqlEx) { } // ignore

		            stmt = null;
		        }
		        if (conn != null) {
					conn.close();
				}
		    }

	      
	    } catch (SQLException ex) {
	        // handle any errors
	        System.out.println("SQLException: " + ex.getMessage());
	        System.out.println("SQLState: " + ex.getSQLState());
	        System.out.println("VendorError: " + ex.getErrorCode());
	    } 

		return id;
	}
	
	/**
	 * Adds User tuple to database
	 * 
	 * @param userJSON
	 * 
	 * @return
	 * 
	 * @throws SQLException
	 */
	public static int addUser(UserJSON userJSON) throws SQLException {
	    int status=0;
	     Connection conn = null;


	     PreparedStatement preparedStatement = null;

		String insertTableSQL = "insert into User (username,password) values (?,?)";
		try {
		    	DbController Dbc = new DbController();
		        conn = Dbc.getDBConnection();
		        
				preparedStatement =  conn.prepareStatement(insertTableSQL); 

				preparedStatement.setString(1, userJSON.getUserName());
				preparedStatement.setString(2, userJSON.getPassword());

			
				// execute insert SQL statement
				preparedStatement.executeUpdate();
				status=1;
				System.out.println("Record is inserted into USER table!");

			} catch (SQLException e) {

				System.out.println(e.getMessage());

			} finally {

				if (preparedStatement != null) {
					preparedStatement.close();
				}

				if (conn != null) {
					conn.close();
				}

			}
	        
		return status;
	}
	
	public static List<JobOpeningJSON> deleteUser(UserJSON user)
	{
		String sql = "DELETE FROM User WHERE id=?";
		
		int intId = user.getId();
		String userId = Integer.toString(intId);
		
		OtherRequirementsController ORC = new OtherRequirementsController();
		List<JobOpeningJSON> userJobOpenings = ORC.getUJOList(userId);
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		String[] values = {userId};
		setPlaceholderValues(values, preparedStatement);
		
		executeUpdate(preparedStatement);
		
		return userJobOpenings;
	}
}
