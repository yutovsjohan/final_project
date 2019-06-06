package com.website.springmvc.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.springmvc.entities.News;

@Repository
public class NewsDAO extends DAO<News>{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<News> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from News").list();
	}

	@Override
	public News get(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (News) session.get(News.class, new Long(id));
	}	

	@Override
	public News add(News News) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(News);
		return News;
	}

	@Override
	public Boolean update(News News) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(News);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean delete(News News) {
		Session session = this.sessionFactory.getCurrentSession();
		if (null != News) {
			try {
				session.delete(News);
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
		News News = (News) session.load(News.class, new Long(id));
		if (null != News) {
			session.delete(News);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}		
}
