package com.exam.jpmorgan.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MyMainClass1 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	    String s;
	    while ((s = in.readLine()) != null) {
	      if (s.isEmpty()) break;
	      Scanner scn = new Scanner(s);
	      String s1 = scn.next();
	      String s2 = scn.next();
	      System.out.println(addTwoBinaryNumbers1(s1, s2));
	      System.out.println(addTwoBinaryNumbers2(s1, s2));
	      scn.close();
	    }

	}
	
	private static String addTwoBinaryNumbers1(String s1, String s2) {
		int i1 = Integer.parseUnsignedInt(s1, 2);
	      int i2 = Integer.parseUnsignedInt(s2, 2);
	      return Integer.toUnsignedString(i1 + i2, 2);
	}
	
	private static String addTwoBinaryNumbers2(String s1, String s2) {
		String first = (s1.length() > s2.length()) ? s1 : s2;
		String second = (s1.length() > s2.length()) ? s2 : s1;
		
		first = reverse(first);
		second = reverse(second);
		
		StringBuilder sb = new StringBuilder();		
		int f = 0;
		for (int i = 0; i < first.length(); i++) {
			int a = (i >= second.length()) ? 0 : Integer.valueOf(new String(new char[] {second.charAt(i)}));;
			int b = Integer.valueOf(new String(new char[] {first.charAt(i)}));
			sb.append(a ^ b ^ f);
			f = (a & b) | (b & f) | (a & f);
		}
		sb.append(f);
		sb.reverse();
		
		sb = removeZeroesAtStart(sb);
		return sb.toString();
	}
	
	private static String reverse(String s) {
		return new StringBuilder(s).reverse().toString();
	}
	
	private static StringBuilder removeZeroesAtStart(StringBuilder sb) {
		int pos = 0;
		while (pos < sb.length()) {
			if ((sb.charAt(pos) != '0') && (sb.charAt(pos) != ' ')) break;
			pos++;
		}
		sb.replace(0, pos, "");
		if (sb.length() == 0) sb.append('0');
		return sb;
	}

}
