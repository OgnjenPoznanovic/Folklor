package entity_dto;

public class ClanSortDTO {
	
	private String kriterijum;
	
	private String vrsta;

	public ClanSortDTO() {
		
	}

	public ClanSortDTO(String kriterijum, String vrsta) {
		this.kriterijum = kriterijum;
		this.vrsta = vrsta;
	}

	public String getKriterijum() {
		return kriterijum;
	}

	public void setKriterijum(String kriterijum) {
		this.kriterijum = kriterijum;
	}

	public String getVrsta() {
		return vrsta;
	}

	public void setVrsta(String vrsta) {
		this.vrsta = vrsta;
	}
	
	
	
	
	

}
