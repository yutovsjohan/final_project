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
@Table(name = "orderstatus", catalog = "manga")
public class orderstatus implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	
	@Column(name = "content", length = 200)
	private String content;
	
	@Column(name = "note", length = 200)
	private String note;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idBill", nullable = false)
	private bill bill;
	
	public orderstatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public bill getBill() {
		return bill;
	}

	public void setBill(bill bill) {
		this.bill = bill;
	}
	
}
