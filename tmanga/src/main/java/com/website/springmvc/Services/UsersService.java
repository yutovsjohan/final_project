package com.website.springmvc.Services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.entities.Comic;
import com.website.springmvc.entities.Users;

@Transactional
@Service
public class UsersService {
	
	@Autowired
	DAO<Users> UsersDAO;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Users get(String email) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Users) session.createQuery("from Users where email = :email").setParameter("email", email).uniqueResult();
	}
	
	public Users get(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Users) session.get(Users.class, id);
	}
	
	public List<Users> getAll(){
		return UsersDAO.getAll();
	}
	
	public Users get(Long id){
		return UsersDAO.get(id);
	}
	
	public Users add(Users t){
		return UsersDAO.add(t);
	}
	
	public Boolean update(Users t){
		return UsersDAO.update(t);
	}
	
	public Boolean delete(Users t){
		return UsersDAO.delete(t);
	}
	
	public Boolean delete(Long id){
		return UsersDAO.delete(id);
	}
}
