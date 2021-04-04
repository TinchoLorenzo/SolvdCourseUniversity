package main.java.com.solvd.university.model.members;
import java.util.Date;


import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;

import main.java.com.solvd.university.interfaces.IObserve;
import main.java.com.solvd.university.interfaces.IWork;
import main.java.com.solvd.university.model.courses.Book;
import main.java.com.solvd.university.model.courses.Course;
import main.java.com.solvd.university.model.courses.Exam;
import main.java.com.solvd.university.model.courses.Lesson;

public class Student extends UniversityMember implements IWork, IObserve<Lesson>{

	private final static Logger LOGGER = Logger.getLogger(Student.class);
	
	//Just for testing the program
	private HashMap<String, String> form;
	
	//Fields
	private Date startingDate;
	private List<Course> enrolledCourses;
	private Set<Book<?>> books;
	
	//Constructors
	public Student() {
		// TODO Auto-generated constructor stub
		initializeStructures();
	}

	public Student(Date startingDate) {
		this.startingDate = startingDate;
		initializeStructures();
	}
	
	/**
	 * @param name
	 * @param id
	 * @param birthDate
	 * @param email
	 */
	public Student(String name, long id, Date birthDate, String email) {
		super(name, id, birthDate, email);
		// TODO Auto-generated constructor stub
		initializeStructures();
	}

	private void initializeStructures() {
		this.enrolledCourses= new ArrayList<Course>();
		this.books= new HashSet<Book<?>>();
	}
	//Getter and setters
	
	public Date getStartingDate() {
		return startingDate;
	}


	public HashMap<String, String> getForm() {
		return form;
	}

	public void setForm(HashMap<String, String> form) {
		this.form = form;
	}

	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}


	public List<Course> getEnrolledCourses() {
		return enrolledCourses;
	}


	public void setEnrolledCourses(List<Course> enrolledCourses) {
		this.enrolledCourses = enrolledCourses;
	}

	

	public boolean containsCourse(Object o) {
		return enrolledCourses.contains(o);
	}

	public boolean addCourse(Course e) {
		return enrolledCourses.add(e);
	}

	public boolean removeCourse(Object o) {
		return enrolledCourses.remove(o);
	}

	@Override
	public int hashCode() {
		return Objects.hash(enrolledCourses, startingDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Student))
			return false;
		if (obj.hashCode() != this.hashCode())
			return false;
		Student other = (Student) obj;
		return Objects.equals(enrolledCourses, other.enrolledCourses)
				&& Objects.equals(startingDate, other.startingDate);
	}
	
	public void addEnrolledCourse(Course course) {
		enrolledCourses.add(course);
	}
	
	@Override
	public void work() {
		try {
			//studying
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
		}
	}
	
	public void takeExam(Exam exam) {
		// TODO Auto-generated method stub
		if (this.form!=null)
			exam.setForm(this.form);
		else
			for (String question:exam.getForm().keySet()) {
				exam.getForm().put(question, generateRandomAnswer());
			}
		
	}
	
	private String generateRandomAnswer() {
		int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    return buffer.toString();
	}
	
	@Override
	public void updateObserver(String link) {
		// TODO Auto-generated method stub
		//Join meet link
		LOGGER.debug(this.getName() + " is taking the lesson on link: " + link);
	}

	public Set<Book<?>> getBooks() {
		return books;
	}

	public void setBooks(Set<Book<?>> books) {
		this.books = books;
	}
	
	public void studyBook(Book<?> book) {
		book.study();
		this.books.add(book);
	}
	
	
	
	
	
}
