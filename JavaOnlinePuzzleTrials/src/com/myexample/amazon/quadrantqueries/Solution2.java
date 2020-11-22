package com.myexample.amazon.quadrantqueries;

import java.util.Scanner;

public class Solution2 {

	public static void main(String[] args) {
		Scanner myScanner = new Scanner(System.in);

		/* No of points. */
		int myNumberOfPoints = myScanner.nextInt();

		Point[] myPoints = new Point[myNumberOfPoints];
		int myX;
		int myY;
		
		/* Get all the points. */
		for (int i = 0; i < myNumberOfPoints; i++) {
			myX = myScanner.nextInt();
			myY = myScanner.nextInt();
			myPoints[i] = new Point(myX, myY);
		}
		
		int myNumberOfQueries = myScanner.nextInt();
		
		/* Get all the queries. */
		String myQueryType;
		int myI;
		int myJ;

        long l = System.currentTimeMillis();
		
		for (int i = 0; i < myNumberOfQueries; i++) {
			myQueryType = myScanner.next().trim();
			myI = myScanner.nextInt();
			myJ = myScanner.nextInt();
			
			if (myQueryType.equals("X")) {
				Point.reflectOnX(myPoints, myI, myJ);
			}
			if (myQueryType.equals("Y")) {
				Point.reflectOnY(myPoints, myI, myJ);
			}
			if (myQueryType.equals("C")) {
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
		
		int currentQ;
		for (int i1=(startI-1), i2=(endJ-1); (i1 <= i2); i1++, i2--) {
			currentQ = Point.getQuadrant(aPoints[i1]);
			if (currentQ == 1) {
				q1++;
			}
			if (currentQ == 2) {
				q2++;
			}
			if (currentQ == 3) {
				q3++;
			}
			if (currentQ == 4) {
				q4++;
			}
			
			if (i1 != i2) {
				currentQ = Point.getQuadrant(aPoints[i2]);
				if (currentQ == 1) {
					q1++;
				}
				if (currentQ == 2) {
					q2++;
				}
				if (currentQ == 3) {
					q3++;
				}
				if (currentQ == 4) {
					q4++;
				}
			}
		}
		
		return (q1 + " " + q2 + " " + q3 + " " + q4);
	}
	
	private static class Point implements Comparable<Point> {
		public int x;
		public int y;
		
		Point(int aX, int aY) {
			x = aX;
			y = aY;
		}
		
		public static void reflectOnX(Point[] aPoint, int startI, int endJ) {
			for (int i1=(startI-1), i2=(endJ-1); (i1 <= i2); i1++, i2--) {
				if (i1 == i2) {
					aPoint[i2].y *= -1;
				}
				else {
					aPoint[i1].y *= -1;
					aPoint[i2].y *= -1;
				}
			}
		}
		
		public static void reflectOnY(Point[] aPoint, int startI, int endJ) {
			for (int i1=(startI-1), i2=(endJ-1); (i1 <= i2); i1++, i2--) {
				if (i1 == i2) {
					aPoint[i2].x *= -1;
				}
				else {
					aPoint[i1].x *= -1;
					aPoint[i2].x *= -1;
				}
			}
		}
		
		public static int getQuadrant(Point aPoint) {
			if ((aPoint.x > 0) && (aPoint.y < 0)) {
				return 4;
			}
			if ((aPoint.x < 0) && (aPoint.y < 0)) {
				return 3;
			}
			if ((aPoint.x < 0) && (aPoint.y > 0)) {
				return 2;
			}
			if ((aPoint.x > 0) && (aPoint.y > 0)) {
				return 1;
			}
			
			return 0;
		}
		
		@Override
		public int compareTo(Point aPoint) {
			return (this.x > aPoint.x) ? 1 : ((this.x < aPoint.x) ? -1 : 0);
		}
	}
}
