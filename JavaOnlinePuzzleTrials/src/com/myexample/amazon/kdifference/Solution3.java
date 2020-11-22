package com.myexample.amazon.kdifference;

import java.io.IOException;
import java.util.Scanner;


/*
Question: K Difference (50 Points)

Given N numbers , [N<=10^5] we need to count the total pairs of numbers that have a difference of K. [K>0 and K<1e9]

Input Format:
1st line contains N & K (integers).
2nd line contains N numbers of the set. All the N numbers are assured to be distinct.
Output Format:
One integer saying the no of pairs of numbers that have a diff K.

Sample Input #00:
5 2
1 5 3 4 2

Sample Output #00:
3
 
Sample Input #01:
10 1
363374326 364147530 61825163 1073065718 1281246024 1399469912 428047635 491595254 879792181 1069262793 
 
Sample Output #01:
0
 
Note: Java/C# code should be in a class named "Solution"
Read input from STDIN and write output to STDOUT. 
*/
public class Solution3 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Scanner myScanner = new Scanner(System.in);
		String myLineString = myScanner.nextLine();
		String[] myInputNumbers = myLineString.split("\\s+");

		/*
		 * Reading input from STDIN.
		 */
		int myN = Integer.parseInt(myInputNumbers[0]);
		int myK = Integer.parseInt(myInputNumbers[1]);
		
		int myNumbers[] = new int[myN];
		
		/*
		 * Reading input array from STDIN.
		 */
		myLineString = myScanner.nextLine();
		myInputNumbers = myLineString.split("\\s+");
		for (int i = 0; i < myN; i++) {
			myNumbers[i] = Integer.parseInt(myInputNumbers[i]);
		}
		
		myLineString = null;
		myInputNumbers = null;
		
		long l = System.currentTimeMillis();
		System.out.println(getCountOfKDifferenceCombinations(myNumbers, myK));
		System.out.println(l - System.currentTimeMillis());
	}

	/*
	 * Using a non-recursive algorithm to get combinations of size 2 out of the input array
	 * of size N which matches the condition of K difference.
	 */
	private static int getCountOfKDifferenceCombinations(int[] aNumbers, int aKDifference) {
		final int myArraySize = aNumbers.length;
		int myCount = 0;
		int i1;
		int i2;
		
		for (i1 = 0; i1 < (myArraySize-1); i1++) {
			for (i2 = (i1 + 1); i2 < myArraySize; i2++) {
				if ((Math.abs(aNumbers[i1] - aNumbers[i2]) == aKDifference)) {
					//System.out.println(aNumbers[i1] + " " + aNumbers[i2]);
					myCount++;
				}
			}
		}
		
		return myCount;
	}
}
