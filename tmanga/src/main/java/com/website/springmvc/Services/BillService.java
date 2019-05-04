package com.website.springmvc.Services;


import java.sql.Date;
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
	
	public Long countBillByDate(int day, int month, int year){
		Session session = this.sessionFactory.getCurrentSession();
		return (Long) session.createQuery("select count(id) from Bill where year(orderDate) = :year and month(orderDate) = :month and day(orderDate) = :day and active = 2").setParameter("day", day).setParameter("month", month).setParameter("year", year).uniqueResult();
	}

	public Long getBillUnView(){
		Session session = this.sessionFactory.getCurrentSession();
		return (Long) session.createQuery("select count(id) from Bill where view = 0").uniqueResult();
	}
	
	public Long getReportByYear(int year){
		Session session = this.sessionFactory.getCurrentSession();
		return (Long) session.createQuery("select sum(total) from Bill where year(orderDate) = :year and active = 2").setParameter("year", year).uniqueResult();
	}
	
	public Long getReportByMonth(int month, int year){
		Session session = this.sessionFactory.getCurrentSession();
		return (Long) session.createQuery("select sum(total) from Bill where year(orderDate) = :year and month(orderDate) = :month and active = 2").setParameter("month", month).setParameter("year", year).uniqueResult();
	}
		
	public Long getReportByDate(int day, int month, int year){
		Session session = this.sessionFactory.getCurrentSession();
		return (Long) session.createQuery("select sum(total) from Bill where year(orderDate) = :year and month(orderDate) = :month and day(orderDate) = :day and active = 2").setParameter("day", day).setParameter("month", month).setParameter("year", year).uniqueResult();
	}
	
//	public List<Object[]> getListMoneyByDate(Date dateStart, Date dateEnd){
//		Session session = this.sessionFactory.getCurrentSession();
//		return session.createQuery("select total, day(orderDate), month(orderDate), year(orderDate) from Bill where orderDate between :dateStart and :dateEnd and active = 2 order by orderDate asc").setParameter("dateStart", dateStart).setParameter("dateEnd", dateEnd).list();
//	}
	
	public Long getCountNewBill() {
		Session session = this.sessionFactory.getCurrentSession();
		return (Long) session.createQuery("select count(id) from Bill where view = 0").uniqueResult();
	}
	
	public List<Bill> getAll(int firstResult, int maxResult) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Bill order by orderDate desc");
		
		if(maxResult != 0) {
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);
		}
		return query.list();
	}
	
	
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
