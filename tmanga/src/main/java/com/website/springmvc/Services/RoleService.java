package com.website.springmvc.Services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.entities.Role;

@Transactional
@Service
public class RoleService {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	DAO<Role> RoleDAO;
		
	public List<Role> getListRole(int firstResult, int maxResult, String name){
		Session session = this.sessionFactory.getCurrentSession();
		Query query = null;
		
		query = session.createQuery("from Role where name like :name");
		
		query.setParameter("name", "%" + name + "%");
		
		if(maxResult != 0) {
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);
		}
		
		return query.list();
	}
	
	public List<Role> getAll(){
		return RoleDAO.getAll();
	}
	
	public Role get(Long id){
		return RoleDAO.get(id);
	}
	
	public Role add(Role t){
		return RoleDAO.add(t);
	}
	
	public Boolean update(Role t){
		return RoleDAO.update(t);
	}
	
	public Boolean delete(Role t){
		return RoleDAO.delete(t);
	}
	
	public Boolean delete(Long id){
		return RoleDAO.delete(id);
	}
}
