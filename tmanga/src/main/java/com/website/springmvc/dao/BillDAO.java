package com.website.springmvc.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.springmvc.entities.bill;

@Repository
public class BillDAO extends DAO<bill>{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<bill> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from bill").list();
	}

	@Override
	public bill get(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (bill) session.get(bill.class, new Long(id));
	}

	@Override
	public bill add(bill Bil) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(Bil);
		return Bil;
	}

	@Override
	public Boolean update(bill Bil) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(Bil);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean delete(bill Bil) {
		Session session = this.sessionFactory.getCurrentSession();
		if (null != Bil) {
			try {
				session.delete(Bil);
				return Boolean.TRUE;
			} catch (Exception e) {
				return Boolean.FALSE;
			}
		}
		return Boolean.FALSE;
	}

	@Override
	public Boolean delete(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		bill Bil = (bill) session.load(bill.class, new Long(id));
		if (null != Bil) {
			session.delete(Bil);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
