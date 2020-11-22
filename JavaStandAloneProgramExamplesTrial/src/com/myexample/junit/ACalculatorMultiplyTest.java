package com.myexample.junit;

import junit.framework.TestCase;

public class ACalculatorMultiplyTest extends TestCase {
	Calculator mCalculator;
	
	public ACalculatorMultiplyTest(String name) {
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

	public void testMultiply() {
		System.out.println("Inside Multiply Test.");
		assertEquals(36, mCalculator.multiply(4, 9));
	}

}
