package com.myexample.amazon.quadrantqueries;

import java.util.Scanner;

public class Solution4 {

	public static void main(String[] args) {
		Scanner myScanner = new Scanner(System.in);

		/* No of points. */
		int myNumberOfPoints = myScanner.nextInt();

		Point[] myPoints = new Point[myNumberOfPoints];
		int myX;
		int myY;
		
		/* Get all the points. */
		for (int i = 0 ; i < myNumberOfPoints; i++) {
			myX = myScanner.nextInt();
			myY = myScanner.nextInt();
			myPoints[i] = new Point(myX, myY);
		}
		
		int myNumberOfQueries = myScanner.nextInt();
		
		/* Get all the queries. */
		char myQueryType;
		int myI;
		int myJ;

        long l = System.currentTimeMillis();
		
		for (int i = myNumberOfQueries; --i >= 0; ) {
			myQueryType = myScanner.next().charAt(0);
			myI = myScanner.nextInt();
			myJ = myScanner.nextInt();
			
			if (myQueryType == 'X') {
				Point.reflectOnX(myPoints, myI, myJ);
			}
			else if (myQueryType == 'Y') {
				Point.reflectOnY(myPoints, myI, myJ);
			}
			else if (myQueryType == 'C') {
				System.out.println(getQuadrantCount(myPoints, myI, myJ));
			}
		}
		
        System.out.println("Time : " + (System.currentTimeMillis() - l));

	}

	private static String getQuadrantCount(Point[] aPoints, int startI, int endJ) {
		int q1 = 0;
		int q2 = 0;
		int q3 = 0; 
		int q4 = 0;
		
		for (int i = (startI -1); i < endJ; i++) {
			if ((aPoints[i].x > 0) && (aPoints[i].y < 0)) {
				q4++;
			}
			else if ((aPoints[i].x < 0) && (aPoints[i].y < 0)) {
				q3++;
			}
			else if ((aPoints[i].x < 0) && (aPoints[i].y > 0)) {
				q2++;
			}
			else if ((aPoints[i].x > 0) && (aPoints[i].y > 0)) {
				q1++;
			}
		}
		
		return (q1 + " " + q2 + " " + q3 + " " + q4);
	}
	
	private static class Point {
		public int x;
		public int y;
		
		Point(int aX, int aY) {
			x = aX;
			y = aY;
		}
		
		@SuppressWarnings("unused")
		public int getQuadrant() {
			if ((x > 0) && (y < 0)) {
				return 4;
			}
			if ((x < 0) && (y < 0)) {
				return 3;
			}
			if ((x < 0) && (y > 0)) {
				return 2;
			}
			if ((x > 0) && (y > 0)) {
				return 1;
			}
			
			return 0;
		}
		
		public static void reflectOnX(Point[] aPoint, int startI, int endJ) {
			for (int i = (startI - 1); i < endJ; i++) {
				aPoint[i].y *= -1;
			}
		}
		
		public static void reflectOnY(Point[] aPoint, int startI, int endJ) {
			for (int i = (startI - 1); i < endJ; i++) {
				aPoint[i].x *= -1;
			}
		}
	}
}
