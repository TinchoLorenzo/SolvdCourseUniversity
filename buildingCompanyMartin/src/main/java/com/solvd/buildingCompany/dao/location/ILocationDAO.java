package com.solvd.buildingCompany.dao.location;

import java.util.List;

import com.solvd.buildingCompany.dao.jdbc.realization.IEntityDAO;
import com.solvd.buildingCompany.model.location.Location;

public interface ILocationDAO extends IEntityDAO<Location>{

	public List<Location> getLocationsByCityId(long id);
}
