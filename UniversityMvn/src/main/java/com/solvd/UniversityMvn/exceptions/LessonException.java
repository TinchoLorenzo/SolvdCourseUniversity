package com.solvd.UniversityMvn.exceptions;

public class LessonException extends Exception {

	public LessonException() {
		// TODO Auto-generated constructor stub
		super("The lesson couldn't start because of observers problems");
	}

	public LessonException(String message) {
		super("The lesson couldn't start. " + message);
	}
}
