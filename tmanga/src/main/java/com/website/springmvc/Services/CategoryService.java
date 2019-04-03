package com.website.springmvc.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.entities.category;

@Transactional
@Service
public class CategoryService {
	@Autowired
	DAO<category> categoryDao;
	
	public List<category> getAll() {
		return categoryDao.getAll();
	}

	public category get(Long id) {
		return categoryDao.get(id);
	}

	public category add(category category) {
		return categoryDao.add(category);
	}

	public Boolean update(category category) {
		return categoryDao.update(category);
	}

	public Boolean delete(category category) {
		return categoryDao.delete(category);
	}

	public Boolean delete(Long id) {
		return categoryDao.delete(id);
	}
}
