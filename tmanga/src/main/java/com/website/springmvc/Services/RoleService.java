package com.website.springmvc.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.entities.Role;

@Transactional
@Service
public class RoleService {
	
	@Autowired
	DAO<Role> RoleDAO;
	
	public List<Role> getAll(){
		return RoleDAO.getAll();
	}
	
	public Role get(Long id){
		return RoleDAO.get(id);
	}
	
	public Role add(Role t){
		return RoleDAO.add(t);
	}
	
	public Boolean update(Role t){
		return RoleDAO.update(t);
	}
	
	public Boolean delete(Role t){
		return RoleDAO.delete(t);
	}
	
	public Boolean delete(Long id){
		return RoleDAO.delete(id);
	}
}
