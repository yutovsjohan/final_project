package com.website.springmvc.DAO;

import java.util.List;

public abstract class DAO<T> {
	public abstract List<T> getAll();

	public abstract T get(Long id);

	public abstract T add(T t);

	public abstract Boolean update(T t);

	public abstract Boolean delete(T t);

	public abstract Boolean delete(Long id);
}
