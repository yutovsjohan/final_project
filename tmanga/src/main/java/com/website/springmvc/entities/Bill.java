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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "bill", catalog = "manga")
public class Bill implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "orderDate")
	private Date orderDate;
	
	@Column(name = "total")
	private int total;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "deliveryDate")
	private Date deliveryDate;
		
	@Column(name = "active")
	private int active;
	
	@Column(name = "view")
	private byte view;
	
	@Column(name = "note", length = 256)
	private String note;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "bill")
	private Set<OrderStatus> orderstatus = new HashSet<OrderStatus>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "comic")
	private Set<BillDetail> billdetail = new HashSet<BillDetail>();
	
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "idUser", nullable = false)
//	private Users idUser;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "delivery", nullable = false)
	private Users delivery;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idAddress", nullable = false)
	private Address address;
		
	public Bill() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
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

	public Set<OrderStatus> getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(Set<OrderStatus> orderstatus) {
		this.orderstatus = orderstatus;
	}

	public Set<BillDetail> getBilldetail() {
		return billdetail;
	}

	public void setBilldetail(Set<BillDetail> billdetail) {
		this.billdetail = billdetail;
	}

	public Users getDelivery() {
		return delivery;
	}

	public void setDelivery(Users delivery) {
		this.delivery = delivery;
	}
	
}
