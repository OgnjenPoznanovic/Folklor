package com.eproject.folklor.markovic.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
@Table(name="nosnja")
public class Nosnja {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="nosnja_id")
	private int nosnja_id;
	
	@Column(name="pol")
	private String pol;
	
	@Column(name="oblast")
	private String oblast;
	
	@Column(name="deo_tela")
	private String deo_tela;
	
	@Column(name="velicina")
	private String velicina;
	
	@Column(name="oznaka")
	private String oznaka;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="koreografija_id")
	private Koreografija koreografija_id;

	public Nosnja() {
		
	}

	public Nosnja(String pol, String oblast, String deo_tela, String velicina, String oznaka) {		
		this.pol = pol;
		this.oblast = oblast;
		this.deo_tela = deo_tela;
		this.velicina = velicina;
		this.oznaka = oznaka;
	}

	public int getNosnja_id() {
		return nosnja_id;
	}

	public void setNosnja_id(int nosnja_id) {
		this.nosnja_id = nosnja_id;
	}

	public String getPol() {
		return pol;
	}

	public void setPol(String pol) {
		this.pol = pol;
	}

	public String getOblast() {
		return oblast;
	}

	public void setOblast(String oblast) {
		this.oblast = oblast;
	}

	public String getDeo_tela() {
		return deo_tela;
	}

	public void setDeo_tela(String deo_tela) {
		this.deo_tela = deo_tela;
	}

	public String getVelicina() {
		return velicina;
	}

	public void setVelicina(String velicina) {
		this.velicina = velicina;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public Koreografija getKoreografija_id() {
		return koreografija_id;
	}

	public void setKoreografija_id(Koreografija koreografija_id) {
		this.koreografija_id = koreografija_id;
	}


	
	
	
}
