package com.hit.ws.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.hit.controller.PatternQueryController;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 
 * Service for the PatternQueries controller
 *
 */
@Path("/patternQueries")
public class PatternQueriesService
{      
    @GET
    @Path("/langInZip")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPopLangInZip(@QueryParam("zip") String zip) throws Exception {
        return Response.ok(PatternQueryController.getPopLangInZip(zip))
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET")
            .build();
    } 
    
    @GET
    @Path("/fwInZip")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPopFWInZip(@QueryParam("zip") String zip) throws Exception {
        return Response.ok(PatternQueryController.getPopFWInZip(zip))
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET")
            .build();
    } 
    
    @GET
    @Path("/cityForTechInState")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPopCityForTechInState(@QueryParam("state") String state, @QueryParam("tech") String tech) throws Exception {
        return Response.ok(PatternQueryController.getPopCityForTechInState(state, tech))
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET")
            .build();
    } 
    
    @GET
    @Path("/stateForTech")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPopStateForTech(@QueryParam("tech") String tech) throws Exception {
        return Response.ok(PatternQueryController.getPopStateForTech(tech))
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET")
            .build();
    }

}
