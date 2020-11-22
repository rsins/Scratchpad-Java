package com.neustar.programming.contest.two;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MyMainClass2 {

	public static void main(String[] args) {
		int count = 0;
		List<StringBuilder> sList = new ArrayList<StringBuilder> ();
		String outS = "";
		while (count < 9) {
			do {
				sList = getNextNumber(sList);
				System.out.println("Digit size : " + sList.get(0).length());
			}
			while (! isNeuStarNumber(sList.get(0).length()));
			for (StringBuilder s1 : sList) {
				if (s1.toString().equals("4477744")) {
					System.out.println();
				}
				if ( isAwsomeNeustarNumber(s1) && isPalindrom(s1)) {
					count++;
					System.out.println(s1);
					if (count == 9) {
						outS = s1.toString();
						break;
					}
				}
			}
		}

		System.out.println("9th awsome neustar palindrom number is = " + outS);
	}

	private static List<StringBuilder> getNextNumber(List<StringBuilder> s) {
		List<StringBuilder> as = new ArrayList<StringBuilder>();
		if (s.size() == 0) s.add(new StringBuilder()); // Need to start with empty (one) element to the list.
		for (StringBuilder s1 : s) {
			StringBuilder ss1 = new StringBuilder(s1);
			StringBuilder ss2 = new StringBuilder(s1);
			ss1.append("4");
			ss2.append("7");
			as.add(ss1);
			as.add(ss2);
		}
		return as;
	}
	
	private static boolean isNeuStarNumber(int number) {
		StringBuilder ss = new StringBuilder();
		ss.append(number);
		return isNeuStarNumber(ss);
	}
	
	private static boolean isNeuStarNumber(StringBuilder number) {
		// Positive number
		//if (Long.valueOf(number) < 4) return false;
		
		Pattern p = Pattern.compile("[^47]");
		
		// Does not contain 4 and 7 only.
		if (p.matcher(number).find()) return false;
		
		return true;
	}
	
	private static boolean isPalindrom(StringBuilder s) {
		s.trimToSize();
		for (int i = 0, j = (s.length()-1); i < j; i++, j--) {
			if (s.charAt(i) != s.charAt(j)) return false;
		}
		return true;
	}
	
	private static boolean isAwsomeNeustarNumber(StringBuilder number) {
		//if (!isNeuStarNumber(number)) return false;
		
		if (!isNeuStarNumber(number.length())) return false;
		
		//int count1 = number.length() - number.replaceAll(new String("4"), "").length();
		//int count2 = number.length() - number.replaceAll(new String("7"), "").length();
		int count1 = getCharCount(number, '4');
		int count2 = getCharCount(number, '7');
		
		boolean bcount1 = (isNeuStarNumber(count1) || (count1 == 0)) ? true : false;
		boolean bcount2 = (isNeuStarNumber(count2) || (count2 == 0)) ? true : false;
		if ((!bcount1 && !bcount2)) return false;
		
		return true;
	}
	
	private static int getCharCount(StringBuilder sb, char ch) {
		int count = 0;
		for (int i = 0; i < sb.length(); i++) {
			if (sb.charAt(i) == ch) {
				count++;
			}
		}
		return count;
	}
}
