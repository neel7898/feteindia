package com.neel.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.neel.dto.Address;

@Component
public class TestDao {
	@Autowired
	HibernateTemplate template;
	
	public void save(Address add){
		template.save(add);
	}
}
