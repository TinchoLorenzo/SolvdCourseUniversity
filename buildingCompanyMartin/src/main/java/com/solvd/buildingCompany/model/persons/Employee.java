package com.solvd.buildingCompany.model.persons;

import com.solvd.buildingCompany.model.projects.Asset;

public class Employee extends Asset{

	private String name;
	private String email;
	private long id;
	private double salary;
	private License license;
	
	public Employee(double salary, License license) {
		super();
		this.salary = salary;
		this.license=license;
	}

	public Employee(String name, String email, long id, double salary, License license) {
		super();
		this.name = name;
		this.email = email;
		this.id = id;
		this.salary = salary;
		this.license = license;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public License getLicense() {
		return license;
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

	public void setLicense(License license) {
		this.license = license;
	}
	
	
	
	
}
