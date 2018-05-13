package com.neel.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="packages")
public class Packages {
	
	@Id
	@GenericGenerator(name="pkgId", strategy="com.neel.services.packageIdGenerator")
	@GeneratedValue(generator="pkgId")
	@Column(name="packageId")
	private String packageId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="costPerPerson")
	private double costPerPerson;
	
	@ManyToOne
	private Restaurant rest;
	
	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public Restaurant getRest() {
		return rest;
	}

	public void setRest(Restaurant rest) {
		this.rest = rest;
	}

	@OneToMany
	private List<Menu> menu;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCostPerPerson() {
		return costPerPerson;
	}

	public void setCostPerPerson(double costPerPerson) {
		this.costPerPerson = costPerPerson;
	}

	public List<Menu> getMenu() {
		return menu;
	}

	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}

}
