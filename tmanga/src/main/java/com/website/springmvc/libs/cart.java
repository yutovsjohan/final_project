package com.website.springmvc.libs;

import java.util.ArrayList;
import java.util.HashMap;
import com.website.springmvc.entities.Comic;


public class Cart {
	HashMap<Integer, Item> cart;
	
	public Cart() {
		super();
		cart = new HashMap<>();
	}

	public Cart(HashMap<Integer, Item> cart) {
		super();
		this.cart = cart;
	}

	public HashMap<Integer, Item> getCart() {
		return cart;
	}

	public void setCart(HashMap<Integer, Item> cart) {
		this.cart = cart;
	}
	
	public void add(int id, Comic comic, int amount) {
		if(cart.containsKey(id)) {
			Item a = cart.get(id);
			a.setAmount(a.getAmount() + amount);
		}
		else {
			Item a = new Item(comic, amount); 
			cart.put(id, a);
		}
	}
	
	public void delete(int id) {
		cart.remove(id);
	}
	
	public void deleteAll() {
		cart.clear();
	}
	
	public ArrayList<Item> getList(){
		ArrayList<Item> giohang = new ArrayList<>();
		for (Item i : cart.values()) {
			giohang.add(i);
		}
		return giohang;
	}
	
	public int total() {
		int total=0;
		for (Item i : cart.values()) {
			total += (i.getAmount() * i.getComic().getSale());
		}
		return total;
	}
	
	public int quantity() {
		int total=0;
		for (Item i : cart.values()) {
			total += i.amount;
		}
		return total;
	}
	
	public Item getItemForId(int id) {
		Item item = new Item();
		for (Item i : cart.values()) {
			if(i.comic.getId() == id) {
				item = i;
			}
		}
		return item;
	}
}
