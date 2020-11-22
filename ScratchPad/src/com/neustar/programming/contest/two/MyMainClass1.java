package com.neustar.programming.contest.two;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MyMainClass1 {

	public static void main(String[] args) throws IOException {
		final String inputFile = "D:\\Work\\Repositories\\programmingContest\\programmingContest2Input1.txt";
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		//BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		long count1 = 0; // first word
		long count2 = 0; // second word
		long count3 = 0; // none
		String firstWordNo68 = "";
		String line = reader.readLine();
		while (line != null) {
			Scanner scanner = new Scanner(line);
			String word1 = scanner.next();
			String word2 = scanner.next();
			scanner.close();
			
			int winner = getWordWinner(word1, word2);
			if (winner == 1) {
				count1++;
			}
			else if (winner == 2) {
				count2++;
			}
			else {
				count3++;
			}
			//System.out.println(winner);
			if (count1 == 68) firstWordNo68 = word1; 
			line = reader.readLine();
		}
		
		reader.close();
		System.out.println("Word1 count = " + count1 + ", Word2 count = " + count2 + ", None = " + count3);
		System.out.println(firstWordNo68 + count1);
	}
	
	private static int getWordWinner(String word1, String word2) {
		final char[] charArr = "zyxwvutsrqponmlkjihgfedcba".toCharArray();
		//final char[] symbolAndNumberArr = "~!@#$%^&*()?<>:;|{}[]=\\/\"-+_`.,0123456789".toCharArray();
		final String patternStr = "[^A-Za-z]";
		final Pattern p = Pattern.compile(patternStr);
		
		if (word1.contains("no") || word2.contains("no")) return 0;
		
		//if ((word1.matches(patternStr)) || (word2.matches(patternStr))) return 0;
		if ((p.matcher(word1).find()) || (p.matcher(word2).find())) return 0;

		for (char ch : charArr) {
			int count1 = word1.length() - word1.replaceAll(new String(new char[] {ch}), "").length();
			int count2 = word2.length() - word2.replaceAll(new String(new char[] {ch}), "").length();
			if (count1 > count2) return 1;
			if (count2 > count1) return 2;
		}
		return 0;	
	}

}

