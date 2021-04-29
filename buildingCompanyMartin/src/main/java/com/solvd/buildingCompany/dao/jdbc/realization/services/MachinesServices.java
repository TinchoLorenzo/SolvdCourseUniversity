package com.solvd.buildingCompany.dao.jdbc.realization.services;

import com.solvd.buildingCompany.dao.jdbc.realization.machines.CompanyDAO;
import com.solvd.buildingCompany.dao.jdbc.realization.machines.MachineDAO;
import com.solvd.buildingCompany.model.machines.Machine;

public class MachinesServices {

	public MachinesServices() {
		// TODO Auto-generated constructor stub
	}
	
	public Machine getMachineById(long id) {
		MachineDAO md= new MachineDAO();
		CompanyDAO cd = new CompanyDAO();
		
		Machine m = md.getById(id);
		m.setCompany(cd.getById(m.getCompany().getId()));
		return m;
	}
}
