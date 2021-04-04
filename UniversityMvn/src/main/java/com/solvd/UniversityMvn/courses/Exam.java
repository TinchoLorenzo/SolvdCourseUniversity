package com.solvd.UniversityMvn.courses;

import java.util.Date;
import java.util.HashMap;

import com.solvd.UniversityMvn.members.Student;

public class Exam {

	private Course course;
	private HashMap<String, String> form;
	private Student author;
	private GPA mark;
	private Date date;
	private ExamState state;
	
	public Exam() {
		// TODO Auto-generated constructor stub
		form = new HashMap<String, String>();
		this.state=ExamState.CREATED;
	}
	
	public Exam(Course course, HashMap<String, String> form, Date date) {
		this.course = course;
		this.form = form;
		this.state=ExamState.CREATED;
		this.date=date;
	}

	/**
	 * @param course
	 * @param reservation
	 * @param form
	 * @param author
	 */
	public Exam(Course course, HashMap<String, String> form, Student author, GPA mark, Date date) {
		this.course = course;
		this.form = form;
		this.author = author;
		this.mark = mark;
		this.date = date;
		this.state=ExamState.CORRECTED;
	}
	
	public Exam(Exam e) {
		this.course = e.getCourse();
		this.form = new HashMap<String, String>(e.getForm());
		this.author = e.getAuthor();
		this.mark = e.getMark();
		this.date = e.getDate();
		this.state=e.state;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public HashMap<String, String> getForm() {
		return form;
	}

	public void setForm(HashMap<String, String> form) {
		this.form = form;
	}

	public Student getAuthor() {
		return author;
	}

	public void setAuthor(Student author) {
		this.author = author;
	}

	public GPA getMark() {
		return mark;
	}

	public void setMark(GPA mark) {
		this.mark = mark;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public void markAsDone(Student student, HashMap<String, String> form) {
		this.state = ExamState.DONE;
		this.author=student;
		this.form=form;
		this.getCourse().getProfessorInCharge().addExamToCorrect(this);
	}
	
	public void markAsCorrected(GPA mark) {
		this.state=ExamState.CORRECTED;
		this.mark=mark;
	}
	
	public boolean sameExam(Exam e) {
		return this.getDate().equals(e.getDate()) && this.getCourse().equals(e.getCourse());
	}

	
}

