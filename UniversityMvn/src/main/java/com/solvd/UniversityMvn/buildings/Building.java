package com.solvd.UniversityMvn.buildings;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.UniversityMvn.interfaces.Cleanable;

import java.util.ArrayList;

public class Building<T extends Room> implements Cleanable {

	private final static Logger LOGGER = LogManager.getLogger(Building.class);

	private List<T> rooms;
	private String location;
	private long buildingId;

	public Building() {
		// TODO Auto-generated constructor stub
		initializeStructures();
	}

	/**
	 * @param rooms
	 * @param location
	 * @param buildingId
	 */
	public Building(String location, long buildingId) {
		this.location = location;
		this.buildingId = buildingId;
		initializeStructures();
	}

	private void initializeStructures() {
		rooms = new ArrayList<T>();
	}

	public List<T> getRooms() {
		return rooms;
	}

	public void setRooms(List<T> rooms) {
		this.rooms = rooms;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(long buildingId) {
		this.buildingId = buildingId;
	}

	public boolean containsRoom(Object o) {
		return rooms.contains(o);
	}

	public void addRoom(T element) {
		rooms.add(element);
	}

	public boolean removeRoom(Object o) {
		return rooms.remove(o);
	}

	@Override
	public void getCleanedUp() {
		// TODO Auto-generated method stub
		LOGGER.info("Cleaning Building " + getBuildingId());
		rooms.stream().forEach(r -> r.getCleanedUp());
	}

	public int getCapacity() {
		return rooms.stream()
				.reduce(0, (subtotal, room) -> subtotal + room.getCapacity(), Integer::sum);
	}

}
