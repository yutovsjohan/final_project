package com.website.springmvc.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "news", catalog = "manga")
public class News implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "title", length = 255)
	private String title;

	@Column(name = "unsignedTitle", length = 255)
	private String unsignedTitle;

	@Column(name = "summary", length = 256)
	private String summary;

	@Column(name = "content", length = 256)
	private String content;

	@Column(name = "image", length = 255)
	private String image;

	@Column(name = "status")
	private byte status;

	@Column(name = "banner")
	private byte banner;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
	private Date created_at;
		
	public News(String title, String unsignedTitle, String summary, String content, String image, byte status,
			byte banner, Date created_at) {
		super();
		this.title = title;
		this.unsignedTitle = unsignedTitle;
		this.summary = summary;
		this.content = content;
		this.image = image;
		this.status = status;
		this.banner = banner;
		this.created_at = created_at;
	}

	public News(int id, String title, String unsignedTitle, String summary, String content, String image, byte status,
			byte banner, Date created_at) {
		super();
		this.id = id;
		this.title = title;
		this.unsignedTitle = unsignedTitle;
		this.summary = summary;
		this.content = content;
		this.image = image;
		this.status = status;
		this.banner = banner;
		this.created_at = created_at;
	}

	public News() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getUnsignedTitle() {
		return unsignedTitle;
	}

	public void setUnsignedTitle(String unsignedTitle) {
		this.unsignedTitle = unsignedTitle;
	}
	
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}	
	
	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}
	public byte getBanner() {
		return banner;
	}

	public void setBanner(byte banner) {
		this.banner = banner;
	}
	
	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	
	
}