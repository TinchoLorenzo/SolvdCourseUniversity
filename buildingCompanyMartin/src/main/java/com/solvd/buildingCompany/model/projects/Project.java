package com.solvd.buildingCompany.model.projects;

import java.util.Date;
import java.util.List;

import com.solvd.buildingCompany.model.location.Location;

public class Project {

	private long id;
	private Date beginDate;
	private String description;
	private Date expectedEndDate;
	private Location location;
	private List<Asset> assets;
	
	public Project() {
		// TODO Auto-generated constructor stub
	}

	public Project(long id, Date beginDate, String description, Date expectedEndDate, Location location) {
		super();
		this.id = id;
		this.beginDate = beginDate;
		this.description = description;
		this.expectedEndDate = expectedEndDate;
		this.location = location;
	}


	public Project(long id, Date beginDate, String description, Date expectedEndDate, Location location,
			List<Asset> assets) {
		super();
		this.id = id;
		this.beginDate = beginDate;
		this.description = description;
		this.expectedEndDate = expectedEndDate;
		this.location = location;
		this.assets = assets;
	}


	public List<Asset> getAssets() {
		return assets;
	}


	public void setAssets(List<Asset> assets) {
		this.assets = assets;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getExpectedEndDate() {
		return expectedEndDate;
	}

	public void setExpectedEndDate(Date expectedEndDate) {
		this.expectedEndDate = expectedEndDate;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location=location;
	}
	
	
}
