package com.website.springmvc.DAO;

import java.util.List;

public abstract class DAO<T> {
	public List<T> getFor() {
		return null;
	}
	
	public List<T> getForTopSelling() {
		return null;
	}
	
	public List<T> getForNew() {
		return null;
	}
	
	public List<T> getForNewInHome() {
		return null;
	}
	
	public List<T> getForOther() {
		return null;
	}
	
	public List<T> getForOtherInHome() {
		return null;
	}
	
	public abstract List<T> getAll();
	
	public abstract T get(Long id);

	public abstract T add(T t);

	public abstract Boolean update(T t);

	public abstract Boolean delete(T t);

	public abstract Boolean delete(Long id);	
	
}
