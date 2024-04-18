package entity_dto;

import java.util.List;

import com.eproject.folklor.markovic.entity.Koreografija;

public class NastupKoreografijeDTO {

	private List<Koreografija> koreografije;
	
	private List<Integer> nastup_id;
	
	private List<Boolean> repertoar;

	public List<Koreografija> getKoreografije() {
		return koreografije;
	}

	public void setKoreografije(List<Koreografija> koreografije) {
		this.koreografije = koreografije;
	}

	public List<Integer> getNastup_id() {
		return nastup_id;
	}

	public void setNastup_id(List<Integer> nastup_id) {
		this.nastup_id = nastup_id;
	}

	public List<Boolean> getRepertoar() {
		return repertoar;
	}

	public void setRepertoar(List<Boolean> repertoar) {
		this.repertoar = repertoar;
	}
	
	
	
}
