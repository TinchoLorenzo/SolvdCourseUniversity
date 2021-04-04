package com.solvd.UniversityMvn.employers;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.UniversityMvn.courses.Course;
import com.solvd.UniversityMvn.interfaces.IPay;
import com.solvd.UniversityMvn.members.Employee;

public class Department extends Employer implements IPay {

	private final static Logger LOGGER = LogManager.getLogger(Department.class);
	private List<Course> courses;

	public Department() {
		// TODO Auto-generated constructor stub
		courses = new ArrayList<Course>();
	}

	/**
	 * @param name
	 * @param id
	 */
	public Department(String name, long id) {
		super(name, id);
		// TODO Auto-generated constructor stub
		courses = new ArrayList<Course>();
	}

	public boolean containsCourse(Object o) {
		return courses.contains(o);
	}

	public boolean addCourse(Course e) {
		return courses.add(e);
	}

	public boolean removeCourse(Object o) {
		return courses.remove(o);
	}

	@Override
	public void pay(Employee employee) {
		// Should execute bank transaction
		// Should send email instead of sysout
		this.setReportData("The employee " + employee.getName() + ", id " + employee.getId() + " has reciebed "
				+ employee.getSalary() * 0.9 + " from " + this.getName() + " and " + employee.getSalary() * 0.1
				+ " were withheld for taxes");
		LOGGER.debug(generateReport());
	}
}
