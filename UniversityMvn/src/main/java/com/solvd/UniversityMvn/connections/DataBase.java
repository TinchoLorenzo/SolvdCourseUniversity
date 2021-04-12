package com.solvd.UniversityMvn.connections;

public class DataBase {

	private static String a  = "Hi! I'm a. My data is: ";
	private static String b = "Hi! I'm b. My data is: ";
	
	public DataBase() {
		// TODO Auto-generated constructor stub
	}
	
	public void insert(String key, String data) {
		synchronized (this) {
			if (key.equals("a")) {
				a= "Hi! I'm a. My data is: "+ data ;
			}
			if (key.equals("b")) {
				b= "Hi! I'm a. My data is: "+ data ;
			}
		}
	}
	
	public String select(String key) {
		if (key.equals("a")) {
			return a;
		}
		if (key.equals("b")) {
			return b;
		}
		return "";
	}
	
}
