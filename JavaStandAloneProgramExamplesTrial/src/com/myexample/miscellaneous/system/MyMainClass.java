package com.myexample.miscellaneous.system;

import java.io.PrintStream;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

public class MyMainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PrintStream myPrintStream = System.out;
		
		myPrintStream.printf("Trying the System.getENV.\n");
		Map<String, String> mySystemEnvMap = System.getenv();
		for (Entry<String, String> myEnvEntry : mySystemEnvMap.entrySet()) {
			myPrintStream.printf("%s --> %s\n", myEnvEntry.getKey(), myEnvEntry.getValue());
		}
		
		myPrintStream.printf("\n\nTrying the System.getPROPERTIES.\n");
		Properties mySystemProperties = System.getProperties();
		for (Entry<Object, Object> myPropertyEntry : mySystemProperties.entrySet()) {
			myPrintStream.printf("%s --> %s\n", myPropertyEntry.getKey(), myPropertyEntry.getValue());
		}
		
		myPrintStream.printf("\n\nTrying the System Exit Command here.\n");
		System.exit(1);

	}
}
