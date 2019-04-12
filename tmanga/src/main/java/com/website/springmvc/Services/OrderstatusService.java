package com.website.springmvc.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.entities.OrderStatus;

@Transactional
@Service
public class OrderStatusService {
	
	@Autowired
	DAO<OrderStatus> orderDAO;
	
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
