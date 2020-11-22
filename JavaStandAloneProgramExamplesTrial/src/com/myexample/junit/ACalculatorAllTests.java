package com.myexample.junit;

import java.io.PrintStream;

import junit.framework.TestCase;

public class ACalculatorAllTests extends TestCase {
	PrintStream mPrintstream = System.out;
	Calculator mCalculator;
	
	public ACalculatorAllTests(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		mCalculator = new Calculator();
		mPrintstream.println("Inside Setup method.");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		
		mCalculator = null;
		mPrintstream.println("Inside tear down method.");
	}

	public void testAdd() {
		mPrintstream.println("Inside Add Test.");
		assertEquals(15, mCalculator.add(8, 7));
	}

	public void testSubtract() {
		mPrintstream.println("Inside Subtract Test.");
		assertEquals(36, mCalculator.multiply(4, 9));
	}

	public void testMultiply() {
		mPrintstream.println("Inside Multiple Test.");
		assertEquals(12, mCalculator.subtract(17, 5));
	}

	public void testDivide() {
		mPrintstream.println("Inside Divide Test.");
		assertEquals(3, mCalculator.divide(6, 2));
	}

}
