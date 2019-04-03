package com.website.springmvc.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.entities.users;

@Transactional
@Service
public class UsersService {
	
	@Autowired
	DAO<users> usersDAO;
	
	public List<users> getAll(){
		return usersDAO.getAll();
	}
	
	public users get(Long id){
		return usersDAO.get(id);
	}
	
	public users add(users t){
		return usersDAO.add(t);
	}
	
	public Boolean update(users t){
		return usersDAO.update(t);
	}
	
	public Boolean delete(users t){
		return usersDAO.delete(t);
	}
	
	public Boolean delete(Long id){
		return usersDAO.delete(id);
	}
}
