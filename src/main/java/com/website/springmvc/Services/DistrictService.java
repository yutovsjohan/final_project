package com.website.springmvc.Services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.entities.District;

@Transactional
@Service
public class DistrictService {
	@Autowired
	private SessionFactory sessionFactory;
		
	@Autowired
	DAO<District> DistrictDAO;
	
	public List<District> getDistrictByCity(Long idCity) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from District where city.id = :keyword order by name asc").setParameter("keyword", idCity).list();
	}
	
	public List<District> getAll(){
		return DistrictDAO.getAll();
	}
	
	public District get(Long id){
		return DistrictDAO.get(id);
	}
	
	public District add(District t){
		return DistrictDAO.add(t);
	}
	
	public Boolean update(District t){
		return DistrictDAO.update(t);
	}
	
	public Boolean delete(District t){
		return DistrictDAO.delete(t);
	}
	
	public Boolean delete(Long id){
		return DistrictDAO.delete(id);
	}
}
