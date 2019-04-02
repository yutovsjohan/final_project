package com.website.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.DAO.NewsDAO;
import com.website.springmvc.entities.news;

@Transactional
@Service
public class NewsService {
	@Autowired
	DAO<news> newsDao;
	
	public List<news> getNewsForBanner(){
		return newsDao.getFor();
	}
	
	public List<news> getNews(){
		return newsDao.getForNew();
	}
	
	public List<news> getAll() {
		return newsDao.getAll();
	}

	public news get(Long id) {
		return newsDao.get(id);
	}

	public news add(news news) {
		return newsDao.add(news);
	}

	public Boolean update(news news) {
		return newsDao.update(news);
	}

	public Boolean delete(news news) {
		return newsDao.delete(news);
	}

	public Boolean delete(Long id) {
		return newsDao.delete(id);
	}		
}