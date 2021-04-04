package com.solvd.utils.iterators;

public interface BidirectionalIterator<E> {

	public boolean hasNext();

	public boolean hasPrevious();

	// return current data and update pointer
	public E next();

	public E previous();

}
