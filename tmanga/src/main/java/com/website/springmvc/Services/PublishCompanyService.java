package com.website.springmvc.Services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.entities.PublishCompany;

@Transactional
@Service
public class PublishCompanyService {
	@Autowired
	DAO<PublishCompany> publishCompanyDao;
	
	public List<PublishCompany> getListPublishCompany(int firstResult, int maxResult){
		Session session = this.sessionFactory.getCurrentSession();
		Query query = null;
		query = session.createQuery("from PublishCompany");

		if(maxResult != 0) {
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);
		}

		return query.list();
	}
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public PublishCompany get(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (PublishCompany) session.get(PublishCompany.class, new Integer(id));
	}
	
	public PublishCompany get(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		return (PublishCompany) session.createQuery("from PublishCompany where unsignedName like :keyword").setParameter("keyword", name).uniqueResult();
	}
	
	public List<PublishCompany> getAll() {
		return publishCompanyDao.getAll();
	}

	public PublishCompany get(Long id) {
		return publishCompanyDao.get(id);
	}

	public PublishCompany add(PublishCompany publishcompany) {
		return publishCompanyDao.add(publishcompany);
	}

	public Boolean update(PublishCompany publishcompany) {
		return publishCompanyDao.update(publishcompany);
	}

	public Boolean delete(PublishCompany publishcompany) {
		return publishCompanyDao.delete(publishcompany);
	}

	public Boolean delete(Long id) {
		return publishCompanyDao.delete(id);
	}
}
