package com.solvd.buildingCompany.model.projects;

import java.util.Date;
import java.util.List;

import com.solvd.buildingCompany.model.persons.Client;

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
	
	

	public Contract(long id, Date begin_date, Date end_date, double prize, Client client, Project project,
			List<Payment> payments) {
		super();
		this.id = id;
		this.begin_date = begin_date;
		this.end_date = end_date;
		this.prize = prize;
		this.client = client;
		this.project = project;
		this.payments = payments;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getBegin_date() {
		return begin_date;
	}

	public void setBegin_date(Date begin_date) {
		this.begin_date = begin_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public double getPrize() {
		return prize;
	}

	public void setPrize(double prize) {
		this.prize = prize;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}
	
	
	
}
