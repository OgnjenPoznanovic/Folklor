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
@Table(name="clanarina")
public class Clanarina {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="clanarina_id")
	private int clanarina_id;
	
	@Column(name="mesec")
	private String mesec;
	
	@Column(name="godina")
	private String godina;
	
	@Column(name="cena")
	private int cena;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="ansambl_id")
	private Ansambl ansambl_id;


	@ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="placene_obaveze",
			joinColumns=@JoinColumn(name="clanarina_id"),
			inverseJoinColumns=@JoinColumn(name="igrac_id"))
	private List<Clan> clanovi;
	

	public Clanarina() {
		
	}
	
	

	public Clanarina(int clanarina_id, String mesec, String godina, int cena) {
		
		this.clanarina_id = clanarina_id;
		this.mesec = mesec;
		this.godina = godina;
		this.cena = cena;
	}



	public Clanarina(String mesec, String godina, int cena) {
		this.mesec = mesec;
		this.godina = godina;
		this.cena = cena;
	}

	public int getClanarina_id() {
		return clanarina_id;
	}

	public void setClanarina_id(int clanarina_id) {
		this.clanarina_id = clanarina_id;
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

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
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
	
	public void deleteClana(Clan tempClan) {

        clanovi.remove(tempClan);
    }
	
	public Ansambl getAnsambl_id() {
		return ansambl_id;
	}



	public void setAnsambl_id(Ansambl ansambl_id) {
		this.ansambl_id = ansambl_id;
	}



	public List<Clan> getClanovi() {
		return clanovi;
	}



	public void setClanovi(List<Clan> clanovi) {
		this.clanovi = clanovi;
	}
	
	
	
}
