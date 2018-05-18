package Queries;

/**
 * 
 * Class containing SQL for the complex queries (requirement number 4 in grading rubric)
 *
 */
public class ComplexQueries
{
	public String getSqlPopOfTwoTechsByCityState()
	{
		
		String sql = "SELECT " + 
				"    COUNT(T.name) as count, max_name_count, name" + 
				"    FROM" + 
				"    Location AS L," + 
				"    Technology AS T," + 
				"    Job AS J," + 
				"    Uses AS U," + 
				"    (SELECT " + 
				"        MAX(name_count1) AS max_name_count" + 
				"    FROM" + 
				"        (SELECT " + 
				"        COUNT(name) AS name_count1, T.name" + 
				"    FROM" + 
				"        Location AS L, Technology AS T, Job AS J, Uses AS U" + 
				"    WHERE" + 
				"        (city = ?  AND state = ? " +
				"            AND U.t_id = T.id" + 
				"            AND U.j_id = J.id" + 
				"            AND J.zipcode = L.zipcode" + 
				"            AND name =  ? )" + 
				"            OR (name = ? AND city = ? " + 
				"            AND state = ? " + 
				"            AND U.t_id = T.id" + 
				"            AND U.j_id = J.id" + 
				"            AND J.zipcode = L.zipcode)" + 
				"    GROUP BY name" + 
				"    ORDER BY COUNT(name) DESC LIMIT 1) A" + 
				"    GROUP BY name" + 
				"    ) B" + 
				"    WHERE" + 
				"    (city = ? AND state = ? " + 
				"        AND U.t_id = T.id" + 
				"        AND U.j_id = J.id" + 
				"        AND J.zipcode = L.zipcode" + 
				"        AND name = ? )" + 
				"        OR (name = ? AND city = ? " + 
				"        AND state = ? " + 
				"        AND U.t_id = T.id" + 
				"        AND U.j_id = J.id" + 
				"        AND J.zipcode = L.zipcode)" + 
				"    GROUP BY max_name_count , name" + 
				"    HAVING max_name_count = COUNT(T.name)";
		
		return sql;
	}

	public String getSqlPopOfTwoTechsByZip()
	{
		
		String sql = "SELECT " + 
				"    COUNT(T.name) as count, max_name_count, name" + 
				"    FROM" + 
				"    Location AS L," + 
				"    Technology AS T," + 
				"    Job AS J," + 
				"    Uses AS U," + 
				"    (SELECT " + 
				"        MAX(name_count1) AS max_name_count" + 
				"    FROM" + 
				"        (SELECT " + 
				"        COUNT(name) AS name_count1, T.name" + 
				"    FROM" + 
				"        Location AS L, Technology AS T, Job AS J, Uses AS U" + 
				"    WHERE" + 
				"        (L.zipcode = ? " +
				"            AND U.t_id = T.id" + 
				"            AND U.j_id = J.id" + 
				"            AND J.zipcode = L.zipcode" + 
				"            AND name = ?)" + 
				"            OR (name = ? AND L.zipcode = ?" + 
				"            AND U.t_id = T.id" + 
				"            AND U.j_id = J.id" + 
				"            AND J.zipcode = L.zipcode)" + 
				"    GROUP BY name" + 
				"    ORDER BY COUNT(name) DESC LIMIT 1) A" + 
				"    GROUP BY name" + 
				"    ) B" + 
				"    WHERE" + 
				"    (L.zipcode = ?" + 
				"        AND U.t_id = T.id" + 
				"        AND U.j_id = J.id" + 
				"        AND J.zipcode = L.zipcode" + 
				"        AND name = ?)" + 
				"        OR (name = ? AND L.zipcode = ?" +
				"        AND U.t_id = T.id" + 
				"        AND U.j_id = J.id" + 
				"        AND J.zipcode = L.zipcode)" + 
				"    GROUP BY max_name_count , name" + 
				"    HAVING max_name_count = COUNT(T.name)";
		return sql;
	}
	
