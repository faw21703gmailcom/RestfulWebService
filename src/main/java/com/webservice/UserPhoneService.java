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
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.ejb.UserPhoneManager;
import com.entity.UserPhone;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.webservice.util.GsonInfoUtil;
import com.webservice.util.GsonUtilInterface;


/**
 * A simple REST service which is able to say hello to someone using HelloService Please take a look at the web.xml where JAX-RS
 * is enabled
 */

@Path("/phone")
public class UserPhoneService {

	
	
    @GET
    @Path("phones")
    @Produces({ "application/json" })
    public Response getUsers() {
	    	String response = "";
	    	UserPhoneManager userPhoneManager = new UserPhoneManager();
	    	List<UserPhone> phones = userPhoneManager.getAllUserPhones();
	   
	    	GsonUtilInterface gUtil = new GsonInfoUtil();
	    	response = gUtil.toJsonString(phones);
	    	
	    return Response.ok(response).build();
    }

    @POST
    @Path("no")
    @Produces({ "application/json" })
    @Consumes({ "application/json" })
    public Response deletePhoneByUserIdAndType(String phoneJson) {
	    	String response = "";
	    	UserPhoneManager userPhoneManager = new UserPhoneManager();
    		JsonElement jsonElement = new JsonParser().parse(phoneJson);
    		JsonObject jsonObject = jsonElement.getAsJsonObject();

    		String userId = getJsonAttribute(jsonObject, "userId");
    		String phoneType = getJsonAttribute(jsonObject, "type");
    		
    		userPhoneManager.deletePhoneByUserIdAndPhoneType(userId, phoneType);
    		
        return Response.ok(response).build();
    }
    
    @POST
    @Path("phoneinfo")
    @Produces({ "application/json" })
    @Consumes({ "application/json" })
    public Response updaetPhoneByUserIdTypePhonenumber(String phoneJson) {
	    	String response = "";
	    	UserPhoneManager userPhoneManager = new UserPhoneManager();
    		JsonElement jsonElement = new JsonParser().parse(phoneJson);
    		JsonObject jsonObject = jsonElement.getAsJsonObject();

    		String userId = getJsonAttribute(jsonObject, "userId");
    		String phoneType = getJsonAttribute(jsonObject, "type");
    		String phoneNumber = getJsonAttribute(jsonObject, "phoneNumber");
    		
    		response = userPhoneManager.updatePhone(userId, phoneType, phoneNumber);
    		
        return Response.ok(response).build();
    }
    
    private String getJsonAttribute(JsonObject jsonObject, String attr) {
    		String value = null;
    		JsonElement jsonUserId = jsonObject.get(attr);
    		value = jsonUserId.getAsString();
		return value;
    }

}
