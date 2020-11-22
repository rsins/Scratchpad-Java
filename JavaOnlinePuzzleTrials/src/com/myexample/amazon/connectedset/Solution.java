package com.myexample.amazon.connectedset;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {

/* 
Given a 2–d matrix , which has only 1’s and 0’s in it. Find the total number of connected sets in that matrix.
 
 
Explanation:
Connected set can be defined as group of cell(s) which has 1 mentioned on it and have at least one other cell in that set with which they share the neighbor relationship. A cell with 1 in it and no surrounding neighbor having 1 in it can be considered as a set with one cell in it. Neighbors can be defined as all the cells adjacent to the given cell in 8 possible directions ( i.e N , W , E , S , NE , NW , SE , SW direction ). A cell is not a neighbor of itself.
 
 
Input format :
 
First line of the input contains T , number of test-cases.
Then follow T testcases. Each testcase has given format.
N [ representing the dimension of the matrix N X N ].
Followed by N lines , with N numbers on each line.
 
 
 
Ouput format :
 
For each test case print one line ,  number of connected component it has.
 
Sample Input :
 
4
4
0 0 1 0
1 0 1 0
0 1 0 0
1 1 1 1
4
1 0 0 1
0 0 0 0
0 1 1 0
1 0 0 1
5
1 0 0 1 1
0 0 1 0 0
0 0 0 0 0
1 1 1 1 1
0 0 0 0 0
8
0 0 1 0 0 1 0 0
1 0 0 0 0 0 0 1
0 0 1 0 0 1 0 1
0 1 0 0 0 1 0 0
1 0 0 0 0 0 0 0
0 0 1 1 0 1 1 0
1 0 1 1 0 1 1 0
0 0 0 0 0 0 0 0
 
Sample output :
 
1
3
3
9
 
Constraint :
 
0 < T < 6 
0 < N < 1009 */
	
	public static void main(String[] args) {
		Scanner myScanner = new Scanner(System.in);
		
		int myT = myScanner.nextInt();
		
		int myN = 0;
		
		for (int i = 0; i < myT; i++) {
			myN = myScanner.nextInt();
			
			int myMatrix[][] = new int[myN][myN];
			
			for (int j = 0; j < myN; j++) {
				for (int k = 0; k < myN; k++) {
					myMatrix[j][k] = myScanner.nextInt();
				}
			}
			
			System.out.println(getConnectedSetCount(myMatrix));
		}
		
		myScanner.close();
	}

	private static int getConnectedSetCount(int aMatrix[][]) {
		int mySetCount = 0;
		boolean myMarkerMatrix[][] = new boolean[aMatrix.length][aMatrix[0].length]; 

		for (int i = 0; i < aMatrix.length; i++) {
			Arrays.fill(myMarkerMatrix[i], false);
		}
		
		for (int j = 0; j < aMatrix.length; j++) {
			for (int k = 0; k < aMatrix[0].length; k++) {
				if ((aMatrix[j][k] == 1) && (myMarkerMatrix[j][k] == false)) {
					mySetCount++;
					
					markConnectedSet(aMatrix, myMarkerMatrix, j, k);
				}
			}
		}
		
		return mySetCount;
	}
	
	private static void markConnectedSet(int aMatrix[][], boolean aMarkerMatrix[][], int j, int k) {
		if ((j < 0) || (j >= aMarkerMatrix.length) || 
		    (k < 0) || (k >= aMarkerMatrix[0].length)) {
			return;
		}
		
		if ((aMatrix[j][k] == 1) && (aMarkerMatrix[j][k] == false)) {
			aMarkerMatrix[j][k] = true;
			
			markConnectedSet(aMatrix, aMarkerMatrix, j - 1, k);
			markConnectedSet(aMatrix, aMarkerMatrix, j + 1, k);
			markConnectedSet(aMatrix, aMarkerMatrix, j, k - 1);
			markConnectedSet(aMatrix, aMarkerMatrix, j, k + 1);
			
			markConnectedSet(aMatrix, aMarkerMatrix, j - 1, k - 1);
			markConnectedSet(aMatrix, aMarkerMatrix, j - 1, k + 1);
			markConnectedSet(aMatrix, aMarkerMatrix, j + 1, k + 1);
			markConnectedSet(aMatrix, aMarkerMatrix, j + 1, k - 1);
		}
	}
}
