package com.myexample.amazon.quadrantqueries;

import java.util.ArrayList;
import java.util.Scanner;

/*********************************** IN PROGRESS ***************************************/
public class Solution {

	public static void main(String[] args) {
		Scanner myScanner = new Scanner(System.in);

		/* No of points. */
		int myNumberOfPoints = myScanner.nextInt();

		Point[] myPoints = new Point[myNumberOfPoints];
		
		/* Get all the points. */
		for (int i = 0 ; i < myNumberOfPoints; i++) {
			myPoints[i] = new Point(myScanner.nextInt(), myScanner.nextInt());
		}
		
		int myNumberOfQueries = myScanner.nextInt();
		
		/* Get all the queries. */
		StringBuilder myStringBuilder = new StringBuilder();
		ArrayList<int[]> myQueryArrayList = new ArrayList<int []>();
		ArrayList<int[]> myProcessedXYCommands = new ArrayList<int []>();
		
		long l = System.currentTimeMillis();
		
		for (int i = myNumberOfQueries; --i >= 0; ) {
			myQueryArrayList.add(new int[] {(int) myScanner.next().charAt(0), myScanner.nextInt(), myScanner.nextInt()});
		}
		
		char myQueryType;		
		for (int[] myQuery : myQueryArrayList ) {
			myQueryType = (char) myQuery[0];

			if (myQueryType == 'X') {
				myProcessedXYCommands = prepareForReflectOnXY(myProcessedXYCommands, myQuery);
			}
			else if (myQueryType == 'Y') {
				myProcessedXYCommands = prepareForReflectOnXY(myProcessedXYCommands, myQuery);
			}
			else if (myQueryType == 'C') {
				doReflectOnXY(myPoints, myProcessedXYCommands);
				myProcessedXYCommands = new ArrayList<int []>();
				
				myStringBuilder.append("\n");
				myStringBuilder.append(getQuadrantCount(myPoints, myQuery[1], myQuery[2]));
			}
		}
		myStringBuilder.delete(0, 1);
		System.out.println(myStringBuilder.toString());
				
		System.out.println("Time : " + (System.currentTimeMillis() - l));

	}

	private static String getQuadrantCount(Point[] aPoints, int startI, int endJ) {
		int q1 = 0;
		int q2 = 0;
		int q3 = 0; 
		int q4 = 0;
		
		int ax;
		int ay;

		for (int i = (startI -1); i < endJ; i++) {
			ax = aPoints[i].x;
			ay = aPoints[i].y;
			
			if ((ax > 0) && (ay < 0)) {
				q4++;
			}
			else if ((ax < 0) && (ay < 0)) {
				q3++;
			}
			else if ((ax < 0) && (ay > 0)) {
				q2++;
			}
			else if ((ax > 0) && (ay > 0)) {
				q1++;
			}
		}
		
		return (q1 + " " + q2 + " " + q3 + " " + q4);
	}
	
	private static ArrayList<int[]> prepareForReflectOnXY(ArrayList<int[]> aProcessedXYCommands, int[] aCurrentQuery) {
		ArrayList<int[]> myNewProcessedXYCommands = new ArrayList<int[]> ();
		boolean addCurrentQuery = true;
		
		for (int[] myOldQuery : aProcessedXYCommands) {
			if (myOldQuery == aCurrentQuery) {
				myNewProcessedXYCommands.add(myOldQuery);
			}
			else if (myOldQuery[0] != aCurrentQuery[0]) {
				myNewProcessedXYCommands.add(myOldQuery);
			}
			else if (myOldQuery[0] == aCurrentQuery[0]) {
				if (myOldQuery[1] > aCurrentQuery[2]) {
					myNewProcessedXYCommands.add(myOldQuery);
				}
				else if (myOldQuery[2] < aCurrentQuery[1]) {
					myNewProcessedXYCommands.add(myOldQuery);
				}
				else if (myOldQuery[1] == aCurrentQuery[2]) {
					myNewProcessedXYCommands.add(new int[] {aCurrentQuery[0], aCurrentQuery[1], myOldQuery[2]});
					addCurrentQuery = false;
				}
				else if (myOldQuery[2] == aCurrentQuery[1]) {
					myNewProcessedXYCommands.add(new int[] {aCurrentQuery[0], myOldQuery[1], aCurrentQuery[2]});
					addCurrentQuery = false;
				}
				else if ((myOldQuery[1] > aCurrentQuery[1]) && (myOldQuery[2] > aCurrentQuery[2])) {
					myNewProcessedXYCommands.add(new int[] {aCurrentQuery[0], aCurrentQuery[1], myOldQuery[1]});
					myNewProcessedXYCommands.add(new int[] {aCurrentQuery[0], aCurrentQuery[2], myOldQuery[2]});
					addCurrentQuery = false;
				}
				else if ((myOldQuery[1] < aCurrentQuery[1]) && (myOldQuery[2] > aCurrentQuery[2])) {
					myNewProcessedXYCommands.add(new int[] {aCurrentQuery[0], myOldQuery[1], aCurrentQuery[1]});
					myNewProcessedXYCommands.add(new int[] {aCurrentQuery[0], aCurrentQuery[2], myOldQuery[2]});
					addCurrentQuery = false;
				}
				else if ((myOldQuery[1] < aCurrentQuery[1]) && (myOldQuery[2] < aCurrentQuery[2])) {
					myNewProcessedXYCommands.add(new int[] {aCurrentQuery[0], myOldQuery[1], aCurrentQuery[1]});
					myNewProcessedXYCommands.add(new int[] {aCurrentQuery[0], myOldQuery[2], aCurrentQuery[2]});
					addCurrentQuery = false;
				}
				
				if (!addCurrentQuery) {
					break;
				}
			}
		}
		
		if (addCurrentQuery) {
			myNewProcessedXYCommands.add(aCurrentQuery);
		}
		
		return myNewProcessedXYCommands;
	}
	
	private static void doReflectOnXY(Point[] aPoints, ArrayList<int[]> aProcessedXYCommands) {
		char myQueryType;
		for (int[] myQuery : aProcessedXYCommands) {
			myQueryType = (char) myQuery[0];

			if (myQueryType == 'X') {
				Point.reflectOnX(aPoints, myQuery[1], myQuery[2]);
			}
			else if (myQueryType == 'Y') {
				Point.reflectOnY(aPoints, myQuery[1], myQuery[2]);
			}
			//else if (myQueryType == 'Z') {
			//	Point.reflectOnXY(aPoints, myQuery[1], myQuery[2]);
			//}
		}
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
		
		//public static void reflectOnXY(Point[] aPoint, int startI, int endJ) {
		//	for (int i = (startI - 1); i < endJ; i++) {
		//		aPoint[i].x *= -1;
		//		aPoint[i].y *= -1;
		//	}
		//}
	}
}
