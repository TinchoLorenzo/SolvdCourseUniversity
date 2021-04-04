package com.solvd.UniversityMvn.members;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import com.solvd.UniversityMvn.interfaces.Cleanable;
import com.solvd.UniversityMvn.interfaces.IWork;

public class Auxiliar extends Employee implements IWork {

	private List<Cleanable> assignedCleanables;

	public Auxiliar() {
		// TODO Auto-generated constructor stub
		initializeStructures();
	}

	/**
	 * @param name
	 * @param id
	 * @param birthDate
	 * @param email
	 * @param salary
	 * @param jobId
	 */
	public Auxiliar(String name, long id, Date birthDate, String email, double salary, long jobId) {
		super(name, id, birthDate, email, salary, jobId);
		initializeStructures();
		// TODO Auto-generated constructor stub
	}

	private void initializeStructures() {
		assignedCleanables = new ArrayList<Cleanable>();
	}

	public List<Cleanable> getAssignedCleanables() {
		return assignedCleanables;
	}

	public void setAssignedCleanables(List<Cleanable> assignedCleanables) {
		this.assignedCleanables = assignedCleanables;
	}

	public boolean containsCleanable(Object o) {
		return assignedCleanables.contains(o);
	}

	public boolean addCleanable(Cleanable e) {
		return assignedCleanables.add(e);
	}

	public boolean removeCleanable(Object o) {
		return assignedCleanables.remove(o);
	}

	@Override
	public void work() {
		assignedCleanables.stream().forEach(c -> c.getCleanedUp());
	}
}
