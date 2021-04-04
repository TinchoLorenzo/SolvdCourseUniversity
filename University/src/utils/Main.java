package utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		Integer i1 = Integer.valueOf(0);
		Integer i2 = Integer.valueOf(2);
		Integer i3 = Integer.valueOf(4);
		list.add(0, i1);
		list.add(0, i2);
		list.add(2, i3);
		for(Integer i: list) {
			System.out.println(i);
		}
		System.out.println();
		
		
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(0, 3);
		list2.add(0, 7);
		list2.add(0, 8);
		list.addAll(2, list2);
		for(Integer i: list) {
			System.out.println(i);
		}
		System.out.println();
		list.remove(4);
		
		for(Integer i: list) {
			System.out.println(i);
		}
		
		System.out.println(list.contains(3));
		
		
		Iterator<Integer> it = list.iterator();
		
		while(it.hasNext()) {
			System.out.println(it.next());	
		}
		
		System.out.println();
		Iterator <Integer> reverseIt = list.reverseIterator();
		while(reverseIt.hasNext()) {
			System.out.println(reverseIt.next());	
		}
		
		System.out.println();
		
		BidirectionalIterator<Integer> bidirectionalIt = list.bidirectionalIterator();
		while(bidirectionalIt.hasNext()) {
			Integer i = bidirectionalIt.next();
			System.out.println(i);
			if(i.equals(8)) {
				break;
			}
		}
		System.out.println("The next number of 8 is " + bidirectionalIt.previous());
		bidirectionalIt.previous();
		System.out.println("The previous numbers of 8 are ");
		
		
		while (bidirectionalIt.hasPrevious()) {
			System.out.println(bidirectionalIt.previous());
		}
		
	}

}
