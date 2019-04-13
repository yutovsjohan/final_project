package com.website.springmvc.libs;

import com.website.springmvc.entities.Comic;

public class Item {
	Comic comic;
	int amount;
	
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Item(Comic comic, int amount) {
		super();
		this.comic = comic;
		this.amount = amount;
	}

	public Comic getComic() {
		return comic;
	}

	public void setComic(Comic comic) {
		this.comic = comic;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
