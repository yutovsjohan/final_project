package com.website.springmvc.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.springmvc.entities.comic;

@Repository
public class ComicDAO extends DAO<comic>{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<comic> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from comic").list();
	}

	@Override
	public comic get(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (comic) session.get(comic.class, new Long(id));
	}

	@Override
	public comic add(comic Com) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(Com);
		return Com;
	}

	@Override
	public Boolean update(comic Com) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(Com);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean delete(comic Com) {
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
		comic Com = (comic) session.load(comic.class, new Long(id));
		if (null != Com) {
			session.delete(Com);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
