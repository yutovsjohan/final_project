package com.website.springmvc.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.springmvc.entities.Author;
import com.website.springmvc.entities.PublishCompany;

@Repository
public class PublishComapyDAO extends DAO<PublishCompany>{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<PublishCompany> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from PublishCompany").list();
	}

	@Override
	public PublishCompany get(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (PublishCompany) session.get(PublishCompany.class, new Long(id));
	}

	@Override
	public PublishCompany add(PublishCompany Pub) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(Pub);
		return Pub;
	}

	@Override
	public Boolean update(PublishCompany Pub) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(Pub);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean delete(PublishCompany Pub) {
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
		PublishCompany Pub = (PublishCompany) session.load(PublishCompany.class, new Long(id));
		if (null != Pub) {
			session.delete(Pub);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
