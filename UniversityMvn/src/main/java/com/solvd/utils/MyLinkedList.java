package com.solvd.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.solvd.utils.iterators.BidirectionalIterator;
import com.solvd.utils.iterators.impl.MyLinkedListBidirectionalIterator;
import com.solvd.utils.iterators.impl.MyLinkedListReverseIterator;

public class MyLinkedList<E> implements Iterable<E> {

	protected Node head;
	protected Node tail;
	private int size;

	public class Node {

		protected Node next;
		protected Node prev;
		protected E data;

		public Node() {
			data = null;
			// TODO Auto-generated constructor stub
		}

		public Node(E data) {
			this.data = data;
		}

		public boolean isNull() {
			return data == null;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public Node getPrev() {
			return prev;
		}

		public void setPrev(Node prev) {
			this.prev = prev;
		}

		public E getData() {
			return data;
		}

		public void setData(E data) {
			this.data = data;
		}

	}

	public MyLinkedList() {
		// TODO Auto-generated constructor stub
		initializeStructure();
	}

	private void initializeStructure() {
		head = new Node();
		tail = new Node();
		head.next = tail;
		tail.prev = head;
		size = 0;
	}

	public boolean contains(Object o) {
		for (E element : this) {
			if (element.equals(o))
				return true;
		}
		return false;
	}

	public int size() {
		return size;
	}

	public boolean addAll(int index, Collection<? extends E> c) {
		if (index < 0 || index > size()) {
			return false;
		}

		Node temp = head;
		for (int i = 0; i < index; i++) {
			temp = temp.next;
		}
		for (E element : c) {
			Node newNode = new Node(element);
			newNode.next = temp.next;
			newNode.prev = temp;
			temp.next.prev = newNode;
			temp.next = newNode;
			// Go next
			temp = temp.next;
		}
		size += c.size();
		return true;
	}

	public E get(int index) {
		Node node = head.next;
		for (int i = 0; i < index; i++)
			node = node.next;
		return node.data;
	}

	public void add(int index, E element) {
		if (index < 0 || index > size())
			throw new IndexOutOfBoundsException();
		size++;
		Node temp = head;
		for (int i = 0; i < index; i++) {
			temp = temp.next;
		}
		Node newNode = new Node(element);
		newNode.next = temp.next;
		newNode.prev = temp;
		temp.next.prev = newNode;
		temp.next = newNode;
	}

	public E remove(int index) {
		if (index < 0 || index > size())
			throw new IndexOutOfBoundsException();
		Node node = head;
		for (int i = 0; i < index; i++)
			node = node.next;
		Node toRemove = node.next;
		node.next = toRemove.next;
		toRemove.next.prev = node;
		toRemove.next = null;
		toRemove.prev = null;
		return toRemove.data;
	}

	public void clear() {
		initializeStructure();
	}

	public Node getHead() {
		return this.head;
	}

	public Node getTail() {
		return this.tail;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new MyLinkedListBidirectionalIterator<E>(this, true);
	}

	public Iterator<E> reverseIterator() {
		return new MyLinkedListReverseIterator<E>(this);
	}

	public BidirectionalIterator<E> bidirectionalIterator() {
		return new MyLinkedListBidirectionalIterator<E>(this, true);
	}
}
