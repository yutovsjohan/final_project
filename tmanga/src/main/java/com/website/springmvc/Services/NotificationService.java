package com.website.springmvc.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.entities.Notification;

@Transactional
@Service
public class NotificationService {
	
	@Autowired
	DAO<Notification> notiDAO;
	
	public List<Notification> getAll(){
		return notiDAO.getAll();
	}
	
	public Notification get(Long id){
		return notiDAO.get(id);
	}
	
	public Notification add(Notification t){
		return notiDAO.add(t);
	}
	
	public Boolean update(Notification t){
		return notiDAO.update(t);
	}
	
	public Boolean delete(Notification t){
		return notiDAO.delete(t);
	}
	
	public Boolean delete(Long id){
		return notiDAO.delete(id);
	}
}
