package com.myexample.amazon.meetingpoint;

import java.util.Scanner;

public class Solution3 {

	public static void main(String[] args) {
		Scanner myScanner = new Scanner(System.in);

		/* No of points. */
		int myNumberOfPlaces = myScanner.nextInt();
		
		int[] myX = new int[myNumberOfPlaces];
		int[] myY = new int[myNumberOfPlaces];
		
		/* Get all the points. */
		for (int i = 0; i < myNumberOfPlaces; i++) {
			myX[i] = myScanner.nextInt();
			myY[i] = myScanner.nextInt();
		}
		
		long l = System.currentTimeMillis();
		System.out.println(getMinimumSumOfTravelTime(myX, myY, myNumberOfPlaces));
		System.out.println("Time : " + (System.currentTimeMillis() - l));
	}
	
	private static long getMinimumSumOfTravelTime(int[] aX, int[] aY, int aNumberOfPlaces) {
		long[] mySummationArray = new long[aNumberOfPlaces];
		long myTempLong;
		
		for (int i1 = 0; i1 < aNumberOfPlaces; i1++) {
			for (int i2 = (i1+1); i2 < aNumberOfPlaces; i2++) {
				myTempLong = Math.max(Math.abs(aX[i1] - aX[i2]), Math.abs(aY[i1] - aY[i2]));
				mySummationArray[i1] += myTempLong;
				mySummationArray[i2] += myTempLong;
			}
		}

		// Get the minimum ..
		myTempLong = mySummationArray[0];
		for (int i = 1; i < aNumberOfPlaces; i++) {
			myTempLong = Math.min(mySummationArray[i], myTempLong);
		}
		
		return myTempLong;
	}
}
