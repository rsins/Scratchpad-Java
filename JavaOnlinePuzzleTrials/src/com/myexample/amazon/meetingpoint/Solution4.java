package com.myexample.amazon.meetingpoint;

import java.util.Scanner;

public class Solution4 {

	public static void main(String[] args) {
		Scanner myScanner = new Scanner(System.in);

		/* No of points. */
		int myNumberOfPlaces = myScanner.nextInt();

		Point[] myPoints = new Point[myNumberOfPlaces];
		int myX;
		int myY;
		
		/* Get all the points. */
		for (int i = 0; i < myNumberOfPlaces; i++) {
			myX = myScanner.nextInt();
			myY = myScanner.nextInt();
			myPoints[i] = new Point(myX, myY);
		}
		
		long l = System.currentTimeMillis();
		System.out.println(getMinimumSumOfTravelTime(myPoints, myNumberOfPlaces));
		System.out.println("Time : " + (System.currentTimeMillis() - l));
	}
	
	/*
	private static long getMinimumSumOfTravelTime(Point[] aPoints, int aNumberOfPlaces) {
		long[] mySummationArray = new long[aNumberOfPlaces];
		long myTempLong;
		long myTempLongResult;
		
		// Start from middle of index to both sides of array.
		for (int i1 = 0, i2 = (aNumberOfPlaces - 1); (i1 <= i2); i1++, i2--) {
			
			myTempLong = Point.getDistanceBetweenPoints(aPoints[i1], aPoints[i2]);
			mySummationArray[i1] += myTempLong;
			mySummationArray[i2] += myTempLong;
			
			for (int i3 = (i1 + 1); (i3 < i2); i3++) {
				myTempLong = Point.getDistanceBetweenPoints(aPoints[i1], aPoints[i3]);
				mySummationArray[i1] += myTempLong;
				mySummationArray[i3] += myTempLong;
				myTempLong = Point.getDistanceBetweenPoints(aPoints[i2], aPoints[i3]);
				mySummationArray[i2] += myTempLong;
				mySummationArray[i3] += myTempLong;
			}
		}
		
		myTempLongResult = mySummationArray[0];
		for (int i1 = 1; i1 < aNumberOfPlaces; i1++) {
			myTempLongResult = Math.min(mySummationArray[i1], myTempLongResult);
		}
		
		return myTempLongResult;
	}
	*/
	
	private static long getMinimumSumOfTravelTime(Point[] aPoints, int aNumberOfPlaces) {
		long[] mySummationArray = new long[aNumberOfPlaces];
		long myTempLong;
		long myTempLongResult;
		
		int myMiddleIndex = aNumberOfPlaces / 2;
		
		// Start from middle of index to both sides of array.
		for (int i1 = 0,                 i2 = myMiddleIndex, 
				 i3 = (myMiddleIndex+1), i4 = (aNumberOfPlaces - 1); 
				(i1 <= i2) && (i3 <= i4); i1++, i2--, i3++, i4--) {
			
			myTempLong = Point.getDistanceBetweenPoints(aPoints[i1], aPoints[i2]);
			mySummationArray[i1] += myTempLong;
			mySummationArray[i2] += myTempLong;
			
			myTempLong = Point.getDistanceBetweenPoints(aPoints[i1], aPoints[i3]);
			mySummationArray[i1] += myTempLong;
			mySummationArray[i3] += myTempLong;
			
			myTempLong = Point.getDistanceBetweenPoints(aPoints[i1], aPoints[i4]);
			mySummationArray[i1] += myTempLong;
			mySummationArray[i4] += myTempLong;
			
			myTempLong = Point.getDistanceBetweenPoints(aPoints[i2], aPoints[i3]);
			mySummationArray[i2] += myTempLong;
			mySummationArray[i3] += myTempLong;
			
			myTempLong = Point.getDistanceBetweenPoints(aPoints[i2], aPoints[i4]);
			mySummationArray[i2] += myTempLong;
			mySummationArray[i4] += myTempLong;
			
			myTempLong = Point.getDistanceBetweenPoints(aPoints[i3], aPoints[i4]);
			mySummationArray[i3] += myTempLong;
			mySummationArray[i4] += myTempLong;
			
			for (int i5 = (i1 + 1); (i5 < i2); i5++) {
				myTempLong = Point.getDistanceBetweenPoints(aPoints[i1], aPoints[i5]);
				mySummationArray[i1] += myTempLong;
				mySummationArray[i5] += myTempLong;
				myTempLong = Point.getDistanceBetweenPoints(aPoints[i2], aPoints[i5]);
				mySummationArray[i2] += myTempLong;
				mySummationArray[i5] += myTempLong;
				myTempLong = Point.getDistanceBetweenPoints(aPoints[i3], aPoints[i5]);
				mySummationArray[i3] += myTempLong;
				mySummationArray[i5] += myTempLong;
				myTempLong = Point.getDistanceBetweenPoints(aPoints[i4], aPoints[i5]);
				mySummationArray[i4] += myTempLong;
				mySummationArray[i5] += myTempLong;
			}
			
			for (int i5 = (i3 + 1); (i5 < i4); i5++) {
				myTempLong = Point.getDistanceBetweenPoints(aPoints[i1], aPoints[i5]);
				mySummationArray[i1] += myTempLong;
				mySummationArray[i5] += myTempLong;
				myTempLong = Point.getDistanceBetweenPoints(aPoints[i2], aPoints[i5]);
				mySummationArray[i2] += myTempLong;
				mySummationArray[i5] += myTempLong;
				myTempLong = Point.getDistanceBetweenPoints(aPoints[i3], aPoints[i5]);
				mySummationArray[i3] += myTempLong;
				mySummationArray[i5] += myTempLong;
				myTempLong = Point.getDistanceBetweenPoints(aPoints[i4], aPoints[i5]);
				mySummationArray[i4] += myTempLong;
				mySummationArray[i5] += myTempLong;
			}
		}
		
		myTempLongResult = mySummationArray[0];
		for (int i1 = 1; i1 < aNumberOfPlaces; i1++) {
			myTempLongResult = Math.min(mySummationArray[i1], myTempLongResult);
		}
		
		return myTempLongResult;
	}
	
	private static class Point implements Comparable<Point> {
		public int x;
		public int y;

		Point(int aX, int aY) {
			x = aX;
			y = aY;
		}
		
		public static int getDistanceBetweenPoints(Point aPoint1, Point aPoint2) {
			return Math.max(Math.abs(aPoint1.x - aPoint2.x), Math.abs(aPoint1.y - aPoint2.y));
		}

		@Override
		public int compareTo(Point aPoint) {
			return (this.x > aPoint.x) ? 1 : ((this.x < aPoint.x) ? -1 : 0);
		}
	}
}
