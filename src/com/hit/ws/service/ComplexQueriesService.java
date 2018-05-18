package com.hit.ws.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.hit.controller.ComplexQueryController;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 
 * Service for the ComplexQueries controller
 *
 */
@Path("/complexQueries")
public class ComplexQueriesService
{      
    @GET
    @Path("/twoTechsByCityState")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTwoTechsByCityState(@QueryParam("techOne") String techOne, @QueryParam("techTwo") String techTwo, 
    		@QueryParam("city") String city, @QueryParam("state") String state) throws Exception {
        return Response.ok(ComplexQueryController.getPopOfTwoTechsByCityState(techOne, techTwo, city, state))
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET")
            .build();
    }
    
    @GET
    @Path("/twoTechsByZip")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTwoTechsByZip(@QueryParam("techOne") String techOne, @QueryParam("techTwo") String techTwo, 
    		@QueryParam("zip") String zip) throws Exception {
    	 return Response.ok(ComplexQueryController.getPopOfTwoTechsByZip(techOne, techTwo, zip))
	        .header("Access-Control-Allow-Origin", "*")
	        .header("Access-Control-Allow-Methods", "GET")
	        .build();
    }
    
    @GET
    @Path("/twoCityStatesForTech")
    @Produces(MediaType.APPLICATION_JSON)
    public Response gettwoCityStatesForTech(@QueryParam("tech") String tech, 
    		@QueryParam("cityOne") String cityOne, @QueryParam("stateOne") String stateOne, 
    		@QueryParam("cityTwo") String cityTwo, @QueryParam("stateTwo") String stateTwo) throws Exception {
        return Response.ok(ComplexQueryController.getPopOfTwoCityStatesForTech(tech, cityOne,  stateOne,  cityTwo, stateTwo))
	        .header("Access-Control-Allow-Origin", "*")
	        .header("Access-Control-Allow-Methods", "GET")
	        .build();
    }
    
    @GET
    @Path("/fwForLangInCityState")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPopFWForLangInCityState(@QueryParam("language") String language, 
    		@QueryParam("city") String city, @QueryParam("state") String state) throws Exception {
    	return Response.ok(ComplexQueryController.getPopFWForLangInCityState(language,  city,  state))
	        .header("Access-Control-Allow-Origin", "*")
	        .header("Access-Control-Allow-Methods", "GET")
	        .build();
    }
    
    @GET
    @Path("/cityStateNJobsForTech")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getcityStateNJobsForTech(@QueryParam("state") String state, 
    		@QueryParam("tech") String tech, 
    		@QueryParam("numJobs") String numJobsRequest) throws Exception {
    	return Response.ok(ComplexQueryController.getCityInStateAtLeastNJobsForTech(state,  tech,  numJobsRequest))
	        .header("Access-Control-Allow-Origin", "*")
	        .header("Access-Control-Allow-Methods", "GET")
	        .build();
    }
}
