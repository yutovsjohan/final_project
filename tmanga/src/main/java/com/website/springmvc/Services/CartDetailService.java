package com.website.springmvc.Services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.entities.Cart;
import com.website.springmvc.entities.CartDetail;

@Transactional
@Service
public class CartDetailService {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	DAO<CartDetail> cartDetailDAO;
	
	public List<CartDetail> getDetailByIdCart(long idCart){
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from CartDetail where cart.id = :idCart").setParameter("idCart", idCart).list();
	}
	
	public CartDetail getDetailByCartAndProduct(Long idCart, Long idComic) {
		Session session = this.sessionFactory.getCurrentSession();
		return (CartDetail) session.createQuery("from CartDetail where cart.id = :idCart and comic.id = :idComic").setParameter("idCart", idCart).setParameter("idComic", idComic).uniqueResult();
	}
	
	public CartDetail get(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (CartDetail) session.get(CartDetail.class, new Integer(id));
	}
	
	public List<CartDetail> getAll(){
		return cartDetailDAO.getAll();
	}
	
	public CartDetail get(Long id){
		return cartDetailDAO.get(id);
	}
	
	public CartDetail add(CartDetail t){
		return cartDetailDAO.add(t);
	}
	
	public Boolean update(CartDetail t){
		return cartDetailDAO.update(t);
	}
	
	public Boolean delete(CartDetail t){
		return cartDetailDAO.delete(t);
	}
	
	public Boolean delete(Long id){
		return cartDetailDAO.delete(id);
	}
}
