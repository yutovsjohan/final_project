package com.website.springmvc.Services;

import java.util.List;

import org.hibernate.Query;
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
	
	public Bill getBillByIdBillAndUser(Long idBill, Long idUser) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Bill) session.createQuery("from Bill where id = :idBill and idUser.id = :idUser").setParameter("idBill", idBill).setParameter("idUser", idUser).uniqueResult();
	}
	
	public List<Bill> getBillByUser(Long iduser, int firstResult, int maxResult) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Bill where idUser.id = :keyword order by orderDate desc").setParameter("keyword", iduser);
		
		if(maxResult != 0) {
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);
		}
		return query.list();
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
