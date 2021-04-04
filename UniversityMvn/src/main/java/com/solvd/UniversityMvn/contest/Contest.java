package com.solvd.UniversityMvn.contest;

import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import com.solvd.UniversityMvn.courses.Course;
import com.solvd.UniversityMvn.members.Professor;

public class Contest<T extends Course, E extends Professor, F extends Comparator<E>> {

	private T contentCourse;
	private Queue<E> meritOrder;
	private F comparator;

	public Contest() {
		// TODO Auto-generated constructor stub
		this.meritOrder = new PriorityQueue<E>();
	}

	/**
	 * @param contentCourse
	 * @param meritOrder
	 * @param comparator
	 */
	public Contest(T contentCourse, Collection<E> meritOrder, F comparator) {
		this.contentCourse = contentCourse;
		this.meritOrder = new PriorityQueue<E>(comparator);
		this.meritOrder.addAll(meritOrder);
		this.comparator = comparator;
	}

	public void addCompetitor(E professor) {
		meritOrder.add(professor);
	}

	public T getContentCourse() {
		return contentCourse;
	}

	public void setContentCourse(T contentCourse) {
		this.contentCourse = contentCourse;
	}

	public Queue<E> getMeritOrder() {
		return meritOrder;
	}

	public void setMeritOrder(Queue<E> meritOrder) {
		this.meritOrder = meritOrder;
	}

	public F getComparator() {
		return comparator;
	}

	public void setComparator(F comparator) {
		Queue<E> queue = new PriorityQueue<E>(comparator);
		queue.addAll(meritOrder);
		this.comparator = comparator;
	}

}
