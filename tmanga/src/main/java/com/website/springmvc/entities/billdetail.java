package com.website.springmvc.entities;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "billdetail", catalog = "manga")
public class billdetail implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	@Column(name = "price")
	private int price;
	
	@Column(name = "amount")
	private byte amount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idComic", nullable = false)
	private comic comic;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idBill", nullable = false)
	private bill bill;

	public billdetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public byte getAmount() {
		return amount;
	}

	public void setAmount(byte amount) {
		this.amount = amount;
	}
}
