package com.website.springmvc.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.entities.contact;

@Transactional
@Service
public class ContactService {
	
	@Autowired
	DAO<contact> contactDAO;
	
	public List<contact> getAll(){
		return contactDAO.getAll();
	}
	
	public contact get(Long id){
		return contactDAO.get(id);
	}
	
	public contact add(contact t){
		return contactDAO.add(t);
	}
	
	public Boolean update(contact t){
		return contactDAO.update(t);
	}
	
	public Boolean delete(contact t){
		return contactDAO.delete(t);
	}
	
	public Boolean delete(Long id){
		return contactDAO.delete(id);
	}
}
