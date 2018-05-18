package com.hit.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hit.json.TextJSON;

import Queries.PatternQueries;

/**
 * 
 * Class containing the queries used to fulfill requirement number 5 in the grading rubric. Inherits from Controller
 * All the SQL for this class is stored in Queries/PatternQueries.java
 */
public class PatternQueryController extends Controller
{
	/**
	 * Given a zipcode, this method returns the most poopular programming language
	 * in that zipcode and the number of jobs for that language.
	 * 
	 * @param zip
	 * 
	 * @return JSON object with result String
	 */
	public static TextJSON getPopLangInZip(String zip)
	{
		PatternQueries SQL = new PatternQueries();
		String sql = SQL.getSQLPopLangInZip();
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		String[] values = {zip, zip};
		setPlaceholderValues(values, preparedStatement);
		
		ResultSet resultSet = getResultSet(preparedStatement);
		
		PatternStringMaker sm = new PatternStringMaker();
		String resultString = sm.getStringPopTechInZip(resultSet, "language");
		
        TextJSON textJSON = new TextJSON();
        textJSON.setResult(resultString);
        
        return textJSON;
	}
	
	/**
	 * Given a zipcode, this method returns the most popular framework
	 * in that zipcode and the number of jobs for that framework
	 * 
	 * @param zip
	 * 
	 * @return JSON object with result String
	 */
	public static TextJSON getPopFWInZip(String zip)
	{
		PatternQueries SQL = new PatternQueries();
		String sql = SQL.getSQLPopFWInZip();
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		String[] values = {zip, zip};
		setPlaceholderValues(values, preparedStatement);
		
		ResultSet resultSet = getResultSet(preparedStatement);
		
		PatternStringMaker sm = new PatternStringMaker();
		String resultString = sm.getStringPopTechInZip(resultSet, "framework");
		
        TextJSON textJSON = new TextJSON();
        textJSON.setResult(resultString);
        
        return textJSON;
	}
	
	/**
	 * Given a state and a technology, this method returns the city with the most jobs
	 * in that state for the given language and the number of jobs.
	 * 
	 * @param state
	 * @param tech
	 * 
	 * @return JSON object with result String
	 */
	public static TextJSON getPopCityForTechInState(String state, String tech)
	{
		PatternQueries SQL = new PatternQueries();
		String sql = SQL.getSQLPopCityForTechInState();
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		String[] values = {state, tech, state, tech};
		setPlaceholderValues(values, preparedStatement);
		
		ResultSet resultSet = getResultSet(preparedStatement);
		
		PatternStringMaker sm = new PatternStringMaker();
		String resultString = sm.getStringPopLocationForTech(resultSet, "city");
		
        TextJSON textJSON = new TextJSON();
        textJSON.setResult(resultString);
        
        return textJSON;
	}
	
	/**
	 * Given a technology, this method returns the state with the most jobs
	 * for the given technology and the number of jobs
	 * 
	 * @param tech
	 * 
	 * @return JSON object with result String
	 */
	public static TextJSON getPopStateForTech(String tech)
	{
		PatternQueries SQL = new PatternQueries();
		String sql = SQL.getSQLPopStateForTech();
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		String[] values = {tech, tech};
		setPlaceholderValues(values, preparedStatement);
		
		ResultSet resultSet = getResultSet(preparedStatement);
		
		PatternStringMaker sm = new PatternStringMaker();
		String resultString = sm.getStringPopLocationForTech(resultSet, "state");
		
        TextJSON textJSON = new TextJSON();
        textJSON.setResult(resultString);
        
        return textJSON;
	}

}
