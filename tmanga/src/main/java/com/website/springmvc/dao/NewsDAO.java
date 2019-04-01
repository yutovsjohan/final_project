package com.website.springmvc.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.springmvc.entities.news;

@Repository
public class NewsDAO extends DAO<news>{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<news> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from news").list();
	}

	@Override
	public news get(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (news) session.get(news.class, new Long(id));
	}

	@Override
	public news add(news News) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(News);
		return News;
	}

	@Override
	public Boolean update(news News) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(News);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean delete(news News) {
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
		news News = (news) session.load(news.class, new Long(id));
		if (null != News) {
			session.delete(News);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
}
