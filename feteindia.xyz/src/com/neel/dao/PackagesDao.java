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

import com.neel.dto.Packages;
import com.neel.dto.Restaurant;

@Component
public class PackagesDao {
	
	SessionFactory factory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
	
	public void addPackagesToRestaurant(Packages m,String feteId){
		Session session = factory.openSession();
		session.beginTransaction();
		Restaurant rest = (Restaurant) session.get(Restaurant.class, feteId);
		m.setPackageId(rest.getFeteId()+ generatePackagesId());
		m.setRest(rest);
		session.save(m);
		rest.addPackagesItem(m);
		session.getTransaction().commit();
		session.close();
		
	}
	
	public String generatePackagesId(){
		Random random = new Random();
		int prefix = random.nextInt(1000);
		return "P"+prefix;
	}

	public List<Packages> getPackagesListByFeteId(String feteId) {
		// TODO Auto-generated method stub
		List<Packages> packagesList = new ArrayList<Packages>();
		Session session = factory.openSession();
		session.beginTransaction();
		Restaurant rest = (Restaurant) session.get(Restaurant.class, feteId);
		try{
		packagesList = rest.getPackages();
		if(packagesList.size() != 0){
		Hibernate.initialize(packagesList);
		}
		}catch(Exception e){
			System.out.println("Packages Dao Class : Error in getPackagesList method : "+e.getMessage());
		}
		session.getTransaction().commit();
		session.close();
		return packagesList;
	}

	public void updatePackagesItem(Packages packages) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		session.beginTransaction();
		session.update(packages);
		session.getTransaction().commit();
		session.close();
	}

	public void deletePackagesItem(String feteId,Packages packages) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		session.beginTransaction();
		String sql = "delete  FROM restaurant_Packages WHERE Packages_Packagesid = '"+packages.getPackageId()+"'";
		SQLQuery query = session.createSQLQuery(sql);
		query.executeUpdate();
		session.delete(packages);
		session.getTransaction().commit();
		session.close();
	}
	
}
