package com.myexample.miscellaneous.stringpuzzle;

import java.util.ArrayList;


public class StringPuzzle {
	
	public static char returnFirstNonRepeatingCharFromString1(String aString) {
		char[] myStringChars = aString.toCharArray();
		char myChar = 0;
		
		for (int i1 = 0; i1 < myStringChars.length; i1++) {
			int myRepeatCount = 0;
			
			for (int i2 = 0; i2 < myStringChars.length; i2++) {
				if (myStringChars[i1] == myStringChars[i2]) {
					myRepeatCount += 1;
				}
			}
			
			if (myRepeatCount == 1) {
				myChar = myStringChars[i1];
				break;
			}
		}
		
		return myChar;
	}
	
	public static char returnFirstNonRepeatingCharFromString2(String aString) {
		char[] myStringChar = aString.toCharArray();
		ArrayList<Integer> mySeenOnce = new ArrayList<Integer> ();
		ArrayList<Integer> mySeenMoreThanOnce = new ArrayList<Integer> ();
		char myChar = 0;
		
		for (char myCurrentChar: myStringChar) {
			if (mySeenMoreThanOnce.contains(new Integer((int) myCurrentChar))) {
				continue;
			}
			
			if (mySeenOnce.contains(new Integer((int) myCurrentChar))) {
				int myCurCharIndex = mySeenOnce.indexOf(new Integer((int) myCurrentChar));
				mySeenMoreThanOnce.add(mySeenOnce.remove(myCurCharIndex));
			}
			else {
				mySeenOnce.add(new Integer((int) myCurrentChar));
			}
		}
		
		if (mySeenOnce.size() > 0) {
			myChar = (char) mySeenOnce.get(0).intValue();
		}

		return myChar;
	}
	
	public static String trim(String aString) {
		char[] myInputChars = aString.toCharArray();
		final char mySpaceChar = ' ';
		
		int myStartPos = 0;
		int myEndPos = myInputChars.length - 1;
		
		while ((myStartPos < myInputChars.length) && (myInputChars[myStartPos] == mySpaceChar)) {
			myStartPos++;
		}
		
		while ((myEndPos >= 0) && (myInputChars[myEndPos] == mySpaceChar)) {
			myEndPos--;
		}
		
		if ((myStartPos == 0) && (myEndPos == myInputChars.length)) {
			return aString;
		}
		
		return new String(myInputChars, myStartPos, myEndPos - myStartPos + 1);
	}
	
	public static String reverse(String aString) {
		char[] myInputchars = aString.toCharArray();
		
		for (int i=0, j=(myInputchars.length-1); i < j; i++,j--) {
			char temp = myInputchars[i];
			myInputchars[i] = myInputchars[j];
			myInputchars[j] = temp;
		}
		
		return new String(myInputchars);
	}
	
	public static boolean isPalindrom(String aString) {
		boolean myStringIsPalindrom = true;
		
		for (int i = 0, j = (aString.length() - 1); i < j; i++, j--) {
			if (aString.charAt(i) != aString.charAt(j)) {
				myStringIsPalindrom = false;
				break;
			}
		}
		
		return myStringIsPalindrom;
	}
}
