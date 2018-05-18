package com.hit.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hit.json.JobOpeningJSON;

/**
 *  Class containing the queries used to fulfill requirements 2 and 3 in the grading rubric
 *
 */
public class OtherRequirementsController extends Controller
{

	/**
	 * Adds a new job opening entered by the user to the database and creates all necessary relations.
	 * If the new job opening is for a programming language or framework not already in the technology
	 * table, it adds the new technology to the table.
	 * 
	 * @param jobOpeningJSON
	 * 
	 * @return
	 */
	public static String addJobOpening(JobOpeningJSON jobOpeningJSON) 
	{	
		String userId = jobOpeningJSON.getUserId();
		System.out.println(userId);
		String zipcode = jobOpeningJSON.getZipcode();
		List<String> techs = jobOpeningJSON.getTechs();
		
		JobController jc = new JobController();
		String jobId = jc.insertJob(userId, zipcode);
		
		List<String> techIds = techHandler(techs);
		
		UsesController uc = new UsesController();
		for(String techId: techIds) {
			uc.insertUses(techId, jobId);
		}
		
		return jobId;
	}

	/**
	 * Gets the techId from DB if it exists; otherwise inserts new tech in DB and gets new id
	 * 
	 * @param techs
	 * @return list of techIds
	 */
	private static List<String> techHandler(List<String> techs)
	{
		TechnologyController tc = new TechnologyController();
		List<String> techIds = new ArrayList<>();
		
		for(String tech: techs) {
			String techId;
			if(tc.techExists(tech)) {
				techId = tc.getIdByName(tech);
			} else {
				techId = tc.insertTech(tech);
			}
			techIds.add(techId);
		}
		return techIds;
	}
	
	/**
	 * Gets a list of all job openings created by a user identified by userId. A job opening
	 * contains the city, state, and zipcode of the job as well as any technologies being used.
	 * 
	 * @param userId
	 * 
	 * @return List of JobOpenings
	 */
	public static List<JobOpeningJSON> getUJOList(String userId)
	{
		String sql = "SELECT city, state, L.zipcode, name, J.id as jobId, Ur.id as Userid FROM Location AS L, Uses AS Us, Job AS J, User AS Ur, Technology AS T" + 
				"	WHERE Ur.id= ? AND J.u_id=Ur.id AND Us.j_id=J.id AND Us.t_id=T.id AND L.zipcode=J.zipcode ORDER BY J.id";
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		String[] values = {userId};
		setPlaceholderValues(values, preparedStatement);
		
		ResultSet resultSet = getResultSet(preparedStatement);
		
		List<JobOpeningJSON> UJOs = jobOpeningsForUserListBuilder(resultSet);
		
		return UJOs;
		
	}
	
	/**
	 * Builds the JobOpenings list for the getJobOpeningsForUser method
	 * 
	 * @param resultSet
	 * 
	 * @return List of JobOpenings
	 */
	private static List<JobOpeningJSON> jobOpeningsForUserListBuilder(ResultSet resultSet)
	{
		List<JobOpeningJSON> UJOs = new ArrayList<>();
		
		int sizeOfResultSet = getSizeOfResultSet(resultSet);
		for(int i = 0; i < sizeOfResultSet; i++) {
			JobOpeningJSON UJO = new JobOpeningJSON();
			
			setRow(resultSet, i);
			
			String city = getStringResultByColNameNoReset("city", resultSet);
			UJO.setCity(city);
			setRow(resultSet, i);
			
			String state = getStringResultByColNameNoReset("state", resultSet);
			UJO.setState(state);
			setRow(resultSet, i);
			
			String zipcode = getStringResultByColNameNoReset("zipcode", resultSet);
			if (zipcode.length() == 4) zipcode = "0" + zipcode;
			UJO.setZipcode(zipcode);
			setRow(resultSet, i);
			
			String tech = getStringResultByColNameNoReset("name", resultSet);
			List<String> techs = new ArrayList<>();
			techs.add(tech);
			setRow(resultSet, i);
			
			String id = getStringResultByColNameNoReset("userId", resultSet);
			UJO.setUserId(id);
			setRow(resultSet, i);
			
			i = multipleTechsForOneJobHandler(UJO, techs, resultSet, i);
			
			UJOs.add(UJO);
		}
		return UJOs;
	}

	/**
	 * Handles JobOpenings with more than one technology. Increases the row pointer which doubles as
	 * the loop counter for the jobOpeningsForUserListBuilder method
	 * 
	 * @param UJO
	 * @param techs
	 * @param resultSet
	 * @param rowPointer
	 * 
	 * @return row pointer
	 */
	private static int multipleTechsForOneJobHandler(JobOpeningJSON UJO, List<String> techs, ResultSet resultSet, int rowPointer)
	{
		
		int jobIdForPreviousTech = getIntResultByColNameNoReset("jobId", resultSet);
		int jobIdForCurrentTech = getIntResultByColNameNoReset("jobId", resultSet);
		while(jobIdForCurrentTech == jobIdForPreviousTech) {
			rowPointer++;
			
			setRow(resultSet, rowPointer);
			String currentTech = getStringResultByColNameNoReset("name", resultSet);
			techs.add(currentTech);
			
			//advance to next job
			jobIdForCurrentTech = getIntResultByColNameNoReset("jobId", resultSet);
		}
		UJO.setTechs(techs);
		
		return rowPointer;		
	}
}
