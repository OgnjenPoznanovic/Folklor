package entity_dto;

public class ClanDTO {
	
	private String ime;
	
	private String prezime;
	
	public ClanDTO() {
		
	}

	public ClanDTO(String ime, String prezime) {
	
		this.ime = ime;
		this.prezime = prezime;
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
