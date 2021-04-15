package com.solvd.UniversityMvn.connections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyRunnable implements Runnable{

	private final static Logger LOGGER = LogManager.getLogger(MyRunnable.class);

	public MyRunnable() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		Connection c;
		try {
			c = ConnectionPool.getInstance().getConnection();
			LOGGER.info("Thread "+ Thread.currentThread().getId() + " is running");
			c.execute("insert a");
			c.execute("insert b");
			
			ConnectionPool.getInstance().releaseConnection(c);
		} catch (InterruptedException e) {
			LOGGER.info(e.getMessage());
		}
		
	}
}
