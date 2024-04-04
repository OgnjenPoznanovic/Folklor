package entity_dto;

import java.util.List;

import com.eproject.folklor.markovic.entity.Clan;

public class ClanClanarinaForm {

private List<Clan> clanovi;
	
	private List<Integer> clanarina_id;
	
	private List<Boolean> platio;

	public List<Clan> getClanovi() {
		return clanovi;
	}

	public void setClanovi(List<Clan> clanovi) {
		this.clanovi = clanovi;
	}

	public List<Integer> getClanarina_id() {
		return clanarina_id;
	}

	public void setClanarina_id(List<Integer> clanarina_id) {
		this.clanarina_id = clanarina_id;
	}

	public List<Boolean> getPlatio() {
		return platio;
	}

	public void setPlatio(List<Boolean> platio) {
		this.platio = platio;
	}
	
	
	
}
