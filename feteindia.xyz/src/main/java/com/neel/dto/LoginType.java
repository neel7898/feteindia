package com.neel.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="loginType")
public class LoginType {
	@Id
	@Column(name="id")
	String id;
	
	@Column(name="userType")
	String userType;
	
	@Column(name="id")

	public String getId() {
		return id;
	}

	public LoginType(String id, String userType) {
		super();
		this.id = id;
		this.userType = userType;
	}
	
	public LoginType(){}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
}
