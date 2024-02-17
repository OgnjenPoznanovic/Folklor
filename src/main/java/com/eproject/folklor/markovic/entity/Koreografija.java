package com.eproject.folklor.markovic.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
@Table(name="koreografija")
public class Koreografija {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="koreografija_id")
	private int koreografija_id;
	
	@Column(name="naziv")
	private String naziv;
	
	@Column(name="broj_parova")
	private int broj_parova;
	
	@Column(name="trajanje")
	private int trajanje;
	
	@Column(name="broj_momaka")
	private int broj_momaka;
	
	@Column(name="broj_devojaka")
	private int broj_devojaka;
	
	@OneToMany(mappedBy="koreografija_id",
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.DETACH, CascadeType.REFRESH})
	private List<Nosnja> nosnje;
	
	@ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="nastupa",
			joinColumns=@JoinColumn(name="koreografija_id"),
			inverseJoinColumns=@JoinColumn(name="igrac_id"))
	private List<Clan> clanovi;
	
	
	@ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="repertoar",
			joinColumns=@JoinColumn(name="koreografija_id"),
			inverseJoinColumns=@JoinColumn(name="nastupi_id"))
	private List<Nastup> nastupi;

	public Koreografija() {
		
	}
	
	

	public Koreografija(String naziv, int broj_parova, int trajanje, int broj_momaka, int broj_devojaka) {
		this.naziv = naziv;
		this.broj_parova = broj_parova;
		this.trajanje = trajanje;
		this.broj_momaka = broj_momaka;
		this.broj_devojaka = broj_devojaka;
	}

	

	public int getKoreografija_id() {
		return koreografija_id;
	}

	public void setKoreografija_id(int koreografija_id) {
		this.koreografija_id = koreografija_id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getBroj_parova() {
		return broj_parova;
	}

	public void setBroj_parova(int broj_parova) {
		this.broj_parova = broj_parova;
	}

	public int getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}

	public int getBroj_momaka() {
		return broj_momaka;
	}

	public void setBroj_momaka(int broj_momaka) {
		this.broj_momaka = broj_momaka;
	}

	public int getBroj_devojaka() {
		return broj_devojaka;
	}

	public void setBroj_devojaka(int broj_devojaka) {
		this.broj_devojaka = broj_devojaka;
	}



	public List<Nosnja> getNosnje() {
		return nosnje;
	}



	public void setNosnje(List<Nosnja> nosnje) {
		this.nosnje = nosnje;
	}
	
	public void add(Nosnja tempNosnja) {
		
		if(nosnje == null) {
			nosnje = new ArrayList<>();
		}
		
		nosnje.add(tempNosnja);
		
		tempNosnja.setKoreografija_id(this);
	}
	
	public void setClana(List<Clan> clanovi) {
        this.clanovi = clanovi;
    }
	
	
	public void addKoreografiju(Clan tempClan) {

        if (clanovi == null) {
        	clanovi = new ArrayList<>();
        }

        clanovi.add(tempClan);
    }
	
	public void setNastupe(List<Nastup> nastupi) {
        this.nastupi = nastupi;
    }
	
	
	public void addNastupe(Nastup tempNastup) {

        if (nastupi == null) {
        	nastupi = new ArrayList<>();
        }

        nastupi.add(tempNastup);
    }
	
}
