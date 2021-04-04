package com.solvd.UniversityMvn.members;

import java.util.Date;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.UniversityMvn.courses.Course;
import com.solvd.UniversityMvn.courses.Exam;
import com.solvd.UniversityMvn.courses.GPA;
import com.solvd.UniversityMvn.exceptions.ProfessorNotAllowedException;
import com.solvd.UniversityMvn.interfaces.IObserve;
import com.solvd.UniversityMvn.interfaces.IReport;
import com.solvd.UniversityMvn.interfaces.IWork;

public class Professor extends Employee implements IReport, IWork, IObserve {

	private final static Logger LOGGER = LogManager.getLogger(Professor.class);

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
		if (obj == null)
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
		examsToMark.stream().forEach(studentExam -> examsCreated.stream().filter(e -> e.sameExam(studentExam)).forEach(perfectExam -> {
			double mark = 0.0;
			mark=studentExam.getForm().entrySet().stream()
					.mapToDouble(elem -> elem.getValue().equals(perfectExam.getForm().get(elem.getKey())) ? 1d:0d).average().getAsDouble();
			if (GPA.get(mark).compareTo(Course.PASS_MARK)>0) {
				studentExam.markAsCorrected(GPA.get(mark));
				correctedExams.add(studentExam);
			}
		}));
		examsToMark.clear();
		examsCreated.clear();
	}

	@Override
	public String generateReport() {
		String report = "On the date " + (new Date(System.currentTimeMillis())).toString() + " the professor "
				+ this.getName() + " reports that: ";
		return correctedExams.stream().reduce(report, (acum, elem) -> acum+= "\nStudent " + elem.getAuthor().getName() + " has passed "
				+ elem.getCourse().getName() + " with the mark: " + elem.getMark(), String::concat);
	}

	public void createExam(Course course, HashMap<String, String> form) throws ProfessorNotAllowedException {
		if (!this.equals(course.getProfessorInCharge()))
			throw new ProfessorNotAllowedException();

		Date now = Calendar.getInstance().getTime();

		Exam e = new Exam(course, form, now);
		examsCreated.add(e);
		course.getEnrolledSudents().stream().forEach(student-> student.takeExam(new Exam(e)));
	}

	@Override
	public void updateObserver(String link) {
		// TODO Auto-generated method stub
		LOGGER.debug(this.getName() + " is teching the lesson on link: " + link);
	}
}
