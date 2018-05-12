package com.neel.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.neel.dto.LoginType;

@Component
public class LoginDao {
	
	//SessionFactory factory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
	@Autowired
	HibernateTemplate template;
	
	public String checkLoginType(String id) {
		// TODO Auto-generated method stub
		String loginType = "NA";
		LoginType type = template.get(LoginType.class,id);
		loginType = type.getUserType();
		return loginType;
	}

	public String getPasswordForRestID(String id) {
		String credential = "NA";
		@SuppressWarnings("unchecked")
		List<String> cred = template.find("select password from Restaurant where feteid = '"+id+"'");
		if(!cred.isEmpty()){
			credential = cred.get(0);
		}
		return credential;
	}

	public String getPasswordForCustID(String id) {
		String credential = "NA";
		@SuppressWarnings("unchecked")
		List<String> cred = template.find("select password from Restaurant where feteid = '"+id+"'");
		if(!cred.isEmpty()){
			credential = cred.get(0);
		}
		return credential;
	}
	

}
