package com.solvd.buildingCompany.model.persons;

import java.util.Date;

public class License {
	
	private long id;
	private String number;
	private Date expDate;
	
	public License() {
		// TODO Auto-generated constructor stub
	}

	public License(long id, String number, Date expDate) {
		super();
		this.id = id;
		this.number = number;
		this.expDate = expDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}
	
	
}
