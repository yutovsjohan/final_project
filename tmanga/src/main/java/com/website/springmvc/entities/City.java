package com.website.springmvc.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "city", catalog = "manga")
public class City {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "name", length = 255)
	private String name;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "city")
	private Set<District> district = new HashSet<District>();
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "city")
	private Set<Address> address = new HashSet<Address>();
	
	public City() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Set<District> getDistrict() {
		return district;
	}


	public void setDistrict(Set<District> district) {
		this.district = district;
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
}
