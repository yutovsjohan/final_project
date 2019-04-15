package com.website.springmvc.Services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.entities.OrderStatus;

@Transactional
@Service
public class OrderStatusService {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	DAO<OrderStatus> orderDAO;
	
	public List<OrderStatus> getOrderStatusByIdBill(Long idBill) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from OrderStatus where idBill = :idBill").setParameter("idBill", idBill).list();
	}
	
	public List<OrderStatus> getAll(){
		return orderDAO.getAll();
	}
	
	public OrderStatus get(Long id){
		return orderDAO.get(id);
	}
	
	public OrderStatus add(OrderStatus t){
		return orderDAO.add(t);
	}
	
	public Boolean update(OrderStatus t){
		return orderDAO.update(t);
	}
	
	public Boolean delete(OrderStatus t){
		return orderDAO.delete(t);
	}
	
	public Boolean delete(Long id){
		return orderDAO.delete(id);
	}
}
