package entity_dto;

public class AnsamblProbaDTO {

	private int proba_id;
	
	private String naziv;
	
	private String dan;
	
	private String mesec;
	
	private String godina;
	
	private String pocetak;
	
	
	

	public AnsamblProbaDTO() {
		
	}
	

	public AnsamblProbaDTO(int proba_id, String naziv, String dan, String mesec, String godina, String pocetak) {
		this.proba_id = proba_id;
		this.naziv = naziv;
		this.dan = dan;
		this.mesec = mesec;
		this.godina = godina;
		this.pocetak = pocetak;
	}








	public int getProba_id() {
		return proba_id;
	}



	public void setProba_id(int proba_id) {
		this.proba_id = proba_id;
	}



	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
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

	public String getPocetak() {
		return pocetak;
	}

	public void setPocetak(String pocetak) {
		this.pocetak = pocetak;
	}
	
	
	
	
	
	
}
