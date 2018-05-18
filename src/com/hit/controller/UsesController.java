package com.hit.controller;

import java.sql.PreparedStatement;

/**
 * This is the controller for the Uses relation
 *
 */
public class UsesController extends Controller
{
	/**
	 * Given a techId and jobId, this method inserts a Uses tuple into the database
	 * 
	 * @param techId
	 * @param jobId
	 * 
	 * @return true if successful; false otherwise
	 */
	public boolean insertUses(String techId, String jobId)
	{
		String sql = "INSERT INTO Uses (t_id, j_id) VALUES(?, ?)";
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		String[] values = {techId, jobId};
		setPlaceholderValues(values, preparedStatement);
		
		return executeUpdate(preparedStatement);
	}
}
