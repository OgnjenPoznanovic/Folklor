package com.eproject.folklor.markovic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name="uloge")
@IdClass(UlogeKey.class)
public class Uloge {

	@Id
	@Column(name = "username")
	private String username;
	
	@Id
	@Column(name = "uloga")
	private String uloga;
	
	public Uloge() {
		
	}	
	

	public Uloge(String username, String uloga) {		
		this.username = username;
		this.uloga = uloga;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}
	
	
	
}
