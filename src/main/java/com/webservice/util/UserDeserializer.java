package com.webservice.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.entity.User;
import com.entity.UserHobby;
import com.entity.UserPhone;
import com.entity.UserRole;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class UserDeserializer {

	public static User deserialize(final JsonElement json) throws JsonParseException {

		final JsonObject jsonObject = json.getAsJsonObject();

		final JsonElement jsonUserName = jsonObject.get("userName");
		final String userName = jsonUserName.getAsString();

		final JsonElement jsonEmail = jsonObject.get("email");
		final String email = jsonEmail.getAsString();

		final JsonElement jsonPassword = jsonObject.get("password");
		final String password = jsonPassword.getAsString();

		final User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setUserName(userName);

		

		final JsonArray jsonUserRoleArray = jsonObject.get("userRoles").getAsJsonArray();
		final List<UserRole> userRoles = new ArrayList<UserRole>();
		for (int i = 0; i < jsonUserRoleArray.size(); i++) {
			JsonObject jsonUserRole = jsonUserRoleArray.get(i).getAsJsonObject();
			UserRole userRole = new UserRole();
			JsonElement role = jsonUserRole.get("roleName");
			String roleName = role.getAsString();

			userRole.setUser(user);
			userRole.setRoleName(roleName);
			userRole.setUserRoleId(0);
			userRoles.add(userRole);
		}

		

		final JsonArray jsonUserHobbyArray = jsonObject.get("userHobbies").getAsJsonArray();
		final List<UserHobby> userHobbies = new ArrayList<UserHobby>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss.SSS'Z'");

		for (int i = 0; i < jsonUserHobbyArray.size(); i++) {
			JsonObject jsonUserHobby = jsonUserHobbyArray.get(i).getAsJsonObject();
			UserHobby userHobby = new UserHobby();

			JsonElement jsonHobby = jsonUserHobby.get("hobby");
			String hobby = jsonHobby.getAsString();

			JsonElement jsonCreatedBy = jsonUserHobby.get("createdBy");
			String createdBy = jsonCreatedBy.getAsString();

			JsonElement jsonCreatedOn = jsonUserHobby.get("createdOn");
			Date createdOn = null;

			try {
				createdOn = formatter.parse(jsonCreatedOn.getAsString());
			} catch (ParseException e) {
				e.printStackTrace();
			}

			userHobby.setUser(user);
			userHobby.setHobby(hobby);
			userHobby.setCreatedBy(createdBy);
			userHobby.setCreatedOn(createdOn);
			userHobby.setUserHobbyId(0);
			userHobbies.add(userHobby);
		}

		
		final JsonArray jsonUserPhoneArray = jsonObject.get("userPhones").getAsJsonArray();
		final List<UserPhone> userPhones = new ArrayList<UserPhone>();

		for (int i = 0; i < jsonUserPhoneArray.size(); i++) {
			JsonObject jsonUserPhone = jsonUserPhoneArray.get(i).getAsJsonObject();
			UserPhone userPhone = new UserPhone();

			JsonElement jsonPhoneNumber = jsonUserPhone.get("phoneNumber");
			String phoneNumber = jsonPhoneNumber.getAsString();

			JsonElement jsonPhoneType = jsonUserPhone.get("type");
			String type = jsonPhoneType.getAsString();

			JsonElement jsonCreatedBy = jsonUserPhone.get("createdBy");
			String createdBy = jsonCreatedBy.getAsString();

			JsonElement jsonCreatedOn = jsonUserPhone.get("createdOn");
			Date createdOn = null;
			
			try {
				createdOn = formatter.parse(jsonCreatedOn.getAsString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			userPhone.setUser(user);
			userPhone.setPhoneNumber(phoneNumber);
			userPhone.setType(type);
			userPhone.setCreatedBy(createdBy);
			userPhone.setCreatedOn(createdOn);
			userPhone.setUserPhoneId(0);
			userPhones.add(userPhone);
		}
		
		user.setUserHobbies(userHobbies);
		user.setUserPhones(userPhones);
		user.setUserRoles(userRoles);
		
		return user;
	}

}
