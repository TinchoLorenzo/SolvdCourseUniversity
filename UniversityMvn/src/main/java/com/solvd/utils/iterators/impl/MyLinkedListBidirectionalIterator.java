package com.solvd.utils.iterators.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.solvd.utils.MyLinkedList;
import com.solvd.utils.MyLinkedList.Node;
import com.solvd.utils.iterators.BidirectionalIterator;

public class MyLinkedListBidirectionalIterator<E> implements BidirectionalIterator<E>, Iterator<E> {
	private Node current;

	// initialize pointer to head of the list for iteration
	/*
	 * public MyLi nkedListBidirectionalIterator(boolean forward) { if (forward) {
	 * current = head.next; } else current = tail.prev; }
	 */
	public MyLinkedListBidirectionalIterator(MyLinkedList<E> list, boolean forward) {
		if (forward)
			current = list.getHead().getNext();
		else
			current = list.getTail().getPrev();
	}

	// returns false if next element does not exist
	public boolean hasNext() {
		return !current.isNull();
	}

	public boolean hasPrevious() {
		return !current.isNull();
	}

	// return current data and update pointer
	public E next() {
		if (!hasNext())
			throw new NoSuchElementException();
		E data = (E) current.getData();
		current = current.getNext();
		return data;
	}

	public E previous() {
		if (!hasPrevious())
			throw new NoSuchElementException();

		E data = (E) current.getData();
		current = current.getPrev();
		return data;
	}
}
