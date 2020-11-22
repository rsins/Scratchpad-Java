package com.myexample.junit;

import java.io.PrintStream;

public class ACalculatorTestMainClass {

	public static void main(String[] args) {
		PrintStream myPrintstream = System.out;
		
		myPrintstream.println("**** Running the test suite.......");
		junit.textui.TestRunner.run(ACalculatorTestSuite.suite());
		
		myPrintstream.println("**** Running the test with all the tests methods.......");
		junit.textui.TestRunner.run(ACalculatorAllTests.class);
	}
}
