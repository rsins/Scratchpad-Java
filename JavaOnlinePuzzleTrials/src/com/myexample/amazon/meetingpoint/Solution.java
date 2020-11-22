package com.myexample.amazon.meetingpoint;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {

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
		
		//long l = System.currentTimeMillis();
		System.out.println(getMinimumSumOfTravelTime(myPoints, myNumberOfPlaces));
		//System.out.println("Time : " + (System.currentTimeMillis() - l));
	}
	
	private static long getMinimumSumOfTravelTime(Point[] aPoints, int aNumberOfPlaces) {
		// To check which points have been already covered for meeting point.
		boolean[] myPointCovered = new boolean[aNumberOfPlaces]; 
		long myTempLong = 0L;
		long myTempLong1 = 0L;
		long myTempLongResult = 0L;
		
		Arrays.fill(myPointCovered, false);
		
		/* Get middle X & Y positions. */
		myTempLong = 0L;
		myTempLong1 = 0L;
		for (int i = 0; i < aNumberOfPlaces; i++) {
			myTempLong += aPoints[i].x;
			myTempLong1 += aPoints[i].y;
		}
		int myMiddleX = (int) (myTempLong / aNumberOfPlaces);
		int myMiddleY = (int) (myTempLong1 / aNumberOfPlaces);
		// Center / Centroid / Median point
		Point myCenterPoint = new Point(myMiddleX, myMiddleY);
		Point myKeyPoint = myCenterPoint;

		Point myNextKeyPoint = null;
		
		// First nearest point.
		myTempLong1 = Point.getDistanceBetweenPoints(myKeyPoint, aPoints[0]);
		int previousI = -1;
		for (int i = 1; i < aNumberOfPlaces; i++) {
			myTempLong = Point.getDistanceBetweenPoints(myKeyPoint, aPoints[i]);
				
			if ((myTempLong <= myTempLong1) && (myPointCovered[i] == false)) {
				myTempLong1 = myTempLong;
				myNextKeyPoint = aPoints[i];
				previousI = i;
			}
		}
		myPointCovered[previousI] = true;
		myKeyPoint = myNextKeyPoint;
		
		long myTempLongResult1 = 0L;
		
		// Check for 100 points near to the center point. 
		for (int ii = 0; ii < 100; ii++) {
			myTempLong1 = Point.getDistanceBetweenPoints(myCenterPoint, aPoints[0]);
			myNextKeyPoint = aPoints[0];
			previousI = 0;
			myPointCovered[previousI] = true;
			
			myTempLongResult1 = Point.getDistanceBetweenPoints(myKeyPoint, aPoints[0]);;
			
			for (int i = 1; i < aNumberOfPlaces; i++) {
				myTempLong = Point.getDistanceBetweenPoints(myCenterPoint, aPoints[i]);
				myTempLongResult1 += Point.getDistanceBetweenPoints(myKeyPoint, aPoints[i]);
				
				// Nearest point for next key point.
				if ((myTempLong <= myTempLong1) && (myPointCovered[i] == false)) {
					myTempLong1 = myTempLong;
					myNextKeyPoint = aPoints[i];
					previousI = i;
				}
			}
			
			if ((myTempLongResult1 < myTempLongResult) || (myTempLongResult == 0L)) {
				myTempLongResult = myTempLongResult1;
			}
			
			// Prepare for the next key point to be checked for meeting point.
			myPointCovered[previousI] = true;
			myKeyPoint = myNextKeyPoint;
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
