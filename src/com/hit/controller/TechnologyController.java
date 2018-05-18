package com.hit.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hit.json.TechnologyJSON;

/**
 * This is the controller for Technology entity
 *
 */
public class TechnologyController extends Controller
{
	/**
	 * Given a name of a technology, this method returns the technology's id
	 * 
	 * @param name
	 * 
	 * @return tech id
	 */
	public String getIdByName(String name)
	{
		String sql = "SELECT id FROM Technology WHERE name=?";
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		String[] values = {name};
		setPlaceholderValues(values, preparedStatement);
		
		ResultSet resultSet = getResultSet(preparedStatement);
		
		int techIdInt = getIntResultByColNameAndReset("id", resultSet);
		
		String techId = Integer.toString(techIdInt);
		
		return techId;
	}

	/**
	 * This method returns true if the technology is in the database; false otherwise
	 * 
	 * @param name
	 * 
	 * @return boolean
	 */
	public boolean techExists(String name)
	{
		String sql = "SELECT id FROM Technology where name= ?";
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		String[] values = {name};
		setPlaceholderValues(values, preparedStatement);
		
		ResultSet resultSet = getResultSet(preparedStatement);
		
		return (getSizeOfResultSet(resultSet) != 0);
	}

	/**
	 * Given a technology name, this method inserts a Technology tuple into the database
	 * 
	 * @param userId
	 * @param zipcode
	 * 
	 * @return id of inserted Technology
	 */
	public String insertTech(String tech)
	{
		String sql = "INSERT INTO Technology (name) VALUES(?)";
		
		PreparedStatement preparedStatement = getPreparedStatementPersistentId(sql);
		
		String[] values = {tech};
		setPlaceholderValues(values, preparedStatement);
		
		executeUpdate(preparedStatement);
		
		int techIdInt = getIdForLastInsert(preparedStatement);
		
		String techId = Integer.toString(techIdInt);
		
		return techId;	
	}

	/**
	 * Returns a list of all Technology tuples
	 * 
	 * @return List of Technology Json objects
	 */
	public static List<TechnologyJSON> getListOfTechnologies()
	{
		String sql = "SELECT * FROM Technology ORDER BY name";
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		ResultSet resultSet = getResultSet(preparedStatement);

		List<TechnologyJSON> techs = technologyListBuilder(resultSet);
		
		return techs;
	}

	/**
	 * Builds the list for the getListOfTechnologies method
	 * 
	 * @param resultSet
	 * 
	 * @return List of Technology Json objects
	 */
	private static List<TechnologyJSON> technologyListBuilder(ResultSet resultSet)
	{
		List<TechnologyJSON> techs = new ArrayList<TechnologyJSON>();
		
		int sizeOfResultSet = getSizeOfResultSet(resultSet);
		for(int i = 0; i < sizeOfResultSet; i++) {
			TechnologyJSON Tech = new TechnologyJSON();
			
			setRow(resultSet, i);
			String id = getStringResultByColNameNoReset("id", resultSet);
			Tech.setId(id);
			
			setRow(resultSet, i);
			String name = getStringResultByColNameNoReset("name", resultSet);
			Tech.setName(name);
			
			setRow(resultSet, i);
			String type = getStringResultByColNameNoReset("type", resultSet);
			Tech.setType(type);
			
			techs.add(Tech);
		}	
		return techs;
	}
}
