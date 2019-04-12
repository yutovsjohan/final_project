package com.website.springmvc.Services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.entities.Comic;

@Transactional
@Service
public class ComicService {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	DAO<Comic> comicDao;
		
	public Boolean updateComic(Comic comic) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "update comic set amount = :amount where id = :id";
		 
		Query query = session.createQuery(hql);
		query.setParameter("amount", comic.getAmount());
		query.setParameter("id", comic.getId());
		 
		int rowsAffected = query.executeUpdate();
		if (rowsAffected > 0) {
			return true;
		}
		return false;
	}
	
	public List<Comic> getListComic(String name){
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from comic where name like :keyword").setParameter("keyword", "%" + name + "%").list();
	}
	
	public List<Comic> getListComic(String name, int firstResult, int maxResult){
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from comic where name like :keyword").setParameter("keyword", "%" + name + "%");
		
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		
		return query.list();
	}
	
	public List<Comic> getListForAuthor(int id){
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from comic where idAuthor = :keyword order by quantitySold desc").setParameter("keyword", id).setMaxResults(4).list();
	}
	
	public Comic get(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Comic) session.createQuery("from comic where unsignedName like :keyword").setParameter("keyword", name).uniqueResult();
	}
	
	public Comic get(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Comic) session.get(Comic.class, new Integer(id));
	}
	
	public List<Comic> getListComic(String key, int id) {
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
	
	public List<Comic> getListComic(String key, int id, int firstResult, int maxResult) {
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

	public List<Comic> getComicForTopSelling(){
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from comic order by quantitySold desc").setMaxResults(12).list();
	}

	public List<Comic> getNewComic(){
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from comic order by publishDate desc").setMaxResults(36).list();
	}

	public List<Comic> getNewComicInHomePage(){
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from comic order by publishDate desc").setMaxResults(4).list();
	}

	public List<Comic> getOtherComic(){
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from comic where idCategory = 7 order by publishDate desc").list();
	}
	
	public List<Comic> getOtherComicInHomePage(){
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from comic where idCategory = 7 order by publishDate desc").setMaxResults(4).list();
	}
	
	public List<Comic> getAll() {
		return comicDao.getAll();
	}

	public Comic get(Long id) {
		return comicDao.get(id);
	}

	public Comic add(Comic comic) {
		return comicDao.add(comic);
	}

	public Boolean update(Comic comic) {
		return comicDao.update(comic);
	}

	public Boolean delete(Comic comic) {
		return comicDao.delete(comic);
	}

	public Boolean delete(Long id) {
		return comicDao.delete(id);
	}		
}
