package com.myexample.collection.list;

import java.io.PrintStream;
import java.util.Collection;
import java.util.Collections;
import java.util.TreeSet;

public class MyMainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PrintStream myPrintStream = System.out;
		
		Collection<Employee> myEmloyeeList = new TreeSet<Employee>(new EmployeeComparator()); 
		//new LinkedList<Employee>();
		//new ArrayList<Employee>();

		myEmloyeeList.add(new Employee(1001,"Shahrukh","","Khan"));
		myEmloyeeList.add(new Employee(1005,"Katrina","","Khaif"));
		myEmloyeeList.add(new Employee(1008,"Salman","","Khan"));
		myEmloyeeList.add(new Employee(1006,"Pryanka","","Chopra"));
		myEmloyeeList.add(new Employee(1007,"Rithik","","Roshan"));
		myEmloyeeList.add(new Employee(1002,"John","P","Travolta"));
		myEmloyeeList.add(new Employee(1004,"Drew","Barry","Moore"));
		myEmloyeeList.add(new Employee(1003,"Jessica","","Alba"));
		myEmloyeeList.add(new Employee(1009,"Charlize","","Theron"));
		myEmloyeeList.add(new Employee(1010,"Rani","L","Mukharjee"));
		
		myPrintStream.println("********************************");
		myPrintStream.println("* Before Any Sorting....");
		myPrintStream.println("********************************");
		printArrayList(myEmloyeeList, myPrintStream);
		myPrintStream.println("********************************");
		myPrintStream.println("* After Internal Sorting....");
		myPrintStream.println("********************************");
		//Collections.sort(myEmloyeeList);
		printArrayList(myEmloyeeList, myPrintStream);
		myPrintStream.println("********************************");
		myPrintStream.println("* After Comparator Sorting....");
		myPrintStream.println("********************************");
		//Collections.sort(myEmloyeeList, new EmployeeComparator());
		printArrayList(myEmloyeeList, myPrintStream);
		myPrintStream.println("********************************");
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
