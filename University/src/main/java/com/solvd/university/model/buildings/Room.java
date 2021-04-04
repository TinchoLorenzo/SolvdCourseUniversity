package main.java.com.solvd.university.model.buildings;
import java.util.Date;

import org.apache.log4j.Logger;

import main.java.com.solvd.university.interfaces.Cleanable;

import java.util.ArrayList;

public class Room implements Cleanable{

	private final static Logger LOGGER = Logger.getLogger(Room.class);

	
	private int capacity;
	private long roomId;
	
	public Room() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param capacity
	 * @param roomId
	 */
	public Room(int capacity, long roomId) {
		super();
		this.capacity = capacity;
		this.roomId = roomId;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}
	
	@Override
	public void getCleanedUp() {
		// TODO Auto-generated method stub
		try {
			//studying
			Thread.sleep(50 * capacity);
			LOGGER.info("Cleaning room " + this.getRoomId());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
		}
	}
	
}

