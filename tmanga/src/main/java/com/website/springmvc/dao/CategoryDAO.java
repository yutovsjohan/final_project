package com.website.springmvc.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.springmvc.entities.category;


@Repository
public class CategoryDAO extends DAO<category>{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<category> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from category").list();
	}

	@Override
	public category get(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (category) session.get(category.class, new Long(id));
	}

	@Override
	public category add(category Cate) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(Cate);
		return Cate;
	}

	@Override
	public Boolean update(category Cate) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(Cate);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean delete(category Cate) {
		Session session = this.sessionFactory.getCurrentSession();
		if (null != Cate) {
			try {
				session.delete(Cate);
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
		category Cate = (category) session.load(category.class, new Long(id));
		if (null != Cate) {
			session.delete(Cate);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
}
