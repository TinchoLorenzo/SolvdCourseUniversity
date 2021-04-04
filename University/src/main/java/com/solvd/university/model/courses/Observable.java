package main.java.com.solvd.university.model.courses;

import java.util.ArrayList;

import java.util.List;

import main.java.com.solvd.university.interfaces.IObserve;

import java.util.Collection;

public abstract class Observable{

	private List<IObserve<? extends Observable> > observers;
	
	public Observable() {
		// TODO Auto-generated constructor stub
		observers = new ArrayList<IObserve<?>>();
	}

	public List<IObserve<? extends Observable>> getObservers() {
		return observers;
	}

	public void setObservers(List<IObserve<? extends Observable>> observers) {
		this.observers = observers;
	}
	
	public boolean containsObserver(Object o) {
		return observers.contains(o);
	}

	public boolean addObserver(IObserve<? extends Observable> e) {
		return observers.add(e);
	}

	public boolean removeObserver(Object o) {
		return observers.remove(o);
	}
	
	

	public boolean addAllObservers(Collection<? extends IObserve<? extends Observable>> c) {
		return observers.addAll(c);
	}
	
	

	public boolean removeAllObservers(Collection<?> c) {
		return observers.removeAll(c);
	}

	public abstract void notifyObservers();
}
