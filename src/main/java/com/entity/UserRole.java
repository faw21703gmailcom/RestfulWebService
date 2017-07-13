package com.entity;


import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@NamedNativeQueries({
@NamedNativeQuery(
name = "getRoles",
query = "select * from userrole",
resultClass = UserRole.class
),
@NamedNativeQuery(
name = "findRoleByRoleNameNativeSQL",
query = "select * from userrole u where u.rolename = :roleName",
    resultClass = UserRole.class
),
@NamedNativeQuery(
name = "findRoleByRoleidNativeSQL",
query = "select * from userrole u where u.role_id = :roleId",
    resultClass = UserRole.class
)
})
@Entity
@Table(name = "userrole")
public class UserRole implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Expose 
	private int userRoleId;
	private User user;
	@Expose
	private String roleName;

	public UserRole() {
	}

	public UserRole(int userRoleId, User user, String username, String roleName) {
		this.userRoleId = userRoleId;
		this.roleName = roleName;
		this.user = user;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "role_id", unique = true, nullable = false, precision = 20, scale = 0)
	public int getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "rolename", nullable = false, length = 20)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
