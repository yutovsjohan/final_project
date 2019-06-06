package com.website.springmvc.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.springmvc.entities.FavoriteList;


@Repository
public class FavoriteListDAO extends DAO<FavoriteList>{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<FavoriteList> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from FavoriteList").list();
	}

	@Override
	public FavoriteList get(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (FavoriteList) session.get(FavoriteList.class, new Long(id));
	}

	@Override
	public FavoriteList add(FavoriteList address) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(address);
		return address;
	}

	@Override
	public Boolean update(FavoriteList address) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(address);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean delete(FavoriteList address) {
		Session session = this.sessionFactory.getCurrentSession();
		if (null != address) {
			try {
				session.delete(address);
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
		FavoriteList address = (FavoriteList) session.load(FavoriteList.class, new Long(id));
		if (null != address) {
			session.delete(address);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
}
