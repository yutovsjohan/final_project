package com.website.springmvc.Services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.entities.Author;

@Transactional
@Service
public class AuthorService {
	@Autowired
	DAO<Author> authorDao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Author> getListAuthors(String name, int firstResult, int maxResult){
		Session session = this.sessionFactory.getCurrentSession();
		Query query = null;
		query = session.createQuery("from Author where name like :keyword").setParameter("keyword", "%" + name + "%");
		
		if(maxResult != 0) {
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);
		}

		return query.list();
	}
	
	public Author get(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Author) session.createQuery("from Author where unsignedName like :keyword").setParameter("keyword", name).uniqueResult();
	}
	
	public Author get(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Author) session.get(Author.class, new Integer(id));
	}
	
	public List<Author> getAll() {
		return authorDao.getAll();
	}

	public Author get(Long id) {
		return authorDao.get(id);
	}

	public Author add(Author author) {
		return authorDao.add(author);
	}

	public Boolean update(Author author) {
		return authorDao.update(author);
	}

	public Boolean delete(Author author) {
		return authorDao.delete(author);
	}

	public Boolean delete(Long id) {
		return authorDao.delete(id);
	}
}
