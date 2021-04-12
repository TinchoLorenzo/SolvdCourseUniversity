package com.solvd.UniversityMvn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.UniversityMvn.buildings.Building;
import com.solvd.UniversityMvn.buildings.ExclusiveRoom;
import com.solvd.UniversityMvn.buildings.Room;
import com.solvd.UniversityMvn.connections.DataBase;
import com.solvd.UniversityMvn.courses.Book;
import com.solvd.UniversityMvn.courses.Course;
import com.solvd.UniversityMvn.courses.GPA;
import com.solvd.UniversityMvn.courses.Semester;
import com.solvd.UniversityMvn.employers.Department;
import com.solvd.UniversityMvn.employers.University;
import com.solvd.UniversityMvn.exceptions.LessonException;
import com.solvd.UniversityMvn.exceptions.ProfessorNotAllowedException;
import com.solvd.UniversityMvn.members.Auxiliar;
import com.solvd.UniversityMvn.members.Professor;
import com.solvd.UniversityMvn.members.Student;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Calendar;

public class Main {
	private final static Logger LOGGER = LogManager.getLogger(Main.class);

	public static void main(String[] args) {
		University university = new University("University 1", 1);
		Department mathsDepartment = new Department("Maths department", 1);
		Department systemsDepartment = new Department("Systems department", 2);
		university.addDepartment(mathsDepartment);
		university.addDepartment(systemsDepartment);

		Course algebraCourse = new Course("Algebra course");
		Course pythonCourse = new Course("Python course");
		mathsDepartment.addCourse(algebraCourse);
		systemsDepartment.addCourse(pythonCourse);

		Calendar c = Calendar.getInstance();
		c.set(1980, 3, 19);
		Professor pythonProfessor = new Professor("John Doe", 1, c.getTime(), "johndoe@gmail.com", 3000d, 1);
		c.set(1999, 6, 16);
		Student pythonStudent1 = new Student("Richard Public", 2, c.getTime(), "richardpublic@gmail.com");
		c.set(2000, 9, 29);
		Student pythonStudent2 = new Student("Nomen Nescio", 3, c.getTime(), "nomennescio@gmail.com");
		c.set(1989, 12, 29);

		pythonCourse.setProfessorInCharge(pythonProfessor);
		pythonCourse.addStudent(pythonStudent1);
		pythonCourse.addStudent(pythonStudent2);
		pythonCourse.setSemester(Semester.First2021);
		
		try {
			pythonCourse.startLesson();
		} catch (LessonException e1) {
			// TODO Auto-generated catch block
			LOGGER.error(e1.getMessage());
		}

		HashMap<String, String> form = new HashMap<String, String>();
		form.put("What is python?", "Python is a programming language");
		form.put("Python is a dynamic or a static language?", "Python is a dynamic language");
		form.put("Python is a dynamic or a static language?", "Python is a dynamic language");

		HashMap<String, String> form1 = new HashMap<String, String>();
		form1.put("What is python?", "Python is a programming language");
		form1.put("Python is a dynamic or a static language?", "Python is a dynamic language");
		form1.put("Python is a dynamic or a static language?", "Python is a dynamic language");
		pythonStudent1.setForm(form1);

		HashMap<String, String> form2 = new HashMap<String, String>();
		form2.put("What is python?", "Python is a programming language");
		form2.put("Python is a dynamic or a static language?", "Python is a static language");
		form2.put("Python is a dynamic or a static language?", "Python is a static language");
		pythonStudent2.setForm(form2);

		pythonCourse.setProfessorInCharge(pythonProfessor);
		try {
			pythonProfessor.createExam(pythonCourse, form);
		} catch (ProfessorNotAllowedException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
		}
		pythonProfessor.work();

		LOGGER.debug(pythonProfessor.generateReport());

		Auxiliar auxiliar = new Auxiliar("J.K. Rowling", 4, c.getTime(), "jkrowling@gmail.com", 2000d, 2);
		Room sharedRoom1 = new Room(30, 1);
		Room sharedRoom2 = new Room(50, 2);
		Room systemsRoom = new ExclusiveRoom(25, 3, mathsDepartment);
		Building<Room> sharedBuilding = new Building<Room>("Abc 2000", 1);
		sharedBuilding.addRoom(sharedRoom1);
		sharedBuilding.addRoom(sharedRoom2);

		auxiliar.addCleanable(sharedBuilding);
		auxiliar.addCleanable(systemsRoom);
		//auxiliar.work();

		Book<Room> b = new Book<Room>(sharedRoom1);
		Set<Book<?>> s = new HashSet<Book<?>>();
		s.add(b);
		Book<Building<Room>> b2 = new Book<Building<Room>>(sharedBuilding);
		s.add(b2);
		pythonStudent1.setBooks(s);
		
		
		try {
			Class<?> getClass = Class.forName("com.solvd.UniversityMvn.members.Student");
			Arrays.stream(getClass.getFields()).forEach(e -> LOGGER.info(e.toString()));  
			Arrays.stream(getClass.getConstructors()).forEach(e -> LOGGER.info(e.toString())); 
			Arrays.stream(getClass.getMethods()).forEach(e -> LOGGER.info(e.toString())); 
			
			Arrays.stream(getClass.getMethods())
					.forEach(x -> {
						if (x.getName().equals("containsCourse"))
							try {
								LOGGER.info(x.invoke(pythonStudent1, algebraCourse));
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								LOGGER.error(e1.getMessage());
							}
					});
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			LOGGER.error(e1.getMessage());
		}
		
		
		DataBase db = new DataBase();
		db.insert("a", "hey there");
		db.insert("b", "hey there");
		try {
			Thread.sleep(1000);
		} catch (Exception e2) {
			// TODO: handle exception
		}
		db.select("b");
	}

}
