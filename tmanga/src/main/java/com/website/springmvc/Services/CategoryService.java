package com.website.springmvc.Services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.entities.author;
import com.website.springmvc.entities.category;

@Transactional
@Service
public class CategoryService {
	@Autowired
	DAO<category> categoryDao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public category get(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (category) session.get(category.class, new Integer(id));
	}
	
	public category get(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		return (category) session.createQuery("from category where unsignedName like :keyword").setParameter("keyword", name).uniqueResult();
	}
	public List<category> getAll() {
		return categoryDao.getAll();
	}

	public category get(Long id) {
		return categoryDao.get(id);
	}

	public category add(category category) {
		return categoryDao.add(category);
	}

	public Boolean update(category category) {
		return categoryDao.update(category);
	}

	public Boolean delete(category category) {
		return categoryDao.delete(category);
	}

	public Boolean delete(Long id) {
		return categoryDao.delete(id);
	}
}
