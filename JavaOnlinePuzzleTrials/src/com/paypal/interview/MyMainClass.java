package com.paypal.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyMainClass {

	public static void main(String[] args) {
		question1();
		question2();

	}

	// With an int array arr[] and a number X, check for pair in arr[] with sum as X
	private static void question1() { question1Impl(); }
	
	// Given 2D Array of only 0s and 1s, Find the row which gives max decimal value.
	private static void question2() { question2Impl(); }

	// ---------------------------------------------------------------------------------
	private static void question1Impl() {
		int[] arr = { 1 ,6 , 3, 0, 2, -3 , 8 };
		int x = 3;
		
		Arrays.sort(arr);
		List<int[]> resultArrList =  new ArrayList<int[]>();
		
		for (int i = 0; i < (arr.length-1); i++) {
			findPairsImpl(arr, x, resultArrList, i, arr.length, i);
		}
		
		
		// Result printing.
		System.out.print("Original Array: [ ");
		for (int i = 0; i < (arr.length-1); i++) {
			System.out.print(arr[i] + ", ");
		}
		System.out.println(arr[arr.length-1] + " ]");
		
		System.out.println("Resulting Pairs: ");
		for (int[] ar : resultArrList) {
			System.out.println("* " + ar[0] + ", " + ar[1]);
		}
	}
	
	private static void findPairsImpl(int[] arr, int x, List<int[]> resultArrList, int start, int end, int pivot) {
		if (start == end) return;
		int secondIndex = ((end-start) == 1) ? end : (start + end) / 2;
		int sum = arr[pivot] + arr[secondIndex];
		
		if ((start == secondIndex) || (end == secondIndex)) return;
		
		if (sum == x) {
			resultArrList.add(new int[] {arr[pivot], arr[secondIndex]});
		}
		else if (sum > x) {
			findPairsImpl(arr, x, resultArrList, start, secondIndex, pivot);
		}
		else if (sum < x) {
			findPairsImpl(arr, x, resultArrList, secondIndex, end, pivot);
		}
	}

	// ---------------------------------------------------------------------------------
	private static void question2Impl() {
		int arr[][] = {{0,1,0}, {1,1,0}, {1,0,1}};
		
		List<int[]> resultArr = new ArrayList<>();
		for (int[] el : arr) {
			resultArr.add(el);
		}
		for (int j = 0; j < arr[0].length; j++) {
			resultArr = findMaxDecimalValueImpl(resultArr, j);
			if (resultArr.size() <= 1) break;
		}
		
		// Result printing.
		System.out.print("Original Array: [ ");
		for (int i = 0; i < (arr.length-1); i++) {
			System.out.print("[ ");
			for (int j = 0; j < (arr[i].length-1); j++) {
				System.out.print(arr[i][j] + ", ");
			}
			System.out.print(arr[i][arr[i].length-1] + " ], ");
		}
		System.out.print("[ ");
		for (int j = 0; j < (arr.length-1); j++) {
			System.out.print(arr[arr.length-1][j] + ", ");
		}
		System.out.println(arr[arr.length-1][arr[arr.length-1].length-1] + " ]]");
		
		System.out.println("Resulting array: ");
		for (int[] ar : resultArr) {
			System.out.print("* ");
			for (int i = 0; i < (ar.length-1); i++) {
				System.out.print(ar[i] + ", ");
			}
			System.out.print(ar[ar.length-1]);
		}
	}
	
	private static List<int[]> findMaxDecimalValueImpl(List<int[]> arr, int level) {
		int maxDigit = 0;
		List<int[]> newArrList = new ArrayList<int[]> ();
		for (int i = 0; i < arr.size(); i++) {
			if (arr.get(i)[level] < maxDigit) {
				continue;
			}
			if (arr.get(i)[level] > maxDigit) {
				newArrList.clear();
				maxDigit = arr.get(i)[level];
			}
			newArrList.add(arr.get(i));
		}
		
		return newArrList;
	}
}
