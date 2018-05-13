package com.neel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neel.dao.TestDao;
import com.neel.dto.Address;

@Controller
public class TestController {
	
	@Autowired
	TestDao dao;
	
	@RequestMapping(value="/test")
	public void saveAddress(){
		Address add = new Address();
		add.setAddressLine("Freegunj");
		add.setCity("Ujjain");
		add.setPinCode("456010");
		add.setState("Madhya Pradesh");
		dao.save(add);
	}
}
