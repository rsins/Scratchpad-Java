package com.myexample.amazon.fibonaccifactor;

import java.util.Scanner;

/*
 Given a number K, find the smallest Fibonacci number that shares a common factor( other than 1 ) with it. A number is said to be a common factor of two numbers if it exactly divides both of them. 
 
Output two separate numbers, F and D, where F is the smallest fibonacci number and D is the smallest number other than 1 which divides K and F.
 
Input Format 

First line of the input contains an integer T, the number of testcases.
Then follows T lines, each containing an integer K.
 
Output Format
 
Output T lines, each containing the required answer for each corresponding testcase.
 

Sample Input 
 
3
3
5
161
 
Sample Output
 
3 3
5 5
21 7
 
Explanation
 
There are three testcases. The first test case is 3, the smallest required fibonacci number  3. The second testcase is 5 and the third is 161. For 161 the smallest fibonacci numer sharing a common divisor with it is 21 and the smallest number other than 1 dividing 161 and 7 is 7.
 
Constraints :
 
1 <= T <= 5
2 <= K <= 1000,000
The required fibonacci number is guranteed to be less than 10^18. 
*/
public class Solution {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner myScanner = new Scanner(System.in);

		/*
		 * Reading input from STDIN.
		 */
		int myT = myScanner.nextInt();
		int myK[] = new int[myT];
		
		for (int i = 0; i < myT; i++) {
			myK[i] = myScanner.nextInt();
			
			System.out.println(getSmallestFibonnaciNumberSharingCommonDivisor(myK[i]));
		}
		
		myScanner.close();
	}

	/*
	 * 
	 */
	private static String getSmallestFibonnaciNumberSharingCommonDivisor(int aK) {
		long myLastFibonacci = 1;
		long myFibonacci = 1;
		int myCommonDivisor = 2;
		int myRange = aK;
		
		boolean myValueFound = false;
		
		while ((myValueFound==false) && (myFibonacci < 10e18)) {
			myCommonDivisor = 2;
			myFibonacci += myLastFibonacci;
			myLastFibonacci = myFibonacci - myLastFibonacci;
			
			if (myFibonacci < aK) {
				myRange = (int) myFibonacci;
			}
			else
			{
				myRange = aK;
			}
			
			while ((myValueFound==false)  && (myCommonDivisor <= myRange)) {
				if (((aK % myCommonDivisor) == 0) && ((myFibonacci % myCommonDivisor) == 0)) {
					myValueFound = true;
					
					return myFibonacci + " " + myCommonDivisor;
				}
				
				myCommonDivisor++;
			}
		}
		
		return (myValueFound)? "": "NOT FOUND";
	}
}
