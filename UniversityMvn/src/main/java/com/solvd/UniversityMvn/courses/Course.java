package com.solvd.UniversityMvn.courses;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.solvd.UniversityMvn.exceptions.LessonException;
import com.solvd.UniversityMvn.exceptions.NullProfessorException;
import com.solvd.UniversityMvn.exceptions.NullStudentsException;
import com.solvd.UniversityMvn.members.Professor;
import com.solvd.UniversityMvn.members.Student;

public class Course {

	public static final GPA PASS_MARK = GPA.D;
	private List<Student> enrolledSudents;
	private Professor professorInCharge;
	private String name;
	private Lesson lesson;
	private Semester semester;

	public Course() {
		// TODO Auto-generated constructor stub
		initializeStructures();
	}

	/**
	 * @param enrolledSudents
	 * @param professorInCharge
	 * @param name
	 */
	public Course(String name) {
		super();
		this.name = name;
		initializeStructures();
	}

	private void initializeStructures() {
		enrolledSudents = new ArrayList<Student>();
		lesson = new Lesson();
	}

	public List<Student> getEnrolledSudents() {
		return enrolledSudents;
	}

	public void setEnrolledSudents(List<Student> enrolledSudents) {
		lesson.removeAllObservers(this.enrolledSudents);
		this.enrolledSudents = enrolledSudents;
		lesson.addAllObservers(this.enrolledSudents);
	}

	@Override
	public int hashCode() {
		return Objects.hash(PASS_MARK, enrolledSudents);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Professor getProfessorInCharge() {
		return professorInCharge;
	}

	public void setProfessorInCharge(Professor professorInCharge) {
		lesson.removeObserver(this.professorInCharge);
		this.professorInCharge = professorInCharge;
		lesson.addObserver(professorInCharge);

	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	public void setSemester(Semester semester) {
		this.semester=semester;
	}
	
	public Semester getSemester() {
		return this.semester;
	}
	
	public boolean containsStudent(Object o) {
		return enrolledSudents.contains(o);
	}

	public boolean addStudent(Student e) {
		lesson.addObserver(e);
		return enrolledSudents.add(e);
	}

	public boolean removeStudent(Object o) {
		lesson.removeObserver(o);
		return enrolledSudents.remove(o);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Course))
			return false;
		if (obj.hashCode() != this.hashCode())
			return false;
		Course other = (Course) obj;
		return Objects.equals(enrolledSudents, other.enrolledSudents) && 
				Objects.equals(semester, other.semester);
	}

	public void startLesson() throws LessonException {
		if (this.professorInCharge == null)
			throw new NullProfessorException();
		else if (enrolledSudents == null || enrolledSudents.isEmpty())
			throw new NullStudentsException();
		this.lesson.notifyObservers();
	}

}
