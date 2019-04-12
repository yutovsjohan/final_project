package com.website.springmvc.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.springmvc.entities.Author;
import com.website.springmvc.entities.Category;


@Repository
public class CategoryDAO extends DAO<Category>{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Category> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from category").list();
	}

	@Override
	public Category get(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Category) session.get(Category.class, new Long(id));
	}

	@Override
	public Category add(Category Cate) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(Cate);
		return Cate;
	}

	@Override
	public Boolean update(Category Cate) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(Cate);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean delete(Category Cate) {
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
		Category Cate = (Category) session.load(Category.class, new Long(id));
		if (null != Cate) {
			session.delete(Cate);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
}
