package com.ejb;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.entity.UserRole;
import com.util.HibernateUtil;

public class UserRoleManager extends Manager {
	
	public List<UserRole> getAllUserRoles() {
		Session session = HibernateUtil.getSessionFactory().openSession();
	    
	    	Query query = session.getNamedQuery("getRoles");
	    	List<UserRole> userRoles = query.list();
	    	session.close();
	    	return userRoles;
	}
	
	public UserRole getUserRoleByName(String roleName) {
		UserRole userRole = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
	    
	    	Query query = session.getNamedQuery("findRoleByRoleNameNativeSQL");
	    query.setParameter("roleName", roleName);
	    	userRole = (UserRole) query.list().get(0);
	    	session.close();
	    	return userRole;
	}
	
	public UserRole getUserRoleById(String userRoleId) {
	 	UserRole userRole = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
	    
	    	Query query = session.getNamedQuery("findRoleByRoleidNativeSQL");
	    	query.setParameter("roleId", userRoleId);
	    	userRole = (UserRole) query.list().get(0);
	    	session.close();
	    	return userRole;
	}
	
	/*
	public String addUserRole(UserRole userRole) {
		String response = "";
		Session session = HibernateUtil.getSessionFactory().openSession();
	    
	    session.save(userRole);
	    session.getTransaction().commit();
	    session.close();
	    	return response;
	}
	
	public String updateUserRole(UserRole userRole) {
		String response = "";
		Session session = HibernateUtil.getSessionFactory().openSession();
	    
		session.update(userRole);
		session.getTransaction().commit();
	    session.close();
	    	
	    	return response;
	}
	
	public String deleteUser(UserRole userRole) {
		String response = "";
		Session session = HibernateUtil.getSessionFactory().openSession();
	    
		session.delete(userRole);
		session.getTransaction().commit();
	    session.close();
	    	
	    	return response;
	}
	*/
}
