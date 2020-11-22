package com.myexample.junit;

import junit.framework.TestCase;

public class ACalculatorAddTest extends TestCase {
	Calculator mCalculator;
	
	public ACalculatorAddTest(String name) {
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

	public void testAdd() {
		System.out.println("Inside Add Test.");
		assertEquals(15, mCalculator.add(8, 7));
	}
}
