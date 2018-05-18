package com.hit.controller;

import com.hit.json.CityJSON;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.hit.json.LocationJSON;
import com.hit.json.StateJSON;

/**
 * This is the class for the Location entity
 *
 */
public class LocationController extends Controller {
	
	/**
	 * Given a state and tech, this method returns the number of jobs for
	 * the city in the given state with the most jobs for the given tech
	 * 
	 * @param state
	 * @param tech
	 * 
	 * @return number of jobs
	 */
	public int getMaxJobsForLangInCityByStateTech(String state, String tech)
	{
		String sql = "SELECT " + 
				"    MAX(count1) as max" + 
				"    FROM" + 
				"    (SELECT " + 
				"        COUNT(*) AS count1, name, city" + 
				"    FROM" + 
				"        Location, Uses, Job, Technology" + 
				"    WHERE" + 
				"        state = ? AND name = ?" + 
				"            AND Uses.j_id = Job.id" + 
				"            AND Uses.t_id = Technology.id" + 
				"            AND Job.zipcode = Location.zipcode" + 
				"    GROUP BY city) A;";
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		String[] values = {state, tech};
		setPlaceholderValues(values, preparedStatement);
		
		ResultSet resultSet = getResultSet(preparedStatement);
		
		int max = getIntResultByColNameNoReset("max", resultSet);
		
		return max;
	}

    /**
     * Returns of list of all Location tuples
     *
     * @return List of Location Json objects
     */
    public List<LocationJSON> getListOfLocations() {
        String sql = "SELECT * FROM Location";

        PreparedStatement preparedStatement = getPreparedStatement(sql);

        ResultSet resultSet = getResultSet(preparedStatement);

        List<LocationJSON> locations = locationListBuilder(resultSet);

        return locations;
    }

    /**
     * Builds the list for the getListOfLocations method
     *
     * @param resultSet
     *
     * @return List of Location Json objects
     */
    private List<LocationJSON> locationListBuilder(ResultSet resultSet) {
        List<LocationJSON> locations = new ArrayList<LocationJSON>();

        int sizeOfResultSet = getSizeOfResultSet(resultSet);
        for (int i = 0; i < sizeOfResultSet; i++) {
            LocationJSON Location = new LocationJSON();

            setRow(resultSet, i);
            String zipcode = getStringResultByColNameNoReset("zipcode", resultSet);
            Location.setZipcode(zipcode);

            setRow(resultSet, i);
            String city = getStringResultByColNameNoReset("city", resultSet);
            Location.setCity(city);

            setRow(resultSet, i);
            String state = getStringResultByColNameNoReset("state", resultSet);
            Location.setState(state);

            locations.add(Location);
        }
        return locations;
    }

    /**
     * Returns a list of all states in the database
     * 
     */
    public static List<StateJSON> getListOfStates() {
        String sql = "SELECT DISTINCT state FROM Location ORDER BY state";

        PreparedStatement preparedStatement = getPreparedStatement(sql);

        ResultSet resultSet = getResultSet(preparedStatement);

        List<StateJSON> states = stateListBuilder(resultSet);

        return states;
    }

    /**
     * Builds the list of states for the getListOfStatesMethod
     * 
     * @param resultSet
     * @return
     */
    private static List<StateJSON> stateListBuilder(ResultSet resultSet) {
        List<StateJSON> states = new ArrayList<StateJSON>();

        int sizeOfResultSet = getSizeOfResultSet(resultSet);
        for (int i = 0; i < sizeOfResultSet; i++) {
            StateJSON state = new StateJSON();

            setRow(resultSet, i);
            String stateName = getStringResultByColNameNoReset("state", resultSet);
            state.setState(stateName);
            
            states.add(state);
        }
        return states;
    }

    /**
     * returns a list of all cities in a given state
     * 
     * @param state
     * @return
     */
    public static List<CityJSON> getListOfCities(String state) {
        String sql = "SELECT DISTINCT city FROM Location WHERE state= ? ORDER BY city";

        PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		String[] values = {state};
		setPlaceholderValues(values, preparedStatement);

        ResultSet resultSet = getResultSet(preparedStatement);

        List<CityJSON> cities = cityListBuilder(resultSet);

        return cities;
    }
    
    /**
     * Builds the list of cities for the getListOfCities method
     * 
     * @param resultSet
     * @return
     */
	private static List<CityJSON> cityListBuilder(ResultSet resultSet) {
	    List<CityJSON> cities = new ArrayList<CityJSON>();
	
	    int sizeOfResultSet = getSizeOfResultSet(resultSet);
	    for (int i = 0; i < sizeOfResultSet; i++) {
	        CityJSON city = new CityJSON();
	
	        setRow(resultSet, i);
	        String cityName = getStringResultByColNameNoReset("city", resultSet);
	        city.setCity(cityName);
	        cities.add(city);
	    }
	    return cities;
	}
     

}
