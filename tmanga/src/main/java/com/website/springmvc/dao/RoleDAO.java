package com.website.springmvc.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.springmvc.entities.role;

@Repository
public class RoleDAO extends DAO<role>{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<role> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from role").list();
	}

	@Override
	public role get(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (role) session.get(role.class, new Long(id));
	}

	@Override
	public role add(role r) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(r);
		return r;
	}

	@Override
	public Boolean update(role r) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(r);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean delete(role r) {
		Session session = this.sessionFactory.getCurrentSession();
		if (null != r) {
			try {
				session.delete(r);
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
		role r = (role) session.load(role.class, new Long(id));
		if (null != r) {
			session.delete(r);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
