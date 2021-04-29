package com.solvd.buildingCompany.dao.jdbc.realization.services;

import com.solvd.buildingCompany.dao.jdbc.realization.persons.ClientDAO;
import com.solvd.buildingCompany.dao.jdbc.realization.projects.ContractDAO;
import com.solvd.buildingCompany.dao.jdbc.realization.projects.ProjectDAO;
import com.solvd.buildingCompany.model.projects.Contract;
import com.solvd.buildingCompany.model.projects.Project;

public class ProjectsServices {

	public ProjectsServices() {
		// TODO Auto-generated constructor stub
	}
	
	public Contract getContractById(long id) {
		ContractDAO cd = new ContractDAO();
		Contract c = cd.getById(id);
		ClientDAO clientdao = new ClientDAO();
		c.setClient(clientdao.getById(c.getClient().getId()));
		c.setProject(this.getProjectById(c.getProject().getId()));
	}
	
	public Project getProjectById(long id) {
		ProjectDAO pd= new ProjectDAO();
		Project p = pd.getById(id);
		LocationServices ls = new LocationServices();
		p.setLocation(ls.getLocationById(p.getLocation().getId()));
		return p;
	}
}
