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
import jakarta.persistence.Table;

@Entity
@Table(name="igrac")
public class Clan {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="clan_id")
	private int clan_id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="enabled")
	private Boolean enabled;
	
	@Column(name="ime")
	private String ime;
	
	@Column(name="prezime")
	private String prezime;
	
	@Column(name="godiste")
	private int godiste;
	
	@Column(name="pol")
	private String pol;
	
	@Column(name="goidna_upisa")
	private int goidna_upisa;
	
	@Column(name="email")
	private String email;
	
	@Column(name="telefon")
	private String telefon;
	
	@Column(name="zanimanje")
	private String zanimanje;
	
	@ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="pripada",
			joinColumns=@JoinColumn(name="igrac_id"),
			inverseJoinColumns=@JoinColumn(name="ansambl_id"))
	private List<Ansambl> ansambli;
	
	
	@ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="placene_obaveze",
			joinColumns=@JoinColumn(name="igrac_id"),
			inverseJoinColumns=@JoinColumn(name="clanarina_id"))
	private List<Clanarina> clanarina;
	
	
	@ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="prisustvo",
			joinColumns=@JoinColumn(name="igrac_id"),
			inverseJoinColumns=@JoinColumn(name="proba_id"))
	private List<Proba> probe;
	
	
	@ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="nastupa",
			joinColumns=@JoinColumn(name="igrac_id"),
			inverseJoinColumns=@JoinColumn(name="koreografija_id"))
	private List<Koreografija> koreografije;
	

	public Clan() {
	}

	public Clan(String username, String password, Boolean enabled, String ime, String prezime, int godiste, String pol, int goidna_upisa,
			String email, String telefon, String zanimanje) {
		
		this.username = username;
		this.password = password;
		this.enabled=enabled;
		this.ime = ime;
		this.prezime = prezime;
		this.godiste = godiste;
		this.pol = pol;
		this.goidna_upisa = goidna_upisa;
		this.email = email;
		this.telefon = telefon;
		this.zanimanje = zanimanje;
	}

	
	
	public int getClan_id() {
		return clan_id;
	}

	public void setClan_id(int clan_id) {
		this.clan_id = clan_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public int getGodiste() {
		return godiste;
	}

	public void setGodiste(int godiste) {
		this.godiste = godiste;
	}

	public String getPol() {
		return pol;
	}

	public void setPol(String pol) {
		this.pol = pol;
	}

	public int getGoidna_upisa() {
		return goidna_upisa;
	}

	public void setGoidna_upisa(int goidna_upisa) {
		this.goidna_upisa = goidna_upisa;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getZanimanje() {
		return zanimanje;
	}

	public void setZanimanje(String zanimanje) {
		this.zanimanje = zanimanje;
	}
	
	public void setAnsambl(List<Ansambl> ansambli) {
        this.ansambli = ansambli;
    }
	
	
	public void addAnsambl(Ansambl tempAnsambl) {

        if (ansambli == null) {
        	ansambli = new ArrayList<>();
        }

        ansambli.add(tempAnsambl);
    }
	
	
	public void setClanarinu(List<Clanarina> clanarina) {
        this.clanarina = clanarina;
    }
	
	
	public void addClanarinu(Clanarina tempClanarina) {

        if (clanarina == null) {
        	clanarina = new ArrayList<>();
        }

        clanarina.add(tempClanarina);
    }
	
	
	public void setProbu(List<Proba> probe) {
        this.probe = probe;
    }
	
	
	public void addProba(Proba tempProba) {

        if (probe == null) {
        	probe = new ArrayList<>();
        }

        probe.add(tempProba);
    }
	
	public void setKoreografiju(List<Koreografija> koreografije) {
        this.koreografije = koreografije;
    }
	
	
	public void addKoreografiju(Koreografija tempKoreografija) {

        if (koreografije == null) {
        	koreografije = new ArrayList<>();
        }

        koreografije.add(tempKoreografija);
    }
	
	
	
}
