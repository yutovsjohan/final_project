package com.website.springmvc.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.springmvc.entities.contact;


@Repository
public class ContactDAO extends DAO<contact>{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<contact> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from contact").list();
	}

	@Override
	public contact get(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (contact) session.get(contact.class, new Long(id));
	}

	@Override
	public contact add(contact Con) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(Con);
		return Con;
	}

	@Override
	public Boolean update(contact Con) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(Con);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean delete(contact Con) {
		Session session = this.sessionFactory.getCurrentSession();
		if (null != Con) {
			try {
				session.delete(Con);
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
		contact Con = (contact) session.load(contact.class, new Long(id));
		if (null != Con) {
			session.delete(Con);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
}
