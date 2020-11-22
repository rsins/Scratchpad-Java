package com.myexample.matrixpuzzle;

import java.util.ArrayList;

public class MatrixPuzzle2 {

	/**
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		/* A size (n x n) matrix. */
		char[][] myCharMatrix = new char[][] {{'a', 'b', 'c', 'd', 'q', 'u'},
											  {'e', 'f', 'g', 'h', 'r', 'v'},
											  {'i', 'j', 'k', 'l', 's', 'w'},
											  {'m', 'n', 'o', 'p', 't', 'x'},
											  {'t', 'e', 'w', 's', 'o', 'v'}};
		
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

	/* Wrapper method for a function call for each element. 
	 * It can handle any matrix of size (m x n).
	 * */
	private static void getWords(char[][] aCharMatrix, 
								 ArrayList<String> aListOfWords, 
								 int aOutputLength) {
		
		/* For matrix size. */
		int yLength = aCharMatrix.length;
		int xLength = aCharMatrix[0].length;
	
		for (int i = 0; i < yLength; i++) {
			for (int j = 0; j < xLength; j++) {
				/* Calling the function for each element in the matrix as starting point. */
				getWordsStartingFromAnElement(aCharMatrix, 
								  			  aListOfWords, 
								  			  j,
								  			  i,
								  			  xLength, 
								  			  yLength, 
								  			  aOutputLength);
			}
		}
		
	}
	
	/* Function to get the words starting from a particular element. */
	private static void getWordsStartingFromAnElement(char[][] aCharMatrix,
			 							  ArrayList<String> aListOfWords,
			 							  int aCurrentXIndex,
			 							  int aCurrentYIndex,
			 							  int aXLength,
			 							  int aYLength,
			 							  int aOutputLength) {
		StringBuilder myStringBuilder;
		
		/* Check UP from current position. */
		if (aCurrentYIndex >= (aOutputLength - 1)) {
			myStringBuilder = new StringBuilder();
			
			for (int i = (aCurrentYIndex - aOutputLength + 1); i <= aCurrentYIndex; i++) {
				myStringBuilder.append(aCharMatrix[i][aCurrentXIndex]);
			}
			
			myStringBuilder.reverse();
			aListOfWords.add(myStringBuilder.toString());
		}
		
		/* Check LEFT from current position. */
		if (aCurrentXIndex >= (aOutputLength - 1)) {
			myStringBuilder = new StringBuilder();
			
			for (int j = (aCurrentXIndex - aOutputLength + 1); j <= aCurrentXIndex; j++) {
				myStringBuilder.append(aCharMatrix[aCurrentYIndex][j]);
			}
			
			myStringBuilder.reverse();
			aListOfWords.add(myStringBuilder.toString());
		}
		
		/* Check DOWN from current position. */
		if (aCurrentYIndex <= (aYLength - aOutputLength)) {
			myStringBuilder = new StringBuilder();
			
			for (int i = aCurrentYIndex; i <= (aCurrentYIndex + aOutputLength - 1); i++) {
				myStringBuilder.append(aCharMatrix[i][aCurrentXIndex]);
			}
			
			aListOfWords.add(myStringBuilder.toString());
		}
		
		/* Check RIGHT from current position. */
		if (aCurrentXIndex <= (aXLength - aOutputLength)) {
			myStringBuilder = new StringBuilder();
			
			for (int j = aCurrentXIndex; j <= (aCurrentXIndex + aOutputLength - 1); j++) {
				myStringBuilder.append(aCharMatrix[aCurrentYIndex][j]);
			}
			
			aListOfWords.add(myStringBuilder.toString());
		}
	}
}
