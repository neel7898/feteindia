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

import com.neel.dto.Menu;
import com.neel.dto.Restaurant;

@Component
public class MenuDao {
	
	SessionFactory factory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
	
	public void addMenuToRestaurant(Menu m,String feteId){
		Session session = factory.openSession();
		session.beginTransaction();
		Restaurant rest = (Restaurant) session.get(Restaurant.class, feteId);
		m.setMenuId(rest.getFeteId()+ generateMenuId());
		m.setRest(rest);
		session.save(m);
		rest.addMenuItem(m);
		session.getTransaction().commit();
		session.close();
		
	}
	
	public String generateMenuId(){
		Random random = new Random();
		int prefix = random.nextInt(1000);
		return "M"+prefix;
	}

	public List<Menu> getMenuListByFeteId(String feteId) {
		// TODO Auto-generated method stub
		List<Menu> menuList = new ArrayList<Menu>();
		Session session = factory.openSession();
		session.beginTransaction();
		Restaurant rest = (Restaurant) session.get(Restaurant.class, feteId);
		try{
		menuList = rest.getMenu();
		if(menuList.size() != 0){
		Hibernate.initialize(menuList);
		}
		}catch(Exception e){
			System.out.println("Menu Dao Class : Error in getMenuList method : "+e.getMessage());
		}
		session.getTransaction().commit();
		session.close();
		return menuList;
	}

	public void updateMenuItem(Menu menu) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		session.beginTransaction();
		session.update(menu);
		session.getTransaction().commit();
		session.close();
	}

	public void deleteMenuItem(String feteId,Menu menu) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		session.beginTransaction();
		String sql = "delete  FROM restaurant_menu WHERE menu_menuid = '"+menu.getMenuId()+"'";
		SQLQuery query = session.createSQLQuery(sql);
		query.executeUpdate();
		session.delete(menu);
		session.getTransaction().commit();
		session.close();
	}
	
}
