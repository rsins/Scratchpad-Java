package com.myexample.xmlreader;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Stack;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxXmlReader extends DefaultHandler implements XmlReader {
	private enum XmlFileType {
		ONE,
		TWO,
		NONE;
	}
	
	private static class XmlFileTypeOne {
		private HashMap<String, Boolean> myFlags = new HashMap<String, Boolean>();
		private final Boolean mTrueBoolean = new Boolean(true);
		private final Boolean mFalseBoolean = new Boolean(false);
		
		public XmlFileTypeOne() {
			myFlags.put("Context", mFalseBoolean);
			myFlags.put("Resource", mFalseBoolean);
		}
		
		public void setFlag(String aKey, boolean aflag) {
			myFlags.put(aKey, (aflag ? mTrueBoolean : mFalseBoolean));
		}

		public boolean getFlag(String aKey) {
			return myFlags.get(aKey).booleanValue();
		}
		
		public void resetAllFlags() {
			for (Entry<String, Boolean> myEntry : myFlags.entrySet()) {
				myEntry.setValue(mFalseBoolean);
			}
		}
		
		public boolean isTagNameInTheList(String aTagName) {
			return myFlags.containsKey(aTagName);
		}
	}
	
	private static class XmlFileTypeTwo {
		private HashMap<String, Boolean> myFlags = new HashMap<String, Boolean>();
		private final Boolean mTrueBoolean = new Boolean(true);
		private final Boolean mFalseBoolean = new Boolean(false);
		
		public XmlFileTypeTwo() {
			myFlags.put("databases", mFalseBoolean);
			myFlags.put("database", mFalseBoolean);
			myFlags.put("auth", mFalseBoolean);
			myFlags.put("driverClassName", mFalseBoolean);
			myFlags.put("maxActive", mFalseBoolean);
			myFlags.put("maxIdle", mFalseBoolean);
			myFlags.put("maxWait", mFalseBoolean);
			myFlags.put("name", mFalseBoolean);
			myFlags.put("type", mFalseBoolean);
			myFlags.put("url", mFalseBoolean);
			myFlags.put("username", mFalseBoolean);
			myFlags.put("password", mFalseBoolean);
		}
		
		public void setFlag(String aKey, boolean aflag) {
			myFlags.put(aKey, (aflag ? mTrueBoolean : mFalseBoolean));
		}
		
		public boolean getFlag(String aKey) {
			return myFlags.get(aKey).booleanValue();
		}
		
		public void resetAllFlags() {
			for (Entry<String, Boolean> myEntry : myFlags.entrySet()) {
				myEntry.setValue(mFalseBoolean);
			}
		}
		
		public boolean isTagNameInTheList(String aTagName) {
			return myFlags.containsKey(aTagName);
		}
	}

	private final static String PADDING_TO_ADVANCE = "    ";
	
	private static XmlFileType sFileType = XmlFileType.NONE;
	private static XmlFileTypeOne sXmlFileTypeOne = new XmlFileTypeOne();
	private static XmlFileTypeTwo sXmlFileTypeTwo = new XmlFileTypeTwo();
	private static String sPaddingForDisplay = "";
	private static PrintStream sPrintStream = null; 
	private static Stack<String> sTagNameStack = new Stack<String>();
	private static String currentElementData = null;
	
	@Override
	public void readAndDisplayXmlContent(String aFilePath, PrintStream aPrintStream) {
		try {
			sPrintStream = aPrintStream;
			
			SAXParserFactory mySAXParserFactory = SAXParserFactory.newInstance();
			SAXParser mySAXParser = mySAXParserFactory.newSAXParser();
			
			mySAXParser.parse(new File(aFilePath), this);
		} catch (ParserConfigurationException myParserConfigurationException) {
			myParserConfigurationException.printStackTrace();
		} catch (SAXException mySAXException) {
			mySAXException.printStackTrace();
		} catch (IOException myIOException) {
			myIOException.printStackTrace();
		}
	}

	@Override
	public void startElement(String aUri, String aLocalName, 
			 				 String aRawName, Attributes aAttributes) 
	throws SAXException {
		sFileType = sXmlFileTypeOne.isTagNameInTheList(aRawName) 
					? XmlFileType.ONE
				    : (sXmlFileTypeTwo.isTagNameInTheList(aRawName) ? XmlFileType.TWO : XmlFileType.NONE);
		
		switch (sFileType) {
		case ONE:
			sTagNameStack.push(aRawName);
			sXmlFileTypeOne.setFlag(aRawName, true);
			sPrintStream.println(sPaddingForDisplay + "* " + aRawName);
			
			sPaddingForDisplay += PADDING_TO_ADVANCE;
			
			for (int iAttributeIndex = 0; iAttributeIndex < aAttributes.getLength(); iAttributeIndex++) {
				sPrintStream.println(sPaddingForDisplay + "-- " + 
									 aAttributes.getQName(iAttributeIndex) + "='" + 
									 aAttributes.getValue(iAttributeIndex) + "'");
			}
			break;

		case TWO:
			sTagNameStack.push(aRawName);
			sXmlFileTypeTwo.setFlag(aRawName, true);
			currentElementData = "";
			
			if (aRawName.equals("database") ||
				aRawName.equals("databases")) {
				sPrintStream.println(sPaddingForDisplay + "* " + aRawName);
			}
			else {
				sPrintStream.print(sPaddingForDisplay + "* " + aRawName);
			}
			
			sPaddingForDisplay += PADDING_TO_ADVANCE;
			
			for (int iAttributeIndex = 0; iAttributeIndex < aAttributes.getLength(); iAttributeIndex++) {
				sPrintStream.println(sPaddingForDisplay + "-- " + 
									 aAttributes.getQName(iAttributeIndex) + "='" + 
									 aAttributes.getValue(iAttributeIndex) + "'");
			}
			break;
		
		default:
			break;
		}
	}
	
	@Override
	public void endElement(String aUri, String aLocalName, String aRawName)
	throws SAXException {
		switch (sFileType) {
		case ONE:
			sTagNameStack.pop();
			sXmlFileTypeOne.setFlag(aRawName, false);
			sPaddingForDisplay = sPaddingForDisplay.substring(0, sPaddingForDisplay.length() - PADDING_TO_ADVANCE.length());
			break;

		case TWO:
			if (!currentElementData.trim().isEmpty()) {
				sPrintStream.println("='" + currentElementData + "'");
			}
			
			currentElementData = "";
			
			sTagNameStack.pop();
			sXmlFileTypeTwo.setFlag(aRawName, false);
			sPaddingForDisplay = sPaddingForDisplay.substring(0, sPaddingForDisplay.length() - PADDING_TO_ADVANCE.length());
			break;
		
		default:
			break;
		}
	}
	
	@Override
	public void characters(char[] aCharacters, int aStartPosition, int aLength) 
	throws SAXException {
		currentElementData += new String(aCharacters, aStartPosition, aLength).trim();
		
		switch (sFileType) {
		case ONE:
			break;
			
		case TWO:
			break;
			
		default:
			break;
		}
	}
	
}
