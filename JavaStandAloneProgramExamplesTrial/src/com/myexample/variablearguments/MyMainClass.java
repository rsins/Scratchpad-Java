package com.myexample.variablearguments;

import java.io.PrintStream;

public class MyMainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PrintStream myPrintStream = System.out;
		
		myPrintStream.println(getTotal("Nothing to be added"));
		myPrintStream.println(getTotal("Something to be added as parameters", 1, 5, 6, 7, 8));
		
		int arr[] = {1, 5, 7 ,8 ,9};
		myPrintStream.println(getTotal("Something to be added as array", arr));
	}
	
	private static String getTotal(String aText, int... aNumbers) {
		StringBuilder myStringBuilder = new StringBuilder();
		long myTotal = 0;
		
		myStringBuilder.append(aText);
		myStringBuilder.append(": ");
		
		for (int number: aNumbers) {
			myTotal += number;
		}
		
		myStringBuilder.append(myTotal);
		
		return myStringBuilder.toString();
	}
}
