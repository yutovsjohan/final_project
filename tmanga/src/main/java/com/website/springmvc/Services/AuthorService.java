package com.website.springmvc.Services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.entities.author;

@Transactional
@Service
public class AuthorService {
	@Autowired
	DAO<author> authorDao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public author get(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		return (author) session.createQuery("from author where unsignedName like :keyword").setParameter("keyword", name).uniqueResult();
	}
	
	public author get(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (author) session.get(author.class, new Integer(id));
	}
	
	public List<author> getAll() {
		return authorDao.getAll();
	}

	public author get(Long id) {
		return authorDao.get(id);
	}

	public author add(author author) {
		return authorDao.add(author);
	}

	public Boolean update(author author) {
		return authorDao.update(author);
	}

	public Boolean delete(author author) {
		return authorDao.delete(author);
	}

	public Boolean delete(Long id) {
		return authorDao.delete(id);
	}
}
