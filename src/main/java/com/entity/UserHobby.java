package com.entity;


import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.annotations.Expose;

@NamedNativeQueries({
@NamedNativeQuery(
name = "getHobbies",
query = "select * from userhobby",
resultClass = UserHobby.class
),
@NamedNativeQuery(
name = "findHobbyByHobbynameNativeSQL",
query = "select * from userhobby u where u.hobby = :hobby",
    resultClass = UserHobby.class
),
@NamedNativeQuery(
name = "findHobbyByHobbyidNativeSQL",
query = "select * from userhobby u where u.id = :hobbyId",
    resultClass = UserHobby.class
),
@NamedNativeQuery(
name = "findHobbyByUserIdNativeSQL",
query = "select * from userhobby u where u.user_id = :userId",
    resultClass = UserHobby.class
)
})
@Entity
@Table(name = "userhobby")
public class UserHobby implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5048348159886054118L;
	
	@Expose
	private int userHobbyId;
	private User user;
	@Expose
	private String hobby;
	@Expose
	private String createdBy;
	@Expose
	private Date createdOn;
	

	public UserHobby() {
	}

	public UserHobby(int userHobbyId, String hobby, String createdBy, Date createOn, User user) {
		this.userHobbyId = userHobbyId;
		this.hobby = hobby;
		this.createdBy = createdBy;
		this.createdOn = createOn;
		this.user = user;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false, precision = 20, scale = 0)
	public int getUserHobbyId() {
		return this.userHobbyId;
	}

	public void setUserHobbyId(int userHobbyId) {
		this.userHobbyId = userHobbyId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "hobby", nullable = false, length = 20)
	public String getHobby() {
		return this.hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	
	@Column(name = "createdBy", nullable = false, length = 40)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "createdOn", unique = true, nullable = false, length = 10)
	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

}
