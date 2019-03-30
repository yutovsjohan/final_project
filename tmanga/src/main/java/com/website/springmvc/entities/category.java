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
@Table(name = "category", catalog = "manga")
public class category implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	@Column(name = "name", length = 255)
	private String name;
	
	@Column(name = "unsignedName", length = 255)
	private String unsignedName;
	
	@Column(name = "status")
	private byte status;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private Set<comic> comics = new HashSet<comic>();
	
	
	public category() {
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
	
	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

}