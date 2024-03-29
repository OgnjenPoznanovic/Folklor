package entity_dto;

import java.util.List;

import jakarta.persistence.Entity;

@Entity
public class AnsamblClanoviDTO {
	
	private List<String> naziv;
	
	private String korisnicko_ime;
	
	private String ime;
	
	private String prezime;

	public AnsamblClanoviDTO() {
		
	}

	public AnsamblClanoviDTO(List<String> naziv, String korisnicko_ime, String ime, String prezime) {
	
		this.naziv = naziv;
		this.korisnicko_ime = korisnicko_ime;
		this.ime = ime;
		this.prezime = prezime;
	}

	public List<String> getNaziv() {
		return naziv;
	}

	public void setNaziv(List<String> naziv) {
		this.naziv = naziv;
	}

	public String getKorisnicko_ime() {
		return korisnicko_ime;
	}

	public void setKorisnicko_ime(String korisnicko_ime) {
		this.korisnicko_ime = korisnicko_ime;
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

	
	
	
	
	
	
	

}
