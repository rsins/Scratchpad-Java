package com.myexample.amazon.kdifference;

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
public class Solution1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner myScanner = new Scanner(System.in);
		
		/*
		 * Reading input from STDIN.
		 */
		int myN = myScanner.nextInt();
		int myK = myScanner.nextInt();
		
		int myNumbers[] = new int[myN];
		
		/*
		 * Reading input array from STDIN.
		 */
		for (int i = 0; i < myN; i++) {
			myNumbers[i] = myScanner.nextInt();
		}
		
		System.out.println(getCountOfKDifferenceCombinations(myNumbers, myK));
		
		/*
		System.out.println(myN);
		System.out.println(myK);
		for (int myI : myNumbers) {
			System.out.println(myI);
		}
		*/
	}

	/*
	 * Using a non-recursive algorithm to get combinations of size 2 out of the input array
	 * of size N which matches the condition of K difference.
	 */
	private static int getCountOfKDifferenceCombinations(int[] aNumbers, int aKDifference) {
		final int myIndexSize = 2;
		int myCount = 0;
		int[] myIndexArray = new int[myIndexSize];
		
		for (int i = 0; i < myIndexArray.length; i++) {
			myIndexArray[i] = i;
		}
		
		/*
		 * First possible combination. 
		 */
		if ((Math.abs(aNumbers[0] - aNumbers[1]) == aKDifference)) {
			//System.out.println(aNumbers[myIndexArray[0]] + " " + aNumbers[myIndexArray[1]]);
			myCount++;
		}
		
		int myIndex;
		boolean myCombinationChanged;
		
		while (true) {
			myCombinationChanged = false;
			
			for (myIndex = (myIndexArray.length -1); (myIndex >= 0) && (!myCombinationChanged); myIndex--) {
				if (myIndexArray[myIndex] < ((aNumbers.length - 1) - (myIndexSize - 1) + myIndex)) {
					myIndexArray[myIndex] += 1;
					
					for (int i = (myIndex + 1); i < myIndexArray.length; i++) {
						myIndexArray[i] = myIndexArray[i - 1] + 1;
					}
					
					/*
					 * Other possible combinations.
					 */
					if ((Math.abs(aNumbers[myIndexArray[0]] - aNumbers[myIndexArray[1]]) == aKDifference)) {
						//System.out.println(aNumbers[myIndexArray[0]] + " " + aNumbers[myIndexArray[1]]);
						myCount++;
					}
					
					myCombinationChanged = true;
				}
			}
			
			if (!myCombinationChanged) {
				break;
			}
		}
		
		return myCount;
	}
}
