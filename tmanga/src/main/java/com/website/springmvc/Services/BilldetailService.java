package com.website.springmvc.Services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.entities.billdetail;

@Transactional
@Service
public class BilldetailService {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	DAO<billdetail> billdetailDAO;
	
	public billdetail get(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (billdetail) session.get(billdetail.class, new Integer(id));
	}
	
	public List<billdetail> getAll(){
		return billdetailDAO.getAll();
	}
	
	public billdetail get(Long id){
		return billdetailDAO.get(id);
	}
	
	public billdetail add(billdetail t){
		return billdetailDAO.add(t);
	}
	
	public Boolean update(billdetail t){
		return billdetailDAO.update(t);
	}
	
	public Boolean delete(billdetail t){
		return billdetailDAO.delete(t);
	}
	
	public Boolean delete(Long id){
		return billdetailDAO.delete(id);
	}
}
