package com.website.springmvc.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.springmvc.entities.Role;

@Repository
public class RoleDAO extends DAO<Role>{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Role> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from role").list();
	}

	@Override
	public Role get(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Role) session.get(Role.class, new Long(id));
	}

	@Override
	public Role add(Role r) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(r);
		return r;
	}

	@Override
	public Boolean update(Role r) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(r);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean delete(Role r) {
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
		Role r = (Role) session.load(Role.class, new Long(id));
		if (null != r) {
			session.delete(r);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
