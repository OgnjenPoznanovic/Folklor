package com.eproject.folklor.markovic.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;


@Entity
@Table(name="proba")
public class Proba {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="proba_id")
	private int proba_id;
	
	@Column(name="dan")
	private String dan;
	
	@Column(name="mesec")
	private String mesec;
	
	@Column(name="godina")
	private String godina;
	
	@Column(name="prisustvo")
	private int prisustvo;
	
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="ansambl_id")
	private Ansambl ansambl_id;
	
	@ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="prisustvo",
			joinColumns=@JoinColumn(name="proba_id"),
			inverseJoinColumns=@JoinColumn(name="igrac_id"))
	private List<Clan> clanovi;

	public Proba() {
		
	}

	public Proba(String dan, String mesec, String godina, int prisustvo) {
		
		this.dan = dan;
		this.mesec = mesec;
		this.godina = godina;
		this.prisustvo = prisustvo;
	}

	public int getProba_id() {
		return proba_id;
	}

	public void setProba_id(int proba_id) {
		this.proba_id = proba_id;
	}

	public String getDan() {
		return dan;
	}

	public void setDan(String dan) {
		this.dan = dan;
	}

	public String getMesec() {
		return mesec;
	}

	public void setMesec(String mesec) {
		this.mesec = mesec;
	}

	public String getGodina() {
		return godina;
	}

	public void setGodina(String godina) {
		this.godina = godina;
	}

	public int getPrisustvo() {
		return prisustvo;
	}

	public void setPrisustvo(int prisustvo) {
		this.prisustvo = prisustvo;
	}

	public Ansambl getAnsambl_id() {
		return ansambl_id;
	}

	public void setAnsambl_id(Ansambl ansambl_id) {
		this.ansambl_id = ansambl_id;
	}
	
	
	public void setClana(List<Clan> clanovi) {
        this.clanovi = clanovi;
    }
	
	
	public void addClana(Clan tempClan) {

        if (clanovi == null) {
        	clanovi = new ArrayList<>();
        }

        clanovi.add(tempClan);
    }
	
}
