package entity_dto;

public class AnsamblDTO {

	private String naziv;

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	public AnsamblDTO() {
		
	}

	public AnsamblDTO(String naziv) {
		super();
		this.naziv = naziv;
	}
	
	
}
