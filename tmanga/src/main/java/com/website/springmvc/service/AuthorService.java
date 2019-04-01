package com.website.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.entities.author;

@Transactional
@Service
public class AuthorService {
	@Autowired
	DAO<author> authorDao;
	
	public List<author> getAll() {
		return authorDao.getAll();
	}

	public author get(Long id) {
		return authorDao.get(id);
	}

	public author add(author author) {
		return authorDao.add(author);
	}

	public Boolean update(author author) {
		return authorDao.update(author);
	}

	public Boolean delete(author author) {
		return authorDao.delete(author);
	}

	public Boolean delete(Long id) {
		return authorDao.delete(id);
	}
}
