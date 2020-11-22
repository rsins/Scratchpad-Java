package com.myexample.miscellaneous.matrix;

import java.io.PrintStream;

public class MyMainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PrintStream myPrintStream = System.out;

		/* Matrix 1 */
		Integer[][] myIntegerMatrixArray1 = {{14,  9,  3}, 
									         { 2, 11, 15},
										     { 0, 12, 17},
										     { 5,  2,  3}};
		Matrix<Integer> myMatrix1 = new Matrix<Integer>(myIntegerMatrixArray1);
		myMatrix1.print(myPrintStream);
		myPrintStream.println(myMatrix1.toString());

		/* Matrix 2 */
		Integer[][] myIntegerMatrixArray2 = {{12, 25}, 
									         { 9, 10},
										     { 8,  5}};
		Matrix<Integer> myMatrix2 = new Matrix<Integer>(myIntegerMatrixArray2);
		myPrintStream.println();
		myMatrix2.print(myPrintStream);
		myPrintStream.println(myMatrix2.toString());
		
		/* Multiplication result matrix */
		Matrix<Integer> myMultiplicationMatrix = Matrix.multiply(myMatrix1, myMatrix2);
		myPrintStream.println();
		myMultiplicationMatrix.print(myPrintStream);
		myPrintStream.println(myMultiplicationMatrix.toString());
		
	}

}
