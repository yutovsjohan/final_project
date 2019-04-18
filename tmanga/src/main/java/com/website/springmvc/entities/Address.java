package com.website.springmvc.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address", catalog = "manga")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "name", length = 255)
	private String name;
	
	@Column(name = "phone", length = 15)
	private String phone;
	
	@Column(name = "choose")
	private byte choose;
		
	@Column(name = "address", length = 255)
	private String address;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idUser", nullable = false)
	private Users user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idDistrict", nullable = false)
	private District district;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idCity", nullable = false)
	private City city;

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public byte getChoose() {
		return choose;
	}

	public void setChoose(byte choose) {
		this.choose = choose;
	}

	public Users getIdUser() {
		return user;
	}

	public void setIdUser(Users user) {
		this.user = user;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
		
}
