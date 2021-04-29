package com.solvd.buildingCompany.model.projects;

import java.util.Date;

public class Payment {

	private long id;
	private Date date;
	private double amount;
	private String serialNumber;
	private long contract_id;
	
	public long getContract_id() {
		return contract_id;
	}


	public void setContract_id(long contract_id) {
		this.contract_id = contract_id;
	}


	public Payment() {
		// TODO Auto-generated constructor stub
	}

	
	public Payment(long id, Date date, double amount, String serialNumber) {
		super();
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.serialNumber = serialNumber;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	
	
}
