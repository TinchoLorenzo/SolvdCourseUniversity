package com.solvd.UniversityMvn.connections;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.UniversityMvn.Main;



public class ConnectionPool {

	private static final int MAX_CONNECTIONS=10;
	private static final ConnectionPool instance = new ConnectionPool(MAX_CONNECTIONS);
	private BlockingQueue<Connection> connections;
	private int createdConnections;
	private final static Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

	
	private ConnectionPool(int size) {
		connections = new ArrayBlockingQueue<Connection>(size);
	}
	
	public static ConnectionPool getInstance() {
		return instance;
	}
	
	public Connection getConnection() throws InterruptedException {
		synchronized(this){
			if(connections.size()==0 && createdConnections<MAX_CONNECTIONS) {
				connections.offer(new Connection(connections.size()));
				createdConnections++;
				LOGGER.info("Thread "+ Thread.currentThread().getId() + " is starting a new connection");
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
