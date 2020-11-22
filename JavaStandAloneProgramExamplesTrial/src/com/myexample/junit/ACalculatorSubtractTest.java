package com.myexample.junit;

import junit.framework.TestCase;

public class ACalculatorSubtractTest extends TestCase {
	Calculator mCalculator;
	
	public ACalculatorSubtractTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		mCalculator = new Calculator();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		mCalculator = null;
	}

	public void testSubtract() {
		System.out.println("Inside Subtract Test.");
		assertEquals(12, mCalculator.subtract(17, 5));
	}

}
