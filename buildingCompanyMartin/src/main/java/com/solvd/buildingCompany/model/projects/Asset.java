package com.solvd.buildingCompany.model.projects;

public class Asset {

	private long id;
	private int weeklyHours;
	
	public Asset() {
		// TODO Auto-generated constructor stub
	}

	public Asset(long id, int weeklyHours) {
		super();
		this.id = id;
		this.weeklyHours = weeklyHours;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getWeeklyHours() {
		return weeklyHours;
	}

	public void setWeeklyHours(int weeklyHours) {
		this.weeklyHours = weeklyHours;
	}
	
}
