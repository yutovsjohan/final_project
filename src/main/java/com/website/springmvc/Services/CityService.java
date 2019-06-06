package com.website.springmvc.Services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.entities.Address;
import com.website.springmvc.entities.City;

@Transactional
@Service
public class CityService {
	
	@Autowired
	DAO<City> CityDAO;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<City> getAllSortByName(){
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from City order by name asc").list();
	}
	
	public List<City> getAll(){
		return CityDAO.getAll();
	}
	
	public City get(Long id){
		return CityDAO.get(id);
	}
	
	public City add(City t){
		return CityDAO.add(t);
	}
	
	public Boolean update(City t){
		return CityDAO.update(t);
	}
	
	public Boolean delete(City t){
		return CityDAO.delete(t);
	}
	
	public Boolean delete(Long id){
		return CityDAO.delete(id);
	}
}
