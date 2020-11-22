package com.myexample.amazon.meetingpoint;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class Solution2 {

	public static void main(String[] args) {
		Scanner myScanner = new Scanner(new BufferedInputStream(System.in));
		String myInputLine = myScanner.nextLine();
		Scanner myLineScanner = new Scanner(myInputLine);

		/* No of points. */
		int myNumberOfPlaces = myLineScanner.nextInt();
		
		int[] myX = new int[myNumberOfPlaces];
		int[] myY = new int[myNumberOfPlaces];
		
		/* Get all the points. */
		for (int i = 0; i < myNumberOfPlaces; i++) {
			myInputLine = myScanner.nextLine();
			myLineScanner = new Scanner(myInputLine);
			
			myX[i] = myLineScanner.nextInt();
			myY[i] = myLineScanner.nextInt();
		}
		
		long l = System.currentTimeMillis();
		System.out.println(getMinimumSumOfTravelTime(myX, myY, myNumberOfPlaces));
		System.out.println("Time : " + (System.currentTimeMillis() - l));
	}
	
	private static long getMinimumSumOfTravelTime(int[] aX, int[] aY, int aNumberOfPlaces) {
		long myMinimumSumOfTravelTime = 0L;
		long myCurrentSumOfTravelTime = 0L;
		
		for (int i1 = 0; i1 < aNumberOfPlaces; i1++) {
			myCurrentSumOfTravelTime = 0L;
			
			for (int i2 = 0; i2 < aNumberOfPlaces; i2++) {
				if (i1 == i2) {
					continue;
				}
				
				myCurrentSumOfTravelTime += Math.max(Math.abs(aX[i1] - aX[i2]), 
													 Math.abs(aY[i1] - aY[i2]));
			}
			
			//System.out.println(myCurrentSumOfTravelTime);
			
			if (myCurrentSumOfTravelTime < myMinimumSumOfTravelTime) {
				myMinimumSumOfTravelTime = myCurrentSumOfTravelTime;
			}
			else if (myMinimumSumOfTravelTime == 0) {
				myMinimumSumOfTravelTime = myCurrentSumOfTravelTime;
			}
		}
		
		return myMinimumSumOfTravelTime;
	}
}
