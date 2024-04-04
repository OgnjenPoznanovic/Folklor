package entity_dto;



public class AnsamblClanarinaDTO {

	private int clanarina_id;
	
	private String naziv;
	
	private String mesec;
	
	

	private String godina;
		
	private int cena;

	public AnsamblClanarinaDTO() {
		
	}

	public AnsamblClanarinaDTO(String naziv, String mesec, String godina, int cena) {
	
		this.naziv = naziv;
		this.mesec = mesec;
		this.godina = godina;
		this.cena = cena;
	}
	
	

	public AnsamblClanarinaDTO(int clanarina_id, String naziv, String mesec, String godina, int cena) {
	
		this.clanarina_id = clanarina_id;
		this.naziv = naziv;
		this.mesec = mesec;
		this.godina = godina;
		this.cena = cena;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
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
	
	public int getClanarina_id() {
		return clanarina_id;
	}

	public void setClanarina_id(int clanarina_id) {
		this.clanarina_id = clanarina_id;
	}
	
}
