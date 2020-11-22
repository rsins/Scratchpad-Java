package com.exam.oracle.interview;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class IteratorFlattenerTester {

	public static void main(String[] args) {
		testHasNext();
		testNextWithElements();
		testNextWithNoElements();
		// Test for no exception and test for next value after remove.
		testRemove();
		testRemoveWithoutNext();
		testRemoveMultipleTimes();
		// Flattening of all iterators from same data structure.
		test1();
		// Flattening of all iterators from different data structures.
		test2();
		// Flattening of all iterators where some iterators are null.
		test3();
		// Flattening of all iterators where some iterators are empty.
		test4();
		// Flattening of all iterators where null is passed to IteratorFlattener Constructor.
		test5();

	}
	
	private static void testHasNext() {
		String msg = "Flattening of all iterators - testing hasNext().";
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		list1.add(2);
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(3);
		list2.add(4);
		
		List<Iterator<Integer>> itrList = new ArrayList<Iterator<Integer>>();
		itrList.add(list1.iterator());
		itrList.add(list2.iterator());
		
		IteratorFlattener<Integer> flatItr = new IteratorFlattener<Integer>(itrList.iterator());
		int count = 0;
		while(flatItr.hasNext()) {
			count++;
			flatItr.next();
		}
		if (count == 4) System.out.println("PASS : " + msg);
		else System.out.println("FAILURE : " + msg);
		
	}
	
	// Test if remove has been called again before called another next()
	private static void testRemoveMultipleTimes() {
		String msg = "Flattening of all iterators - testing remove() after remove() has already been called (should receive Exception IllegalStateException).";
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		
		List<Iterator<Integer>> itrList = new ArrayList<Iterator<Integer>>();
		itrList.add(list1.iterator());
		
		IteratorFlattener<Integer> flatItr = new IteratorFlattener<Integer>(itrList.iterator());
		flatItr.next();
		flatItr.remove();
		try {
			flatItr.remove();
		}
		catch (IllegalStateException e) {
			System.out.println("PASS : " + msg);
			return;
		}
		System.out.println("FAILURE : " + msg);
		
	}
	
	private static void testRemoveWithoutNext() {
		String msg = "Flattening of all iterators - testing remove() without next() (should receive Exception IllegalStateException).";
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		
		List<Iterator<Integer>> itrList = new ArrayList<Iterator<Integer>>();
		itrList.add(list1.iterator());
		
		IteratorFlattener<Integer> flatItr = new IteratorFlattener<Integer>(itrList.iterator());
		try {
			flatItr.remove();
		}
		catch (IllegalStateException e) {
			System.out.println("PASS : " + msg);
			return;
		}
		System.out.println("FAILURE : " + msg);
		
	}
	
	private static void testRemove() {
		String msg = "Flattening of all iterators - testing remove().";
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		list1.add(5);
		list1.add(6);
		
		List<Iterator<Integer>> itrList = new ArrayList<Iterator<Integer>>();
		itrList.add(list1.iterator());
		
		IteratorFlattener<Integer> flatItr = new IteratorFlattener<Integer>(itrList.iterator());
		flatItr.next();
		try {
			flatItr.next();
			flatItr.remove();
			Integer curValue = flatItr.next();
			if (curValue != 6) {
				System.out.println("FAILURE (expected 6 and received " + curValue + ") : " + msg);
				return;
			}
		}
		catch (Exception e) {
			System.out.println("FAILURE (Unexpected Exception - " + e.getMessage() +"): " + msg);
			return;
		}
		System.out.println("PASS : " + msg);
		
	}
	
	private static void testNextWithNoElements() {
		String msg = "Flattening of all iterators - testing hasNext() with No Elements (NoSuchElementException Should be thrown)";
		List<Iterator<Integer>> itrList = new ArrayList<Iterator<Integer>>();
		itrList.add(new ArrayList<Integer>().iterator());
		
		IteratorFlattener<Integer> flatItr = new IteratorFlattener<Integer>(itrList.iterator());
		try {
			flatItr.next();
		}
		catch (NoSuchElementException e) {
			System.out.println("PASS : " + msg);
			return;
		}
		System.out.println("FAILURE : " + msg);
		
	}

	private static void testNextWithElements() {
		String msg = "Flattening of all iterators - testing hasNext() with Elements present.";
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		list1.add(5);
		
		List<Iterator<Integer>> itrList = new ArrayList<Iterator<Integer>>();
		itrList.add(list1.iterator());
		
		IteratorFlattener<Integer> flatItr = new IteratorFlattener<Integer>(itrList.iterator());
		flatItr.next();
		if (flatItr.next() == 5) System.out.println("PASS : " + msg);
		else System.out.println("FAILURE : " + msg);
		
	}
	// Flattening of all iterators from same data structure.
	private static void test1() {
		String msg = "Flattening of all iterators from multiple ArrayList<Integer>.";
		Integer[] result =  new Integer[] {1, 2, 3, 4, 5, 6, 7, 8};
		
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		list1.add(2);
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(3);
		list2.add(4);
		List<Integer> list3 = new ArrayList<Integer>();
		list3.add(5);
		list3.add(6);
		List<Integer> list4 = new ArrayList<Integer>();
		list4.add(7);
		list4.add(8);
		
		List<Iterator<Integer>> itrList = new ArrayList<Iterator<Integer>>();
		itrList.add(list1.iterator());
		itrList.add(list2.iterator());
		itrList.add(list3.iterator());
		itrList.add(list4.iterator());
		
		IteratorFlattener<Integer> flatItr = new IteratorFlattener<Integer>(itrList.iterator());
		int index = 0;
		while (flatItr.hasNext()) {
			Integer curValue = flatItr.next();
			// System.out.println(curValue);
			if (!result[index].equals(curValue)) {
				System.out.println("FAILURE : " + msg);
				System.out.println("Example: NOMATCH between " + result[index] + " and " + curValue);
				return;
			}
			index++;
		}
		System.out.println("PASS : " + msg);
	}
	
	// Flattening of all iterators from different data structures.
	private static void test2() {
		String msg = "Flattening of all iterators from multiple Iterables (ArrayList, HashSet, Queue, PriorityQueue).";
		Integer[] result =  new Integer[] {1, 2, 3, 4, 5, 6, 7, 8};
		
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		list1.add(2);
		Set<Integer> set2 = new HashSet<Integer>();
		set2.add(3);
		set2.add(4);
		Queue<Integer> queue3 = new ArrayDeque<Integer>();
		queue3.add(5);
		queue3.add(6);
		PriorityQueue<Integer> pq4 = new PriorityQueue<Integer>();
		pq4.add(7);
		pq4.add(8);
		
		List<Iterator<Integer>> itrList = new ArrayList<Iterator<Integer>>();
		itrList.add(list1.iterator());
		itrList.add(set2.iterator());
		itrList.add(queue3.iterator());
		itrList.add(pq4.iterator());
		
		IteratorFlattener<Integer> flatItr = new IteratorFlattener<Integer>(itrList.iterator());
		int index = 0;
		while (flatItr.hasNext()) {
			Integer curValue = flatItr.next();
			// System.out.println(curValue);
			if (!result[index].equals(curValue)) {
				System.out.println("FAILURE : " + msg);
				System.out.println("Example: NOMATCH between " + result[index] + " and " + curValue);
				return;
			}
			index++;
		}
		System.out.println("PASS : " + msg);
	}
	
	// Flattening of all iterators where some iterators are null.
	private static void test3() {
		String msg = "Flattening of all iterators - some iterators are null (including first and last and few in between).";
		Integer[] result =  new Integer[] {1, 2, 5, 6};
		
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		list1.add(2);
		List<Integer> list3 = new ArrayList<Integer>();
		list3.add(5);
		list3.add(6);
		
		List<Iterator<Integer>> itrList = new ArrayList<Iterator<Integer>>();
		itrList.add(null);
		itrList.add(list1.iterator());
		itrList.add(null);
		itrList.add(list3.iterator());
		itrList.add(null);
		
		IteratorFlattener<Integer> flatItr = new IteratorFlattener<Integer>(itrList.iterator());
		int index = 0;
		while (flatItr.hasNext()) {
			Integer curValue = flatItr.next();
			// System.out.println(curValue);
			if (!result[index].equals(curValue)) {
				System.out.println("FAILURE : " + msg);
				System.out.println("Example: NOMATCH between " + result[index] + " and " + curValue);
				return;
			}
			index++;
		}
		System.out.println("PASS : " + msg);
	}
	
	// Flattening of all iterators where some iterators are empty.
	private static void test4() {
		String msg = "Flattening of all iterators - some iterators are empty (including first and last and few in between).";
		Integer[] result =  new Integer[] {1, 2, 5, 6};
		
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		list1.add(2);
		List<Integer> list3 = new ArrayList<Integer>();
		list3.add(5);
		list3.add(6);
		
		List<Iterator<Integer>> itrList = new ArrayList<Iterator<Integer>>();
		itrList.add(new ArrayList<Integer>().iterator());
		itrList.add(list1.iterator());
		itrList.add(new ArrayList<Integer>().iterator());
		itrList.add(list3.iterator());
		itrList.add(new ArrayList<Integer>().iterator());
		
		IteratorFlattener<Integer> flatItr = new IteratorFlattener<Integer>(itrList.iterator());
		int index = 0;
		while (flatItr.hasNext()) {
			Integer curValue = flatItr.next();
			// System.out.println(curValue);
			if (!result[index].equals(curValue)) {
				System.out.println("FAILURE : " + msg);
				System.out.println("Example: NOMATCH between " + result[index] + " and " + curValue);
				return;
			}
			index++;
		}
		System.out.println("PASS : " + msg);
	}
	
	// Flattening of all iterators where null is passed to IteratorFlattener Constructor.
	private static void test5() {
		String msg = "Flattening of all iterators - null is passed to IteratorFlattener Constructor.";

		IteratorFlattener<Integer> flatItr = new IteratorFlattener<Integer>(null);
		if (flatItr.hasNext()) System.out.println("FAILURE : " + msg);
		else System.out.println("PASS : " + msg);
	}
}
