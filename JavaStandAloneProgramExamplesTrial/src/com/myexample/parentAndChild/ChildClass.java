package com.myexample.parentAndChild;

public class ChildClass extends ParentClass {
	public int i = 10;
	public int j = 5;
	
	public void printI() {
		System.out.println(">> ---------------- Start Child Method i.");
		System.out.println(">> Child: (i, j) = (" + i + ", " + j + ")");
		System.out.println(">> Calling parent's printI method in child method --> ");
		super.printI();
		System.out.println(">> ---------------- End Child Method i.");
	}
	
	public void printJ() {
		System.out.println(">> ---------------- Start Child Method j.");
		System.out.println(">> Child: (j) = (" + j + ")");
		System.out.println(">> ---------------- End Child Method j.");
	}
}
