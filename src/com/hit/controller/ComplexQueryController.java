package com.hit.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Queries.ComplexQueries;
import com.hit.json.TextJSON;
/*
 * Class containing the queries used to fulfill requirement number 4 in the grading rubric. Inherits from Controller
 * All the SQL for this class is stored in Queries/ComplexQueries.java
 */
public class ComplexQueryController extends Controller
{
	/**
	 * Given two technologies, a city, and a state, this method returns which of the two technologies has
	 * the most jobs in the specified city as well as the number of jobs available for that technology.
	 * 
	 * @param techOne 
	 * @param techTwo
	 * @param city
	 * @param state
	 * 
	 * @return JSON object with result String
	 */
        public static TextJSON getPopOfTwoTechsByCityState(String techOne, String techTwo, String city, String state)
	{
		ComplexQueries SQL = new ComplexQueries();
		String sql = SQL.getSqlPopOfTwoTechsByCityState();
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		String[] values = {city, state, techOne, techTwo, city, state, city, state, techOne, techTwo, city, state};
		setPlaceholderValues(values, preparedStatement);
		
		ResultSet resultSet = getResultSet(preparedStatement);
	
		ComplexStringMaker sm = new ComplexStringMaker();
		String resultString = sm.getStringPopOfTwoTechsByZip(resultSet);
		
        TextJSON textJSON = new TextJSON();
        textJSON.setResult(resultString);

        return textJSON;                
	}
        
	
	/**
	 * Given two technologies and a zipcode, this method returns which of the two technologies has
	 * the most jobs in the specified zipcode as well as the number of jobs available for that technology.
	 * 
	 * @param techOne
	 * @param techTwo
	 * @param zip
	 * 
	 * @return JSON object with result String
	 */
	public static TextJSON getPopOfTwoTechsByZip(String techOne, String techTwo, String zip)
	{
		ComplexQueries SQL = new ComplexQueries();
		String sql = SQL.getSqlPopOfTwoTechsByZip();
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		String[] values = {zip, techOne, techTwo, zip, zip, techOne, techTwo, zip};
		setPlaceholderValues(values, preparedStatement);
		
		ResultSet resultSet = getResultSet(preparedStatement);
		
		ComplexStringMaker sm = new ComplexStringMaker();
		String resultString = sm.getStringPopOfTwoTechsByZip(resultSet);
		
        TextJSON textJSON = new TextJSON();
        textJSON.setResult(resultString);
        
        return textJSON;
	}
	
	/**
	 * Given two cities and states and a technology, this method returns which of the two cities
	 * has the most job openings for the given technology as well as the number of jobs for that technology.
	 * 
	 * @param tech
	 * @param cityOne
	 * @param stateOne
	 * @param cityTwo
	 * @param stateTwo
	 * 
	 * @return JSON object with result String
	 */
	public static TextJSON getPopOfTwoCityStatesForTech(String tech, 
			String cityOne, String stateOne, String cityTwo, String stateTwo)
	{
		ComplexQueries SQL = new ComplexQueries();
		String sql = SQL.getSQLPopOfTwoCityStatesForTech();
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		String[] values = {tech, cityOne, stateOne, tech, cityTwo, 
				stateTwo, tech, cityOne, stateOne, tech, cityTwo, stateTwo};
		setPlaceholderValues(values, preparedStatement);
		
		ResultSet resultSet = getResultSet(preparedStatement);
		
		ComplexStringMaker sm = new ComplexStringMaker();
		String resultString = sm.getStringPopOfTwoCityStatesForTech(resultSet);
		
        TextJSON textJSON = new TextJSON();
        textJSON.setResult(resultString);
        
        return textJSON;		
	}
	
	/**
	 * Given a programming language, city, and state, this method returns the framework with the most job openings
	 * associated with the given language in the specified city, state combo and the number of jobs.
	 * 
	 * @param language
	 * @param city
	 * @param state
	 * 
	 * @return Result String
	 */
	public static TextJSON getPopFWForLangInCityState(String language, String city, String state)
	{
		ComplexQueries SQL = new ComplexQueries();
		String sql = SQL.getSQLPopFWForLangInCityState();
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		String[] values = {city, state, language, city, state, city, state, language, city, state, city, state};
		setPlaceholderValues(values, preparedStatement);
		
		ResultSet resultSet = getResultSet(preparedStatement);
		
		ComplexStringMaker sm = new ComplexStringMaker();
		String resultString = sm.getStringPopFWForLangInCityState(resultSet);
		
        TextJSON textJSON = new TextJSON();
        textJSON.setResult(resultString);
        
        return textJSON;
	}
	
	/**
	 * Given a state, technology, and number of jobs, this method returns all cities in the specified state
	 * with at least the specified number of jobs for the given technology and the number of jobs
	 * 
	 * @param state
	 * @param tech
	 * @param numJobsRequest
	 * 
	 * @return JSON object with result String
	 */
	public static TextJSON getCityInStateAtLeastNJobsForTech(String state, String tech, String numJobsRequest)
	{
		ComplexQueries SQL = new ComplexQueries();
		String sql = SQL.getSQLCityInStateAtLeastNJobsForTech();
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		String[] values = {tech, state, numJobsRequest};
		setPlaceholderValues(values, preparedStatement);
		
		ResultSet resultSet = getResultSet(preparedStatement);
		
		ComplexStringMaker sm = new ComplexStringMaker();
		String resultString = sm.getStringCityInStateAtLeastNJobsForTech(resultSet, numJobsRequest);
		
        TextJSON textJSON = new TextJSON();
        textJSON.setResult(resultString);
        
        return textJSON;
	}

}
