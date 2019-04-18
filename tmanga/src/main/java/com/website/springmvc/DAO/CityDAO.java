package com.website.springmvc.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.springmvc.entities.City;


@Repository
public class CityDAO extends DAO<City>{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<City> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from City").list();
	}

	@Override
	public City get(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (City) session.get(City.class, new Long(id));
	}

	@Override
	public City add(City city) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(city);
		return city;
	}

	@Override
	public Boolean update(City city) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(city);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean delete(City city) {
		Session session = this.sessionFactory.getCurrentSession();
		if (null != city) {
			try {
				session.delete(city);
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
		City city = (City) session.load(City.class, new Long(id));
		if (null != city) {
			session.delete(city);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
}
