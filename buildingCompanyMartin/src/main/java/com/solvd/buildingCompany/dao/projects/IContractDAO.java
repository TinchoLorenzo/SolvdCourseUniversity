package com.solvd.buildingCompany.dao.projects;

import java.util.List;

import com.solvd.buildingCompany.dao.jdbc.realization.IEntityDAO;
import com.solvd.buildingCompany.model.projects.Contract;
import com.solvd.buildingCompany.model.projects.Payment;
import com.solvd.buildingCompany.model.projects.Project;

public interface IContractDAO extends IEntityDAO<Contract>{

	public List<Contract> getContractsByClientId(long id);
	public List<Contract> getContractsByProjectId(long id);
}
