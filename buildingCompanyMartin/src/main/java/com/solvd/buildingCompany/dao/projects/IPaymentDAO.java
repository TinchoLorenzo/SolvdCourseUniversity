package com.solvd.buildingCompany.dao.projects;

import java.util.List;

import com.solvd.buildingCompany.dao.jdbc.realization.IEntityDAO;
import com.solvd.buildingCompany.model.projects.Payment;

public interface IPaymentDAO extends IEntityDAO<Payment>{

	public List<Payment> getPaymentsByContractId(long id);
}
