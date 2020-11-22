package com.myexample.miscellaneous.stringpuzzle;

import java.io.PrintStream;

public class MyMainClass {

	/**
	 * Write a program which returns first non repeating char 
	 * from a string"google"
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		PrintStream myPrintStream = System.out;
		
		/* ############################## */
		/* First non repeating character. */
		myPrintStream.println("** First non repeating character.");
		myPrintStream.println(StringPuzzle.returnFirstNonRepeatingCharFromString1("google"));
		myPrintStream.println(StringPuzzle.returnFirstNonRepeatingCharFromString2("google"));
		
		/* ############################## */
		/* Trimming of string */
		myPrintStream.println("** Trimming of string.");
		myPrintStream.println(" Original string: '" + "   I  have this string. " + "' ");
		myPrintStream.println(" Modified string: '" + StringPuzzle.trim("   I  have this string. ") + "' ");
		
		/* ############################## */
		/* Reversing a string */
		myPrintStream.println("** Reversing a string.");
		myPrintStream.println(" Original string: '" + "I  have this string." + "' ");
		myPrintStream.println(" Modified string: '" + StringPuzzle.reverse("I  have this string.") + "' ");
		
		/* ############################## */
		/* Check if string is palindrome. */
		myPrintStream.println("** Check if string is palindrome.");
		myPrintStream.println(" Original string: '" + "aleghgela" + "' ");
		myPrintStream.println(" Palindrome check result: " + StringPuzzle.isPalindrom("aleghgela"));
	}

}
