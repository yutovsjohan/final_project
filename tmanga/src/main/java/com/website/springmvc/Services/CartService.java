package com.website.springmvc.Services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.entities.Cart;

@Transactional
@Service
public class CartService {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	DAO<Cart> cartDAO;
	
	public Cart getCartByUser(int iduser) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Cart) session.createQuery("from Cart where idUser.id = :keyword").setParameter("keyword", iduser).uniqueResult();
	}
	
	public Cart get(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Cart) session.get(Cart.class, new Integer(id));
	}
	
	public List<Cart> getAll(){
		return cartDAO.getAll();
	}
	
	public Cart get(Long id){
		return cartDAO.get(id);
	}
	
	public Cart add(Cart t){
		return cartDAO.add(t);
	}
	
	public Boolean update(Cart t){
		return cartDAO.update(t);
	}
	
	public Boolean delete(Cart t){
		return cartDAO.delete(t);
	}
	
	public Boolean delete(Long id){
		return cartDAO.delete(id);
	}
}
