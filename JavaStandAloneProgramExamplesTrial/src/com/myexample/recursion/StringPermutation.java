package com.myexample.recursion;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class StringPermutation {
	/* Algorithm 1 */
	public static void recursivePermute1(PrintStream aPrintStream, String aString) {
		StringBuilder myStringBuilder;
		boolean[] myUsed = new boolean[aString.length()];
		
		for (int i = 1 ; i <= aString.length(); i++) {
			myStringBuilder = new StringBuilder();
			Arrays.fill(myUsed, false);
			innerRecursivePermute1(aPrintStream, aString.toCharArray(), myStringBuilder, myUsed, 0, i);
		}
	}
	
	/* Algorithm 1 */
	private static void innerRecursivePermute1(PrintStream aPrintStream, 
										 char[] aChars, 
										 StringBuilder aStringBuilder,
										 boolean[] aUsed,
										 int aLevel,
										 int aLengthOfOutputString) {
		
		if (aLevel == aLengthOfOutputString) {
			aPrintStream.println(aStringBuilder.toString());
			return;
		}
		
		for (int i = 0; i < aChars.length; i++) {
			if (aUsed[i]) {
				continue;
			}
			
			aUsed[i] = true;
			aStringBuilder.append(aChars[i]);
			
			innerRecursivePermute1(aPrintStream, aChars, aStringBuilder, aUsed, aLevel + 1, aLengthOfOutputString);

			aUsed[i] = false;
			aStringBuilder.setLength(aStringBuilder.length() - 1);
		}
	}
	
	/* Algorithm 2 */
	public static void recursivePermute2(PrintStream aPrintStream, String aString) {
		for (int i = 1 ; i <= aString.length(); i++) {
			innerRecursivePermute2(aPrintStream, "", aString.toCharArray(), i);
		}
	}
	
	/* Algorithm 2 */
	private static void innerRecursivePermute2(PrintStream aPrintStream,
										 String aPrefix,
										 char[] aChars, 
										 int aLengthOfOutputString) {
		
		if ((aChars.length == 0) || (aPrefix.length() == aLengthOfOutputString)) {
			aPrintStream.println(aPrefix);
			return;
		}
		
		for (int i = 0; i < aChars.length; i++) {
			String myString = new String(Arrays.copyOfRange(aChars, 0, i)) + new String(Arrays.copyOfRange(aChars, i + 1, aChars.length));
			innerRecursivePermute2(aPrintStream, aPrefix + aChars[i], myString.toCharArray(), aLengthOfOutputString);
		}
	}
	
	/* Algorithm 1 */
	/*
	public static void nonRecursivePermute1(PrintStream aPrintStream, String aString) {
		Boolean[] myUsed = new Boolean[aString.length()];
		char[] myChars = aString.toCharArray();
		
		for (int myOutputLength = 1 ; myOutputLength <= aString.length(); myOutputLength++) {
			StringBuilder myStringBuilder = new StringBuilder();
			Arrays.fill(myUsed, new Boolean(false));

			Stack<Boolean[]> myUsedStack = new Stack<Boolean[]>();
			Stack<Integer> myLevelStack = new Stack<Integer>();
			Stack<Integer> myIStack = new Stack<Integer>();
			
			Integer myLevel = new Integer(0);
			
			myUsedStack.push(myUsed);
			myLevelStack.push(myLevel);
			
			Integer myI;
			while (true) {
				if ((myUsedStack.size() == 0) || (myLevelStack.size() == 0)) {
					break;
				}
				
				myUsed = myUsedStack.pop();
				myLevel = myLevelStack.pop();
				
				if (myLevel == myOutputLength) {
					aPrintStream.println(myStringBuilder.toString());
					
					myStringBuilder.setLength(myStringBuilder.length() - 1);

					myI = myIStack.pop();
					myI = new Integer(myI + 1);
					
					continue;
				}					
				else {
					myI = new Integer(0);
				}
				
				while (true) {
					if (myI >= myChars.length) {
						myUsed = myUsedStack.pop();
						myLevel = myLevelStack.pop();
						if (myIStack.size() > 0) {
							myI = myIStack.pop();
							myI = new Integer(myI + 1);
						}
						else {
							break;
						}
						
						continue;
					}

					if (myUsed[myI.intValue()].booleanValue() == true) {
						myI = new Integer(myI + 1);
						continue;
					}
					else {
						myStringBuilder.append(myChars[myI.intValue()]);

						Boolean[] myUsedCopy = new Boolean[aString.length()];
						for (int j = 0; j < myUsedCopy.length; j++) {
							myUsedCopy[j] = myUsed[j];
						}
						myUsedCopy[myI.intValue()] = Boolean.TRUE;
						myUsedStack.push(myUsedCopy);

						myLevel = new Integer(myLevel + 1);
						myLevelStack.push(myLevel);
						
						myIStack.push(myI);
						
						break;
					}
				}
			}
		}
	}
	*/
	
	private static void swap(int[] aArray, int aFirst, int aSecond) {
		int myTemp = aArray[aFirst];
		aArray[aFirst] = aArray[aSecond];
		aArray[aSecond] = myTemp;
	}

	private static void reverse(int[] aArray, int aFirst, int aLast) {
		int i;
		int j;
		
		for (i = aFirst, j = aLast; i < j; i++, j--) {
			swap(aArray, i, j);
		}
	}

	private static String generateString(char[] aChars, int[] aIndexes) {
		StringBuilder myStringBuilder = new StringBuilder();
		
		for (int i = 0; i < aIndexes.length; i++) {
			myStringBuilder.append(aChars[aIndexes[i]]);
		}
		
		return myStringBuilder.toString();
	}

	/* Algorithm 3 */
	public static void nonRecursivePermuteAll1(PrintStream aPrintStream, String aString) {
		ArrayList<int[]> myResultsPermutationsArrayList = new ArrayList<int[]> ();
		TreeSet<String> myResultTreeSet = new TreeSet<String> ();
		
		innerNonRecursivePermute1(myResultsPermutationsArrayList, aString);
		
		for (int myOutputLength = 1; myOutputLength <= aString.length(); myOutputLength++) {
			for (int[] myPermutation: myResultsPermutationsArrayList) {
				myResultTreeSet.add(generateString(aString.toCharArray(), myPermutation).substring(0, myOutputLength));
			}
		}
		   
		for (String myStringResult: myResultTreeSet) {
			aPrintStream.println(myStringResult);
		}
	}

	/* Algorithm 3 */
	public static void nonRecursivePermuteFullLength1(PrintStream aPrintStream, String aString) {
		ArrayList<int[]> myResultsPermutationsArrayList = new ArrayList<int[]> ();
		
		innerNonRecursivePermute1(myResultsPermutationsArrayList, aString);
		
		for (int[] myPermutation: myResultsPermutationsArrayList) {
			aPrintStream.println(generateString(aString.toCharArray(), myPermutation));
		}
	}
	
	/* Algorithm 3 */
	public static void innerNonRecursivePermute1(ArrayList<int[]> aPermutationArrayList, String aString) {
		int[] myIndexes = new int[aString.length()];

		for (int i = 0; i < myIndexes.length; i++) {
			myIndexes[i] = i;
		}

		aPermutationArrayList.add(Arrays.copyOf(myIndexes, myIndexes.length));

		if (aString.length() == 1) {
			return;
		}
		
		while(true) {
			int i1 = 0;
			int i2 = 0;

			for (i1 = myIndexes.length - 2, i2 = myIndexes.length - 1; 
			(i1 > 0) && (myIndexes[i2] <= myIndexes[i1]); 
			i1--, i2--) {
			}

			if (myIndexes[i2] <= myIndexes[i1]) {
				break;
			}
			else {
				for (i2 = myIndexes.length - 1; 
				(i2 > i1) && (myIndexes[i2] <= myIndexes[i1]); 
				i2--) {
				}

				swap(myIndexes, i1, i2);

				reverse(myIndexes, i1 + 1, myIndexes.length - 1);

				aPermutationArrayList.add(Arrays.copyOf(myIndexes, myIndexes.length));
			}
		}
	}
	
	/* Algorithm 4 */
	public static void nonRecursiveCombination2(PrintStream aPrintStream, String aString) {
		ArrayList<int[]> myResultsPermutationsArrayList = new ArrayList<int[]> ();
		
		for (int myOutputLength = 1; myOutputLength <= aString.length(); myOutputLength++) {
			innerNonRecursiveCombination2(myResultsPermutationsArrayList, aString, myOutputLength);
		}
		
		for (int[] myPermutation: myResultsPermutationsArrayList) {
			aPrintStream.println(generateString(aString.toCharArray(), myPermutation));
		}
	}
	
	/* Algorithm 4 */
	public static void nonRecursiveCombinationPermutation2(PrintStream aPrintStream, String aString) {
		ArrayList<int[]> myResultsPermutationsArrayList = new ArrayList<int[]> ();
		
		for (int myOutputLength = 1; myOutputLength <= aString.length(); myOutputLength++) {
			innerNonRecursiveCombination2(myResultsPermutationsArrayList, aString, myOutputLength);
		}
		
		for (int[] myPermutation: myResultsPermutationsArrayList) {
			nonRecursivePermuteFullLength1(aPrintStream, generateString(aString.toCharArray(), myPermutation));
		}
	}

	/* Algorithm 4 */
	public static void innerNonRecursiveCombination2(ArrayList<int[]> aCombinationArrayList, String aString, int aOutputLength) {
		int[] myIndexes = new int[aOutputLength];
		boolean myCombinationChanged = false;
		
		for (int i = 0; i < myIndexes.length; i++) {
			myIndexes[i] = i;
		}
		
		aCombinationArrayList.add(Arrays.copyOf(myIndexes, myIndexes.length));
		
		while (true) {
			myCombinationChanged = false;
			
			for (int i = (myIndexes.length - 1); (i >= 0) && (!myCombinationChanged); i--) {
				if (myIndexes[i] < ((aString.length() - 1) - (myIndexes.length - 1) + i)) {
					myIndexes[i] += 1;
					
					for (int j = (i+1); j < myIndexes.length; j++) {
						myIndexes[j] = myIndexes[j-1] + 1;
					}
					
					aCombinationArrayList.add(Arrays.copyOf(myIndexes, myIndexes.length));
					myCombinationChanged = true;
				}
			}
			
			if (!myCombinationChanged) {
				break;
			}
		}
	}
}
