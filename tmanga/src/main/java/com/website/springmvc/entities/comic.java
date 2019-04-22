package com.website.springmvc.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "comic", catalog = "manga")
public class Comic implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "name", length = 255)
	private String name;

	@Column(name = "unsignedName", length = 255)
	private String unsignedName;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idCategory", nullable = false)
	private Category category;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idAuthor", nullable = false)
	private Author author;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idPublishCompany", nullable = false)
	private PublishCompany publishcompany;

	@Column(name = "price")
	private int price;

	@Column(name = "amount")
	private int amount;

	@Column(name = "image", length = 255)
	private String image;

	@Column(name = "publishDate")
	private Date publishDate;

	@Column(name = "size", length = 50)
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
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date created_at;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
	private Date updated_at;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "idComic")
	private Set<Notification> notification = new HashSet<Notification>();
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "comic")
	private Set<BillDetail> billdetail = new HashSet<BillDetail>();
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "comic")
	private Set<CartDetail> cartdetail = new HashSet<CartDetail>();
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "comic")
	private List<FavoriteList> favoriteList = new ArrayList<FavoriteList>();
			
	public Comic() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public PublishCompany getPublishcompany() {
		return publishcompany;
	}

	public void setPublishcompany(PublishCompany publishcompany) {
		this.publishcompany = publishcompany;
	}

	public List<FavoriteList> getFavoriteList() {
		return favoriteList;
	}

	public void setFavoriteList(List<FavoriteList> favoriteList) {
		this.favoriteList = favoriteList;
	}

//	public Set<notification> getNotification() {
//		return notification;
//	}
//
//	public void setNotification(Set<notification> notification) {
//		this.notification = notification;
//	}

}
