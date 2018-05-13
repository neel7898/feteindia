package com.neel.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="address")
public class Address {
	
	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	@Id
	@GenericGenerator(name="addId", strategy="com.neel.services.addressIdGenerator")
	@GeneratedValue(generator="addId")
	@Column(name="addressId")
	private String addressId;
	
	@Column(name="addressline")
	private String addressLine;
	
	@Column(name="location")
	private String location;
	
	@Column(name="pincode")
	private String pinCode;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@OneToOne
	private Restaurant rest;

	public Restaurant getRest() {
		return rest;
	}

	public void setRest(Restaurant rest) {
		this.rest = rest;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
