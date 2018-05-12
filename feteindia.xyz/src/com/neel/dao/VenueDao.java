package com.neel.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.neel.dto.Venue;

@Component
public class VenueDao {
	
	@Autowired
	HibernateTemplate template;

	public Long getVenueCountFromFeteId(String feteID) {
		// TODO Auto-generated method stub
		Long totalVenues = 0L;
		@SuppressWarnings("unchecked")
		List<Long> count = template.find("select count(*) from venue where feteId = '"+feteID+"'");
		if(!count.isEmpty()){
			totalVenues = count.get(0);
		}
		return totalVenues;
	}

	public List<Venue> getVenueListFromFeteId(String feteId) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Venue> list = template.find("from venue where feteId = '"+feteId+"'");
		return list;
	}

	public void registerVenue(Venue venue) {
		// TODO Auto-generated method stub
		template.save(venue);
	}

	public void deleteVenueById(String id) {
		// TODO Auto-generated method stub
		Venue venue = new Venue();
		venue.setVenueId(id);
		template.delete(venue);
		
	}
	
	
}
