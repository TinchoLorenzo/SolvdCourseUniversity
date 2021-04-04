package utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements  Iterable<E> {

	private Node<E> head;
	private Node<E> tail;	
	private int size;
	
	class Node<E>{
		
		private Node<E> next;
		private Node<E> prev;
		private E data;
		
		public Node() {
			data = null;
			// TODO Auto-generated constructor stub
		}
		
		public Node(E data) {
			this.data=data;
		}
		
		public boolean isNull() {
			return data == null;
		}
	}
	
	class MyLinkedListBidirectionalIterator implements BidirectionalIterator<E>, Iterator<E> { 
	    private Node<E> current; 
	    // initialize pointer to head of the list for iteration 
	    public MyLinkedListBidirectionalIterator(boolean forward) 
	    { 	
	    	if (forward) {
	    		current = head.next;
	    	}
	    	else
	    		current = tail.prev;
	    }
	    public MyLinkedListBidirectionalIterator(MyLinkedList<E> list, boolean forward) 
	    { 	
	    	if (forward)
	    		current = list.head.next;
	    	else
	    		current = list.tail.prev;
	    }
	      
	    // returns false if next element does not exist 
	    public boolean hasNext() 
	    { 
	        return !current.isNull(); 
	    } 
	    
	    public boolean hasPrevious() {
	    	return !current.isNull(); 
	    }
	      
	    // return current data and update pointer 
	    public E next() 
	    { 
	        if (!hasNext()) 
	        	throw new NoSuchElementException();
	        E data = current.data; 
	        current = current.next; 
	        return data; 
	    } 
	      
	    public E previous() {
	        if (!hasPrevious()) 
	        	throw new NoSuchElementException();

	    	E data = current.data;
	    	current = current.prev;
	    	return data;
	    }
	} 
	
	class MyLinkedListReverseIterator implements Iterator<E> { 
	    private MyLinkedListBidirectionalIterator it; 
	    // initialize pointer to head of the list for iteration 
	    public MyLinkedListReverseIterator() 
	    { 	
	    	it = new MyLinkedListBidirectionalIterator(false);
	    }
	    public MyLinkedListReverseIterator(MyLinkedList<E> list) 
	    { 	
	    	it = new MyLinkedListBidirectionalIterator(list, false);
	    }
	      
	    // returns false if next element does not exist 
	    public boolean hasNext() 
	    { 
	    	return it.hasPrevious();
	    } 
	      
	    // return current data and update pointer 
	    public E next() 
	    { 
	    	return it.previous();
	    } 
	} 
	
	public MyLinkedList() {
		// TODO Auto-generated constructor stub
		initializeStructure();
	}
	
	private void initializeStructure() {
		head = new Node<E>();
		tail = new Node<E>();
		head.next=tail;
		tail.prev=head;
		size = 0;
	}

	public boolean contains(Object o) {
		for (E element: this) {
			if (element.equals(o))
				return true;
		}
		return false;
	}

	public int size() {
		return size;
	}

	public boolean addAll(int index, Collection<? extends E> c) {
		if (index < 0 || index > size()){
			return false;
		}
		
		Node<E> temp = head;
		for(int i=0; i<index; i++) {
			temp=temp.next;
		}
		for (E element: c) {
			Node<E> newNode = new Node<E>(element);
			newNode.next = temp.next;
			newNode.prev = temp;
			temp.next.prev=newNode;
			temp.next = newNode;
			//Go next
			temp=temp.next;
		}
		size += c.size();
		return true;
	}

	public E get(int index) {
		Node<E> node = head.next; 
		for (int i=0; i<index; i++)
			node = node.next;
		return node.data;
	}

	public void add(int index, E element) {
		if (index < 0 || index > size())
			throw new IndexOutOfBoundsException(); 
		size++;
		Node<E> temp = head;
		for (int i=0; i<index; i++) {
			temp = temp.next;
		}
		Node<E> newNode = new Node<E>(element);
		newNode.next = temp.next;
		newNode.prev = temp;
		temp.next.prev=newNode;
		temp.next = newNode;
	}

	public E remove(int index) {
		if (index < 0 || index > size())
			throw new IndexOutOfBoundsException();
		Node<E> node = head;
		for(int i=0; i<index; i++)
			node = node.next;
		Node<E> toRemove = node.next;
		node.next=toRemove.next;
		toRemove.next.prev=node;
		toRemove.next=null;
		toRemove.prev=null;
		return toRemove.data;
	}
	
	public void clear() {
		initializeStructure();
	}
	
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new MyLinkedListBidirectionalIterator(this, true);
	}

	public Iterator<E> reverseIterator(){
		return new MyLinkedListReverseIterator();
	}
	
	public BidirectionalIterator<E> bidirectionalIterator(){
		return new MyLinkedListBidirectionalIterator(this, true);
	}
}
