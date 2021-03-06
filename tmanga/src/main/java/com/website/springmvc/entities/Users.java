package com.website.springmvc.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
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

@Entity
@Table(name = "Users", catalog = "manga")
public class Users implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "name", length = 255)
	private String name;
	
	@Column(name = "email", length = 255)
	private String email;
	
	@Column(name = "password", length = 255)
	private String password;
		
	@Column(name = "phone", length = 15)
	private String phone;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idRole", nullable = false)
	private Role role;
	
	@Column(name = "active")
	private byte active;
	
	@Column(name = "passcode", length = 120)
	private String passcode;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private List<FavoriteList> favoriteList = new ArrayList<FavoriteList>();
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "idUser")
	private Set<Bill> bill = new HashSet<Bill>();
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "delivery")
//	private Set<Bill> delivery = new HashSet<Bill>();
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private Set<Address> addressList = new HashSet<Address>();
	
	public Users() {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public byte getActive() {
		return active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public String getPasscode() {
		return passcode;
	}

	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}

	public List<FavoriteList> getFavoriteList() {
		return favoriteList;
	}

	public void setFavoriteList(List<FavoriteList> favoriteList) {
		this.favoriteList = favoriteList;
	}
	
}
