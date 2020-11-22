package com.myexample.thread;

import java.io.PrintStream;
import java.util.ArrayList;

public class MyMainClass {
	private static ArrayList<ImageBlurProcessor> sImageProcessorArrayList = 
										new ArrayList<ImageBlurProcessor>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PrintStream myPrintStream = System.out;
		int myOverallThreadStatus;
		String myStringBeforeProcessing;
		String myStringAfterProcessing;
		
		sImageProcessorArrayList.add(new ImageBlurProcessor(new Integer[] {6, 10, 45, 12, 8}));
		sImageProcessorArrayList.add(new ImageBlurProcessor(new Integer[] {16, 20, 145, 212, 80}));
		sImageProcessorArrayList.add(new ImageBlurProcessor(new Integer[] {54, 70, 5005, 36}));
		
		myStringBeforeProcessing = printValues();
		
		startAllThreads();
		
		do {
			myOverallThreadStatus = getOverallThreadStatus();
			myPrintStream.println("Current status: " + myOverallThreadStatus);
			
		} while (myOverallThreadStatus < 100);
		
		myPrintStream.println("All threads finished.");
		
		myStringAfterProcessing = printValues();
		myPrintStream.println("------------- Before processing ------------");
		myPrintStream.println(myStringBeforeProcessing);
		myPrintStream.println("------------- After processing ------------");
		myPrintStream.println(myStringAfterProcessing);
	}

	private static void startAllThreads() {
		for (ImageBlurProcessor myImageThread : sImageProcessorArrayList) {
			new Thread(myImageThread).start();
		}
	}
	
	private static int getOverallThreadStatus() {
		int myCurrentStatus = 0;
		
		for (ImageBlurProcessor myImageThread : sImageProcessorArrayList) {
			myCurrentStatus += myImageThread.getCurrentStatus();
		}
		
		myCurrentStatus /= sImageProcessorArrayList.size();
		
		return myCurrentStatus;
	}
	
	private static String printValues() {
		ArrayList<Integer> myArrayList = new ArrayList<Integer>();
		
		for (ImageBlurProcessor myImageThread : sImageProcessorArrayList) {
			for (Integer myInteger: myImageThread.getArrayProcessed()) {
				myArrayList.add(myInteger);
			}
		}
		
		return myArrayList.toString();
	}
}
