package com.website.springmvc.libs;

import com.website.springmvc.entities.comic;

public class item {
	comic comic;
	int amount;
	
	public item() {
		super();
		// TODO Auto-generated constructor stub
	}

	public item(comic comic, int amount) {
		super();
		this.comic = comic;
		this.amount = amount;
	}

	public comic getComic() {
		return comic;
	}

	public void setComic(comic comic) {
		this.comic = comic;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
