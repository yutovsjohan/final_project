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
@Table(name = "comic", catalog = "manga")
public class comic implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "name", length = 255)
	private String name;

	@Column(name = "unsignedName", length = 255)
	private String unsignedName;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCategory", nullable = false)
	private category category;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idAuthor", nullable = false)
	private author author;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idPublishCompany", nullable = false)
	private publishcompany publishcompany;

	@Column(name = "price")
	private int price;

	@Column(name = "amount")
	private int amount;

	@Column(name = "image", length = 255)
	private String image;

	@Column(name = "publishDate")
	private Date publishDate;

	@Column(name = "size")
	private String size;

	@Column(name = "weight")
	private int weight;

	@Column(name = "bookCover", length = 50)
	private String bookCover;

	@Column(name = "content", length = 256)
	private String content;

	@Column(name = "sale")
	private int sale;

	@Column(name = "quantitySold")
	private int quantitySold;	

	@Column(name = "status")
	private byte status;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "comic")
	private Set<notification> notification = new HashSet<notification>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "comic")
	private Set<billdetail> billdetail = new HashSet<billdetail>();
			
	public comic() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getUnsignedName() {
		return unsignedName;
	}

	public void setUnsignedName(String unsignedName) {
		this.unsignedName = unsignedName;
	}	
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public String getBookCover() {
		return bookCover;
	}

	public void setBookCover(String bookCover) {
		this.bookCover = bookCover;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public int getSale() {
		return sale;
	}

	public void setSale(int sale) {
		this.sale = sale;
	}
	
	public int getQuantitySold() {
		return quantitySold;
	}

	public void setQuantitySold(int quantitySold) {
		this.quantitySold = quantitySold;
	}
	
	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public category getCategory() {
		return category;
	}

	public void setCategory(category category) {
		this.category = category;
	}

	public author getAuthor() {
		return author;
	}

	public void setAuthor(author author) {
		this.author = author;
	}

	public publishcompany getPublishcompany() {
		return publishcompany;
	}

	public void setPublishcompany(publishcompany publishcompany) {
		this.publishcompany = publishcompany;
	}

	public Set<notification> getNotification() {
		return notification;
	}

	public void setNotification(Set<notification> notification) {
		this.notification = notification;
	}

}