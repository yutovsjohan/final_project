package com.website.springmvc.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.springmvc.entities.author;

@Repository
public class AuthorDAO extends DAO<author>{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public author get(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		return (author) session.createQuery("from author where unsignedName like :keyword").setParameter("keyword", name).uniqueResult();
	}
	
	@Override
	public author get(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (author) session.get(author.class, new Integer(id));
	}
	
	@Override
	public List<author> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from author").list();
	}

	@Override
	public author get(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (author) session.get(author.class, new Long(id));
	}

	@Override
	public author add(author Au) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(Au);
		return Au;
	}

	@Override
	public Boolean update(author Au) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(Au);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean delete(author Au) {
		Session session = this.sessionFactory.getCurrentSession();
		if (null != Au) {
			try {
				session.delete(Au);
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
		author Au = (author) session.load(author.class, new Long(id));
		if (null != Au) {
			session.delete(Au);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
