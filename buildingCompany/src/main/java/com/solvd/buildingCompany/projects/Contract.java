package com.solvd.buildingCompany.projects;

import java.util.Date;
import java.util.List;

import com.solvd.buildingCompany.persons.Client;

public class Contract {

	private long id;
	private Date begin_date;
	private Date end_date;
	private double prize;
	private Client client;
	private Project project;
	private List<Payment> payments;
	
	public Contract() {
		// TODO Auto-generated constructor stub
	}
	
}
