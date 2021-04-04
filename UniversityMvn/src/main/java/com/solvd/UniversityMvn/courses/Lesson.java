package com.solvd.UniversityMvn.courses;

import java.util.Observer;

import com.solvd.UniversityMvn.interfaces.IObserve;

public class Lesson extends Observable {

	private String meetLink;

	public Lesson() {
		// Default meetlink
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
	public void notifyObservers() {
		getObservers().stream().forEach(observer -> observer.updateObserver(meetLink));
	}

}
