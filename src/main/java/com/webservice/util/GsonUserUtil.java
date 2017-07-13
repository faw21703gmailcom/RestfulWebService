package com.webservice.util;

import com.entity.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUserUtil implements GsonUtilInterface {
	
	public String toJsonString(Object gsonObj){
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		new GraphAdapterBuilder()
		.addType(User.class)
		.registerOn(gsonBuilder);
		Gson gson = gsonBuilder.create();
		return gson.toJson(gsonObj);	
	}

}
