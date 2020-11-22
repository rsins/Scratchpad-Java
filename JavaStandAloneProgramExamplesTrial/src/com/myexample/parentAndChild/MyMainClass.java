package com.myexample.parentAndChild;

public class MyMainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ParentClass myParentClass = new ChildClass();
		
		// Parent's member is used by JVM to return the value.
		System.out.println("Main Class --> myParentClass.i = " + myParentClass.i);
		
		// Child's member 'j' will not be accessible here.
		//System.out.println(myParentClass.j);
		
		// Child's member 'i' will be accessible here after type casting.
		System.out.println("Main Class --> ((ChildClass)myParentClass).i = " + ((ChildClass)myParentClass).i);
		
		// Child's member 'j' will be accessible here after type casting.
		System.out.println("Main Class --> ((ChildClass)myParentClass).j = " + ((ChildClass)myParentClass).j);
		
		// Child's method is used to call the method by JVM.
		myParentClass.printI();
		
		((ChildClass)myParentClass).printJ();
	}
}
