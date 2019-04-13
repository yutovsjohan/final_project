package com.website.springmvc.Services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.entities.Bill;

@Transactional
@Service
public class BillService {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	DAO<Bill> billDAO;
	
	public List<Bill> getBillOfUser(int iduser) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from bill where idUser.id = :keyword").setParameter("keyword", iduser).list();
	}
	
	public Bill get(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Bill) session.get(Bill.class, new Integer(id));
	}
	
	public List<Bill> getAll(){
		return billDAO.getAll();
	}
	
	public Bill get(Long id){
		return billDAO.get(id);
	}
	
	public Bill add(Bill t){
		return billDAO.add(t);
	}
	
	public Boolean update(Bill t){
		return billDAO.update(t);
	}
	
	public Boolean delete(Bill t){
		return billDAO.delete(t);
	}
	
	public Boolean delete(Long id){
		return billDAO.delete(id);
	}
}
