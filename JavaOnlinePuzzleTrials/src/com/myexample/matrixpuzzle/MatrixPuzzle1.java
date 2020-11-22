package com.myexample.matrixpuzzle;

import java.util.ArrayList;

public class MatrixPuzzle1 {

	/**
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		/* A size (n x n) matrix. */
		char[][] myCharMatrix = new char[][] {{'a', 'b', 'c', 'd'},
											  {'e', 'f', 'g', 'h'},
											  {'i', 'j', 'k', 'l'},
											  {'m', 'n', 'o', 'p'}};

		
		/* This variable is to store the list of words generated 
		 * from the matrix for a given length size. */
		ArrayList<String> myWords = new ArrayList<String> ();
		
		/* Get a list of words from char matrix into 'myWords' variable. */
		getWords(myCharMatrix, myWords, 5);
		
		/* Print the list of words. */
		for (String mySingleWord: myWords) {
			System.out.println(mySingleWord);
		}
	}

	/* Wrapper method for a recursive function call. 
	 * It can handle any matrix of size (m x n).
	 * */
	private static void getWords(char[][] aCharMatrix, 
								 ArrayList<String> aListOfWords, 
								 int aOutputLength) {
		
		/* For matrix size. */
		int yLength = aCharMatrix.length;
		int xLength = aCharMatrix[0].length;
	
		/* To build the word by traversing the matrix. */
		StringBuilder myStringBuilder = new StringBuilder();

		/* building the used indicator for matrix elements. */
		boolean[][] myUsedIndicatorArray = new boolean[yLength][xLength];
		for (int i = 0; i < yLength; i++) {
			for (int j = 0; j < xLength; j++) {
				myUsedIndicatorArray[i][j] = false;
			}
		}
		
		for (int i = 0; i < yLength; i++) {
			for (int j = 0; j < xLength; j++) {
				/* Call to recursive function starts here. 
				 * Calling the function for each element in the matrix as starting point. */
				myStringBuilder = new StringBuilder();
				recursiveGetWords(aCharMatrix, 
								  myUsedIndicatorArray, 
								  aListOfWords, 
								  myStringBuilder,
								  j,
								  i,
								  1,  /* Level must start from 1 as first call. */
								  xLength, 
								  yLength, 
								  aOutputLength);
			}
		}
		
	}
	
	/* Recursive function to get the words. */
	private static void recursiveGetWords(char[][] aCharMatrix,
										  boolean[][] aUsedIndicatorArray,
			 							  ArrayList<String> aListOfWords,
			 							  StringBuilder aStringBuilder,
			 							  int aCurrentXIndex,
			 							  int aCurrentYIndex,
			 							  int aCurrentLevel,
			 							  int aXLength,
			 							  int aYLength,
			 							  int aOutputLength) {
		
		/* If current element is already used then return. */
		if (aUsedIndicatorArray[aCurrentYIndex][aCurrentXIndex]) {
			return;
		}
		
		/* Use the current element from matrix. */
		aStringBuilder.append(aCharMatrix[aCurrentYIndex][aCurrentXIndex]);
		
		/* Check if we already have the required string length. */
		if (aCurrentLevel == aOutputLength) {
			/* Add the result word in the final result list. */
			aListOfWords.add(aStringBuilder.toString());
			/* Reduce the string size as it had one character appended in this method call. */
			aStringBuilder.setLength(aStringBuilder.length() - 1);
			return;
		}
		
		/* Used indicator for the current element in the matrix. */
		aUsedIndicatorArray[aCurrentYIndex][aCurrentXIndex] = true;
		
		/*
		 *        Up
		 *  Left   *   Right
		 *       Down
		 * */
		
		/* Check for Element to UP of current position. */
		if (aCurrentYIndex > 0) {
			recursiveGetWords(aCharMatrix, 
					          aUsedIndicatorArray, 
					          aListOfWords, 
					          aStringBuilder,
					          aCurrentXIndex, 
					          aCurrentYIndex - 1, 
					          aCurrentLevel + 1, 
					          aXLength, 
					          aYLength, 
					          aOutputLength);
		}
		
		/* Check for Element to LEFT of current position. */
		if (aCurrentXIndex > 0) {
			recursiveGetWords(aCharMatrix, 
					          aUsedIndicatorArray, 
					          aListOfWords, 
					          aStringBuilder,
					          aCurrentXIndex - 1, 
					          aCurrentYIndex,
					          aCurrentLevel + 1, 
					          aXLength, 
					          aYLength, 
					          aOutputLength);
		}
		
		/* Check for Element to DOWN of current position. */
		if (aCurrentYIndex < (aYLength - 1)) {
			recursiveGetWords(aCharMatrix, 
					          aUsedIndicatorArray, 
					          aListOfWords, 
					          aStringBuilder,
					          aCurrentXIndex, 
					          aCurrentYIndex + 1, 
					          aCurrentLevel + 1, 
					          aXLength, 
					          aYLength, 
					          aOutputLength);
		}
		
		/* Check for Element to RIGHT of current position. */
		if (aCurrentXIndex < (aXLength - 1)) {
			recursiveGetWords(aCharMatrix, 
					          aUsedIndicatorArray, 
					          aListOfWords, 
					          aStringBuilder,
					          aCurrentXIndex + 1, 
					          aCurrentYIndex,
					          aCurrentLevel + 1, 
					          aXLength, 
					          aYLength, 
					          aOutputLength);
		}
		
		/* Reduce the string size as it had one character appended in this method call. */
		aStringBuilder.setLength(aStringBuilder.length() - 1);
		/* Make the used indicator false before returning. */
		aUsedIndicatorArray[aCurrentYIndex][aCurrentXIndex] = false;
		
	}
}
