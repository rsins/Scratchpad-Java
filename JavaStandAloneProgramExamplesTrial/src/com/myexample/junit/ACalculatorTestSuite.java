package com.myexample.junit;

import junit.framework.Test;
import junit.framework.TestSuite;

public class ACalculatorTestSuite {

	public static Test suite() {
		TestSuite myTestSuite = new TestSuite("Test for com.myexample.junit.Calculator");
		//$JUnit-BEGIN$
		myTestSuite.addTestSuite(ACalculatorAddTest.class);
		myTestSuite.addTestSuite(ACalculatorDivideTest.class);
		myTestSuite.addTestSuite(ACalculatorSubtractTest.class);
		myTestSuite.addTestSuite(ACalculatorMultiplyTest.class);
		//$JUnit-END$
		return myTestSuite;
	}

}
