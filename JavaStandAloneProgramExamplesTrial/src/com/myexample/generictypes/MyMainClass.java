package com.myexample.generictypes;

import java.io.PrintStream;
import java.util.Collection;
import java.util.NavigableSet;
import java.util.TreeSet;

import com.myexample.collection.list.Employee;
import com.myexample.collection.list.EmployeeComparator;

public class MyMainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PrintStream myPrintStream = System.out;
		
		Collection<Employee> myEmployeeList = new TreeSet<Employee>(new EmployeeComparator()); 

		myEmployeeList.add(new Employee(1001,"Shahrukh","","Khan"));
		myEmployeeList.add(new Employee(1005,"Katrina","","Khaif"));
		myEmployeeList.add(new Employee(1008,"Salman","","Khan"));
		myEmployeeList.add(new Employee(1006,"Pryanka","","Chopra"));
		myEmployeeList.add(new Employee(1007,"Rithik","","Roshan"));
		myEmployeeList.add(new Employee(1002,"John","P","Travolta"));
		myEmployeeList.add(new Employee(1004,"Drew","Barry","Moore"));
		myEmployeeList.add(new Employee(1003,"Jessica","","Alba"));
		myEmployeeList.add(new Employee(1009,"Charlize","","Theron"));
		myEmployeeList.add(new Employee(1010,"Rani","L","Mukharjee"));
		
		myPrintStream.println("********************************");
		myPrintStream.println("* Before Any Action....");
		myPrintStream.println("********************************");
		printArrayList(myEmployeeList, myPrintStream);
		myPrintStream.println("********************************");
		myPrintStream.println("* Before using generic compare function....");
		myPrintStream.println("********************************");
		
		NavigableSet<Employee> myNavigableEmployeeSet = new TreeSet<Employee>();
		myNavigableEmployeeSet.addAll(myEmployeeList);
		
		printArrayList(myNavigableEmployeeSet, myPrintStream);
		
		myPrintStream.println("********************************");
		myPrintStream.println("* After Using generic compare function....");
		myPrintStream.println("********************************");
		
		for (Employee myEmployee : myNavigableEmployeeSet) {
			Employee myLowerEmployee = myNavigableEmployeeSet.lower(myEmployee);
			Employee myHigerEmployee = myNavigableEmployeeSet.higher(myEmployee);
			
			if (myLowerEmployee != null) {
				myPrintStream.println(compare(myEmployee, myLowerEmployee));
			}
			if (myHigerEmployee != null) {
				myPrintStream.println(compare(myEmployee, myHigerEmployee));
			}
		}
	}

	private static <T extends Comparable<? super T>> String compare(T aInput1, T aInput2) {
		String myReturnString = null;
		int myCompareResult = aInput1.compareTo(aInput2);
		
		if (myCompareResult < 0) {
			myReturnString = "'" + aInput1.toString() + "' ** is less than ** '" + aInput2.toString();
		}
		else if (myCompareResult > 0) {
			myReturnString = "'" + aInput1.toString() + "' ** is greater than ** '" + aInput2.toString();
		}
		else {
			myReturnString = "'" + aInput1.toString() + "' ** is equal to ** '" + aInput2.toString();
		}
		
		return myReturnString;
	}
	
	private static void printArrayList(Collection<Employee> anEmployeeList, PrintStream aPrintStream) {
		aPrintStream.println("* -- " 
							 + anEmployeeList.getClass().getName()
							 + "(" + Employee.class.getName() + ")");
		for (Employee myEmployee : anEmployeeList) {
			aPrintStream.println("   |-- " + myEmployee);
		}
	}
}
