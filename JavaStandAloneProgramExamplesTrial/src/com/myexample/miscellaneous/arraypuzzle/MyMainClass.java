package com.myexample.miscellaneous.arraypuzzle;

import java.io.PrintStream;

public class MyMainClass {

	/**
	 * Consider array [-1,-2,-1,0,4,6,2,1,3]. Now write an 
	 * algorithm to find the pair of all possible values 
  	 * which on adding returns the number  2.
	 * @param args
	 */
	public static void main(String[] args) {
		PrintStream myPrintStream = System.out;
		int[] myArray = new int[] { -1, -2, -1, 0, 4, 6, 2, 1, 3};

		/* ######################### */
		myPrintStream.println("** Recursive print including duplicates. ");
		ArrayPuzzle.recursivePrintResultIncludingDuplicates(myPrintStream, myArray, 2, 2);
		
		/* ######################### */
		myPrintStream.println("** non recursive print excluding duplicates. ");
		ArrayPuzzle.nonRecursivePrintResultExcludingDuplicates(myPrintStream, myArray, 2, 2);
	}

}
