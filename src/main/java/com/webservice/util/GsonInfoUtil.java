package com.webservice.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonInfoUtil implements GsonUtilInterface {
	
	public String toJsonString(Object gsonObj){
			
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		
		Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(gsonObj);	
	}

}
