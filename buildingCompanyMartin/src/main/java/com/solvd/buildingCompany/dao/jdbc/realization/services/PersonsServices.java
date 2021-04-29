package com.solvd.buildingCompany.dao.jdbc.realization.services;

import com.solvd.buildingCompany.dao.jdbc.realization.persons.EmployeeDAO;
import com.solvd.buildingCompany.dao.jdbc.realization.persons.LicenseDAO;
import com.solvd.buildingCompany.model.persons.Employee;

public class PersonsServices {

	public PersonsServices() {
		// TODO Auto-generated constructor stub
	}
	
	public Employee getEmployeeById(long id) {
		EmployeeDAO ed = new EmployeeDAO();
		LicenseDAO ld = new LicenseDAO();
		Employee e = ed.getById(id);
		e.setLicense(ld.getById(e.getLicense().getId()));
		return e;
	}
}
