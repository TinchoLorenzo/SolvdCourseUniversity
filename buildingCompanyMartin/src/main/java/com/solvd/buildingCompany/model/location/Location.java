package com.solvd.buildingCompany.model.location;

public class Location {

	private long id;
	private String name;
	private City city;
	
	public Location() {
		// TODO Auto-generated constructor stub
	}

	public Location(long id, String name, City city) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	
}
