package com.website.springmvc.Services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.entities.FavoriteList;

@Transactional
@Service
public class FavoriteListService {
	
	@Autowired
	DAO<FavoriteList> FavoriteListDAO;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public FavoriteList getByUsersAndComic(Long idUser, Long idComic){
		Session session = this.sessionFactory.getCurrentSession();
		return (FavoriteList) session.createQuery("from FavoriteList where user.id = :idUser and comic.id = :idComic").setParameter("idUser", idUser).setParameter("idComic", idComic).uniqueResult();
	}
	
	public List<FavoriteList> getListByUser(Long idUser, int firstResult, int maxResult){
		Session session = this.sessionFactory.getCurrentSession();
		Query query = null;
		query = session.createQuery("from FavoriteList where user.id = :idUser");
		query.setParameter("idUser", idUser);
		if(maxResult != 0) {
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);
		}
		return query.list();
	}
	
	public List<FavoriteList> getAll(){
		return FavoriteListDAO.getAll();
	}
	
	public FavoriteList get(Long id){
		return FavoriteListDAO.get(id);
	}
	
	public FavoriteList add(FavoriteList t){
		return FavoriteListDAO.add(t);
	}
	
	public Boolean update(FavoriteList t){
		return FavoriteListDAO.update(t);
	}
	
	public Boolean delete(FavoriteList t){
		return FavoriteListDAO.delete(t);
	}
	
	public Boolean delete(Long id){
		return FavoriteListDAO.delete(id);
	}
}
