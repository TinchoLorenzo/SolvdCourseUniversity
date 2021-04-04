package com.solvd.UniversityMvn.buildings;

import com.solvd.UniversityMvn.employers.Department;

public class ExclusiveRoom extends Room {

	private Department owner;

	public ExclusiveRoom() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param capacity
	 * @param roomId
	 */
	public ExclusiveRoom(int capacity, long roomId, Department owner) {
		super(capacity, roomId);
		this.owner = owner;
		// TODO Auto-generated constructor stub
	}

	public ExclusiveRoom(Department owner) {
		this.owner = owner;
	}

	public Department getOwner() {
		return owner;
	}

	public void setOwner(Department owner) {
		this.owner = owner;
	}

}
