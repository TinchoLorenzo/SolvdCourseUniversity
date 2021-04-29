package com.solvd.buildingCompany.dao.jdbc.realization.suppliers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.solvd.buildingCompany.dao.jdbc.realization.AbstractDAO;
import com.solvd.buildingCompany.dao.suppliers.ISupplierDAO;
import com.solvd.buildingCompany.model.suppliers.Material;
import com.solvd.buildingCompany.model.suppliers.Supplier;

public class SupplierDAO extends AbstractDAO implements ISupplierDAO {

	public SupplierDAO() {
	}
	
	@Override
	public void save(Supplier data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Supplier data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Supplier getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Supplier data) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Material> getMaterialsBySupplierId(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Supplier> getSuppliersByLocationId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
