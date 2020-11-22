package com.neustar.programming.contest.four;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyMainClass1 {

	public static void main(String[] args) throws FileNotFoundException {
		final String inputFile = "D:\\Work\\Repositories\\programmingContest\\programmingContest4Input.txt";
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));

		int count = 0;
		int count1= 0;
		List<String> numList = new ArrayList<String>();
		String r[] = {"", "", ""};
		String line = null;
		try {
			line = reader.readLine();
			while (line != null) {
				r[count] = line;
				count++;
				if (count == 3) {
					String number = getNumbers(r);
					if (isSilvaCasinoNumber(number)) {
						numList.add(number);
					}
					if (isValidAccount(number)) {
						count1++;
					}
					count = 0;
				}
				
				line = reader.readLine();
			}
			
			System.out.print("answer1 = ");
			for (int i = 0; i < numList.size(); i++) { System.out.print(" " + numList.get(i) + " "); }
			System.out.println();
			System.out.println("answer2 = " + count1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static boolean isSilvaCasinoNumber(String num) {
		return num.contains("33333") ? true : false;
	}
	
	private static boolean isValidAccount(String num) {
		int sum = 0;
		for (int i=0; i< 10; i++) {
			sum += Integer.valueOf(new String(new char[] {num.charAt(i)})) * (i + 1);
		}
		
		return ((sum % 12) == 0) ? true : false;
	}

	private static String getNumbers(String[] r) {
		StringBuilder strBuilder = new StringBuilder();
		for (int i = 0; i < 30; i += 3) {
			strBuilder.append(getDigit(r, i, i+3));
		}
		return strBuilder.toString();
	}
	
	private static String getDigit(String[] r, int start, int end) {
		String[][] NUMBERS = { {" _ ", "| |", "|_|"},
							   {"   ", "  |", "  |"},
							   {" _ ", " _|", "|_ "},
							   {" _ ", " _|", " _|"},
							   {"   ", "|_|", "  |"},
							   {" _ ", "|_ ", " _|"},
							   {" _ ", "|_ ", "|_|"}
							 };
		for (int i = 0; i < 7; i++) {
			if (r[0].substring(start, end).equals(NUMBERS[i][0]) && r[1].substring(start, end).equals(NUMBERS[i][1]) && r[2].substring(start, end).equals(NUMBERS[i][2])) {
				return Integer.valueOf(i).toString();
			}
		}
		return "*";
	}
}
