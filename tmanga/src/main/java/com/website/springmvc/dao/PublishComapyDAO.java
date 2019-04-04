package com.website.springmvc.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.springmvc.entities.author;
import com.website.springmvc.entities.publishcompany;

@Repository
public class PublishComapyDAO extends DAO<publishcompany>{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<publishcompany> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from publishcompany").list();
	}

	@Override
	public publishcompany get(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (publishcompany) session.get(publishcompany.class, new Long(id));
	}

	@Override
	public publishcompany add(publishcompany Pub) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(Pub);
		return Pub;
	}

	@Override
	public Boolean update(publishcompany Pub) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(Pub);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean delete(publishcompany Pub) {
		Session session = this.sessionFactory.getCurrentSession();
		if (null != Pub) {
			try {
				session.delete(Pub);
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
		publishcompany Pub = (publishcompany) session.load(publishcompany.class, new Long(id));
		if (null != Pub) {
			session.delete(Pub);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
