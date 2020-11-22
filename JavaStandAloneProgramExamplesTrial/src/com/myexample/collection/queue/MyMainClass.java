package com.myexample.collection.queue;

import java.util.PriorityQueue;
import java.util.Queue;

public class MyMainClass {

	public static void main(String[] args) {
		Queue<Employee> q = new PriorityQueue<Employee> (10, new EmployeeComparator(EmployeeComparator.SORT_BY_ID)); 
		prepareData(q);
		printData(q, "**** Sorted on ID ****");
		
		q = new PriorityQueue<Employee> (10, new EmployeeComparator(EmployeeComparator.SORT_BY_NAME)); 
		prepareData(q);
		printData(q, "**** Sorted on NAME ****");
	}

	private static void prepareData(Queue<Employee> qd) {
		qd.offer(new Employee(1001, "Salma Hayk"));
		qd.offer(new Employee(1005, "Shradha Kapoor"));
		qd.offer(new Employee(1003, "Pareeniti Chopra"));
		qd.offer(new Employee(1004, "Vidya B"));
		qd.offer(new Employee(1002, "Tom B"));
	}
	
	private static void printData(Queue<Employee> qd, String msg) {
		System.out.println(msg);
		while (!qd.isEmpty()) {
			System.out.println(qd.poll().toString());
		}
	}
}
