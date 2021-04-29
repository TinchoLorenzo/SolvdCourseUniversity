package com.solvd.buildingCompany.connections;


import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MyRunnable implements Runnable{

	private final static Logger LOGGER = LogManager.getLogger(MyRunnable.class);

	public MyRunnable() {
		// TODO Auto-generated constructor stub
	}
	
	public void run() {
		// TODO Auto-generated method stub
		
		Connection c;
		try {
			c = ConnectionPool.getInstance().getConnection();
			LOGGER.info("Thread "+ Thread.currentThread().getId() + " is running");
			ConnectionPool.getInstance().releaseConnection(c);
		} catch (InterruptedException e) {
			LOGGER.error(e.getMessage());
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
		}

	}
}
