/**
 * 
 */
package com.neel.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Neel
 *
 */

@Entity(name="menu")
public class Menu {
	
	@Id
	@Column(name="menuId")
	private String menuId;
	
	@Column(name="feteId")
	private String feteId;
	
	@Column(name="category")
	private String category;
	
	@Column(name="itemName")
	private String itemName;
	
	@Column(name="itemType")
	private String itemType;
	
	@Column(name="serving")
	private String serving;
	
	@Column(name="halfPlate")
	private String halfPlate;
	
	@Column(name="halfPlateCost")
	private String halfPlateCost;
	
	@Column(name="photoPath")
	private String photoPath;
	
	@Column(name="cost")
	private String cost;
	
	@ManyToOne
	private Restaurant rest;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getFeteId() {
		return feteId;
	}

	public void setFeteId(String feteId) {
		this.feteId = feteId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getServing() {
		return serving;
	}

	public void setServing(String serving) {
		this.serving = serving;
	}

	public String getHalfPlate() {
		return halfPlate;
	}

	public void setHalfPlate(String halfPlate) {
		this.halfPlate = halfPlate;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public Restaurant getRest() {
		return rest;
	}

	public void setRest(Restaurant rest) {
		this.rest = rest;
	}

	public String getHalfPlateCost() {
		return halfPlateCost;
	}

	public void setHalfPlateCost(String halfPlateCost) {
		this.halfPlateCost = halfPlateCost;
	}

	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", feteId=" + feteId + ", category=" + category + ", itemName=" + itemName
				+ ", itemType=" + itemType + ", serving=" + serving + ", halfPlate=" + halfPlate + ", photoPath="
				+ photoPath + ", cost=" + cost + ", rest=" + rest + "]";
	}
	
	
}
