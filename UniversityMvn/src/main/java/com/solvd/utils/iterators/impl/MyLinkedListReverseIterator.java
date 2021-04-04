package com.solvd.utils.iterators.impl;

import java.util.Iterator;

import com.solvd.utils.MyLinkedList;

public class MyLinkedListReverseIterator<E> implements Iterator<E> {
	private MyLinkedListBidirectionalIterator<E> it;
	// initialize pointer to head of the list for iteration

	/*
	 * public MyLinkedListReverseIterator() { it = new
	 * MyLinkedListBidirectionalIterator<E>(false); }
	 */
	public MyLinkedListReverseIterator(MyLinkedList<E> list) {
		it = new MyLinkedListBidirectionalIterator<E>(list, false);
	}

	// returns false if next element does not exist
	public boolean hasNext() {
		return it.hasPrevious();
	}

	// return current data and update pointer
	public E next() {
		return it.previous();
	}
}