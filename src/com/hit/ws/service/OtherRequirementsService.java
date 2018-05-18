package com.hit.ws.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.hit.controller.OtherRequirementsController;
import com.hit.json.JobOpeningJSON;

/**
 * 
 * Service for the OtherRequirements controller
 *
 */
@Path("/otherRequirements")
public class OtherRequirementsService 
{
    @GET
    @Path("/UJOList")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUJOList(@QueryParam("userId") String userId) throws Exception {
        return Response.ok(OtherRequirementsController.getUJOList(userId))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET")
                .build();

    }
	
	@POST
	@Path("/addJobOpening")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addJobOpening(JobOpeningJSON JobOpeningJSON) {
		Response response = null;
		response = Response.ok(OtherRequirementsController.addJobOpening(JobOpeningJSON)).header("Access-Control-Allow-Origin", "*")
				.header("Allow-Control-Allow-Credentials", "true")
				.header("Access-Control-Allow-Methods", "POST").build();
		return response;
	}
}
