	package com.solvd.UniversityMvn.members;

import java.util.Date;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.UniversityMvn.exceptions.NotPositiveSalaryException;

public abstract class Employee extends UniversityMember {

	private final static Logger LOGGER = LogManager.getLogger(Employee.class);

	private double salary;
	private long jobId;

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param id
	 * @param birthDate
	 * @param email
	 */
	public Employee(String name, long id, Date birthDate, String email, double salary, long jobId) {
		super(name, id, birthDate, email);
		try {
			setSalary(salary);
		} catch (NotPositiveSalaryException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
		}
		this.jobId = jobId;
		// TODO Auto-generated constructor stub
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) throws NotPositiveSalaryException {
		if (salary <= 0.0)
			throw new NotPositiveSalaryException();
		this.salary = salary;
	}

	public long getJobId() {
		return jobId;
	}

	public void setJobId(long jobId) {
		this.jobId = jobId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(jobId, salary);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Employee))
			return false;
		Employee other = (Employee) obj;
		return jobId == other.jobId && Double.doubleToLongBits(salary) == Double.doubleToLongBits(other.salary);
	}

}
