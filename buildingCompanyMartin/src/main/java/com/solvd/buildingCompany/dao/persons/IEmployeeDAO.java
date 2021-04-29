package com.solvd.buildingCompany.dao.persons;

import com.solvd.buildingCompany.dao.jdbc.realization.IEntityDAO;
import com.solvd.buildingCompany.model.persons.Employee;

public interface IEmployeeDAO extends IEntityDAO<Employee>{

	public Employee getEmployeeByLicenseID(long id);
}
