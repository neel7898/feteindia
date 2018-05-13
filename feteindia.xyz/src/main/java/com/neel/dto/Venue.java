package com.neel.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="venue")
public class Venue {
	
	@Column(name = "feteId")
	private String feteID;
	
	@Id
	@Column(name="venueId")
	private String venueId;
	
	@Column(name="venueName")
	private String venueName;
	
	@Column(name="type")
	private String type;
	
	@Column(name="capacity")
	private int capacity;
	
	@Column(name="charges")
	private int charges;
	
	@Column(name="parkingCapacity")
	private int parkingCapacity;
	
	@Column(name="suitableFor")
	private String suitableFor;
	
	@Column(name="facilities")
	private String facilities;
	
	
	@Column(name="photoPath")
	private String photoPath;
	
	@Column(name="photoCount")
	private int photoCount;
	
	public Venue(){
		
	}
	
	public String getFeteID() {
		return feteID;
	}

	public void setFeteID(String feteID) {
		this.feteID = feteID;
	}

	public String getVenueId() {
		return venueId;
	}

	public void setVenueId(String venueId) {
		this.venueId = venueId;
	}

	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getCharges() {
		return charges;
	}

	public void setCharges(int charges) {
		this.charges = charges;
	}

	public int getParkingCapacity() {
		return parkingCapacity;
	}

	public void setParkingCapacity(int parkingCapacity) {
		this.parkingCapacity = parkingCapacity;
	}

	public String getSuitableFor() {
		return suitableFor;
	}

	public void setSuitableFor(String suitableFor) {
		this.suitableFor = suitableFor;
	}

	public String getFacilities() {
		return facilities;
	}

	public void setFacilities(String facilities) {
		this.facilities = facilities;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public int getPhotoCount() {
		return photoCount;
	}

	public void setPhotoCount(int photoCount) {
		this.photoCount = photoCount;
	}



}
