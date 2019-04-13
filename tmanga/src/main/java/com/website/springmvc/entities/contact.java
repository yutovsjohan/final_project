package com.website.springmvc.entities;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contact", catalog = "manga")
public class Contact implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	@Column(name = "sender", length = 100)
	private String sender;

	@Column(name = "title", length = 250)
	private String title;

	@Column(name = "content", length = 256)
	private String content;

	@Column(name = "view")
	private byte view;

	@Column(name = "status")
	private byte status;
	
	public Contact(String sender, String title, String content, byte view, byte status) {
		super();
		this.sender = sender;
		this.title = title;
		this.content = content;
		this.view = view;
		this.status = status;
	}

	public Contact(int id, String sender, String title, String content, byte view, byte status) {
		super();
		this.id = id;
		this.sender = sender;
		this.title = title;
		this.content = content;
		this.view = view;
		this.status = status;
	}

	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
		
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public byte getView() {
		return view;
	}

	public void setView(byte view) {
		this.view = view;
	}
	
	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}
	
	
}