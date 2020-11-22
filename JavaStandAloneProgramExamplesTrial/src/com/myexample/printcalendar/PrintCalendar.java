package com.myexample.printcalendar;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.Locale;


public interface PrintCalendar {
	public final static int PADDING_LENGTH_BETWEEN_DAYS = 2;
	public final static int PADDING_LENGTH_BETWEEN_MONTHS = 6;

	public void prepareForPrint(int aYear);
	public void prepareForPrint(int aMonth, int aYear, boolean aPrintYear);
	
	public void print(PrintStream aPrintStream);
	
	public Iterator<String> printLineIterator();

	public char getPaddingChar();
	public void setPaddingChar(char aChar);
	
	public int getPrintLineLength();
	public void setPrintLineLength(int aLineLength);

	public Locale getLocale();
	public void setLocale(Locale aLocale);
}
