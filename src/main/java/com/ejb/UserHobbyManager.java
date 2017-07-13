package com.ejb;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.entity.UserHobby;
import com.util.HibernateUtil;

public class UserHobbyManager extends Manager {
	
	public List<UserHobby> getAllHobbies() {
		Session session = HibernateUtil.getSessionFactory().openSession();
	    
	    	Query query = session.getNamedQuery("getHobbies");
	    	List<UserHobby> userHobbies = query.list();
	    	session.close();
	    	return userHobbies;
	}
	
	public UserHobby getUserHobbyByName(String HobbyName) {
		UserHobby userHobby = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
	    
	    	Query query = session.getNamedQuery("findHobbyByHobbynameNativeSQL");
	    query.setParameter("hobby", HobbyName);
	    	userHobby = (UserHobby) query.list().get(0);
	    	session.close();
	    	return userHobby;
	}
	
	public UserHobby getUserHobbyById(String userHobbyId) {
	 	UserHobby userHobby = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
	    
	    	Query query = session.getNamedQuery("findHobbyByHobbyidNativeSQL");
	    	query.setParameter("hobbyId", userHobbyId);
	    	userHobby = (UserHobby) query.list().get(0);
	    	session.close();
	    	return userHobby;
	}
	
	public List<UserHobby> getUserHobbyByUserId(String userId) {
	 	Session session = HibernateUtil.getSessionFactory().openSession();
	    
	    	Query query = session.getNamedQuery("findHobbyByUserIdNativeSQL");
	    	query.setParameter("userId", userId);
	    	List<UserHobby> userHobbies = query.list();
	    	session.close();
	    	return userHobbies;
	}

}
