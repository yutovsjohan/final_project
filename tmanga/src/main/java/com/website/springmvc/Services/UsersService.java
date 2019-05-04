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
	
	public Long getCountStaff(){
		Session session = this.sessionFactory.getCurrentSession();
		return (Long) session.createQuery("select count(id) from Users where role.id != 2").uniqueResult();
	}
	
	public Long getCountCustomer(){
		Session session = this.sessionFactory.getCurrentSession();
		return (Long) session.createQuery("select count(id) from Users where role.id = 2").uniqueResult();
	}
	
	public List<Users> getListDelivery() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from Users where role.id = 3").list();
	}
	
	public List<Users> getListStaff(int firstResult, int maxResult, String name) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Users  where role.id != 2 and name like :name").setParameter("name", "%" + name + "%");
		
		if(maxResult != 0) {
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);
		}
		return query.list();
	}
	
	public Users get(String email) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Users) session.createQuery("from Users where email = :email").setParameter("email", email).uniqueResult();
	}
	
	public Long getUserNum() {
		Session session = this.sessionFactory.getCurrentSession();
		Long userNum = (Long) session.createQuery("select count(*) from Users").uniqueResult();
		return userNum;
	}
	public Long getUserRole(long check) {
		Session session = this.sessionFactory.getCurrentSession();
		Users u = (Users) session.get(Users.class, check);
		return u.getRole().getId();
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
