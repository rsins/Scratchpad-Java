package com.myexample.xmlreader;

import java.io.PrintStream;

public class MyMainClass {
	private final static String XML_FILE1_PATH 
	= System.getProperty("user.dir") + "/JavaStandAloneProgramExamplesTrial/src/com/myexample/xmlreader/DBConnections_1.xml";
	private final static String XML_FILE2_PATH
	= System.getProperty("user.dir") + "/JavaStandAloneProgramExamplesTrial/src/com/myexample/xmlreader/DBConnections_2.xml";
	
	public static void main(String[] args) {
		PrintStream myPrintStream = System.out;
		XmlReader myDomReader = new DomXmlReader();
		XmlReader mySaxReader = new SaxXmlReader();
		XmlReader myStaxReader = new StaxXmlReader();
		
		myPrintStream.print("#######################################");
		myPrintStream.println("#######################################");
		myPrintStream.println("# File 1: '" + XML_FILE1_PATH + "'");
		myPrintStream.print("#######################################");
		myPrintStream.println("#######################################");
		myPrintStream.println(" Dom Reader Output ...");
		myPrintStream.print("---------------------------------------");
		myPrintStream.println("---------------------------------------");
		myDomReader.readAndDisplayXmlContent(XML_FILE1_PATH, myPrintStream);
		myPrintStream.print("---------------------------------------");
		myPrintStream.println("---------------------------------------");
		myPrintStream.println(" Sax Reader Output ...");
		myPrintStream.print("---------------------------------------");
		myPrintStream.println("---------------------------------------");
		mySaxReader.readAndDisplayXmlContent(XML_FILE1_PATH, myPrintStream);
		myPrintStream.print("---------------------------------------");
		myPrintStream.println("---------------------------------------");
		myPrintStream.println(" Stax Reader Output ...");
		myPrintStream.print("---------------------------------------");
		myPrintStream.println("---------------------------------------");
		myStaxReader.readAndDisplayXmlContent(XML_FILE1_PATH, myPrintStream);
		
		myPrintStream.println("");
				
		myPrintStream.print("#######################################");
		myPrintStream.println("#######################################");
		myPrintStream.println("# File 2: '" + XML_FILE2_PATH + "'");
		myPrintStream.print("#######################################");
		myPrintStream.println("#######################################");
		myPrintStream.println(" Dom Reader Output ...");
		myPrintStream.print("---------------------------------------");
		myPrintStream.println("---------------------------------------");
		myDomReader.readAndDisplayXmlContent(XML_FILE2_PATH, myPrintStream);
		myPrintStream.print("---------------------------------------");
		myPrintStream.println("---------------------------------------");
		myPrintStream.println(" Sax Reader Output ...");
		myPrintStream.print("---------------------------------------");
		myPrintStream.println("---------------------------------------");
		mySaxReader.readAndDisplayXmlContent(XML_FILE2_PATH, myPrintStream);
		myPrintStream.print("---------------------------------------");
		myPrintStream.println("---------------------------------------");
		myPrintStream.println(" Stax Reader Output ...");
		myPrintStream.print("---------------------------------------");
		myPrintStream.println("---------------------------------------");
		myStaxReader.readAndDisplayXmlContent(XML_FILE2_PATH, myPrintStream);
		myPrintStream.print("---------------------------------------");
		myPrintStream.println("---------------------------------------");
	}
}
