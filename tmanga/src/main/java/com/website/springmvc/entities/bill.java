package com.website.springmvc.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bill", catalog = "manga")
public class bill implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "orderDate")
	private Date orderDate;
	
	@Column(name = "total")
	private int total;
	
	@Column(name = "status")
	private byte status;
	
	@Column(name = "deliveryDate")
	private Date deliveryDate;
		
	@Column(name = "active")
	private int active;
	
	@Column(name = "view")
	private byte view;
	
	@Column(name = "note", length = 256)
	private String note;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bill")
	private Set<orderstatus> orderstatus = new HashSet<orderstatus>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "comic")
	private Set<billdetail> billdetail = new HashSet<billdetail>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUser", nullable = false)
	private users idUser;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "delivery", nullable = false)
	private users delivery;
		
	public bill() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public byte getView() {
		return view;
	}

	public void setView(byte view) {
		this.view = view;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
}
