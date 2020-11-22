package com.myexample.regexpatterns;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyMainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PrintStream myPrintStream = System.out;
		Scanner myInputScanner = new Scanner(System.in);
		Pattern myPattern = Pattern.compile("[0-9]{1,}\\s{1,}[0-9]{1,}");
		Matcher myMatcher;
		
		String myInputString;
		
		myPrintStream.println("Type message for matching: ");
		
		while (true) {
			myInputString = myInputScanner.nextLine();
			myMatcher = myPattern.matcher(myInputString);
			
			if (myInputString.equalsIgnoreCase("exit")
					|| myInputString.equals("0000")) {
				myPrintStream.println("Now exiting..");
				break;
			}
			
			if (myMatcher.find()) {
				myPrintStream.println("Matched Pattern: '" + myMatcher.group() + "'");
				
				while(myMatcher.find()) {
					myPrintStream.println("Matched Pattern: '" + myMatcher.group() + "'");
				}
			}
			else {
				myPrintStream.println("Input did not match the pattern. Try Again.");
			}
			
			myPrintStream.println("Type another message for matching: ");
		}
		
		myInputScanner.close();
	}

}
