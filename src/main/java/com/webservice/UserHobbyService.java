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

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.ejb.UserHobbyManager;
import com.entity.UserHobby;
import com.webservice.util.GsonInfoUtil;
import com.webservice.util.GsonUtilInterface;


/**
 * A simple REST service which is able to say hello to someone using HelloService Please take a look at the web.xml where JAX-RS
 * is enabled
 */

@Path("/hobby")
public class UserHobbyService {

	
    @GET
    @Path("hobbies")
    @Produces({ "application/json" })
    public Response getUsers() {
	    	String response = "";
	    	
	    	UserHobbyManager userHobbyManager = new UserHobbyManager();
	    	List<UserHobby> hobbies = userHobbyManager.getAllHobbies();
	    	
	    	GsonUtilInterface gUtil = new GsonInfoUtil();
	    	response = gUtil.toJsonString(hobbies);
	    	
	    return Response.ok(response).build();
    }

    @GET
    @Path("userid/{userid}")
    @Produces({ "application/json" })
    public Response getHobbyByUserId(@PathParam("userid") String userId) {
    		String response = "";
    		
    		UserHobbyManager userHobbyManager = new UserHobbyManager();
    		List<UserHobby> hobbies = userHobbyManager.getUserHobbyByUserId(userId);
        
    	 	GsonUtilInterface gUtil = new GsonInfoUtil();
	    	response = gUtil.toJsonString(hobbies);
	    	
	    return Response.ok(response).build();
    }



}
