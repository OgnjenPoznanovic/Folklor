package entity_dto;

public class ClanProbaDTO {

	private int proba_Id;
	
	private String ime;
	
	private String prezime;
	
	private Boolean prisutan;

	public ClanProbaDTO() {
		
	}

	public ClanProbaDTO(int proba_Id, String ime, String prezime, Boolean prisutan) {
		this.proba_Id = proba_Id;
		this.ime = ime;
		this.prezime = prezime;
		this.prisutan = prisutan;
	}
	
	public ClanProbaDTO(String ime, String prezime) {
		
		this.ime = ime;
		this.prezime = prezime;
	
	}
	

	public int getProba_Id() {
		return proba_Id;
	}

	public void setProba_Id(int proba_Id) {
		this.proba_Id = proba_Id;
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

	public Boolean getPrisutan() {
		return prisutan;
	}

	public void setPrisutan(Boolean prisutan) {
		this.prisutan = prisutan;
	}
	
	
	
	
	
}
