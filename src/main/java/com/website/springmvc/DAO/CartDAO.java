package com.website.springmvc.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.springmvc.entities.Cart;


@Repository
public class CartDAO extends DAO<Cart>{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Cart> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from Cart").list();
	}

	@Override
	public Cart get(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Cart) session.get(Cart.class, new Long(id));
	}

	@Override
	public Cart add(Cart Bil) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(Bil);
		return Bil;
	}

	@Override
	public Boolean update(Cart Bil) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(Bil);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean delete(Cart Bil) {
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
		Cart Bil = (Cart) session.load(Cart.class, new Long(id));
		if (null != Bil) {
			session.delete(Bil);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
