package com.myexample.junit;

import java.io.PrintStream;

public class MyMainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PrintStream myPrintStream = System.out;
		Calculator myCalculator = new Calculator();
		
		myPrintStream.println("Calculator Add Result: " + myCalculator.add(5, 8));
	}

}
