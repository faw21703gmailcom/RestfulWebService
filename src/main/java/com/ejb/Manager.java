package com.ejb;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.util.HibernateUtil;

public class Manager {
	
	
	public String addItem(Object obj) {
		String response = "";
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.save(obj);
		}
		catch (Exception ex) {
			response = ex.getMessage();
		}
	    
	    if (!tx.wasCommitted()) { 
	        tx.commit();
	    }
	    
	    session.close();
	    return response;
	}
	
	public String updateItem(Object obj, Session session) {
		String response = "";
		Transaction tx = session.beginTransaction();
		
		try {
			session.update(obj);
		}
		catch (Exception ex) {
			response = ex.getMessage();
		}
		
		if (!tx.wasCommitted()) { 
	        tx.commit();
	    }
	    	
	    	return response;
	}
	
	public String deleteItem(Object obj, Session session) {
		String response = "";
		
		Transaction tx = session.beginTransaction();
	    
		try {
			session.delete(obj);
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
