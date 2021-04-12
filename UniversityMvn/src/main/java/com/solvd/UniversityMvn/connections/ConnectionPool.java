package com.solvd.UniversityMvn.connections;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.UniversityMvn.Main;



public class ConnectionPool {

	private static final int MAX_CONNECTIONS=2;
	private static final ConnectionPool instance = new ConnectionPool(MAX_CONNECTIONS);
	private List<Connection> connections;
	private int activeConnections;
	private final static Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

	
	private ConnectionPool(int size) {
		connections = new ArrayList<Connection>(MAX_CONNECTIONS);
	}
	
	public static ConnectionPool getInstance() {
		return instance;
	}
	
	public Connection initConnection() {
		synchronized(this){
			while(activeConnections>=MAX_CONNECTIONS) {
				try {
					LOGGER.info("Thread " + Thread.currentThread().getId() + " waiting for a connection to be free.");
					wait(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					LOGGER.error(e.getMessage());
				}
			}
			Connection c = new Connection(connections.size(), this);
			connections.add(c);
			activeConnections++;
			return c;
		}
	}
	
	public void closeConnection(Connection connection) {
		synchronized(this) {
			LOGGER. info("Thread " + Thread.currentThread().getId() + " Connection number " + connection.getNumber() + " just finished");
			connections.remove(connection);
			activeConnections--;
			notifyAll();
		}
	}
}
