package com.website.springmvc.Services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.entities.Category;

@Transactional
@Service
public class CategoryService {
	@Autowired
	DAO<Category> categoryDao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Category> getListCategories(int firstResult, int maxResult){
		Session session = this.sessionFactory.getCurrentSession();
		Query query = null;
		query = session.createQuery("from Category");

		if(maxResult != 0) {
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);
		}

		return query.list();
	}
	
	public Category get(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Category) session.get(Category.class, new Integer(id));
	}
	
	public Category get(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Category) session.createQuery("from Category where unsignedName like :keyword").setParameter("keyword", name).uniqueResult();
	}
	public List<Category> getAll() {
		return categoryDao.getAll();
	}

	public Category get(Long id) {
		return categoryDao.get(id);
	}

	public Category add(Category category) {
		return categoryDao.add(category);
	}

	public Boolean update(Category category) {
		return categoryDao.update(category);
	}

	public Boolean delete(Category category) {
		return categoryDao.delete(category);
	}

	public Boolean delete(Long id) {
		return categoryDao.delete(id);
	}
}
