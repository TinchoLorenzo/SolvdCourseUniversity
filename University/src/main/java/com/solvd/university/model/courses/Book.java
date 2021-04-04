package main.java.com.solvd.university.model.courses;

import java.util.Objects;

public class Book<T> {

	private T bookDomain;
	private int pages;
	
	public Book() {
		// TODO Auto-generated constructor stub
	}
	
	public Book(T type) {
		this.bookDomain= type;
	}
	
	public T study() {
		int x=0;
		for(int i=0; i<pages; i++) {
			//simulate study
			x= x*2+x;
		}
		return bookDomain;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bookDomain, pages);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Book))
			return false;
		Book other = (Book) obj;
		return Objects.equals(bookDomain, other.bookDomain) && pages == other.pages;
	}
	
	
}
