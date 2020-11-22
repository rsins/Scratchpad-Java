package com.myexample.amazon.quadrantqueries;

import java.util.Scanner;

public class Solution3 {

	public static void main(String[] args) {
		Scanner myScanner = new Scanner(System.in);

		/* No of points. */
		int myNumberOfPoints = myScanner.nextInt();

		int[] myQuadrantInfo = new int[myNumberOfPoints];
		Point[] myPoints = new Point[myNumberOfPoints];
		int myX;
		int myY;
		
		/* Get all the points. */
		for (int i = 0; i < myNumberOfPoints; i++) {
			myX = myScanner.nextInt();
			myY = myScanner.nextInt();
			myPoints[i] = new Point(myX, myY);
			myQuadrantInfo[i] = Point.getQuadrant(myPoints[i]);
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
				Point.reflectOnX(myPoints, myQuadrantInfo, myI, myJ);
			}
			if (myQueryType.equals("Y")) {
				Point.reflectOnY(myPoints, myQuadrantInfo, myI, myJ);
			}
			if (myQueryType.equals("C")) {
				System.out.println(getQuadrantCount(myQuadrantInfo, myI, myJ));
			}
		}
		
        System.out.println("Time : " + (System.currentTimeMillis() - l));

	}

	private static String getQuadrantCount(int[] aQuadrantInfo, int startI, int endJ) {
		int q1 = 0;
		int q2 = 0;
		int q3 = 0; 
		int q4 = 0;
		
		int currentQ;
		for (int i = (startI -1); i < endJ; i++) {
			currentQ = aQuadrantInfo[i];
			
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
		
		return (q1 + " " + q2 + " " + q3 + " " + q4);
	}
	
	private static class Point implements Comparable<Point> {
		public int x;
		public int y;
		
		Point(int aX, int aY) {
			x = aX;
			y = aY;
		}
		
		public static void reflectOnX(Point[] aPoint, int[] aQuadrantInfo, int startI, int EndJ) {
			for (int i = (startI - 1); i < EndJ; i++) {
				aPoint[i].y *= -1;
				//aQuadrantInfo[i] = Point.getQuadrant(aPoint[i]);
				switch (aQuadrantInfo[i]) {
				case 1:
					aQuadrantInfo[i] = 4;
					break;

				case 2:
					aQuadrantInfo[i] = 3;
					break;
				
				case 3:
					aQuadrantInfo[i] = 2;
					break;
					
				case 4:
					aQuadrantInfo[i] = 1;
					break;
				}
			}
		}
		
		public static void reflectOnY(Point[] aPoint, int[] aQuadrantInfo, int startI, int EndJ) {
			for (int i = (startI - 1); i < EndJ; i++) {
				aPoint[i].x *= -1;
				//aQuadrantInfo[i] = Point.getQuadrant(aPoint[i]);
				switch (aQuadrantInfo[i]) {
				case 1:
					aQuadrantInfo[i] = 4;
					break;

				case 2:
					aQuadrantInfo[i] = 3;
					break;
				
				case 3:
					aQuadrantInfo[i] = 2;
					break;
					
				case 4:
					aQuadrantInfo[i] = 1;
					break;
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
