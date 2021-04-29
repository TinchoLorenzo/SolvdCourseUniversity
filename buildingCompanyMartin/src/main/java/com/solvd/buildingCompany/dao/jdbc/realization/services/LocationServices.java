package com.solvd.buildingCompany.dao.jdbc.realization.services;

import com.solvd.buildingCompany.dao.jdbc.realization.AbstractDAO;
import com.solvd.buildingCompany.dao.jdbc.realization.location.CityDAO;
import com.solvd.buildingCompany.dao.jdbc.realization.location.CountryDAO;
import com.solvd.buildingCompany.dao.jdbc.realization.location.LocationDAO;
import com.solvd.buildingCompany.model.location.City;
import com.solvd.buildingCompany.model.location.Location;

public class LocationServices extends AbstractDAO{

	public LocationServices() {
		// TODO Auto-generated constructor stub
	}
	
	public Location getLocationById(long id) {
		LocationDAO ld = new LocationDAO();
		Location l = ld.getById(id);
		l.setCity(this.getCityById(l.getCity().getId()));
		return l;
	}
	
	public City getCityById(long id) {
		CityDAO cid = new CityDAO();
		CountryDAO cod = new CountryDAO();
		City city = cid.getById(id);
		city.setCountry(cod.getById(city.getCountry().getId()));
		return city;
	}
	
}
