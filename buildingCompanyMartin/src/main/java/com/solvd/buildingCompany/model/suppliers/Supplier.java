package com.solvd.buildingCompany.model.suppliers;

import java.util.List;

import com.solvd.buildingCompany.model.location.Location;

public class Supplier {

	private long id;
	private String email;
	private String name;
	private Location location;
	private List<Material> materials;
	
	public Supplier() {
		// TODO Auto-generated constructor stub
	}

	public Supplier(long id, String email, String name, Location location, List<Material> materials) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.location = location;
		this.materials = materials;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<Material> getMaterials() {
		return materials;
	}

	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}
	
	
	
}
