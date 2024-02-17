package com.eproject.folklor.markovic.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="ansambl")
public class Ansambl {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ansambl_id")
	private int ansambl_id;
	
	@Column(name="naziv")
	private String naziv;
	
	
	@OneToMany(mappedBy="ansambl_id", 
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.DETACH, CascadeType.REFRESH})
	private List<Clanarina> clanarine;
	
	@OneToMany(mappedBy="ansambl_id", 
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.DETACH, CascadeType.REFRESH})
	private List<Proba> probe;
	
	
	@ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="pripada",
			joinColumns=@JoinColumn(name="ansambl_id"),
			inverseJoinColumns=@JoinColumn(name="igrac_id"))
	private List<Clan> clanovi;
	
	public Ansambl() {
		
	}
	

	public Ansambl(String naziv) {
		this.naziv = naziv;
	}



	public int getAnsambl_id() {
		return ansambl_id;
	}

	public void setAnsambl_id(int ansambl_id) {
		this.ansambl_id = ansambl_id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}


	public List<Clanarina> getClanarine() {
		return clanarine;
	}


	public void setClanarine(List<Clanarina> clanarine) {
		this.clanarine = clanarine;
	}


	public List<Proba> getProbe() {
		return probe;
	}


	public void setProbe(List<Proba> probe) {
		this.probe = probe;
	}
	
	
	public void addClanarinu(Clanarina tempClanarina) {
		
		if(clanarine == null) {
			clanarine = new ArrayList<>();
		}
		
		clanarine.add(tempClanarina);
		
		tempClanarina.setAnsambl_id(this);
	}
	
	public void addProbe(Proba tempProba) {
		
		if(probe == null) {
			probe = new ArrayList<>();
		}
		
		probe.add(tempProba);
		
		tempProba.setAnsambl_id(this);
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
