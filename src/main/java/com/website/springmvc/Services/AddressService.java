package com.website.springmvc.Services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.entities.Address;

@Transactional
@Service
public class AddressService {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	DAO<Address> AddressDAO;
	
	public Address getDefaultAddressByUser(Long idUser) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Address) session.createQuery("from Address where user.id = :keyword and choose = 1").setParameter("keyword", idUser).uniqueResult();
	}
	
	public List<Address> getListAddressByUser(Long iduser, int firstResult, int maxResult) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Address where user.id = :keyword order by choose desc").setParameter("keyword", iduser);
		
		if(maxResult != 0) {
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);
		}
		return query.list();
	}
	
	public List<Address> getAll(){
		return AddressDAO.getAll();
	}
	
	public Address get(Long id){
		return AddressDAO.get(id);
	}
	
	public Address add(Address t){
		return AddressDAO.add(t);
	}
	
	public Boolean update(Address t){
		return AddressDAO.update(t);
	}
	
	public Boolean delete(Address t){
		return AddressDAO.delete(t);
	}
	
	public Boolean delete(Long id){
		return AddressDAO.delete(id);
	}
}
