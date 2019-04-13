package com.website.springmvc.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.springmvc.entities.Notification;

@Repository
public class NotificationDAO extends DAO<Notification>{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Notification> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from Notification").list();
	}

	@Override
	public Notification get(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Notification) session.get(Notification.class, new Long(id));
	}

	@Override
	public Notification add(Notification Noti) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(Noti);
		return Noti;
	}

	@Override
	public Boolean update(Notification Noti) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(Noti);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean delete(Notification Noti) {
		Session session = this.sessionFactory.getCurrentSession();
		if (null != Noti) {
			try {
				session.delete(Noti);
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
		Notification Noti = (Notification) session.load(Notification.class, new Long(id));
		if (null != Noti) {
			session.delete(Noti);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
