package com.website.springmvc.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.springmvc.entities.billdetail;

@Repository
public class BillDetailDAO extends DAO<billdetail>{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<billdetail> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from billdetail").list();
	}

	@Override
	public billdetail get(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (billdetail) session.get(billdetail.class, new Long(id));
	}

	@Override
	public billdetail add(billdetail detail) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(detail);
		return detail;
	}

	@Override
	public Boolean update(billdetail detail) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(detail);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean delete(billdetail detail) {
		Session session = this.sessionFactory.getCurrentSession();
		if (null != detail) {
			try {
				session.delete(detail);
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
		billdetail detail = (billdetail) session.load(billdetail.class, new Long(id));
		if (null != detail) {
			session.delete(detail);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
}
