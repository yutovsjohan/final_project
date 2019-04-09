package com.website.springmvc.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users", catalog = "manga")
public class users implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	@Column(name = "name", length = 255)
	private String name;
	
	@Column(name = "email", length = 255)
	private String email;
	
	@Column(name = "password", length = 255)
	private String password;
	
	@Column(name = "address", length = 255)
	private String address;
	
	@Column(name = "phone", length = 15)
	private String phone;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idRole", nullable = false)
	private role role;
	
	@Column(name = "active")
	private byte active;
	
	@Column(name = "passcode", length = 120)
	private String passcode;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "favoritelist", joinColumns = { @JoinColumn(name = "idUser") }, inverseJoinColumns = {
			@JoinColumn(name = "idComic") })
	private List<comic> comics = new ArrayList<comic>();
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "idUser")
	private Set<bill> bill = new HashSet<bill>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "delivery")
	private Set<bill> delivery = new HashSet<bill>();

	public users() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public users(String name, String email, String password, String address, String phone) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.phone = phone;
	}

	public users(int id, String name, String email, String password, String address, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.phone = phone;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public role getRole() {
		return role;
	}

	public void setRole(role role) {
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
}
