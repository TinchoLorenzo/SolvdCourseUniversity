package com.solvd.buildingCompany.model.location;

public class City {

	private long id;
	private String name;
	private String abbreviation;
	private int zipCode;
	private Country country;
	
	public City() {
		// TODO Auto-generated constructor stub
	}

	public City(long id, String name, String abbreviation, int zipCode, Country country) {
		super();
		this.id = id;
		this.name = name;
		this.abbreviation = abbreviation;
		this.zipCode = zipCode;
		this.country = country;
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

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
}
