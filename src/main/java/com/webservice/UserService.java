/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.ejb.UserManager;
import com.entity.User;
import com.google.gson.JsonParser;
import com.webservice.util.GsonUserUtil;
import com.webservice.util.GsonUtilInterface;
import com.webservice.util.UserUtil;


/**
 * A simple REST service to manager user information with JAX-RS
 */

@Path("/user")
public class UserService {

	
	
    @GET
    @Path("users")
    @Produces({ "application/json" })
    public Response getUsers() {
	    	String response = "";
	    	UserManager userManager = new UserManager();
	    List<User> users = userManager.getAllUsers();
	    	GsonUtilInterface gUtil = new GsonUserUtil();

	    	response = gUtil.toJsonString(users);
	    	
	    return Response.ok(response).build();
    }

    @GET
    @Path("username/{username}")
    @Produces({ "application/json" })
    public Response getUserByUserName(@PathParam("username") String userName) {
	    	String response = "";
	    	UserManager userManager = new UserManager();
	    User user = userManager.getUserByName(userName);

	    	if (user != null) {
	    	 	GsonUtilInterface gUtil = new GsonUserUtil();
	    		response = gUtil.toJsonString(user);
	    	}
	    	else 
	    		response = UserUtil.jsonBuilder("User with userName of '" + userName + "' does not exist.");
	   
	    return Response.ok(response).build();
    }

    @POST
    @Path("newuser")
    @Produces({ "application/json" })
    @Consumes({ "application/json" })
    public Response addUser(String userJson) {
	    	String response = "";
	    
	    	/*
	    	GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		
		Gson gson = gsonBuilder.create();
	    	User user = gson.fromJson(userJson, User.class);
	    	*/
	    	
	    	User user = UserUtil.deserialize(new JsonParser().parse(userJson));

	    	if (user != null) {
	    	 	UserManager userManager = new UserManager();
	    		response = userManager.addItem(user);
	    		return Response.ok(UserUtil.jsonBuilder(response)).build();
	    	}
	    	else {
	    		response = "Information provided for the new user is not sufficient";
	    		return Response.status(403).type("text/plain")
	                    .entity(UserUtil.jsonBuilder(response)).build();
	    	}
	    	
    }
    
    
}
