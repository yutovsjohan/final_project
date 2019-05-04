package com.website.springmvc.Services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.entities.News;

@Transactional
@Service
public class NewsService {
	@Autowired
	DAO<News> newsDao;

	@Autowired
	private SessionFactory sessionFactory;
	
	public News getNewsById(String name){
		Session session = this.sessionFactory.getCurrentSession();
		return (News) session.createQuery("from News where unsignedTitle = :keyword").setParameter("keyword", name).uniqueResult();
	}
	
	public List<News> getListNews(int firstResult, int maxResult, int status){
		Session session = this.sessionFactory.getCurrentSession();
		Query query = null;
		if(status == 1) {
			query = session.createQuery("from News where status = 1 order by created_at desc");
		}
		else {
			query = session.createQuery("from News order by created_at desc");
		}
		
		if(maxResult != 0) {
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);
		}
		
		return query.list();
	}
	
	public List<News> getNewsForBanner(){
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from News where banner = 1").list();
	}

	public List<News> getNews(){
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from News").list();
	}
	
	public List<News> getAll() {
		return newsDao.getAll();
	}

	public News get(Long id) {
		return newsDao.get(id);
	}

	public News add(News news) {
		return newsDao.add(news);
	}

	public Boolean update(News news) {
		return newsDao.update(news);
	}

	public Boolean delete(News news) {
		return newsDao.delete(news);
	}

	public Boolean delete(Long id) {
		return newsDao.delete(id);
	}		
}
