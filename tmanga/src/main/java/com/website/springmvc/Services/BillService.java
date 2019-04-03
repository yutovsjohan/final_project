package com.website.springmvc.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.entities.bill;

@Transactional
@Service
public class BillService {
	
	@Autowired
	DAO<bill> billDAO;
	
	public List<bill> getAll(){
		return billDAO.getAll();
	}
	
	public bill get(Long id){
		return billDAO.get(id);
	}
	
	public bill add(bill t){
		return billDAO.add(t);
	}
	
	public Boolean update(bill t){
		return billDAO.update(t);
	}
	
	public Boolean delete(bill t){
		return billDAO.delete(t);
	}
	
	public Boolean delete(Long id){
		return billDAO.delete(id);
	}
}