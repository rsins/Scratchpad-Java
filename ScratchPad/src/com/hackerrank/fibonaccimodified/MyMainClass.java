package com.hackerrank.fibonaccimodified;

import java.math.BigInteger;
import java.util.Scanner;

public class MyMainClass {

	/*
	 A series is defined in the following manner:

Given the nth and (n+1)th terms, the (n+2)th can be computed by the following relation
Tn+2 = (Tn+1)2 + Tn

So, if the first two terms of the series are 0 and 1:
the third term = 12 + 0 = 1
fourth term = 12 + 1 = 2
fifth term = 22 + 1 = 5
... And so on.

Given three integers A, B and N, such that the first two terms of the series (1st and 2nd terms) are A and B respectively, compute the Nth term of the series.

Input Format

You are given three space separated integers A, B and N on one line.

Input Constraints
0 <= A,B <= 2
3 <= N <= 20

Output Format

One integer.
This integer is the Nth term of the given series when the first two terms are A and B respectively.

Note

    Some output may even exceed the range of 64 bit integer.

Sample Input

0 1 5  

Sample Output

5

Explanation

The first two terms of the series are 0 and 1. The fifth term is 5. How we arrive at the fifth term, is explained step by step in the introductory sections. 
	 */
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);

		BigInteger a = BigInteger.valueOf(scn.nextLong());
		BigInteger b = BigInteger.valueOf(scn.nextLong());
		int bigN = scn.nextInt();
		
		int curn = 1;
		while (bigN >= (curn + 2)) {
			BigInteger nthplustwo = getSeries(a, b, bigN, curn);
			a = b;
			b = nthplustwo;
			curn++;
		}
		
		scn.close();
	}

	/**
	 * 
	 * @param nth			nth item
	 * @param nthplusone	(n+1)th item
	 * @param bigN			if the (n+2) = N then print to output
	 * @return				(n+2)th item
	 */
	private static BigInteger getSeries(BigInteger nth, BigInteger nthplusone, int bigN, int curn) {
		BigInteger nthplustwo = nthplusone.multiply(nthplusone).add(nth);
		if ((curn + 2) == bigN) System.out.println(nthplustwo.toString());
		
		return nthplustwo;
	}
}