	public String getSQLPopOfTwoCityStatesForTech()
	{
		String sql = "SELECT " + 
				"    COUNT(L.city), max_city_count as count, L.city, L.state" + 
				"    FROM" + 
				"    Location AS L," + 
				"    Technology AS T," + 
				"    Job AS J," + 
				"    Uses AS U," + 
				"    (SELECT " + 
				"        MAX(city_count1) AS max_city_count" + 
				"    FROM" + 
				"        (SELECT " + 
				"        COUNT(city) AS city_count1, L.city" + 
				"    FROM" + 
				"        Location AS L, Technology AS T, Job AS J, Uses AS U" + 
				"    WHERE" + 
				"        (T.name = ? AND U.t_id = T.id" + 
				"            AND U.j_id = J.id" + 
				"            AND J.zipcode = L.zipcode" + 
				"            AND L.city = ?" + 
				"            AND L.state = ?)" + 
				"            OR (name = ? AND U.t_id = T.id" + 
				"            AND U.j_id = J.id" + 
				"            AND J.zipcode = L.zipcode" + 
				"            AND city = ?" + 
				"            AND state = ?)" + 
				"    GROUP BY city" + 
				"    ORDER BY COUNT(name) DESC LIMIT 1) A" + 
				"    GROUP BY city) B" + 
				"    WHERE" + 
				"    (T.name = ? AND U.t_id = T.id" + 
				"        AND U.j_id = J.id" + 
				"        AND J.zipcode = L.zipcode" + 
				"        AND L.city = ?" + 
				"        AND L.state = ?)" + 
				"        OR (name = ? AND U.t_id = T.id" + 
				"        AND U.j_id = J.id" + 
				"        AND J.zipcode = L.zipcode" + 
				"        AND city = ?" + 
				"        AND state = ?)" + 
				"    GROUP BY max_city_count , city" + 
				"    HAVING max_city_count = COUNT(L.city)";
		
		return sql;
	}
	
	public String getSQLPopFWForLangInCityState()
	{
		String sql = "SELECT " + 
				"    COUNT(T.name) as count, max_name_count, T.name, city, state" + 
				"    FROM" + 
				"    Location AS L," + 
				"    Technology AS T," + 
				"    Job AS J," + 
				"    Uses AS U," + 
				"    (SELECT " + 
				"        J.id AS job_id, name, COUNT(name) AS name_count" + 
				"    FROM" + 
				"        Location AS L, Uses AS U, Technology AS T, Job AS J, (SELECT " + 
				"        J.id AS id" + 
				"    FROM" + 
				"        Location AS L, Uses AS U, Technology AS T, Job AS J" + 
				"    WHERE" + 
				"        U.j_id = J.id AND U.t_id = T.id" + 
				"            AND L.city = ?" + 
				"            AND L.state = ?" + 
				"            AND J.zipcode = L.zipcode" + 
				"            AND T.name = ?) A" + 
				"    WHERE" + 
				"        T.type = 'fw' AND J.id = A.id" + 
				"            AND U.j_id = J.id" + 
				"            AND U.t_id = T.id" + 
				"            AND L.city = ?" + 
				"            AND L.state = ?" + 
				"            AND J.zipcode = L.zipcode" + 
				"    GROUP BY name , job_id) B," + 
				"    (SELECT " + 
				"        MAX(name_count) AS max_name_count" + 
				"    FROM" + 
				"        (SELECT " + 
				"        name, COUNT(name) AS name_count" + 
				"    FROM" + 
				"        Location AS L, Uses AS U, Technology AS T, Job AS J, (SELECT " + 
				"        J.id AS id" + 
				"    FROM" + 
				"        Location AS L, Uses AS U, Technology AS T, Job AS J" + 
				"    WHERE" + 
				"        U.j_id = J.id AND U.t_id = T.id" + 
				"            AND L.city = ?" + 
				"            AND L.state = ?" + 
				"            AND J.zipcode = L.zipcode" + 
				"            AND T.name = ?) A" + 
				"    WHERE" + 
				"        T.type = 'fw' AND J.id = A.id" + 
				"            AND U.j_id = J.id" + 
				"            AND U.t_id = T.id" + 
				"            AND L.city = ?" + 
				"            AND L.state = ?" + 
				"            AND J.zipcode = L.zipcode" + 
				"    GROUP BY name) B" + 
				"    GROUP BY name" + 
				"    ORDER BY name_count DESC" + 
				"    LIMIT 1) C" + 
				"    WHERE" + 
				"    J.id = job_id AND U.j_id = J.id" + 
				"        AND U.t_id = T.id" + 
				"        AND L.city = ?" + 
				"        AND L.state = ?" + 
				"        AND J.zipcode = L.zipcode" + 
				"        AND T.type = 'fw'" + 
				"    GROUP BY max_name_count , name" + 
				"    HAVING max_name_count = COUNT(T.name)";

		return sql;
	}
	
	public String getSQLCityInStateAtLeastNJobsForTech()
	{
		String sql = "SELECT " + 
				"    city, COUNT(city) as count" + 
				"    FROM" + 
				"    Uses AS U," + 
				"    Job AS J," + 
				"    Technology AS T," + 
				"    Location AS L" + 
				"    WHERE" + 
				"    T.name = ? AND L.state = ?" + 
				"        AND T.id = U.t_id" + 
				"        AND J.id = U.j_id" + 
				"        AND J.zipcode = L.zipcode" + 
				"    GROUP BY city" + 
				"    HAVING count >= ?" +
				"    ORDER BY count DESC";
		
		return sql;
	}

}
