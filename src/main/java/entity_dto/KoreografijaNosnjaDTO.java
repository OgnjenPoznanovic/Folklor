package entity_dto;

public class KoreografijaNosnjaDTO {
	
	private int nosnja_id;
	
	private  String koreografija_naziv;	
	
	private String pol;
	
	private String oblast;
	
	private String deo_tela;
	
	private String velicina;
	
	public int getNosnja_id() {
		return nosnja_id;
	}

	public void setNosnja_id(int nosnja_id) {
		this.nosnja_id = nosnja_id;
	}

	private String oznaka;

	public KoreografijaNosnjaDTO() {
		super();
	}

	public String getKoreografija_naziv() {
		return koreografija_naziv;
	}

	public void setKoreografija_naziv(String koreografija_naziv) {
		this.koreografija_naziv = koreografija_naziv;
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
	
	
}
