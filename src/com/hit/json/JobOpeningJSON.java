package com.hit.json;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the JSON representation of a Job Opening
 * A Job Opening is created by the user, and contains the
 * UserId, city and state of the job, and any technologies used
 * 
 * @author tom
 *
 */
public class JobOpeningJSON
{
	private String city;
	
	private String state;
	
	private String zipcode;
	
	private String userId;
	
	private List<String> techs = new ArrayList<String>();

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getZipcode()
	{
		return zipcode;
	}

	public void setZipcode(String zipcode)
	{
		this.zipcode = zipcode;
	}

	public List<String> getTechs()
	{
		return techs;
	}

	public void setTechs(List<String> techs)
	{
		this.techs = techs;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}
}
