package com.myexample.recursion;

import java.io.PrintStream;
import java.util.ArrayList;

public class MyMainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PrintStream myPrintStream = System.out;
		
		/* #################  */
		ArrayList<Integer> myArrayList = new ArrayList<Integer>();
		myArrayList.add(5);
		myArrayList.add(1000);
		myArrayList.add(500);
		myArrayList.add(5000);
		myArrayList.add(55);
		myArrayList.add(158);
		myArrayList.add(876);
		myPrintStream.printf("** ArrayList before Quick Sort.\n");
		for (Integer myInteger: myArrayList) {
			myPrintStream.printf("%d ", myInteger);
		}
		myPrintStream.println();
		myArrayList = RecursiveFunctions.quickSort(myArrayList);
		myPrintStream.printf("** ArrayList after Quick Sort.\n");
		for (Integer myInteger: myArrayList) {
			myPrintStream.printf("%d ", myInteger);
		}
		myPrintStream.println();
		
		/* #################  */
		myPrintStream.printf("** Factorial of 5: \n%d\n", RecursiveFunctions.getFactorialFor(5));
		
		/* #################  */
		myPrintStream.printf("** Recursive call 1 for Permutations of string: abcd\n");
		StringPermutation.recursivePermute1(myPrintStream, "abcd");
		
		/* #################  */
		myPrintStream.printf("** Recursive call 2 for Permutations of string: abcd\n");
		StringPermutation.recursivePermute2(myPrintStream, "abcd");

		/* #################  */
		myPrintStream.printf("** Non recursive call 1 for Permutations of string: abcd\n");
		StringPermutation.nonRecursivePermuteAll1(myPrintStream, "abcd");
		
		/* #################  */
		myPrintStream.printf("** Non recursive call 2 for combinations of string: abcd\n");
		StringPermutation.nonRecursiveCombination2(myPrintStream, "abcd");
		
		/* #################  */
		myPrintStream.printf("** Non recursive call 2 for Permutations of string: abcd\n");
		StringPermutation.nonRecursiveCombinationPermutation2(myPrintStream, "abcd");
		
		/* #################  */
		myPrintStream.printf("** Non recursive call 2 for Permutations of string full length: abcd\n");
		StringPermutation.nonRecursivePermuteFullLength1(myPrintStream, "abcd");
		
		/* #################  */
		//myPrintStream.printf("** Recursive call 2 for Permutations of string: abcd\n");
		//StringPermutation.recursivePermute2(myPrintStream, "abcd");
		
		/* #################  */
		Tree myTree = Tree.generateDefaultTree();
		myPrintStream.println("** Printing tree details using recursion.");
		myTree.printTree(myPrintStream);

	}

}
