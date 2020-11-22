package com.myexample.miscellaneous.arraypuzzle;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayPuzzle {
	
	/* Algorithm 1*/
	public static void recursivePrintResultIncludingDuplicates(final PrintStream aPrintStream,
								   int[] aArray,
								   int aOutputLength,
								   int aTotalSum) {
		ArrayList<Integer> myIntArrayList = new ArrayList<Integer> ();
		boolean[] myUsed = new boolean[aArray.length];
		Arrays.fill(myUsed, false);
		
		innerRecursivePrintResult(aPrintStream, aArray, myUsed, myIntArrayList, 0, aOutputLength, aTotalSum);
	}
	
	/* Algorithm 1*/
	private static void innerRecursivePrintResult(PrintStream aPrintStream,
								   		 int[] aArray,
								   		 boolean[] aUsed,
								   		 ArrayList<Integer> aIntArrayList,
								   		 int aLevel,
								   		 int aOutputLength, 
								   		 int aTotalSum) {
		
		if (aLevel == aOutputLength) {
			int myCurrentTotalSum = 0;
			for (Integer myI : aIntArrayList) {
				myCurrentTotalSum += myI.intValue();
			}
			
			if (myCurrentTotalSum == aTotalSum) {
				aPrintStream.printf("{ ");
				for (Integer myI : aIntArrayList) {
					aPrintStream.print(myI.intValue() + " ");
				}
				aPrintStream.printf("}\n");
			}
			
			return;
		}
		
		for (int i = 0; i < aUsed.length; i++) {
			if (aUsed[i]) {
				continue;
			}
			
			aUsed[i] = true;
			aIntArrayList.add(new Integer(aArray[i]));
			innerRecursivePrintResult(aPrintStream, aArray, aUsed, aIntArrayList, aLevel + 1, aOutputLength, aTotalSum);
			aUsed[i] = false;
			
			aIntArrayList.remove(aIntArrayList.size() - 1);
		}
	}
	
	/* Algorithm 2*/
	public static void nonRecursivePrintResultExcludingDuplicates
								  (final PrintStream aPrintStream,
								   int[] aArray,
								   int aOutputLength,
								   int aTotalSum) {
		ArrayList<int[]> myIntArrayList = new ArrayList<int[]> ();
		
		innerNonRecursivePrintResultExcludingDuplicates(myIntArrayList, aArray.length, aOutputLength);
		
		for (int[] myIntArray: myIntArrayList) {
			int myResultTotal = 0;
			for (int i = 0; i < myIntArray.length; i++) {
				myResultTotal += aArray[myIntArray[i]];
			}
			
			if (myResultTotal == aTotalSum) {
				aPrintStream.printf("{ ");
				for (int myI : myIntArray) {
					aPrintStream.print(aArray[myI] + " ");
				}
				aPrintStream.printf("}\n");
			}
		}
	}
	
	/* Algorithm 2*/
	public static void innerNonRecursivePrintResultExcludingDuplicates(ArrayList<int[]> aIntArrayList, int aTotalLength, int aOutputLength) {
		int[] myIndexes = new int[aOutputLength];
		
		for (int i = 0; i < myIndexes.length; i++) {
			myIndexes[i] = i;
		}
		
		while (true) {
			boolean myCombinationChanged = false;
			
			for (int i = (myIndexes.length - 1);
			     (i >=0) && !myCombinationChanged; i--) {
				if (myIndexes[i] < ((aTotalLength -1) - (myIndexes.length -1) + i)) {
					myIndexes[i] += 1;
					
					for (int j = (i +1); j < myIndexes.length; j++) {
						myIndexes[j] = myIndexes[j-1] + 1;
					}
					
					aIntArrayList.add(Arrays.copyOf(myIndexes, myIndexes.length));
					myCombinationChanged = true;
				}
			}
			
			if (!myCombinationChanged) {
				break;
			}
		}
	}
}
