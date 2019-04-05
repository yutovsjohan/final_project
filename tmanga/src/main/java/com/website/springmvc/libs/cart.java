package com.website.springmvc.libs;

import java.util.ArrayList;
import java.util.HashMap;
import com.website.springmvc.entities.comic;


public class cart {
	HashMap<Integer, item> cart;
	
	public cart() {
		super();
		cart = new HashMap<>();
	}

	public cart(HashMap<Integer, item> cart) {
		super();
		this.cart = cart;
	}

	public HashMap<Integer, item> getCart() {
		return cart;
	}

	public void setCart(HashMap<Integer, item> cart) {
		this.cart = cart;
	}
	
	public void add(int id, comic comic) {
		if(cart.containsKey(id)) {
			item a = cart.get(id);
			a.setAmount(a.getAmount() + 1);
		}
		else {
			item a = new item(comic,1); 
			cart.put(id, a);
		}
	}
	
	public void delete(int id) {
		cart.remove(id);
	}
	
	public void deleteAll() {
		cart.clear();
	}
	
	public ArrayList<item> getList(){
		ArrayList<item> giohang = new ArrayList<>();
		for (item i : cart.values()) {
			giohang.add(i);
		}
		return giohang;
	}
	
	public int total() {
		int total=0;
		for (item i : cart.values()) {
			total += (i.getAmount() * i.getComic().getSale());
		}
		return total;
	}
	
	public int quantity() {
		int total=0;
		for (item i : cart.values()) {
			total += i.amount;
		}
		return total;
	}
}
