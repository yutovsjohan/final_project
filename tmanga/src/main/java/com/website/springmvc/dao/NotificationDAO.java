package com.website.springmvc.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.springmvc.entities.notification;

@Repository
public class NotificationDAO extends DAO<notification>{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<notification> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from notification").list();
	}

	@Override
	public notification get(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (notification) session.get(notification.class, new Long(id));
	}

	@Override
	public notification add(notification Noti) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(Noti);
		return Noti;
	}

	@Override
	public Boolean update(notification Noti) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(Noti);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean delete(notification Noti) {
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
		notification Noti = (notification) session.load(notification.class, new Long(id));
		if (null != Noti) {
			session.delete(Noti);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
