package main.java.com.solvd.university.model.members;
import java.util.Date;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

import org.apache.log4j.Logger;

import main.java.com.solvd.university.exceptions.ProfessorNotAllowedException;
import main.java.com.solvd.university.interfaces.IObserve;
import main.java.com.solvd.university.interfaces.IObserve;
import main.java.com.solvd.university.interfaces.IReport;
import main.java.com.solvd.university.interfaces.IWork;
import main.java.com.solvd.university.model.courses.Course;
import main.java.com.solvd.university.model.courses.Exam;
import main.java.com.solvd.university.model.courses.Lesson;

public class Professor extends Employee implements IReport, IWork, IObserve<Lesson>{

	private final static Logger LOGGER = Logger.getLogger(Professor.class);
	
	private List<Exam> examsToMark;
	private List<Exam> examsCreated;
	private List<Exam> correctedExams;
	
	public Professor() {
		// TODO Auto-generated constructor stub
		initializeStructure();
	}
	
	
	/**
	 * @param name
	 * @param id
	 * @param birthDate
	 * @param email
	 * @param salary
	 * @param jobId
	 */
	public Professor(String name, long id, Date birthDate, String email, double salary, long jobId) {
		super(name, id, birthDate, email, salary, jobId);
		// TODO Auto-generated constructor stub
		initializeStructure();
	}

	private void initializeStructure() {
		examsToMark = new ArrayList<Exam>();
		examsCreated = new ArrayList<Exam>();
		correctedExams = new ArrayList<Exam>();
	}
	public List<Exam> getExamsToMark() {
		return examsToMark;
	}
	public void setExamsToMark(List<Exam> examsToMark) {
		this.examsToMark = examsToMark;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(examsCreated, examsToMark);
		return result;
	}
	
	



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj==null)
			return false;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Professor))
			return false;
		Professor other = (Professor) obj;
		return Objects.equals(examsCreated, other.examsCreated) && Objects.equals(examsToMark, other.examsToMark);
	}


	public boolean containsExamToCorrect(Object o) {
		return examsToMark.contains(o);
	}

	public boolean addExamToCorrect(Exam e) {
		return examsToMark.add(e);
	}

	public boolean removeExamToCorrect(Object o) {
		return examsToMark.remove(o);
	}

	public void work() {
		for (Exam studentExam: examsToMark) {
			for(Exam perfectExam: examsCreated){
				double mark=0.0;
				if (studentExam.getDate().equals(perfectExam.getDate()) && 
						studentExam.getCourse().equals(perfectExam.getCourse())) {
					for(String question: studentExam.getForm().keySet()) {
						if (studentExam.getForm().get(question).equals(perfectExam.getForm().get(question)))
							mark+=1;
					}
					mark = mark/studentExam.getForm().keySet().size();
					if (mark >= Course.PASS_MARK) {
						studentExam.setMark(mark);
						correctedExams.add(studentExam);
					}
				}
			}
		}
		examsToMark.clear();
		examsCreated.clear();
	}
	
	@Override
	public String generateReport() {
		String report="On the date " + (new Date(System.currentTimeMillis())).toString() + " the professor " + this.getName() + " reports that: ";
		for (Exam studentExam: correctedExams) {
			report += "\nStudent " + studentExam.getAuthor().getName() + " has passed " + 
					studentExam.getCourse().getName() + " with the mark: " + studentExam.getMark();
		}
		return report;
	}
	
	public void createExam(Course course, HashMap<String, String> form) throws ProfessorNotAllowedException{
		if (!this.equals(course.getProfessorInCharge()))
			throw new ProfessorNotAllowedException();
		
		Date now = Calendar.getInstance().getTime();
		
		/*HashMap<String, String> perfectForm = (HashMap<String, String>) form.clone();
		for (String question: form.keySet()) {
			perfectForm.put(question, generateRandomAnswer());
		}*/
		examsCreated.add((new Exam(course,form, null, 0.0, now)));
		for (Student student: course.getEnrolledSudents()) {
			Exam studentExam =new Exam(course, form, student, 0.0, now); 
			student.takeExam(studentExam);
			addExamToCorrect(studentExam);
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
		LOGGER.debug(this.getName() + " is teching the lesson on link: " + link);
	}
}
