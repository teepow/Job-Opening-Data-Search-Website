package com.hit.ws.service;


import com.hit.controller.LocationController;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * Service for the Location Controller
 * 
 */
@Path("/location")
public class LocationService {
    @GET
    @Path("/statelist")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStateList() throws Exception {
        return Response.ok(LocationController.getListOfStates())
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET")
                .build();

    }
    
    @GET
    @Path("/citylist")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCityList(@QueryParam("state") String state) throws Exception {
        return Response.ok(LocationController.getListOfCities(state))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET")
                .build();

    }
    
}
