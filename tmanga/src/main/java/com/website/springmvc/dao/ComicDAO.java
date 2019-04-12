package com.website.springmvc.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.springmvc.entities.Author;
import com.website.springmvc.entities.Comic;

@Repository
public class ComicDAO extends DAO<Comic>{

	@Autowired
	private SessionFactory sessionFactory;	
	
	@Override
	public List<Comic> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from comic").list();
	}

	@Override
	public Comic get(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Comic) session.get(Comic.class, new Long(id));
	}

	@Override
	public Comic add(Comic Com) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(Com);
		return Com;
	}

	@Override
	public Boolean update(Comic Com) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(Com);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean delete(Comic Com) {
		Session session = this.sessionFactory.getCurrentSession();
		if (null != Com) {
			try {
				session.delete(Com);
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
		Comic Com = (Comic) session.load(Comic.class, new Long(id));
		if (null != Com) {
			session.delete(Com);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
}
