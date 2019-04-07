package com.website.springmvc.Services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.entities.comic;

@Transactional
@Service
public class ComicService {
	@Autowired
	DAO<comic> comicDao;
		
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<comic> getListComic(String name){
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from comic where name like :keyword").setParameter("keyword", "%" + name + "%").list();
	}
	
	public List<comic> getListComic(String name, int firstResult, int maxResult){
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from comic where name like :keyword").setParameter("keyword", "%" + name + "%");
		
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		
		return query.list();
	}
	
	public List<comic> getListForAuthor(int id){
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from comic where idAuthor = :keyword order by quantitySold desc").setParameter("keyword", id).setMaxResults(4).list();
	}
	
	public comic get(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		return (comic) session.createQuery("from comic where unsignedName like :keyword").setParameter("keyword", name).uniqueResult();
	}
	
	public comic get(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (comic) session.get(comic.class, new Integer(id));
	}
	
	public List<comic> getListComic(String key, int id) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "";
		Query query = null;
		if(key.equalsIgnoreCase("category")) {
			hql = "from comic where idCategory = :keyword";
			query = session.createQuery(hql).setParameter("keyword", id);
		}	
		else if(key.equalsIgnoreCase("author")) {
			hql = "from comic where idAuthor = :keyword";
			query = session.createQuery(hql).setParameter("keyword", id);
		}
		else if(key.equalsIgnoreCase("publishing-company")) {
			hql = "from comic where idPublishCompany = :keyword";
			query = session.createQuery(hql).setParameter("keyword", id);
		}
		
		return query.list(); 
	}
	
	public List<comic> getListComic(String key, int id, int firstResult, int maxResult) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "";
		Query query = null;
		
		if(key.equalsIgnoreCase("category")) {
			hql = "from comic where idCategory = :keyword";
			query = session.createQuery(hql).setParameter("keyword", id);
		}	
		else if(key.equalsIgnoreCase("author")) {
			hql = "from comic where idAuthor = :keyword";
			query = session.createQuery(hql).setParameter("keyword", id);
		}
		else if(key.equalsIgnoreCase("publishing-company")) {
			hql = "from comic where idPublishCompany = :keyword";
			query = session.createQuery(hql).setParameter("keyword", id);
		}
		
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		
		return query.list(); 
	}

	public List<comic> getComicForTopSelling(){
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from comic order by quantitySold desc").setMaxResults(12).list();
	}

	public List<comic> getNewComic(){
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from comic order by publishDate desc").setMaxResults(36).list();
	}

	public List<comic> getNewComicInHomePage(){
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from comic order by publishDate desc").setMaxResults(4).list();
	}

	public List<comic> getOtherComic(){
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from comic where idCategory = 7 order by publishDate desc").list();
	}
	
	public List<comic> getOtherComicInHomePage(){
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from comic where idCategory = 7 order by publishDate desc").setMaxResults(4).list();
	}
	
	public List<comic> getAll() {
		return comicDao.getAll();
	}

	public comic get(Long id) {
		return comicDao.get(id);
	}

	public comic add(comic comic) {
		return comicDao.add(comic);
	}

	public Boolean update(comic comic) {
		return comicDao.update(comic);
	}

	public Boolean delete(comic comic) {
		return comicDao.delete(comic);
	}

	public Boolean delete(Long id) {
		return comicDao.delete(id);
	}		
}
