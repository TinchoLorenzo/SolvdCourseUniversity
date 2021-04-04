package com.solvd.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.utils.iterators.BidirectionalIterator;

public class Main {

	private final static Logger LOGGER = LogManager.getLogger(Main.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		Integer i1 = Integer.valueOf(0);
		Integer i2 = Integer.valueOf(2);
		Integer i3 = Integer.valueOf(4);
		list.add(0, i1);
		list.add(0, i2);
		list.add(2, i3);
		for (Integer i : list) {
			LOGGER.info(i);
		}
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(0, 3);
		list2.add(0, 7);
		list2.add(0, 8);
		list.addAll(2, list2);
		for (Integer i : list) {
			LOGGER.info(i);
		}
		list.remove(4);

		for (Integer i : list) {
			LOGGER.info(i);
		}

		LOGGER.info(list.contains(3));

		Iterator<Integer> it = list.iterator();

		while (it.hasNext()) {
			LOGGER.info(it.next());
		}

		Iterator<Integer> reverseIt = list.reverseIterator();
		while (reverseIt.hasNext()) {
			LOGGER.info(reverseIt.next());
		}

		BidirectionalIterator<Integer> bidirectionalIt = list.bidirectionalIterator();
		while (bidirectionalIt.hasNext()) {
			Integer i = bidirectionalIt.next();
			LOGGER.info(i);
			if (i.equals(8)) {
				break;
			}
		}
		LOGGER.info("The next number of 8 is " + bidirectionalIt.previous());
		bidirectionalIt.previous();
		LOGGER.info("The previous numbers of 8 are ");

		while (bidirectionalIt.hasPrevious()) {
			LOGGER.info(bidirectionalIt.previous());
		}

	}

}
