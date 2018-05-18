package com.hit.controller;

import java.sql.PreparedStatement;

/**
 * This is the controller for the Job relation
 *
 */
public class JobController extends Controller
{
	/**
	 * Given a userId and zipcode, this method inserts a Job tuple into the database
	 * 
	 * @param userId
	 * @param zipcode
	 * 
	 * @return id of inserted Job
	 */
	public String insertJob(String userId, String zipcode)
	{
		String sql = "INSERT INTO Job (u_id, zipcode) VALUES(?, ?)";
		
		PreparedStatement preparedStatement = getPreparedStatementPersistentId(sql);
		
		String[] values = {userId, zipcode};
		setPlaceholderValues(values, preparedStatement);
		
		executeUpdate(preparedStatement);
		
		int jobIdInt = getIdForLastInsert(preparedStatement);
		
		String jobId = Integer.toString(jobIdInt);
		
		return jobId;
	}
}
