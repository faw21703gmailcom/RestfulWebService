package com.ejb;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entity.UserPhone;
import com.util.HibernateUtil;

public class UserPhoneManager extends Manager {
	
	public List<UserPhone> getAllUserPhones() {
		Session session = HibernateUtil.getSessionFactory().openSession();
	    
	    	Query query = session.getNamedQuery("getPhones");
	    	List<UserPhone> userPhones = query.list();
	    
	    	session.close();
	    	return userPhones;
	}
	
	public UserPhone getUserPhoneByName(String phoneNumber) {
		UserPhone userPhone = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
	    
	    	Query query = session.getNamedQuery("findHobbyByPhonenumberNativeSQL");
	    query.setParameter("phoneNumber", phoneNumber);
	    	userPhone = (UserPhone) query.list().get(0);
	    	session.close();
	    	return userPhone;
	}
	
	public UserPhone getUserPhoneById(String userPhoneId) {
	 	UserPhone userPhone = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
	    
	    	Query query = session.getNamedQuery("findPhoneByPhoneidNativeSQL");
	    	query.setParameter("phoneId", userPhoneId);
	    	userPhone = (UserPhone) query.list().get(0);
	    	session.close();
	    	return userPhone;
	}
	
	public String deletePhoneByUserIdAndPhoneType(String userId, String phoneType) {
		String response = "";
		Session session = HibernateUtil.getSessionFactory().openSession();
	    
	    	Query query = session.getNamedQuery("findPhoneByTypeAndUserIdNativeSQL");
	    	query.setParameter("type", phoneType);
	    	query.setParameter("userId", userId);
	    	
	    	List<UserPhone> userPhones = query.list();
	   
	    	for (UserPhone userPhone : userPhones) {
	    		response += deleteItem(userPhone, session);
	    	}
	    
	    session.close();
	    	return response;
	}
	
	public String updatePhone(String userId, String phoneType, String phoneNumber) {
		String response = "";
		Session session = HibernateUtil.getSessionFactory().openSession();
	    
	    	Query query = session.getNamedQuery("findPhoneByUserIdPhonenumberNativeSQL");
	    query.setParameter("userId", userId);
	    query.setParameter("phoneNumber", phoneNumber);
	    	
	    	List<UserPhone> userPhones = query.list();
	   
	    	if (userPhones.size() > 0)
		    	for (UserPhone userPhone : userPhones) {
		    		userPhone.setType(phoneType);
		    		response += updateItem(userPhone, session);
		    	}
	    	
	    	query = session.getNamedQuery("findPhoneByUserIdTypeNativeSQL");
	    query.setParameter("userId", userId);
	    query.setParameter("type", phoneType);
	    query.setParameter("phoneNumber", phoneNumber);
	    
		List<UserPhone> userPhonesType = query.list();
		   
	    	if (userPhonesType.size() > 0)
		    	for (UserPhone userPhone : userPhonesType) {
		    		userPhone.setPhoneNumber(phoneNumber);
		    		response += updateItem(userPhone, session);
		    	}
	    
	    session.close();
	    	return response;
	}
	
	public String deletePhone(UserPhone userPhone, Session session) {
		String response = "";
		Transaction tx = session.beginTransaction();
	    
		try {
			session.delete(userPhone);
		}
		catch (Exception ex) {
			response = ex.getMessage();
		}
		
		if (!tx.wasCommitted()) { 
	        tx.commit();
	    }

	    	return response;
	}

}
