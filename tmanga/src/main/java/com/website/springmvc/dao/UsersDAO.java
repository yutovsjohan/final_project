package com.website.springmvc.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.springmvc.entities.Users;

@Repository
public class UsersDAO extends DAO<Users>{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Users> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from Users").list();
	}

	@Override
	public Users get(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Users) session.get(Users.class, new Long(id));
	}

	@Override
	public Users add(Users u) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(u);
		return u;
	}

	@Override
	public Boolean update(Users u) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(u);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean delete(Users u) {
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
		Users u = (Users) session.load(Users.class, new Long(id));
		if (null != u) {
			session.delete(u);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
}
