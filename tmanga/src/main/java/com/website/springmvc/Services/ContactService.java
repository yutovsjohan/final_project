package com.website.springmvc.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.entities.Contact;

@Transactional
@Service
public class ContactService {
	
	@Autowired
	DAO<Contact> contactDAO;
	
	public List<Contact> getAll(){
		return contactDAO.getAll();
	}
	
	public Contact get(Long id){
		return contactDAO.get(id);
	}
	
	public Contact add(Contact t){
		return contactDAO.add(t);
	}
	
	public Boolean update(Contact t){
		return contactDAO.update(t);
	}
	
	public Boolean delete(Contact t){
		return contactDAO.delete(t);
	}
	
	public Boolean delete(Long id){
		return contactDAO.delete(id);
	}
}
