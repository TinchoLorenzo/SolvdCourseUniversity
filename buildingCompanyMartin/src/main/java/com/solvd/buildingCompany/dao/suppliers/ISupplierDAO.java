package com.solvd.buildingCompany.dao.suppliers;

import java.util.List;

import com.solvd.buildingCompany.dao.jdbc.realization.IEntityDAO;
import com.solvd.buildingCompany.model.suppliers.*;

public interface ISupplierDAO extends IEntityDAO<Supplier>{

	public List<Material> getMaterialsBySupplierId(long id);
	public List<Supplier> getSuppliersByLocationId(long id);
}
