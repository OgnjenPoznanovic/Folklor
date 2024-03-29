package entity_dto;

import java.util.List;

import com.eproject.folklor.markovic.entity.Clan;

public class ClanProbaForm {

	private List<Clan> clanovi;
	
	private List<Integer> proba_id;
	
	private List<Boolean> prisutan;
	
	

	public List<Clan> getClanovi() {
		return clanovi;
	}

	public void setClanovi(List<Clan> clanovi) {
		this.clanovi = clanovi;
	}
	

	public List<Integer> getProba_id() {
		return proba_id;
	}

	public void setProba_id(List<Integer> proba_id) {
		this.proba_id = proba_id;
	}

	public List<Boolean> getPrisutan() {
		return prisutan;
	}

	public void setPrisutan(List<Boolean> prisutan) {
		this.prisutan = prisutan;
	}
	
	
}
