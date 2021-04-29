package com.solvd.buildingCompany.model.machines;

public enum MachineType {

	EXCAVATOR(1, "Excavator", "This machine is used to excave"),
	PAVER(2,"Paver", "This machine is used to pave");
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public static MachineType getById(long id) {
		
		switch((int) id) {
			case 1: return EXCAVATOR;
			case 2: return PAVER;
			default: return null;
		}
			
	}
	
}
