package com.hit.controller;

import java.sql.ResultSet;

/**
 * 
 * This class creates result strings for the ComplexQueries class. Inherits from Controller.
 *
 */
public class PatternStringMaker extends Controller
{

	/**
	 * Creates result string for getPopTechInZip and getPopOfTwoTechsByZip method in PatternQueries class. 
	 * 
	 * @param resultSet
	 * @param type
	 * 
	 * @return
	 */
	public String getStringPopTechInZip(ResultSet resultSet, String type)
	{	
		if(getSizeOfResultSet(resultSet) == 0)
			return getNoResultString();
		
		String name = getStringResultByColNameAndReset("name", resultSet);
		String numJobs = getStringResultByColNameAndReset("count2", resultSet);
		
		String resultString =  "";
		
		if(resultHasMoreThanOneRow(resultSet)) {
			String langList = techListBuilder(resultSet);
			resultString += "The " + type + "s with the most jobs are " + langList;
		} else {
			resultString += "The " + type + " with the most jobs is " + name + " with " + numJobs + " job"
							+ (numJobs.equals("1") ? "" : "s") + ".";
		}
		
		return resultString;
	}
	
	/**
	 * Builds a String representation of technologies for getStringPopTechInZip method
	 * 
	 * @param resultSet
	 * 
	 * @return resultString
	 */
	private String techListBuilder(ResultSet resultSet)
	{
		String resultString = "";
		int sizeOfResultSet = getSizeOfResultSet(resultSet);
		for(int i = 1; i < sizeOfResultSet; i++) {
			resultString += getStringResultByColNameNoReset("name", resultSet);
			resultString += " with ";
			
			setRow(resultSet, i);
			resultString += getStringResultByColNameNoReset("count2", resultSet);
			resultString += " jobs";
			
			resultString += (sizeOfResultSet != 2) ? ", " : "";
			
			setRow(resultSet, i);
		}
		resultString += " and " + getStringResultByColNameAndReset("name", resultSet);
		resultString += " with ";
		resultString += getStringResultByColNameNoReset("count2", resultSet);
		resultString += " jobs";
		
		
		return resultString;
	}

	/**
	 * Creates result string for getPopLocationForTech method in PatternQueries class.
	 * 
	 * @param resultSet
	 * @param location
	 * 
	 * @return result string
	 */
	public String getStringPopLocationForTech(ResultSet resultSet, String location)
	{	
		if(getSizeOfResultSet(resultSet) == 0)
			return getNoResultString();
		
		String name = getStringResultByColNameAndReset(location, resultSet);
		String numJobs = getStringResultByColNameAndReset("count2", resultSet);
		
		String resultString =  "";
		
		if(resultHasMoreThanOneRow(resultSet)) {
			String locationList = locationListBuilder(resultSet, location);
			resultString += "The ";
			resultString += (location.equals("city")) ? "citie" : "state";
			resultString += "s with the most jobs are " + locationList;					
		} else {
			resultString = "The " + location + " with the most jobs is " + name + " with " + numJobs + " job"
							+ (numJobs.equals("1") ? "" : "s") + ".";
		}
		
		return resultString;
	}

	/**
	 * Builds a string representation of locations for getStringPopLocationForTech method
	 * 
	 * @param resultSet
	 * @param location
	 * 
	 * @return result String
	 */
	private String locationListBuilder(ResultSet resultSet, String location)
	{
		String resultString = "";
		int sizeOfResultSet = getSizeOfResultSet(resultSet);
		for(int i = 1; i < sizeOfResultSet; i++) {
			resultString += getStringResultByColNameNoReset(location, resultSet);
			resultString += " with ";
			setRow(resultSet, i);
			resultString += getStringResultByColNameNoReset("count2", resultSet);
			resultString += " jobs";
			resultString += (sizeOfResultSet != 2) ? ", " : "";
			setRow(resultSet, i);
		}
		resultString += " and " + getStringResultByColNameAndReset(location, resultSet);
		resultString += " with ";
		resultString += getStringResultByColNameNoReset("count2", resultSet);
		resultString += " jobs";
		
		return resultString;
	}

}
