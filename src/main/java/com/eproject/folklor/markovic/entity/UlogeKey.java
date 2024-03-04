package com.eproject.folklor.markovic.entity;

import java.io.Serializable;
import java.util.Objects;


public class UlogeKey implements Serializable {
	
	
	private String username;
	
	private String uloga;
	
	
	public UlogeKey() {
		
	}


	public UlogeKey(String username, String uloga) {
		this.username = username;
		this.uloga = uloga;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getUloga() {
		return uloga;
	}


	public void setUloga(String uloga) {
		this.uloga = uloga;
	}
	
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UlogeKey ulogeKey = (UlogeKey) o;
        return Objects.equals(username, ulogeKey.username) &&
               Objects.equals(uloga, ulogeKey.uloga);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, uloga);
    }
	
	
	

}
