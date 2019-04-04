package com.website.springmvc.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.springmvc.DAO.DAO;
import com.website.springmvc.entities.author;
import com.website.springmvc.entities.comic;

@Transactional
@Service
public class ComicService {
	@Autowired
	DAO<comic> comicDao;
		
	public List<comic> getListForAuthor(int id) {
		return comicDao.getListForAuthor(id);
	}
	
	public comic get(String name) {
		return comicDao.get(name);
	}
	
	public List<comic> getList(String key, int id){
		return comicDao.getList(key, id);
	}
	
	public List<comic> getAll() {
		return comicDao.getAll();
	}

	public comic get(Long id) {
		return comicDao.get(id);
	}

	public comic add(comic comic) {
		return comicDao.add(comic);
	}

	public Boolean update(comic comic) {
		return comicDao.update(comic);
	}

	public Boolean delete(comic comic) {
		return comicDao.delete(comic);
	}

	public Boolean delete(Long id) {
		return comicDao.delete(id);
	}
	
	public List<comic> getComicForTopSelling(){
		return comicDao.getForTopSelling();
	}
	
	public List<comic> getNewComic(){
		return comicDao.getForNew();
	}
	
	public List<comic> getNewComicInHomePage(){
		return comicDao.getForNewInHome();
	}
	
	public List<comic> getOtherComic(){
		return comicDao.getForOther();
	}
	
	public List<comic> getOtherComicInHomePage(){
		return comicDao.getForOtherInHome();
	}
}
