package com.myexample.miscellaneous.matrix;

import java.io.PrintStream;


public class Matrix <T extends Number> {
	private int mWidth;
	private int mHeight;
	
	private T[][] mMatrixArray;
	
	public Matrix() {
		
	}
	
	public Matrix(T[][] aMatrixArray) {
		mWidth = aMatrixArray[0].length;
		mHeight = aMatrixArray.length;
		mMatrixArray = aMatrixArray;
	}

	public int getWidth() {
		return mWidth;
	}

	public int getHeight() {
		return mHeight;
	}
	
	public T[][] getMatrixArray() {
		return mMatrixArray;
	}

	@Override
	public String toString() {
		StringBuilder myStringBuilder = new StringBuilder();
		
		myStringBuilder.append("{");
		
		for (int i = 0; i < mHeight; i++) {
			myStringBuilder.append("(");
			for (int j = 0; j < mWidth; j++) {
				myStringBuilder.append(mMatrixArray[i][j]);
				
				if (j < (mWidth - 1)) {
					myStringBuilder.append(",");
				}
			}
			myStringBuilder.append(")");
		}
		
		myStringBuilder.append("}");
		
		return myStringBuilder.toString();
	}
	
	public void print(PrintStream aPrintStream) {
		aPrintStream.printf("~~ Matrix Array (height, width) : (%d, %d)\n", mHeight, mWidth);
		for (int i = 0; i < mHeight; i++) {
			for (int j = 0; j < mWidth; j++) {
				aPrintStream.printf("%s",mMatrixArray[i][j].toString());
				
				if (j < (mWidth - 1)) {
					aPrintStream.printf(" , ");
				}
				else {
					aPrintStream.printf("\n");
				}
			}
			
		}
	}
	
	public static Matrix<Integer> multiply(Matrix<Integer> aMatrix1, Matrix<Integer> aMatrix2) {
		if (aMatrix1.getWidth() != aMatrix2.getHeight()) {
			return null;
		}
		
		Integer[][] myResultMatrixArray = new Integer[aMatrix1.getHeight()][aMatrix2.getWidth()];
	
		for (int i = 0; i < aMatrix1.getHeight(); i++) {
			for (int j = 0; j < aMatrix2.getWidth(); j++) {
				int myDotProductResult = 0;
				
				for (int k = 0; k < aMatrix1.getWidth(); k++) {
					myDotProductResult += aMatrix1.mMatrixArray[i][k].intValue() * aMatrix2.mMatrixArray[k][j].intValue();
				}
				
				myResultMatrixArray[i][j] = myDotProductResult;
			}
		}
		
		return new Matrix<Integer> (myResultMatrixArray);
	}
}
