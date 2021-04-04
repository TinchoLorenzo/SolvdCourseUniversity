package com.solvd.UniversityMvn.buildings;

import com.solvd.UniversityMvn.employers.Department;

public class ExclusiveBuilding extends Building<ExclusiveRoom> {

	private Department owner;

	public ExclusiveBuilding() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param rooms
	 * @param location
	 * @param buildingId
	 */
	public ExclusiveBuilding(String location, long buildingId, Department owner) {
		super(location, buildingId);
		this.owner = owner;
	}

	public ExclusiveBuilding(Department owner) {
		// TODO Auto-generated constructor stub
		this.owner = owner;
	}

	public Department getOwner() {
		return owner;
	}

	@Override
	public void addRoom(ExclusiveRoom element) {
		// TODO Auto-generated method stub
		element.setOwner(owner);
		super.addRoom(element);
	}

	public void setOwner(Department owner) {
		this.owner = owner;
	}

}
