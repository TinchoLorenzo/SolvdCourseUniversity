package main.java.com.solvd.university.model.employers;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import main.java.com.solvd.university.interfaces.IPay;
import main.java.com.solvd.university.model.courses.Course;
import main.java.com.solvd.university.model.members.Employee;

public class Department extends Employer implements IPay{
	
	private final static Logger LOGGER = Logger.getLogger(Department.class);
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
		//Should execute bank transaction
		//Should send email instead of sysout
		this.setReportData("The employee " + employee.getName() + ", id " + employee.getId() +
							" has reciebed " + employee.getSalary() * 0.9 + " from " + this.getName() + 
							" and " + employee.getSalary() * 0.1 + " were withheld for taxes");
		LOGGER.debug(generateReport());
	}
}
