package com.website.springmvc.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.entities.role;

@Transactional
@Service
public class RoleService {
	
	@Autowired
	DAO<role> roleDAO;
	
	public List<role> getAll(){
		return roleDAO.getAll();
	}
	
	public role get(Long id){
		return roleDAO.get(id);
	}
	
	public role add(role t){
		return roleDAO.add(t);
	}
	
	public Boolean update(role t){
		return roleDAO.update(t);
	}
	
	public Boolean delete(role t){
		return roleDAO.delete(t);
	}
	
	public Boolean delete(Long id){
		return roleDAO.delete(id);
	}
}
