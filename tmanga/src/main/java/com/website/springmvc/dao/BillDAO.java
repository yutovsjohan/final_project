package com.website.springmvc.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.springmvc.entities.Bill;

@Repository
public class BillDAO extends DAO<Bill>{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Bill> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from Bill").list();
	}

	@Override
	public Bill get(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Bill) session.get(Bill.class, new Long(id));
	}

	@Override
	public Bill add(Bill Bil) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(Bil);
		return Bil;
	}

	@Override
	public Boolean update(Bill Bil) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(Bil);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean delete(Bill Bil) {
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
		Bill Bil = (Bill) session.load(Bill.class, new Long(id));
		if (null != Bil) {
			session.delete(Bil);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
