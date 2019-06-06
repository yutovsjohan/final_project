package com.website.springmvc.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.springmvc.entities.District;


@Repository
public class DistrictDAO extends DAO<District>{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<District> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from District").list();
	}

	@Override
	public District get(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (District) session.get(District.class, new Long(id));
	}

	@Override
	public District add(District district) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(district);
		return district;
	}

	@Override
	public Boolean update(District district) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(district);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean delete(District district) {
		Session session = this.sessionFactory.getCurrentSession();
		if (null != district) {
			try {
				session.delete(district);
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
		District district = (District) session.load(District.class, new Long(id));
		if (null != district) {
			session.delete(district);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
}
