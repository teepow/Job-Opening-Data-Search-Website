package com.hit.ws.service;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



import com.hit.controller.UserController;
import com.hit.json.UserJSON;

/**
 * 
 * Service for the UserController
 *
 */
@Path("/user")
public class UserService {
	

	@POST
	@Path("/checklogin")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response checkLogin(UserJSON userJSON) {
		Response response = null;
		response = Response.ok(UserController.checkLogin(userJSON)).header("Access-Control-Allow-Origin", "*")
				.header("Allow-Control-Allow-Credentials", "true")
				.header("Access-Control-Allow-Methods", "POST").build();
		return response;
	}
	
	@POST
	@Path("/isuser")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response isUser(UserJSON userJSON) {
		Response response = null;
		response = Response.ok(UserController.isUser(userJSON)).header("Access-Control-Allow-Origin", "*")
				.header("Allow-Control-Allow-Credentials", "true")
				.header("Access-Control-Allow-Methods", "POST").build();
		return response;
	}
	
	@POST
	@Path("/adduser")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser(UserJSON userJSON) throws SQLException {
		Response response = null;
		response = Response.ok(UserController.addUser(userJSON)).header("Access-Control-Allow-Origin", "*")
				.header("Allow-Control-Allow-Credentials", "true")
				.header("Access-Control-Allow-Methods", "POST").build();
		return response;
	}
	
	@POST
	@Path("/deleteUser")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteUser(UserJSON userJSON) throws SQLException {
		Response response = null;
		response = Response.ok(UserController.deleteUser(userJSON)).header("Access-Control-Allow-Origin", "*")
				.header("Allow-Control-Allow-Credentials", "true")
				.header("Access-Control-Allow-Methods", "POST").build();
		return response;
	}
	
	

}
