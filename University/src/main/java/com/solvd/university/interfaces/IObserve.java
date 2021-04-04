package main.java.com.solvd.university.interfaces;

import main.java.com.solvd.university.model.courses.Observable;

public interface IObserve<T extends Observable>{

	public void updateObserver(String link);
}
