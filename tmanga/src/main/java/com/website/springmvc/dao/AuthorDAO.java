package com.website.springmvc.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.springmvc.entities.Author;

@Repository
public class AuthorDAO extends DAO<Author>{
	
	@Autowired
	private SessionFactory sessionFactory;	
	
	@Override
	public List<Author> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from author").list();
	}

	@Override
	public Author get(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Author) session.get(Author.class, new Long(id));
	}

	@Override
	public Author add(Author Au) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(Au);
		return Au;
	}

	@Override
	public Boolean update(Author Au) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(Au);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean delete(Author Au) {
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
		Author Au = (Author) session.load(Author.class, new Long(id));
		if (null != Au) {
			session.delete(Au);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
