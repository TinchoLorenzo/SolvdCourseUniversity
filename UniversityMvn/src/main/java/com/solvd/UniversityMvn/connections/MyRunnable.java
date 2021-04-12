package com.solvd.UniversityMvn.connections;

public class MyRunnable implements Runnable{

	public MyRunnable() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		Connection c = ConnectionPool.getInstance().initConnection();
		
		c.execute("insert a");
		c.execute("insert b");
		c.close();
	}
}
