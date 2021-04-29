package com.solvd.buildingCompany.dao.jdbc.realization;

import com.solvd.buildingCompany.connections.ConnectionPool;

public abstract class AbstractDAO {

	protected ConnectionPool cp;
	
	public AbstractDAO() {
		// TODO Auto-generated constructor stub
		cp = ConnectionPool.getInstance();
	}
	
	
}
