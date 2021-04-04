package com.solvd.UniversityMvn.exceptions;

public class ProfessorNotAllowedException extends Exception {

	public ProfessorNotAllowedException() {
		// TODO Auto-generated constructor stub
		super("The professor is not allowed to create an exam of this course");
	}
}
