package Queries;

/**
 * 
 * Class containing the SQL queries to demonstrate verifiable patterns (requirement number 5 in grading rubric) 
 *
 */
public class PatternQueries
{
	public String getSQLPopLangInZip()
	{
		String sql = "SELECT " + 
				"    name, count2" + 
				"    FROM" + 
				"    (SELECT " + 
				"        name, COUNT(*) AS count2, count1" + 
				"    FROM" + 
				"        Location L, Uses U, Job J, Technology T, (SELECT " + 
				"        COUNT(*) AS count1" + 
				"    FROM" + 
				"        Location L, Uses U, Job J, Technology T" + 
				"    WHERE" + 
				"    T.id = U.t_id AND J.id = U.j_id" + 
				"        AND L.zipcode = J.zipcode" + 
				"        AND L.zipcode = ?" + 
				"        AND type='pl'" + 
				"    GROUP BY name" + 
				"    ORDER BY count1 DESC" + 
				"    LIMIT 1) A" + 
				"    WHERE" + 
				"    T.id = U.t_id AND J.id = U.j_id" + 
				"        AND L.zipcode = J.zipcode" + 
				"        AND L.zipcode = ?" + 
				"        AND type='pl'" + 
				"    GROUP BY name" + 
				"    HAVING count2 = count1) B";
				
				return sql;
	}
	
	public String getSQLPopFWInZip()
	{

		String sql = "SELECT " + 
				"    name, count2" + 
				"    FROM" + 
				"    (SELECT " + 
				"        name, COUNT(*) AS count2, count1" + 
				"    FROM" + 
				"        Location L, Uses U, Job J, Technology T, (SELECT " + 
				"        COUNT(*) AS count1" + 
				"    FROM" + 
				"        Location L, Uses U, Job J, Technology T" + 
				"    WHERE" + 
				"    T.id = U.t_id AND J.id = U.j_id" + 
				"        AND L.zipcode = J.zipcode" + 
				"        AND L.zipcode = ?" + 
				"        AND type='fw'" + 
				"    GROUP BY name" + 
				"    ORDER BY count1 DESC" + 
				"    LIMIT 1) A" + 
				"    WHERE" + 
				"    T.id = U.t_id AND J.id = U.j_id" + 
				"        AND L.zipcode = J.zipcode" + 
				"        AND L.zipcode = ?" + 
				"        AND type='fw'" + 
				"    GROUP BY name" + 
				"    HAVING count2 = count1) B";
		
		return sql;
	}
	
	public String getSQLPopCityForTechInState()
	{
		String sql = "SELECT " + 
				"    city, count2" + 
				"    FROM" + 
				"    (SELECT " + 
				"        city, COUNT(*) AS count2, count1" + 
				"    FROM" + 
				"        Location L, Uses U, Job J, Technology T, (SELECT " + 
				"        COUNT(*) AS count1" + 
				"    FROM" + 
				"        Location L, Uses U, Job J, Technology T" + 
				"    WHERE" + 
				"        T.id = U.t_id AND J.id = U.j_id" + 
				"            AND L.zipcode = J.zipcode" + 
				"            AND state = ?" + 
				"            AND name = ?" + 
				"    GROUP BY city" + 
				"    ORDER BY count1 DESC" + 
				"    LIMIT 1) A" + 
				"    WHERE" + 
				"        T.id = U.t_id AND J.id = U.j_id" + 
				"            AND L.zipcode = J.zipcode" + 
				"            AND state = ?" + 
				"            AND name = ?" + 
				"    GROUP BY city" + 
				"    HAVING count2 = count1) B";
		
		return sql;
	}
	
	public String getSQLPopStateForTech()
	{
		String sql = "SELECT " + 
				"    state, count2" + 
				"    FROM" + 
				"    (SELECT " + 
				"        state, COUNT(*) AS count2, count1" + 
				"    FROM" + 
				"        Location L, Uses U, Job J, Technology T, (SELECT " + 
				"        COUNT(*) AS count1" + 
				"    FROM" + 
				"        Location L, Uses U, Job J, Technology T" + 
				"    WHERE" + 
				"    J.id = U.j_id" + 
				"        AND T.id = U.t_id" + 
				"        AND J.zipcode = L.zipcode" + 
				"        AND name = ?" + 
				"    GROUP BY state" + 
				"    ORDER BY count1 DESC" + 
				"    LIMIT 1) A" + 
				"    WHERE" + 
				"    J.id = U.j_id" + 
				"        AND T.id = U.t_id" + 
				"        AND J.zipcode = L.zipcode" + 
				"        AND name = ?" + 
				"    GROUP BY state" + 
				"    HAVING count2 = count1) B";
		
		return sql;
	}
}
