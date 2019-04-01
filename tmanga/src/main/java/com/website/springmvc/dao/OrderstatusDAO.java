package com.website.springmvc.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.springmvc.entities.orderstatus;

@Repository
public class OrderstatusDAO extends DAO<orderstatus>{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<orderstatus> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from orderstatus").list();
	}

	@Override
	public orderstatus get(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (orderstatus) session.get(orderstatus.class, new Long(id));
	}

	@Override
	public orderstatus add(orderstatus Order) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(Order);
		return Order;
	}

	@Override
	public Boolean update(orderstatus Order) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(Order);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean delete(orderstatus Order) {
		Session session = this.sessionFactory.getCurrentSession();
		if (null != Order) {
			try {
				session.delete(Order);
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
		orderstatus Order = (orderstatus) session.load(orderstatus.class, new Long(id));
		if (null != Order) {
			session.delete(Order);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
