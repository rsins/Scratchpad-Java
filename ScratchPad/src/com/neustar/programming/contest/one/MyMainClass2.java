package com.neustar.programming.contest.one;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class MyMainClass2 {
	public static void main(String[] args) throws FileNotFoundException {
		final String inputFile = "D:\\Work\\Repositories\\programmingContest\\programmingContest1Input2.txt";
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));

		boolean sprinklers[][] = new boolean[1000][1000];
		
		for (int i = 0; i < 1000; i++) {
			Arrays.fill(sprinklers[i], false);
		}

		long count1 = 0;
		
		try {
			String line = reader.readLine();
			while (line != null) {
				line = line.toLowerCase();
				line = line.replaceAll("  ", " ");
				line = line.replaceAll("turn", "");
				//line = line.replaceAll("turn off", "turn_off");
				//line = line.replaceAll("turn on", "turn_on");
				line = line.replaceAll(",", " ");
				Scanner scanner = new Scanner(line);
				
				String action = scanner.next();
	
				if (action.equals("off")) {
					int x1 = scanner.nextInt();
					int y1 = scanner.nextInt();
					scanner.next();
					int x2 = scanner.nextInt();
					int y2 = scanner.nextInt();

					if (x1 > x2) {
						int s = x1;
						x1 = x2;
						x2 = s;
					}
					if ( y1 > y2 ) {
						int d = y1;
						y1 = y2;
						y2 = d;
					}
					for (int i = x1; i <= x2; i++) {
						for (int j = y1; j <= y2; j++) {
							if (sprinklers[i][j]) { count1--; }
							sprinklers[i][j] = false;
						}
					}
				}
				else if (action.equals("toggle")) {
					int x1 = scanner.nextInt();
					int y1 = scanner.nextInt();
					scanner.next();
					int x2 = scanner.nextInt();
					int y2 = scanner.nextInt();
					
					if (x1 > x2) {
						int s = x1;
						x1 = x2;
						x2 = s;
					}
					if ( y1 > y2 ) {
						int d = y1;
						y1 = y2;
						y2 = d;
					}
					for (int i = x1; i <= x2; i++) {
						for (int j = y1; j <= y2; j++) {
							if (sprinklers[i][j]) { count1--; }
							else { count1++; }
							sprinklers[i][j] = ( ! sprinklers[i][j] );
						}
					}
				}
				else if (action.equals("on")) {
					int x1 = scanner.nextInt();
					int y1 = scanner.nextInt();
					scanner.next();
					int x2 = scanner.nextInt();
					int y2 = scanner.nextInt();
					
					if (x1 > x2) {
						int s = x1;
						x1 = x2;
						x2 = s;
					}
					if ( y1 > y2 ) {
						int d = y1;
						y1 = y2;
						y2 = d;
					}
					for (int i = x1; i <= x2; i++) {
						for (int j = y1; j <= y2; j++) {
							if (! sprinklers[i][j]) { count1++; }
							sprinklers[i][j] = true;
						}
					}
				}
				
				scanner.close();
				
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		long count = 0;
		for (int i = 0; i <= 999; i++) {
			for (int j = 0; j <= 999; j++) {
				if (sprinklers[i][j]) {
					count++;
				}
			}
		}
		
		System.out.println("Total number of sprinkler heads ON = " + count + ", Alternate count: " + count1);
	}
}
