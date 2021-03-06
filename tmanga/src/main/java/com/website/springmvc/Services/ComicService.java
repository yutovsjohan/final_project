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
	
	//admin site
	public List<Comic> getAllComic(String name, int firstResult, int maxResult){
		Session session = this.sessionFactory.getCurrentSession();
		Query query = null;
		
		query = session.createQuery("from Comic where name like :name");
		
		query.setParameter("name", "%" + name + "%");
		
		if(maxResult != 0) {
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);
		}
		
		return query.list();
	}
	
	public List<Comic> getListComicByCategory(Long id, int firstResult, int maxResult, String name){
		Session session = this.sessionFactory.getCurrentSession();
		Query query = null;
		
		query = session.createQuery("from Comic where idCategory = :id and name like :name");
		
		query.setParameter("id", id);
		query.setParameter("name", "%" + name + "%");
		
		if(maxResult != 0) {
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);
		}
		
		return query.list();
	}
	
	public List<Comic> getListComicByAuthor(Long id, int firstResult, int maxResult, String name){
		Session session = this.sessionFactory.getCurrentSession();
		Query query = null;
		
		query = session.createQuery("from Comic where idAuthor = :id and name like :name");
		
		query.setParameter("id", id);
		query.setParameter("name", "%" + name + "%");
		
		if(maxResult != 0) {
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);
		}
		
		return query.list();
	}
	
	public List<Comic> getListComicByPublishCompany(Long id, int firstResult, int maxResult, String name){
		Session session = this.sessionFactory.getCurrentSession();
		Query query = null;
		
		query = session.createQuery("from Comic where idPublishCompany = :id and name like :name");
		
		query.setParameter("id", id);
		query.setParameter("name", "%" + name + "%");
		
		if(maxResult != 0) {
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);
		}
		
		return query.list();
	}
		
	//customer site
	//search
	public List<Comic> getListComic(String name, int firstResult, int maxResult, int sort){
		Session session = this.sessionFactory.getCurrentSession();
		Query query = null;
		
		if(sort == 1) {
			query = session.createQuery("from Comic where name like :keyword and status = 1 order by publishDate desc");
		}
		else if(sort == 2) {
			query = session.createQuery("from Comic where name like :keyword and status = 1 order by quantitySold desc");
		}
		else if(sort == 3) {
			query = session.createQuery("from Comic where name like :keyword and status = 1 order by name asc");
		}
		else if(sort == 4) {
			query = session.createQuery("from Comic where name like :keyword and status = 1 order by name desc");
		}
		else if(sort == 5) {
			query = session.createQuery("from Comic where name like :keyword and sale < 20000 and status = 1");
		}
		else if(sort == 6) {
			query = session.createQuery("from Comic where name like :keyword and sale >= 20000 and sale <= 30000 and status = 1");
		}
		else if(sort == 7) {
			query = session.createQuery("from Comic where name like :keyword and sale >= 30000 and sale <= 40000 and status = 1");
		}
		else if(sort == 8) {
			query = session.createQuery("from Comic where name like :keyword and sale >= 40000 and sale <= 50000 and status = 1");
		}
		else if(sort == 9) {
			query = session.createQuery("from Comic where name like :keyword and sale > 50000 and status = 1");
		}
		else if(sort == 10) {
			query = session.createQuery("from Comic where name like :keyword and status = 1 order by sale asc");
		}
		else if(sort == 11) {
			query = session.createQuery("from Comic where name like :keyword and status = 1 order by sale desc");
		}
		
		query.setParameter("keyword", "%" + name + "%");
		
		if(maxResult != 0) {
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);
		}
		
		return query.list();
	}
	
	public List<Comic> getListForAuthor(Long id){
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from Comic where idAuthor = :keyword and status = 1 order by quantitySold desc").setParameter("keyword", id).setMaxResults(4).list();
	}
	
	public Comic get(String name, String action) {
		Session session = this.sessionFactory.getCurrentSession();
		Comic comic = new Comic();
		if(action.equalsIgnoreCase("admin")) {
			comic = (Comic) session.createQuery("from Comic where name = :keyword").setParameter("keyword", name).uniqueResult();
		}
		else if(action.equalsIgnoreCase("customer")) {
			comic = (Comic) session.createQuery("from Comic where unsignedName = :keyword and status = 1").setParameter("keyword", name).uniqueResult();
		}
		return comic;
	}
		
	//list product
	public List<Comic> getListComic(String key, long id, int firstResult, int maxResult, int sort) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "";
		Query query = null;
		
		if(key.equalsIgnoreCase("category")) {
			if(sort == 1) {
				hql = "from Comic where idCategory = :keyword and status = 1 order by publishDate desc";
			}		
			else if(sort == 2) {
				hql = "from Comic where idCategory = :keyword and status = 1 order by quantitySold desc";
			}
			else if(sort == 3) {
				hql = "from Comic where idCategory = :keyword and status = 1 order by name asc";
			}
			else if(sort == 4) {
				hql = "from Comic where idCategory = :keyword and status = 1 order by name desc";
			}			
			else if(sort == 10) {
				hql = "from Comic where idCategory = :keyword and status = 1 order by sale asc";
			}
			else if(sort == 11) {
				hql = "from Comic where idCategory = :keyword and status = 1 order by sale desc";
			}
			query = session.createQuery(hql).setParameter("keyword", id);
		}	
		else if(key.equalsIgnoreCase("author")) {
			if(sort == 1) {
				hql = "from Comic where idAuthor = :keyword and status = 1 order by publishDate desc";
			}
			else if(sort == 2) {
				hql = "from Comic where idAuthor = :keyword and status = 1 order by quantitySold desc";
			}
			else if(sort == 3) {
				hql = "from Comic where idAuthor = :keyword and status = 1 order by name asc";
			}
			else if(sort == 4) {
				hql = "from Comic where idAuthor = :keyword and status = 1 order by name desc";
			}
			else if(sort == 10) {
				hql = "from Comic where idAuthor = :keyword and status = 1 order by sale asc";
			}
			else if(sort == 11) {
				hql = "from Comic where idAuthor = :keyword and status = 1 order by sale desc";
			}
			query = session.createQuery(hql).setParameter("keyword", id);
		}
		else if(key.equalsIgnoreCase("publishing-company")) {
			if(sort == 1) {
				hql = "from Comic where idPublishCompany = :keyword and status = 1 order by publishDate desc";
			}
			else if(sort == 2) {
				hql = "from Comic where idPublishCompany = :keyword and status = 1 order by quantitySold desc";
			}
			else if(sort == 3) {
				hql = "from Comic where idPublishCompany = :keyword and status = 1 order by name asc";
			}
			else if(sort == 4) {
				hql = "from Comic where idPublishCompany = :keyword and status = 1 order by name desc";
			}
			else if(sort == 10) {
				hql = "from Comic where idPublishCompany = :keyword and status = 1 order by sale asc";
			}
			else if(sort == 11) {
				hql = "from Comic where idPublishCompany = :keyword and status = 1 order by sale desc";
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
		return session.createQuery("from Comic where status = 1 order by quantitySold desc").setMaxResults(12).list();
	}

	public List<Comic> getNewComic(int firstResult, int maxResult){
		Session session = this.sessionFactory.getCurrentSession();
		Query query = null;
		query = session.createQuery("from Comic where status = 1 order by created_at desc");
						
		if(maxResult != 0) {
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);
		}
		
		return query.list();
	}

	public List<Comic> getNewComicInHomePage(){
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from Comic where status = 1 order by created_at desc").setMaxResults(4).list();
	}

	public List<Comic> getOtherComic(){
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from Comic where idCategory = 7 and status = 1 order by publishDate desc").list();
	}
	
	public List<Comic> getOtherComicInHomePage(){
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from Comic where idCategory = 7 and status = 1 order by publishDate desc").setMaxResults(4).list();
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
