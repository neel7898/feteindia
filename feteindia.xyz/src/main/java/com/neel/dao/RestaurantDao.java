package com.neel.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.neel.dto.Address;
import com.neel.dto.LoginType;
import com.neel.dto.Restaurant;

@Component
public class RestaurantDao {
	
	@Autowired
	HibernateTemplate template;
	
	public Restaurant registerRestaurant(Restaurant restaurant) {
		template.save(restaurant);
		this.saveLoginType(restaurant);
		return restaurant;
	}

	private void saveLoginType(Restaurant restaurant) {
		 LoginType loginType = new LoginType(restaurant.getEmail(),restaurant.getFeteId());
		 template.save(loginType);
	}

	public Restaurant getRestaurantById(String id){
		Restaurant rest = template.get(Restaurant.class, id);
		return rest;
	}
	
	public Address getAddressByRestaurantId(Restaurant restaurant){
		Restaurant rest = getRestaurantById(restaurant.getFeteId());
		Address add = rest.getAddress();
		return add;
	}

	public Address getAddressByAddressId(String id) {
		Address add = template.get(Address.class, id);
		return add;
	}

	public void updateRestaurantDetails(Restaurant r) {
		// TODO Auto-generated method stub
		template.update(r);
	}

	public Address getAddressId(String id) {
		// TODO Auto-generated method stub
		Address address = null;
		@SuppressWarnings("unchecked")
		List<Address> addId = template.find("select address from Restaurant where feteId = '"+id+"'");
		if(addId.size() != 0){
			address = addId.get(0);
		}
		return address;
	}

}
