package com.website.springmvc.Services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.entities.publishcompany;

@Transactional
@Service
public class PublishCompanyService {
	@Autowired
	DAO<publishcompany> publishCompanyDao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public publishcompany get(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (publishcompany) session.get(publishcompany.class, new Integer(id));
	}
	
	public publishcompany get(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		return (publishcompany) session.createQuery("from publishcompany where unsignedName like :keyword").setParameter("keyword", name).uniqueResult();
	}
	
	public List<publishcompany> getAll() {
		return publishCompanyDao.getAll();
	}

	public publishcompany get(Long id) {
		return publishCompanyDao.get(id);
	}

	public publishcompany add(publishcompany publishcompany) {
		return publishCompanyDao.add(publishcompany);
	}

	public Boolean update(publishcompany publishcompany) {
		return publishCompanyDao.update(publishcompany);
	}

	public Boolean delete(publishcompany publishcompany) {
		return publishCompanyDao.delete(publishcompany);
	}

	public Boolean delete(Long id) {
		return publishCompanyDao.delete(id);
	}
}
