package com.website.springmvc.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.springmvc.entities.OrderStatus;

@Repository
public class OrderStatusDAO extends DAO<OrderStatus>{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<OrderStatus> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from orderstatus").list();
	}

	@Override
	public OrderStatus get(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (OrderStatus) session.get(OrderStatus.class, new Long(id));
	}

	@Override
	public OrderStatus add(OrderStatus Order) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(Order);
		return Order;
	}

	@Override
	public Boolean update(OrderStatus Order) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(Order);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean delete(OrderStatus Order) {
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
		OrderStatus Order = (OrderStatus) session.load(OrderStatus.class, new Long(id));
		if (null != Order) {
			session.delete(Order);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
