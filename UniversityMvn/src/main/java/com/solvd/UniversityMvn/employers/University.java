package com.solvd.UniversityMvn.employers;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.UniversityMvn.interfaces.IPay;
import com.solvd.UniversityMvn.members.Employee;

public class University extends Employer implements IPay {

	private final static Logger LOGGER = LogManager.getLogger(University.class);

	private List<Department> departments;

	public University() {
		// TODO Auto-generated constructor stub
		departments = new ArrayList<Department>();
	}

	/**
	 * @param name
	 * @param id
	 */
	public University(String name, long id) {
		super(name, id);
		// TODO Auto-generated constructor stub
		departments = new ArrayList<Department>();
	}

	public University(List<Department> departments) {
		this.departments = departments;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public boolean containsDepartment(Object o) {
		return departments.contains(o);
	}

	public boolean addDepartment(Department e) {
		return departments.add(e);
	}

	public boolean removeDepartment(Object o) {
		return departments.remove(o);
	}

	@Override
	public void pay(Employee employee) {
		// TODO Auto-generated method stub
		// Should execute bank transaction
		// Should send email instead of sysout
		this.setReportData("The employee " + employee.getName() + ", id " + employee.getId() + " has reciebed "
				+ employee.getSalary() + " from " + this.getName());
		LOGGER.debug(generateReport());
	}

}
