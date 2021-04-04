package main.java.com.solvd.university.model.courses;
import java.util.Date;
import java.util.HashMap;

import main.java.com.solvd.university.model.members.Student;

public class Exam {

	private Course course;
	private HashMap<String, String> form;
	private Student author;
	private double mark;
	private Date date;
	
	public Exam() {
		// TODO Auto-generated constructor stub
		form = new HashMap<String, String>();
	}

	/**
	 * @param course
	 * @param reservation
	 * @param form
	 * @param author
	 */
	public Exam(Course course, HashMap<String, String> form, Student author, double mark, Date date) {
		this.course = course;
		this.form = form;
		this.author = author;
		this.mark=mark;
		this.date=date;
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
	
	public double getMark() {
		return mark;
	}
	
	public void setMark(double mark) {
		this.mark=mark;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
	
}
