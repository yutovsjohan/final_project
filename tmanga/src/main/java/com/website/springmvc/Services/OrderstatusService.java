package com.website.springmvc.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.entities.orderstatus;

@Transactional
@Service
public class OrderstatusService {
	
	@Autowired
	DAO<orderstatus> orderDAO;
	
	public List<orderstatus> getAll(){
		return orderDAO.getAll();
	}
	
	public orderstatus get(Long id){
		return orderDAO.get(id);
	}
	
	public orderstatus add(orderstatus t){
		return orderDAO.add(t);
	}
	
	public Boolean update(orderstatus t){
		return orderDAO.update(t);
	}
	
	public Boolean delete(orderstatus t){
		return orderDAO.delete(t);
	}
	
	public Boolean delete(Long id){
		return orderDAO.delete(id);
	}
}
