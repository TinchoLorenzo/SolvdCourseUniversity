package com.solvd.buildingCompany.model.persons;

public class Client {

	private String name;
	private String email;
	private long id;
	
	public Client() {
		// TODO Auto-generated constructor stub
	}

	public Client(String name, String email, long id) {
		super();
		this.name = name;
		this.email = email;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
