package com.solvd.buildingCompany.dao.location;

import java.util.List;

import com.solvd.buildingCompany.dao.jdbc.realization.IEntityDAO;
import com.solvd.buildingCompany.model.location.City;

public interface ICityDAO extends IEntityDAO<City>{

	public List<City> getCitiesByCountryId(long id);
}
