package main.java.com.solvd.university.model.employers;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import main.java.com.solvd.university.interfaces.IReport;
import main.java.com.solvd.university.model.buildings.Building;
import main.java.com.solvd.university.model.buildings.Room;
import main.java.com.solvd.university.model.members.Employee;



public abstract class Employer implements IReport {

	private String name;
	private long id;
	private List<Employee> employees;
	private List<Building> buildings;
	private String reportData;
	
	public Employer() {
		// TODO Auto-generated constructor stub
		employees = new ArrayList<Employee>();
		buildings = new ArrayList<Building>();
	}
	
	/**
	 * @param name
	 * @param id
	 */
	public Employer(String name, long id) {
		super();
		this.name = name;
		this.id = id;
		employees = new ArrayList<Employee>();
		buildings = new ArrayList<Building>();
	}

	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	public List<Building> getBuildings() {
		return buildings;
	}
	public void setBuildings(List<Building> buildings) {
		this.buildings = buildings;
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
	public String getReportData() {
		return reportData;
	}
	public void setReportData(String reportData) {
		this.reportData = reportData;
	}

	@Override
	public int hashCode() {
		return Objects.hash(buildings, employees);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Employer))
			return false;
		if (obj.hashCode() != this.hashCode())
			return false;
		Employer other = (Employer) obj;
		return Objects.equals(buildings, other.buildings) && Objects.equals(employees, other.employees);
	}
	
	 public boolean addBuilding(Building e) {
		return buildings.add(e);
	}
	 

	public boolean containsBuilding(Object o) {
		return buildings.contains(o);
	}

	public boolean removeBuilding(Object o) {
		return buildings.remove(o);
	}
	
	public boolean containsEmployee(Object o) {
		return employees.contains(o);
	}

	public boolean addEmployee(Employee e) {
		return employees.add(e);
	}

	public boolean removeEmployee(Object o) {
		return employees.remove(o);
	}

	@Override
	public String generateReport() {
		// TODO Auto-generated method stub
		return (this.name + ", id " + id + " reports about payment: " + reportData);
	}
	
	
}
