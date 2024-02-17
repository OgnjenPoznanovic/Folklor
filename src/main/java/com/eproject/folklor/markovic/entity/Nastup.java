package com.eproject.folklor.markovic.entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="nastup")
public class Nastup {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="nastup_id")
	private int nastup_id;
	
	@Column(name="mesto")
	private String mesto;
	
	@Column(name="vreme_dolaska")
	private String vreme_dolaska;
	
	@Column(name="broj_ljudi")
	private int broj_ljudi;
	
	@Column(name="datum")
	private Date datum;
	
	@ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="repertoar",
			joinColumns=@JoinColumn(name="nastupi_id"),
			inverseJoinColumns=@JoinColumn(name="koreografija_id"))
	private List<Koreografija> koreografije;

	
	public Nastup() {
		
	}

	public Nastup(String mesto, String vreme_dolaska, int broj_ljudi, Date datum) {
		this.mesto = mesto;
		this.vreme_dolaska = vreme_dolaska;
		this.broj_ljudi = broj_ljudi;
		this.datum = datum;
	}

	public int getNastup_id() {
		return nastup_id;
	}

	public void setNastup_id(int nastup_id) {
		this.nastup_id = nastup_id;
	}

	public String getMesto() {
		return mesto;
	}

	public void setMesto(String mesto) {
		this.mesto = mesto;
	}

	public String getVreme_dolaska() {
		return vreme_dolaska;
	}

	public void setVreme_dolaska(String vreme_dolaska) {
		this.vreme_dolaska = vreme_dolaska;
	}

	public int getBroj_ljudi() {
		return broj_ljudi;
	}

	public void setBroj_ljudi(int broj_ljudi) {
		this.broj_ljudi = broj_ljudi;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}
	
	public void setKoreografije(List<Koreografija> koreografije) {
        this.koreografije = koreografije;
    }
	
	
	public void addNastupe(Koreografija tempKoreografija) {

        if (koreografije == null) {
        	koreografije = new ArrayList<>();
        }

        koreografije.add(tempKoreografija);
    }
	
	
}
