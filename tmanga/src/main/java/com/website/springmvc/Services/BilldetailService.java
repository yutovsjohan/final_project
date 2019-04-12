package com.website.springmvc.Services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.entities.BillDetail;

@Transactional
@Service
public class BillDetailService {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	DAO<BillDetail> billdetailDAO;
	
	public BillDetail get(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (BillDetail) session.get(BillDetail.class, new Integer(id));
	}
	
	public List<BillDetail> getAll(){
		return billdetailDAO.getAll();
	}
	
	public BillDetail get(Long id){
		return billdetailDAO.get(id);
	}
	
	public BillDetail add(BillDetail t){
		return billdetailDAO.add(t);
	}
	
	public Boolean update(BillDetail t){
		return billdetailDAO.update(t);
	}
	
	public Boolean delete(BillDetail t){
		return billdetailDAO.delete(t);
	}
	
	public Boolean delete(Long id){
		return billdetailDAO.delete(id);
	}
}
