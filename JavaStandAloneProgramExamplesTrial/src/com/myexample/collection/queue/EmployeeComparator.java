package com.myexample.collection.queue;

import java.util.Comparator;

public class EmployeeComparator implements Comparator<Employee> {
	public static final int SORT_BY_ID = 1;
	public static final int SORT_BY_NAME = 2;
	
	int comparableAttribute;
	
	public EmployeeComparator(int sortBy) {
		switch (sortBy) {
		case SORT_BY_ID | SORT_BY_NAME:
			comparableAttribute = sortBy;
			break;

		default:
			comparableAttribute = SORT_BY_ID;
			break;
		}
	}
	
	@Override
	public int compare(Employee o1, Employee o2) {
		switch (comparableAttribute) {
		case SORT_BY_ID:
			return (o1.getId() - o2.getId());
		case SORT_BY_NAME:
			return o1.getName().compareTo(o2.getName());
		}
		return 0;
	}
	
}
