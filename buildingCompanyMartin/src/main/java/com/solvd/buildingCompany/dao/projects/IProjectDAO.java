package com.solvd.buildingCompany.dao.projects;

import java.util.List;

import com.solvd.buildingCompany.dao.jdbc.realization.IEntityDAO;
import com.solvd.buildingCompany.model.projects.Asset;
import com.solvd.buildingCompany.model.projects.Contract;
import com.solvd.buildingCompany.model.projects.Project;

public interface IProjectDAO extends IEntityDAO<Project>{

	public List<Project> getProjectByLocationId(long id);
}
