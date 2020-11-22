package com.neustar.programming.contest.five;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MyMainClass {

	public static void main(String[] args) throws IOException {
		one();
		two();
		three();
		four();
	}

	private static int sumOfDigits(long n) {
		int sum = 0;
        while (n > 0) {
            sum = sum + (int) (n % 10);
            n = n / 10;
        }
        return sum;
	}
	
	private static void one() {
		long n1 = 1; // correct reading
		long n2 = 1; // incorrect reading
		
		while (n2 < 190000) {
			boolean flag = false;
			if ((n2 % 7) == 0) flag = true;
			if ((n2+"").contains("2")) flag = true;
			if (sumOfDigits(n2) > 20) flag = true;
			if (flag) {
				n2++;
			}
			else {
				n1++;
				n2++;
			}
		}
		
		System.out.println("Actual Reading: " + n1);
	}
	
	private static void two()  throws IOException {
		final String inputFile = "D:\\Work\\Repositories\\programmingContest\\SacramentoCrimeJanuary2006.csv";
		LineNumberReader lnr = new LineNumberReader(new FileReader(inputFile));
		
		String line;
		int count = 0;
		while ((line = lnr.readLine()) != null) {}
		count = lnr.getLineNumber();
		lnr.close();
		System.out.println(((float) count) / (31 * 24));
	}
	
	private static boolean isNumeric(String str)
	{
		str = str.trim();
	    for (char c : str.toCharArray())
	    {
	        if (!Character.isDigit(c)) return false;
	    }
	    return true;
	}
	
	private static void three() throws FileNotFoundException {
		final String inputFile = "D:\\Work\\Repositories\\programmingContest\\SacramentoCrimeJanuary2006.csv";
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));

		Map<String, Integer> cm = new HashMap<String, Integer>();
		String line = null;
		try {
			line = reader.readLine();
			while (line != null) {
				String[] cells = line.split(",");
				String[] adds = cells[1].split("/");
				for (int i = 0; i < adds.length; i++) {
					String add = adds[i].trim();
					String[] addWords = add.split(" "); 
					if (isNumeric(addWords[0])) {
						add = add.replace(addWords[0], "");
						add = add.trim();
					}
					
					if (cm.containsKey(add)) {
						cm.put(add, cm.get(add) + 1);
					}
					else {
						cm.put(add, 1);
					}
				}

				line = reader.readLine();
			}
			
			int ad_count = 0;
			String ad = "";
			for (Entry<String, Integer> en : cm.entrySet()) {
				if (en.getValue() > ad_count) {
					ad_count = en.getValue();
					ad = en.getKey();
				}
			}
			
			System.out.println(ad);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void four() throws FileNotFoundException {
		final String inputFile = "D:\\Work\\Repositories\\programmingContest\\SacramentoCrimeJanuary2006.csv";
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));

		Map<String, Integer> cm = new HashMap<String, Integer>();
		String line = null;
		try {
			line = reader.readLine(); // Skip the first line which is header.
			line = reader.readLine();
			int count = 0;
			while (line != null) {
				String[] cells = line.split(",");
				String[] timeParts = cells[0].split(" ")[1].split(":");
				double iTime = new Double(timeParts[0]) + new Double(timeParts[1]) / 60;
				int grid = new Integer(cells[4]);
				int ucrCode = new Integer(cells[6]);
				
				if (iTime <= 6.5) {
					if (grid < 627) {
						if (grid <= 619.5) {
							if (ucrCode == 2404) count++;
						}
						else {
							if (ucrCode == 2299) count++;
						}
					}
					else {
						if (ucrCode == 7000) count++;
					}
				}
				else {
					if (iTime <= 16.5) {
						if (ucrCode == 7000) count++;
					}
					else {
						if (grid <= 506.5) {
							if (ucrCode == 2404) count++;
						}
						else {
							if (ucrCode == 7000) count++;
						}
					}
				}
				
				line = reader.readLine();
			}
			
			System.out.println(count);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
