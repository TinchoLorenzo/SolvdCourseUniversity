package main.java.com.solvd.university.model.courses;

import main.java.com.solvd.university.exceptions.LessonException;


import main.java.com.solvd.university.exceptions.NullProfessorException;
import main.java.com.solvd.university.exceptions.NullStudentsException;
import main.java.com.solvd.university.interfaces.IObserve;

public class Lesson extends Observable{
	
	private String meetLink;
	public Lesson() {
		//Default meetlink
		this.meetLink = "meet.com/abc-dfg-hij";
		// TODO Auto-generated constructor stub
	}
	
	public Lesson(String meetLink) {
		this.meetLink = meetLink;
	}
	
	public String getMeetLink() {
		return meetLink;
	}

	public void setMeetLink(String meetLink) {
		this.meetLink = meetLink;
	}

	@Override
	public void notifyObservers(){
		// TODO Auto-generated method stub
		
		for (IObserve<? extends Observable> observer: this.getObservers()) {
			observer.updateObserver(meetLink);
		}
	}
	
	
	
	
}
