package com.solvd.UniversityMvn.courses;

import java.util.ArrayList;
import java.util.List;

import com.solvd.UniversityMvn.interfaces.IObserve;

import java.util.Collection;

public abstract class Observable {

	private List<IObserve> observers;

	public Observable() {
		// TODO Auto-generated constructor stub
		observers = new ArrayList<IObserve>();
	}

	public List<IObserve> getObservers() {
		return observers;
	}

	public void setObservers(List<IObserve> observers) {
		this.observers = observers;
	}

	public boolean containsObserver(Object o) {
		return observers.contains(o);
	}

	public boolean addObserver(IObserve e) {
		return observers.add(e);
	}

	public boolean removeObserver(Object o) {
		return observers.remove(o);
	}

	public boolean addAllObservers(Collection<? extends IObserve> c) {
		return observers.addAll(c);
	}

	public boolean removeAllObservers(Collection<?> c) {
		return observers.removeAll(c);
	}

	public abstract void notifyObservers();
}
