package com.neel.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.stereotype.Component;

import com.neel.dto.OtherServices;
import com.neel.dto.Restaurant;

@Component
public class OtherServicesDao {
	
	SessionFactory factory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
	
	public void addOtherServicesToRestaurant(OtherServices m,String feteId){
		Session session = factory.openSession();
		session.beginTransaction();
		Restaurant rest = (Restaurant) session.get(Restaurant.class, feteId);
		m.setOtherServicesId(rest.getFeteId()+ generateOtherServicesId());
		m.setRest(rest);
		session.save(m);
		rest.addOtherServicesItem(m);
		session.getTransaction().commit();
		session.close();
		
	}
	
	public String generateOtherServicesId(){
		Random random = new Random();
		int prefix = random.nextInt(1000);
		return "M"+prefix;
	}

	public List<OtherServices> getOtherServicesListByFeteId(String feteId) {
		// TODO Auto-generated method stub
		List<OtherServices> otherServicesList = new ArrayList<OtherServices>();
		Session session = factory.openSession();
		session.beginTransaction();
		Restaurant rest = (Restaurant) session.get(Restaurant.class, feteId);
		try{
		otherServicesList = rest.getOtherServices();
		if(otherServicesList.size() != 0){
		Hibernate.initialize(otherServicesList);
		}
		}catch(Exception e){
			System.out.println("OtherServices Dao Class : Error in getOtherServicesList method : "+e.getMessage());
		}
		session.getTransaction().commit();
		session.close();
		return otherServicesList;
	}

	public void updateOtherServicesItem(OtherServices otherServices) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		session.beginTransaction();
		session.update(otherServices);
		session.getTransaction().commit();
		session.close();
	}

	public void deleteOtherServicesItem(String feteId,OtherServices otherServices) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		session.beginTransaction();
		String sql = "delete  FROM restaurant_otherServices WHERE otherServices_otherServicesid = '"+otherServices.getOtherServicesId()+"'";
		SQLQuery query = session.createSQLQuery(sql);
		query.executeUpdate();
		session.delete(otherServices);
		session.getTransaction().commit();
		session.close();
	}
	
}
