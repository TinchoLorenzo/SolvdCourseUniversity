package com.solvd.buildingCompany.model.machines;

import java.util.Date;

import com.solvd.buildingCompany.model.projects.Asset;

public class Machine extends Asset{
	private long id;
	private Date buyingDate;
	private double number;
	private MachineType type;
	private Company company;

	public Machine() {
		// TODO Auto-generated constructor stub
	}

	
	public Machine(long id, Date buyingDate, double number, MachineType type, Company company) {
		super();
		this.id = id;
		this.buyingDate = buyingDate;
		this.number = number;
		this.type = type;
		this.company = company;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getBuyingDate() {
		return buyingDate;
	}

	public void setBuyingDate(Date buyingDate) {
		this.buyingDate = buyingDate;
	}

	public double getNumber() {
		return number;
	}

	public void setNumber(double number) {
		this.number = number;
	}

	public MachineType getType() {
		return type;
	}

	public void setType(MachineType type) {
		this.type = type;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	
}
