package com.myexample.printcalendar;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class MyMainClass {

	/**
	 * This is to generate and print a Calendar in Console.
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		PrintStream myPrintStream = System.out;
		InputStream myInputStream = System.in;
		Scanner myScanner = new Scanner(myInputStream);
		
		int myMonthNumber = 0;
		int myYear = 0;
		
		
		try {
			myPrintStream.printf("Enter Month number [1-12]: ");
			myMonthNumber = myScanner.nextInt();
			myPrintStream.printf("Enter Year: ");
			myYear = myScanner.nextInt();
		} catch (Exception myException) {
			myPrintStream.printf("Wrong input type --> \n\n");
			myException.printStackTrace();
			System.exit(1);
		}
		
		myPrintStream.printf("-------------------------------------\n\n");
		
		/* For Monthly Calendar */
		MonthlyCalendar myMonthlyCalendar = new MonthlyCalendar();
		myMonthlyCalendar.prepareForPrint(myMonthNumber - 1, myYear, true);
		myMonthlyCalendar.print(myPrintStream);
		
		/* For Yearly Calendar */
		YearlyCalendar myYearlyCalendar = new YearlyCalendar();
		myYearlyCalendar.setNumberOfMonthsPerRow(3);
		myYearlyCalendar.prepareForPrint(myYear);
		myYearlyCalendar.print(myPrintStream);
		
		myScanner.close();
	}

}
