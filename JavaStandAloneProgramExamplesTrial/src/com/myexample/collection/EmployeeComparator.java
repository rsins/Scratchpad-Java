package com.myexample.collection;

import java.util.Comparator;

public class EmployeeComparator implements Comparator<Employee> {

	@Override
	public int compare(Employee aEmployee1, Employee aEmployee2) {
		return (aEmployee1.getEmployeeID() - aEmployee2.getEmployeeID());
	}

}
