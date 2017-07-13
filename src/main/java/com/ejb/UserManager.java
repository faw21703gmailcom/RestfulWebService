package com.ejb;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entity.User;
import com.util.HibernateUtil;

public class UserManager extends Manager {
	
	public List<User> getAllUsers() {
		Session session = HibernateUtil.getSessionFactory().openSession();
	    
	    	Query query = session.getNamedQuery("getUsers");
	    	List<User> users = query.list();
	    
	    	return users;
	}
	
	public User getUserByName(String userName) {
		User user = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
	    
		try{
		    	Query query = session.getNamedQuery("findUserByUsernameNativeSQL");
		    query.setParameter("userName", userName);
		    	user = (User) query.list().get(0);
		}
	    	catch(Exception ex) {
	    		session.close();
	    	}
	    	return user;
	}
	
	public User getUserById(String userId) {
		User user = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
	    
		try{
		    	Query query = session.getNamedQuery("findUserByUseridNativeSQL");
		    	query.setParameter("userId", userId);
		    	user = (User) query.list().get(0);
		}
		catch(Exception ex) {
			session.close();
		}
	    	return user;
	}
	
	
	public String addUser(User user) {
		String response = "";
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
	    session.save(user);
	  
	    if (!tx.wasCommitted()) { 
	        tx.commit();
	    }
	    	return response;
	}
	
	/*
	
	public String updateUser(User user) {
		String response = "";
		Session session = HibernateUtil.getSessionFactory().openSession();
	    
		session.update(user);
		session.getTransaction().commit();
	    session.close();
	    	
	    	return response;
	}
	
	public String deleteUser(User user) {
		String response = "";
		Session session = HibernateUtil.getSessionFactory().openSession();
	    
		session.delete(user);
		session.getTransaction().commit();
	    session.close();
	    	
	    	return response;
	}
	*/

}
