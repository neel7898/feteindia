package com.neel.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name="otherServices")
public class OtherServices {
	
	@Column(name="feteId")
	private String feteId;
	
	@ManyToOne
	private Restaurant rest;
	
	@Id
	@Column(name = "otherServicesId")
	private String otherServicesId;
	
	@Column(name="serviceName")
	private String serviceName;
	
	@Column(name="charges")
	private String charges;

	public String getFeteId() {
		return feteId;
	}

	public void setFeteId(String feteId) {
		this.feteId = feteId;
	}

	public Restaurant getRest() {
		return rest;
	}

	public void setRest(Restaurant rest) {
		this.rest = rest;
	}

	public String getOtherServicesId() {
		return otherServicesId;
	}

	public void setOtherServicesId(String otherServicesId) {
		this.otherServicesId = otherServicesId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getCharges() {
		return charges;
	}

	public void setCharges(String charges) {
		this.charges = charges;
	}
}
