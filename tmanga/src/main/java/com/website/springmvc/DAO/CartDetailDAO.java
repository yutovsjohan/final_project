package com.website.springmvc.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.springmvc.entities.CartDetail;

@Repository
public class CartDetailDAO extends DAO<CartDetail>{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<CartDetail> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from CartDetail").list();
	}

	@Override
	public CartDetail get(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (CartDetail) session.get(CartDetail.class, new Long(id));
	}

	@Override
	public CartDetail add(CartDetail detail) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(detail);
		return detail;
	}

	@Override
	public Boolean update(CartDetail detail) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(detail);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean delete(CartDetail detail) {
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
		CartDetail detail = (CartDetail) session.load(CartDetail.class, new Long(id));
		if (null != detail) {
			session.delete(detail);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
}
