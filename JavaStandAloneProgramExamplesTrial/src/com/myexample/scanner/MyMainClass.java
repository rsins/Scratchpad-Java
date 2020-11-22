package com.myexample.scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class MyMainClass {
	private final static String FILE_NAME = "d:\\documents and settings\\rsins\\Desktop\\Ravi\\Training\\RAD Workspace_Others\\JavaStandAloneProgramExamplesTrial\\src\\com\\myexample\\scanner\\MyFile.txt";
	
	public static void main(String[] args) {
		try {
			PrintStream myPrintStream = System.out;
			Scanner myScanner = new Scanner(new File(FILE_NAME));
			myScanner.useDelimiter(",");
			
			while (myScanner.hasNextLine()) {
				Scanner myLineScanner = new Scanner(myScanner.nextLine());
				myLineScanner.useDelimiter(",");
				
				while (myLineScanner.hasNext()) {
					myPrintStream.print(myLineScanner.next() 
							+ (myLineScanner.hasNext() ? "," : ""));
				}
				
				myPrintStream.println("\n########");
			}
		} catch (FileNotFoundException myFileNotFoundException) {
			myFileNotFoundException.printStackTrace();
		}
	}

}
