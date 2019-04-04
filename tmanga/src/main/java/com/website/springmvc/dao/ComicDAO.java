package com.website.springmvc.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.springmvc.entities.author;
import com.website.springmvc.entities.comic;

@Repository
public class ComicDAO extends DAO<comic>{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<comic> getListForAuthor(int id){
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from comic where idAuthor = :keyword order by quantitySold desc").setParameter("keyword", id).setMaxResults(4).list();
	}
	
	@Override
	public comic get(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		return (comic) session.createQuery("from comic where unsignedName like :keyword").setParameter("keyword", name).uniqueResult();
	}
	
	public List<comic> getList(String key, int id) {
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
	
	@Override
	public List<comic> getForTopSelling(){
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from comic order by quantitySold desc").setMaxResults(12).list();
	}
	
	@Override
	public List<comic> getForNew(){
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from comic order by publishDate desc").setMaxResults(36).list();
	}
	
	@Override
	public List<comic> getForNewInHome(){
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from comic order by publishDate desc").setMaxResults(4).list();
	}
	
	@Override
	public List<comic> getForOther(){
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from comic where idCategory = 7 order by publishDate desc").list();
	}
	
	@Override
	public List<comic> getForOtherInHome(){
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from comic where idCategory = 7 order by publishDate desc").setMaxResults(4).list();
	}
	
	@Override
	public List<comic> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from comic").list();
	}

	@Override
	public comic get(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (comic) session.get(comic.class, new Long(id));
	}

	@Override
	public comic add(comic Com) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(Com);
		return Com;
	}

	@Override
	public Boolean update(comic Com) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(Com);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean delete(comic Com) {
		Session session = this.sessionFactory.getCurrentSession();
		if (null != Com) {
			try {
				session.delete(Com);
				return Boolean.TRUE;
			} catch (Exception e) {
				return Boolean.FALSE;
			}
		}
		return Boolean.FALSE;
	}

	@Override
	public Boolean delete(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		comic Com = (comic) session.load(comic.class, new Long(id));
		if (null != Com) {
			session.delete(Com);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
}
