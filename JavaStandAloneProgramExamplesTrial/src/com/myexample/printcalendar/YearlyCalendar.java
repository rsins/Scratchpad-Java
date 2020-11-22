package com.myexample.printcalendar;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;

public class YearlyCalendar implements PrintCalendar {
	private MonthlyCalendar[] myMonthlyCalendars;
	private ArrayList<String> mPrintLine = new ArrayList<String>();
	private char mPaddingChar = ' ';
	private int mPrintLineLength = 0;
	private Locale mLocale = Locale.getDefault();
	private int mNumberOfMonthsPerRow = 2;
	
	public YearlyCalendar() {

	}
	
	@Override
	public void prepareForPrint(int aYear) {
		StringBuilder myStringBuilder;
		
		mPrintLineLength = ((mPrintLineLength == 0) || (mPrintLineLength < getMinimumPrintLineLength())) 
				   		   ? getMinimumPrintLineLength()
				   		   : mPrintLineLength;

		myMonthlyCalendars = new MonthlyCalendar[12];
				   		
		myMonthlyCalendars[0] = new MonthlyCalendar();
		myMonthlyCalendars[0].setPaddingChar(mPaddingChar);
		myMonthlyCalendars[0].prepareForPrint(Calendar.JANUARY, aYear, false);
		myMonthlyCalendars[1] = new MonthlyCalendar();
		myMonthlyCalendars[1].setPaddingChar(mPaddingChar);
		myMonthlyCalendars[1].prepareForPrint(Calendar.FEBRUARY, aYear, false);
		myMonthlyCalendars[2] = new MonthlyCalendar();
		myMonthlyCalendars[2].setPaddingChar(mPaddingChar);
		myMonthlyCalendars[2].prepareForPrint(Calendar.MARCH, aYear, false);
		myMonthlyCalendars[3] = new MonthlyCalendar();
		myMonthlyCalendars[3].setPaddingChar(mPaddingChar);
		myMonthlyCalendars[3].prepareForPrint(Calendar.APRIL, aYear, false);
		myMonthlyCalendars[4] = new MonthlyCalendar();
		myMonthlyCalendars[4].setPaddingChar(mPaddingChar);
		myMonthlyCalendars[4].prepareForPrint(Calendar.MAY, aYear, false);
		myMonthlyCalendars[5] = new MonthlyCalendar();
		myMonthlyCalendars[5].setPaddingChar(mPaddingChar);
		myMonthlyCalendars[5].prepareForPrint(Calendar.JUNE, aYear, false);
		myMonthlyCalendars[6] = new MonthlyCalendar();
		myMonthlyCalendars[6].setPaddingChar(mPaddingChar);
		myMonthlyCalendars[6].prepareForPrint(Calendar.JULY, aYear, false);
		myMonthlyCalendars[7] = new MonthlyCalendar();
		myMonthlyCalendars[7].setPaddingChar(mPaddingChar);
		myMonthlyCalendars[7].prepareForPrint(Calendar.AUGUST, aYear, false);
		myMonthlyCalendars[8] = new MonthlyCalendar();
		myMonthlyCalendars[8].setPaddingChar(mPaddingChar);
		myMonthlyCalendars[8].prepareForPrint(Calendar.SEPTEMBER, aYear, false);
		myMonthlyCalendars[9] = new MonthlyCalendar();
		myMonthlyCalendars[9].setPaddingChar(mPaddingChar);
		myMonthlyCalendars[9].prepareForPrint(Calendar.OCTOBER, aYear, false);
		myMonthlyCalendars[10] = new MonthlyCalendar();
		myMonthlyCalendars[10].setPaddingChar(mPaddingChar);
		myMonthlyCalendars[10].prepareForPrint(Calendar.NOVEMBER, aYear, false);
		myMonthlyCalendars[11] = new MonthlyCalendar();
		myMonthlyCalendars[11].setPaddingChar(mPaddingChar);
		myMonthlyCalendars[11].prepareForPrint(Calendar.DECEMBER, aYear, false);
		
		/* Header details. */
		mPrintLine.add(CommonUtils.addPaddingtoString("Year" + mPaddingChar + aYear, 
				  									  mPaddingChar, 
				  									  mPrintLineLength, 
				  									  CommonUtils.CENTER_ALIGN));
		mPrintLine.add(CommonUtils.repeatString('-', mPrintLineLength));
		
		/* Month Details. */
		for (int myMonth = 0; myMonth < 12; ) {
			ArrayList<Iterator<String>> myMonthPrintLineIteratorArrayList = new ArrayList<Iterator<String>>();
			int[] myMonthPrintLineLength = new int[mNumberOfMonthsPerRow];
			
			for (int myMonthColumn = 0; ((myMonthColumn < mNumberOfMonthsPerRow) && (myMonth < 12)) ; myMonthColumn++) {
				myMonth++;
				myMonthPrintLineIteratorArrayList.add(myMonthlyCalendars[myMonth -1].printLineIterator());
				myMonthPrintLineLength[myMonthColumn] = myMonthlyCalendars[myMonth -1].getPrintLineLength();
			}
			
			while (atLeastOneHasNext(myMonthPrintLineIteratorArrayList)) {
				myStringBuilder = new StringBuilder();
				
				for (int myIteratorIndex = 0; myIteratorIndex < myMonthPrintLineIteratorArrayList.size(); myIteratorIndex++) {
					String myPrintLine;
				
					if (myMonthPrintLineIteratorArrayList.get(myIteratorIndex).hasNext()) {
						myPrintLine = myMonthPrintLineIteratorArrayList.get(myIteratorIndex).next();
					}
					else {
						myPrintLine = CommonUtils.repeatString(mPaddingChar, myMonthPrintLineLength[myIteratorIndex]);
					}
					
					myStringBuilder.append(CommonUtils.addPaddingtoString(myPrintLine, mPaddingChar, myMonthPrintLineLength[myIteratorIndex], CommonUtils.LEFT_ALIGN));
					
					if (myIteratorIndex != (myMonthPrintLineIteratorArrayList.size()-1)) {
						myStringBuilder.append(CommonUtils.repeatString(mPaddingChar, PrintCalendar.PADDING_LENGTH_BETWEEN_MONTHS));
					}
				}
				
				mPrintLine.add(CommonUtils.addPaddingtoString(myStringBuilder.toString(), mPaddingChar, mPrintLineLength, CommonUtils.CENTER_ALIGN));
			}
			
			mPrintLine.add(CommonUtils.repeatString(mPaddingChar, mPrintLineLength));
		}
		
		myMonthlyCalendars = null;
	}

