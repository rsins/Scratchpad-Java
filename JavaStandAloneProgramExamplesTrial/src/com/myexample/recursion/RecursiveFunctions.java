package com.myexample.recursion;

import java.util.ArrayList;

public class RecursiveFunctions {
	public static long getFactorialFor(int aNumber) {
		return (aNumber == 0) ? 1 : (aNumber * getFactorialFor(aNumber - 1));
	}
	
	public static ArrayList<Integer> quickSort(ArrayList<Integer> aIntArray) {
		ArrayList<Integer> myResultArrayList = new ArrayList<Integer> ();
		ArrayList<Integer> myLeftArrayList = new ArrayList<Integer> ();
		ArrayList<Integer> myRightArrayList = new ArrayList<Integer> ();
		Integer myFirstInteger;
		
		if (aIntArray.size() > 0) {
			myFirstInteger = aIntArray.get(0);
			
			for (int i = 1; i < aIntArray.size(); i++) {
				if (myFirstInteger > aIntArray.get(i)) {
					myLeftArrayList.add(aIntArray.get(i));
				}
				else {
					myRightArrayList.add(aIntArray.get(i));
				}
			}
			
			myResultArrayList.addAll(quickSort(myLeftArrayList));
			myResultArrayList.add(myFirstInteger);
			myResultArrayList.addAll(quickSort(myRightArrayList));
		}
		
		return myResultArrayList;
	}
}
