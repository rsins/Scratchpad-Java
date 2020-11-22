package com.myexample.junit;

import junit.framework.TestCase;

public class ACalculatorDivideTest extends TestCase {
	Calculator mCalculator;
	
	public ACalculatorDivideTest(String name) {
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

	public void testDivide() {
		System.out.println("Inside Divide Test.");
		assertEquals(3, mCalculator.divide(6, 2));
	}

}
