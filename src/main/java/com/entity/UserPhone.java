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
name = "getPhones",
query = "select * from userPhone",
resultClass = UserPhone.class
),
@NamedNativeQuery(
name = "findHobbyByPhonenumberNativeSQL",
query = "select * from userphone u where u.phoneNumber = :phoneNumber",
    resultClass = UserPhone.class
),
@NamedNativeQuery(
name = "findPhoneByPhoneidNativeSQL",
query = "select * from userphone u where u.id = :phoneId",
    resultClass = UserPhone.class
),
@NamedNativeQuery(
name = "findPhoneByTypeAndUserIdNativeSQL",
query = "select * from userphone u where u.type = :type and u.user_id =:userId",
    resultClass = UserPhone.class
),
@NamedNativeQuery(
name = "findPhoneByUserIdPhonenumberNativeSQL",
query = "select * from userphone u where u.user_id =:userId and u.phoneNumber = :phoneNumber",
    resultClass = UserPhone.class
),
@NamedNativeQuery(
name = "findPhoneByUserIdTypeNativeSQL",
query = "select * from userphone u where u.user_id =:userId and u.phoneNumber <> :phoneNumber and u.type = :type",
    resultClass = UserPhone.class
)
})
@Entity
@Table(name = "userphone")
public class UserPhone implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5048348159886054118L;

	@Expose
	private int userPhoneId;
	private User user;
	@Expose
	private String type;
	@Expose
	private String phoneNumber;
	@Expose
	private String createdBy;
	@Expose
	private Date createdOn;
	

	public UserPhone() {
	}

	public UserPhone(int userPhoneId, String type, String phoneNumber,
			String createdBy, Date createOn, User user) {
		this.userPhoneId = userPhoneId;
		this.type = type;
		this.phoneNumber = phoneNumber;
		this.createdBy = createdBy;
		this.createdOn = createOn;
		this.user = user;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false, precision = 20, scale = 0)
	public int getUserPhoneId() {
		return this.userPhoneId;
	}

	public void setUserPhoneId(int userPhoneId) {
		this.userPhoneId = userPhoneId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "type", nullable = false, length = 10)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "phoneNumber", nullable = false, length = 10)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
