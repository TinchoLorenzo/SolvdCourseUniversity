package com.solvd.UniversityMvn.connections;

public class Connection {
	
	private int number;
	private DataBase dataBase;
	
	public Connection() {
		// TODO Auto-generated constructor stub
		this.dataBase = new DataBase();
		this.number=(int) Math.floor(Math.random()*1000+1);
	}
	
	public Connection(int number) {
		//emulates connection to database
		this.dataBase = new DataBase();
		this.number=number;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Connection other = (Connection) obj;
		if (number != other.number)
			return false;
		return true;
	}

	public String execute(String statement){
		if(statement.toLowerCase().contains("insert")) {
			//Here we should parse the statement
			dataBase.insert("a", "executing an insert");
			return "Insert complete";
		}else
		{
			return "Result: " + dataBase.select("a"); 
		}
	}
	
	public int getNumber() {
		return number;
	}
}
