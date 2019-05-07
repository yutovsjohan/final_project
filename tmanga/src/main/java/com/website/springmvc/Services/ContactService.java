package com.website.springmvc.Services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.entities.Contact;

@Transactional
@Service
public class ContactService {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	DAO<Contact> contactDAO;
	
	@Autowired
	private SessionFactory sessionFactiory;
	
	public Long getCountNewContact() {
		Session session = this.sessionFactory.getCurrentSession();
		return (Long) session.createQuery("select count(id) from Contact where view = 0").uniqueResult();
	}
	
	public List<Contact> getAll(int firstResult, int maxResult, String email) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Contact where sender like :email order by created_at desc");
		
		query.setParameter("email", "%" + email + "%");
		
		if(maxResult != 0) {
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);
		}
		return query.list();
	}
	
	public Long getUnReadMessage() {
		Session session = this.sessionFactiory.getCurrentSession();
		Long count = (Long) session.createQuery("select count(*) from Contact where view = 0").uniqueResult();
		return count;
	}
	public Long getAllMessage() {
		Session session = this.sessionFactiory.getCurrentSession();
		Long count = (Long) session.createQuery("select count(*) from Contact").uniqueResult();
		return count;
	}
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
