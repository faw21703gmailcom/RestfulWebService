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

public class UserUtil {

	public static User deserialize(JsonElement json) throws JsonParseException {

		JsonObject jsonObject = json.getAsJsonObject();

		String userName = getJsonAttribute(jsonObject, "userName");
		String email = getJsonAttribute(jsonObject, "email");

		String password = getJsonAttribute(jsonObject, "password");

		if (!(userName != null && email != null && password != null))
			return null;

		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setUserName(userName);

		addRoles(user, jsonObject);

		SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss.SSS'Z'");

		addHobbies(user, jsonObject, formatter);

		addPhones(user, jsonObject, formatter);

		return user;
	}

	public static String getJsonAttribute(JsonObject jsonObject, String attr) {
		String value = null;
		JsonElement jsonElement = jsonObject.get(attr);

		if (jsonElement != null)
			value = jsonElement.getAsString();

		return value;
	}

	public static String jsonBuilder(String str) {
		return "{\"result\":\"" + str + "\"}";
	}

	private static void addRoles(User user, JsonObject jsonObject) {
		JsonElement userRolesElement = jsonObject.get("userRoles");
		List<UserRole> userRoles = null;
		JsonArray jsonUserRoleArray = null;

		if (userRolesElement != null) {

			jsonUserRoleArray = jsonObject.get("userRoles").getAsJsonArray();

			if (jsonUserRoleArray != null) {
				userRoles = new ArrayList<UserRole>();
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
				user.setUserRoles(userRoles);
			}
		}

	}

	private static void addHobbies(User user, JsonObject jsonObject, SimpleDateFormat formatter) {
		JsonElement userHobbiesElement = jsonObject.get("userHobbies");
		List<UserHobby> userHobbies = null;
		JsonArray jsonUserHobbyArray = null;

		if (userHobbiesElement != null) {
			jsonUserHobbyArray = jsonObject.get("userHobbies").getAsJsonArray();

			if (jsonUserHobbyArray != null) {
				userHobbies = new ArrayList<UserHobby>();

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
				user.setUserHobbies(userHobbies);
			}
		}

	}

	private static void addPhones(User user, JsonObject jsonObject, SimpleDateFormat formatter) {
		JsonElement userPhonesElement = jsonObject.get("userPhones");
		List<UserPhone> userPhones = null;
		JsonArray jsonUserPhoneArray = null;

		if (userPhonesElement != null) {

			jsonUserPhoneArray = jsonObject.get("userPhones").getAsJsonArray();

			if (jsonUserPhoneArray != null) {
				userPhones = new ArrayList<UserPhone>();

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
				user.setUserPhones(userPhones);
			}
		}

	}

}
