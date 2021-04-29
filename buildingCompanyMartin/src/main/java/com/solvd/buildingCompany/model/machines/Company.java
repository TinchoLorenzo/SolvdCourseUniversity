package com.solvd.buildingCompany.model.machines;

public class Company {

	private long id;
	private String name;
	
	public Company() {
		// TODO Auto-generated constructor stub
	}

	public Company(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
