package com.solvd.buildingCompany.machines;

public enum MachineType {

	Excavator("Excavator", "This machine is used to excave");
	//Should be enum? I doubt it because of id
	private long id;
	private String description;
	private String name;
	
	private MachineType(String name, String description) {
		this.name=name;
		this.description=description;
	}
	
	private  MachineType(long id, String name, String description) {
		this.name=name;
		this.description=description;
		this.id=id;
	}
}
