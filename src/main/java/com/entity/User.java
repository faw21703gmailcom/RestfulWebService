package com.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedNativeQueries({
@NamedNativeQuery(
name = "getUsers",
query = "select * from user",
    resultClass = User.class
),
@NamedNativeQuery(
name = "findUserByUsernameNativeSQL",
query = "select * from user u where u.username = :userName",
    resultClass = User.class
),
@NamedNativeQuery(
name = "findUserByUseridNativeSQL",
query = "select * from user u where u.user_id = :userId",
    resultClass = User.class
)
})

@Entity
@Table(name = "user")
public class User implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4622613742950311945L;
	private int userId;
	private String userName;
	private String password;
	private String email;
	private List<UserRole> userRoles = new ArrayList<UserRole>();
	private List<UserHobby> userHobbies = new ArrayList<UserHobby>();
	private List<UserPhone> userPhones = new ArrayList<UserPhone>();
	

	public User() {
	}

	public User(int userId, String userName, String password,
			String email, List<UserRole> userRoles, List<UserHobby> userHobbies, List<UserPhone> userPhones) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.userRoles = userRoles;
		this.userHobbies = userHobbies;
		this.userPhones = userPhones;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "user_id", unique = true, nullable = false, precision = 20, scale = 0)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "email", nullable = false, length = 40)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "username", nullable = false, length = 12)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "password", nullable = false, length = 20)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}


	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	public List<UserPhone> getUserPhones() {
		return userPhones;
	}

	public void setUserPhones(List<UserPhone> userPhones) {
		this.userPhones = userPhones;
	}

	

	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	public List<UserHobby> getUserHobbies() {
		return userHobbies;
	}

	public void setUserHobbies(List<UserHobby> userHobbies) {
		this.userHobbies = userHobbies;
	}


}
