package com.myexample.printcalendar;

import java.io.PrintStream;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Locale;

public class MonthlyCalendar implements PrintCalendar {
	private ArrayList<String> mPrintLine = new ArrayList<String>();
	private char mPaddingChar = ' ';
	private int mPrintLineLength = 0;
	private Locale mLocale = Locale.getDefault();
	
	public MonthlyCalendar() {

	}
	
	@Override
	public void prepareForPrint(int aYear) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void prepareForPrint(int aMonth, int aYear, boolean aPrintYear) {
		int myFirstWeekDayOfMonth;
		int myHighestDayInMonth;
		GregorianCalendar myCalendar = new GregorianCalendar(mLocale);
		DateFormatSymbols myDateFormatSymbols = DateFormatSymbols.getInstance(mLocale);
		StringBuilder myStringBuilder;
		String myMonthName;
		String myYear;

		mPrintLineLength = ((mPrintLineLength == 0) || (mPrintLineLength < getMinimumPrintLineLength())) 
						   ? getMinimumPrintLineLength()
						   : mPrintLineLength;
			 	
		myCalendar.set(aYear, aMonth, 1);
		myFirstWeekDayOfMonth = myCalendar.get(Calendar.DAY_OF_WEEK);
		myHighestDayInMonth = myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		mPrintLine.clear();
		
		/* Month Header */
		myMonthName = myCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, mLocale);
		myYear = myCalendar.get(Calendar.YEAR) + "";
		
		if (aPrintYear) {
			mPrintLine.add(CommonUtils.addPaddingtoString(myMonthName + mPaddingChar + myYear, 
														  mPaddingChar, 
														  mPrintLineLength, 
														  CommonUtils.CENTER_ALIGN));
		}
		else {
			mPrintLine.add(CommonUtils.addPaddingtoString(myMonthName, 
					  									  mPaddingChar, 
					  									  mPrintLineLength, 
					  									  CommonUtils.CENTER_ALIGN));
		}
		mPrintLine.add(CommonUtils.repeatString('-', mPrintLineLength));
		
		/* Weekday names */
		myStringBuilder = new StringBuilder();
		String[] myWeekDayNames = myDateFormatSymbols.getShortWeekdays();
		
		/* First element of the array is blank. */
		for (int myIndex = 1; myIndex < myWeekDayNames.length; myIndex++) {
			myStringBuilder.append(myWeekDayNames[myIndex]);
			
			if (myIndex != (myWeekDayNames.length -1)) {
				myStringBuilder.append(CommonUtils.repeatString(mPaddingChar, PrintCalendar.PADDING_LENGTH_BETWEEN_DAYS));
			}
		}
		mPrintLine.add(myStringBuilder.toString());
		myWeekDayNames = null;
		
		/* Days */
		boolean isFirstWeekOfTheMonth = true;
		for (int myMonthDay = 0; myMonthDay < myHighestDayInMonth; ) {
			int myMinimumStartWeekDay;
			myStringBuilder = new StringBuilder();
			myMinimumStartWeekDay = 1;
			
			if (isFirstWeekOfTheMonth) {
				myStringBuilder.append(CommonUtils.repeatString(mPaddingChar, (myFirstWeekDayOfMonth - 1) * (3 + PrintCalendar.PADDING_LENGTH_BETWEEN_DAYS)));
				myMinimumStartWeekDay = myFirstWeekDayOfMonth;
				isFirstWeekOfTheMonth = false;
			}
			
			for (int myWeekDayCount = myMinimumStartWeekDay; (myWeekDayCount <= 7) && (myMonthDay < myHighestDayInMonth); myWeekDayCount++) {
				myMonthDay++;
				myStringBuilder.append(CommonUtils.addPaddingtoString(myMonthDay + "", mPaddingChar, 3, CommonUtils.RIGHT_ALIGN));
				
				if (myWeekDayCount != 7) {
					myStringBuilder.append(CommonUtils.repeatString(mPaddingChar, PrintCalendar.PADDING_LENGTH_BETWEEN_DAYS));
				}
			}
			
			mPrintLine.add(CommonUtils.addPaddingtoString(myStringBuilder.toString(), mPaddingChar, mPrintLineLength, CommonUtils.LEFT_ALIGN));
		}
		
		myStringBuilder = null;
	}

	@Override
	public void print(PrintStream aPrintStream) {
		for (String myPrintLine : mPrintLine) {
			aPrintStream.println(myPrintLine);
		}
	}

	@Override
	public Iterator<String> printLineIterator() {
		return mPrintLine.iterator();
	}

	@Override
	public char getPaddingChar() {
		return mPaddingChar;
	}

	@Override
	public void setPaddingChar(char aChar) {
		mPaddingChar = aChar;
	}
	
	@Override
	public int getPrintLineLength() {
		return mPrintLineLength;
	}

	@Override
	public void setPrintLineLength(int aLineLength) {
		mPrintLineLength = aLineLength;
	}
	
	@Override
	public Locale getLocale() {
		return mLocale;
	}

	@Override
	public void setLocale(Locale aLocale) {
		mLocale = aLocale;
	}

	public final static int getMinimumPrintLineLength() {
		return (21 + 6 * Math.max(PrintCalendar.PADDING_LENGTH_BETWEEN_DAYS, PADDING_LENGTH_BETWEEN_DAYS));
	}
	
}
