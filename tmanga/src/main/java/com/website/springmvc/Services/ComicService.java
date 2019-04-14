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
	
	//search
	public List<Comic> getListComic(String name, int firstResult, int maxResult, int sort){
		Session session = this.sessionFactory.getCurrentSession();
		Query query = null;
		
		if(sort == 1) {
			query = session.createQuery("from Comic where name like :keyword order by publishDate desc");
		}
		else if(sort == 2) {
			query = session.createQuery("from Comic where name like :keyword order by quantitySold desc");
		}
		else if(sort == 3) {
			query = session.createQuery("from Comic where name like :keyword order by name asc");
		}
		else if(sort == 4) {
			query = session.createQuery("from Comic where name like :keyword order by name desc");
		}
		
		query.setParameter("keyword", "%" + name + "%");
		
		if(maxResult != 0) {
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);
		}
		
		return query.list();
	}
	
	public List<Comic> getListForAuthor(int id){
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from Comic where idAuthor = :keyword order by quantitySold desc").setParameter("keyword", id).setMaxResults(4).list();
	}
	
	public Comic get(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Comic) session.createQuery("from Comic where unsignedName = :keyword").setParameter("keyword", name).uniqueResult();
	}
	
	public Comic get(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Comic) session.get(Comic.class, new Integer(id));
	}
	
	//list product
	public List<Comic> getListComic(String key, int id, int firstResult, int maxResult, int sort) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "";
		Query query = null;
		
		if(key.equalsIgnoreCase("category")) {
			if(sort == 1) {
				hql = "from Comic where idCategory = :keyword order by publishDate desc";
			}		
			else if(sort == 2) {
				hql = "from Comic where idCategory = :keyword order by quantitySold desc";
			}
			else if(sort == 3) {
				hql = "from Comic where idCategory = :keyword order by name asc";
			}
			else if(sort == 4) {
				hql = "from Comic where idCategory = :keyword order by name desc";
			}			
			query = session.createQuery(hql).setParameter("keyword", id);
		}	
		else if(key.equalsIgnoreCase("author")) {
			if(sort == 1) {
				hql = "from Comic where idAuthor = :keyword order by publishDate desc";
			}
			else if(sort == 2) {
				hql = "from Comic where idAuthor = :keyword order by quantitySold desc";
			}
			else if(sort == 3) {
				hql = "from Comic where idAuthor = :keyword order by name asc";
			}
			else if(sort == 4) {
				hql = "from Comic where idAuthor = :keyword order by name desc";
			}
			query = session.createQuery(hql).setParameter("keyword", id);
		}
		else if(key.equalsIgnoreCase("publishing-company")) {
			if(sort == 1) {
				hql = "from Comic where idPublishCompany = :keyword order by publishDate desc";
			}
			else if(sort == 2) {
				hql = "from Comic where idPublishCompany = :keyword order by quantitySold desc";
			}
			else if(sort == 3) {
				hql = "from Comic where idPublishCompany = :keyword order by name asc";
			}
			else if(sort == 4) {
				hql = "from Comic where idPublishCompany = :keyword order by name desc";
			}
			query = session.createQuery(hql).setParameter("keyword", id);
		}
		
		if(maxResult != 0) {
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);
		}
		
		return query.list(); 
	}

	public List<Comic> getComicForTopSelling(){
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from Comic order by quantitySold desc").setMaxResults(12).list();
	}

	public List<Comic> getNewComic(){
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from Comic order by publishDate desc").setMaxResults(36).list();
	}

	public List<Comic> getNewComicInHomePage(){
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from Comic order by publishDate desc").setMaxResults(4).list();
	}

	public List<Comic> getOtherComic(){
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from Comic where idCategory = 7 order by publishDate desc").list();
	}
	
	public List<Comic> getOtherComicInHomePage(){
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from Comic where idCategory = 7 order by publishDate desc").setMaxResults(4).list();
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
