package com.solvd.buildingCompany.connections;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ConnectionPool {

	private static final int MAX_CONNECTIONS=10;
	private static ConnectionPool instance; 

	private String driver;
	private String url;
	private String username;
	private String pw;
	private BlockingQueue<Connection> connections;
	private int createdConnections;
	private final static Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

	
	private ConnectionPool(int size) {
		createdConnections=0;
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("src/main/resources/database.properties")));
			driver= prop.getProperty("DRIVER");
			url = prop.getProperty("URL");
			username = prop.getProperty("USERNAME");
			pw = prop.getProperty("PASSWORD");
		} catch (FileNotFoundException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e1) {
			LOGGER.error(e1.getMessage());
		}
		connections = new ArrayBlockingQueue<Connection>(size);
	}
	
	public static ConnectionPool getInstance() {
		if (instance == null)
			instance = new ConnectionPool(MAX_CONNECTIONS);
		return instance;
	}
	
	public Connection getConnection() throws InterruptedException, SQLException {
		synchronized(this){
			if(connections.size()==0 && createdConnections<MAX_CONNECTIONS) {
				try {
				    Class.forName(driver);
				    Connection conn = (Connection) DriverManager.getConnection(url, username, pw);
				    connections.offer(conn);
				    createdConnections++;
				    LOGGER.info("Thread "+ Thread.currentThread().getId() + " is starting a new connection");
				} 
				catch (ClassNotFoundException e) {
				    LOGGER.error(e.getMessage());
				} 
			}
			else
				LOGGER.info("Thread "+ Thread.currentThread().getId() + " is waiting for a connection");
		}
		return 	connections.take();
	}
	
	public void releaseConnection(Connection connection) {
		LOGGER.info("Thread "+ Thread.currentThread().getId() + " is releasing the connection");
		connections.offer(connection);
	}
}
