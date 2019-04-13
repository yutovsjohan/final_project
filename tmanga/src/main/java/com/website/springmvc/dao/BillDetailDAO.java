package com.website.springmvc.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.springmvc.entities.BillDetail;

@Repository
public class BillDetailDAO extends DAO<BillDetail>{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<BillDetail> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from BillDetail").list();
	}

	@Override
	public BillDetail get(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (BillDetail) session.get(BillDetail.class, new Long(id));
	}

	@Override
	public BillDetail add(BillDetail detail) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(detail);
		return detail;
	}

	@Override
	public Boolean update(BillDetail detail) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(detail);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean delete(BillDetail detail) {
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
		BillDetail detail = (BillDetail) session.load(BillDetail.class, new Long(id));
		if (null != detail) {
			session.delete(detail);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
}
