package com.myexample.puzzle.array1;

import java.util.Arrays;

public class MyMainClass {

	/*
	 * 	Given a number in an array form, Come up with an algorithm to push all the zeros to the end. Expectation : O(n) solution

		e.g: 
		Input: int array[] = {1,4,0,5,7,0,4,0,9}; Output: 1 4 9 5 7 4 0 0 0
		Input: int array2[] = {1,4,0,5,7,0,4,0,9,6,5,0,0,0,4,5,7,0,1,0,6,0,7,0}; Output: 1 4 7 5 7 6 4 1 9 6 5 7 5 4 0 0 0 0 0 0 0 0 0 0
	 */
	public static void main(String[] args) {
		int array1[] = {1,4,0,5,7,0,4,0,9};
		int array2[] = {1,4,0,5,7,0,4,0,9,6,5,0,0,0,4,5,7,0,1,0,6,0,7,0};

		System.out.println(Arrays.toString(moveZeroToEnd(array1)));
		System.out.println(Arrays.toString(moveZeroToEnd(array2)));
	}

	private static int[] moveZeroToEnd(int[] arr) {
		int[] oArr = new int [arr.length];
		System.arraycopy(arr, 0, oArr, 0, arr.length);
		
		for (int i=0, j=(arr.length-1); i<j; i++) {
			if (oArr[i] == 0) {
				for ( ;(oArr[j] == 0) && (i < j); j--) {}
				
				if (i < j) {
					oArr[i]=oArr[j];
					oArr[j]=0;
					j--;
				}
			}
		}
		
		return oArr;
	}
}
