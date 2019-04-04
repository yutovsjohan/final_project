package com.website.springmvc.Services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public users get(String email) {
		Session session = this.sessionFactory.getCurrentSession();
		return (users) session.createSQLQuery("from users where email like ?").setParameter(0, "%" + email + "%").uniqueResult();
	}
	
	public users get(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (users) session.get(users.class, id);
	}
	
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
