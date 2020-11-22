package com.myexample.printcalendar;

public abstract class CommonUtils {
	public final static int LEFT_ALIGN = 1;
	public final static int RIGHT_ALIGN = 2;
	public final static int CENTER_ALIGN = 3;
	
	public final static String repeatString(char aChar, int aNumberOfTimes) {
		return repeatString(new String(new char[] {aChar}), aNumberOfTimes);
	}
	
	public final static String repeatString(String aString, int aNumberOfTimes) {
		StringBuilder myStringBuilder = new StringBuilder();
		
		for (int myIndex = 0; myIndex < aNumberOfTimes; myIndex++) {
			myStringBuilder.append(aString);
		}
		
		return myStringBuilder.toString();
	}
	
	public final static String addPaddingtoString(String aString, char aPaddingChar, int aTotalLengthOfResultString, int aInputStringAlignment) {
		StringBuilder myStringBuilder = new StringBuilder();
		int myLeftPaddingLength;
		int myRightPaddingLength;
		
		switch (aInputStringAlignment) {
		case LEFT_ALIGN:
			myLeftPaddingLength = 0;
			myRightPaddingLength = aTotalLengthOfResultString - aString.length();
			
			myStringBuilder.append(aString);
			if (myRightPaddingLength > 0) {
				myStringBuilder.append(repeatString(aPaddingChar, myRightPaddingLength));
			}
			break;
			
		case RIGHT_ALIGN:
			myLeftPaddingLength = aTotalLengthOfResultString - aString.length();
			myRightPaddingLength = 0;
			
			if (myLeftPaddingLength > 0) {
				myStringBuilder.append(repeatString(aPaddingChar, myLeftPaddingLength));
			}
			myStringBuilder.append(aString);
			break;
			
		case CENTER_ALIGN:
			myLeftPaddingLength = (aTotalLengthOfResultString - aString.length())/2;
			myRightPaddingLength = aTotalLengthOfResultString - aString.length() - myLeftPaddingLength;
			
			if (myLeftPaddingLength > 0) {
				myStringBuilder.append(repeatString(aPaddingChar, myLeftPaddingLength));
			}
			myStringBuilder.append(aString);
			if (myRightPaddingLength > 0) {
				myStringBuilder.append(repeatString(aPaddingChar, myRightPaddingLength));
			}
			break;
			
		default:
			break;
		}
		
		return myStringBuilder.toString();
	}
}
