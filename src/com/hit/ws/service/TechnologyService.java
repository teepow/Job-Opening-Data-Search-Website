package com.hit.ws.service;

import javax.ws.rs.GET;
import com.hit.controller.TechnologyController;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * Service for the Technology Controller
 * 
 */
@Path("/tech")
public class TechnologyService {

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserList() throws Exception {
        return Response.ok(TechnologyController.getListOfTechnologies())
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET")
                .build();

    }

}
