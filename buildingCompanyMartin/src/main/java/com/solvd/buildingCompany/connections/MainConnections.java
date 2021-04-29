package com.solvd.buildingCompany.connections;

import java.util.ArrayList;
import java.util.List;

public class MainConnections {
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Thread> threads = new ArrayList<Thread>();
		for(int i=0; i<10; i++) {
			Thread object = new Thread(new MyRunnable());
			threads.add(object);
			object.start();	
		}
	}
	
}
