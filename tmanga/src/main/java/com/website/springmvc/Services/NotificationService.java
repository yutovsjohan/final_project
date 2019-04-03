package com.website.springmvc.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.entities.notification;

@Transactional
@Service
public class NotificationService {
	
	@Autowired
	DAO<notification> notiDAO;
	
	public List<notification> getAll(){
		return notiDAO.getAll();
	}
	
	public notification get(Long id){
		return notiDAO.get(id);
	}
	
	public notification add(notification t){
		return notiDAO.add(t);
	}
	
	public Boolean update(notification t){
		return notiDAO.update(t);
	}
	
	public Boolean delete(notification t){
		return notiDAO.delete(t);
	}
	
	public Boolean delete(Long id){
		return notiDAO.delete(id);
	}
}
