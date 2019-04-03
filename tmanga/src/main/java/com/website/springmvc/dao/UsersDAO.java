package com.website.springmvc.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.springmvc.entities.users;

@Repository
public class UsersDAO extends DAO<users>{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public users get(String email) {
		Session session = this.sessionFactory.getCurrentSession();
		return (users) session.createSQLQuery("from users where email like ?").setParameter(0, "%" + email + "%").uniqueResult();
	}
	
	@Override
	public users get(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (users) session.get(users.class, id);
	}
	
	@Override
	public List<users> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from users").list();
	}

	@Override
	public users get(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (users) session.get(users.class, new Long(id));
	}

	@Override
	public users add(users u) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(u);
		return u;
	}

	@Override
	public Boolean update(users u) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(u);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean delete(users u) {
		Session session = this.sessionFactory.getCurrentSession();
		if (null != u) {
			try {
				session.delete(u);
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
		users u = (users) session.load(users.class, new Long(id));
		if (null != u) {
			session.delete(u);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
}
