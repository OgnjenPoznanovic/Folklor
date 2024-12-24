package com.eproject.folklor.markovic.entity;

import java.util.List;

public class PDF {
	
	private String mesto;
	
	private String uslov;
	
	public PDF() {
		
	}

	public PDF(String mesto, String uslov) {
		this.mesto = mesto;
		this.uslov = uslov;
	}
	
	
	public String getMesto() {
		return mesto;
	}

	public void setMesto(String mesto) {
		this.mesto = mesto;
	}

	public String getUslov() {
		return uslov;
	}

	public void setUslov(String uslov) {
		this.uslov = uslov;
	}
	
	

}