	private boolean atLeastOneHasNext(ArrayList<Iterator<String>> aIteratorArrayList) {
		boolean myIteratorHasNext = false;
		
		for (Iterator<String> myIterator: aIteratorArrayList) {
			if (myIterator.hasNext()) {
				myIteratorHasNext = true;
				break;
			}
		}
		
		return myIteratorHasNext;
	}
	
	@Override
	public void prepareForPrint(int aMonth, int aYear, boolean aPrintYear) {
		throw new UnsupportedOperationException();
		
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

	public void setNumberOfMonthsPerRow(int aNumberOfMonthsPerRow) {
		mNumberOfMonthsPerRow = aNumberOfMonthsPerRow;
	}

	public int getNumberOfMonthsPerRow() {
		return mNumberOfMonthsPerRow;
	}

	public final int getMinimumPrintLineLength() {
		int myMinimumLineLength = MonthlyCalendar.getMinimumPrintLineLength();
		myMinimumLineLength = mNumberOfMonthsPerRow * myMinimumLineLength + (mNumberOfMonthsPerRow - 1) * PrintCalendar.PADDING_LENGTH_BETWEEN_MONTHS;
		
		return myMinimumLineLength;
	}

	/*
	public final int getActualPrintLineLength(int aMonthlyCalendarLineLength) {
		return mNumberOfMonthsPerRow * aMonthlyCalendarLineLength + (mNumberOfMonthsPerRow - 1) * PrintCalendar.PADDING_LENGTH_BETWEEN_MONTHS;
	}
	*/
}
