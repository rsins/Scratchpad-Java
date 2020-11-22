package com.myexample.xmlreader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;
import java.util.Map.Entry;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.Namespace;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class StaxXmlReader implements XmlReader {
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
	
	@Override
	public void readAndDisplayXmlContent(String aFilePath, PrintStream aPrintStream) {
		try {
			sPrintStream = aPrintStream;
			
			XMLInputFactory myXmlInputFactory = XMLInputFactory.newInstance();
			Reader myXmlFileReader = new FileReader(aFilePath);
			XMLEventReader myXmlEventReader = myXmlInputFactory.createXMLEventReader(myXmlFileReader);
			
			while (myXmlEventReader.hasNext()) {
				XMLEvent myXmlEvent = myXmlEventReader.nextEvent();
				processEvent(myXmlEvent);
			}

		} catch (FileNotFoundException myFileNotFoundException) {
			myFileNotFoundException.printStackTrace();
		} catch (XMLStreamException myXMLStreamException) {
			myXMLStreamException.printStackTrace();
		}
	}

	private void processEvent(XMLEvent anXmlEvent) {
		switch (anXmlEvent.getEventType()) {
		case XMLStreamConstants.START_ELEMENT:
			processStartElement((StartElement) anXmlEvent);
			break;

		case XMLStreamConstants.CHARACTERS:
			processCharacters((Characters) anXmlEvent);
			break;
			
		case XMLStreamConstants.END_ELEMENT:
			processEndElement((EndElement) anXmlEvent);
			break;
			
		default:
			break;
		}
	}
	
	public void processStartElement(StartElement aStartElement) {
		QName myQName = aStartElement.getName();

		String myElementName = myQName.getLocalPart();

		sFileType = sXmlFileTypeOne.isTagNameInTheList(myElementName) 
					? XmlFileType.ONE
				    : (sXmlFileTypeTwo.isTagNameInTheList(myElementName) ? XmlFileType.TWO : XmlFileType.NONE);
		
		switch (sFileType) {
		case ONE:
			sTagNameStack.push(myElementName);
			sXmlFileTypeOne.setFlag(myElementName, true);
			sPrintStream.println(sPaddingForDisplay + "* " + myElementName);
			
			sPaddingForDisplay += PADDING_TO_ADVANCE;
			
			for (Iterator myNamesSpacesIterator = aStartElement.getNamespaces(); myNamesSpacesIterator.hasNext(); ) {
				Namespace myNameSpace = (Namespace) myNamesSpacesIterator.next();
				
				String myNameSpaceNamePrefix = myNameSpace.getName().getPrefix();
				String myNameSpaceNameLocalPart = myNameSpace.getName().getLocalPart();
				
				sPrintStream.println(sPaddingForDisplay + "-- " + 
						 ((myNameSpaceNamePrefix.trim() == "") ? myNameSpaceNameLocalPart : (myNameSpaceNamePrefix + ":" + myNameSpaceNameLocalPart))
						 + "='" + 
						 myNameSpace.getValue() + "'");
			}
			
			for (Iterator myAttributeIterator = aStartElement.getAttributes(); myAttributeIterator.hasNext(); ) {
				Attribute myAttribute = (Attribute) myAttributeIterator.next();
				
				String myAttributeNamePrefix = myAttribute.getName().getPrefix();
				String myAttributeNameLocalPart = myAttribute.getName().getLocalPart();
				
				sPrintStream.println(sPaddingForDisplay + "-- " + 
									 ((myAttributeNamePrefix.trim() == "") ? myAttributeNameLocalPart : (myAttributeNamePrefix + ":" + myAttributeNameLocalPart))
									 + "='" + 
									 myAttribute.getValue() + "'");
			}
			break;

		case TWO:
			sTagNameStack.push(myElementName);
			sXmlFileTypeTwo.setFlag(myElementName, true);
			
			if (myElementName.equals("database") ||
				myElementName.equals("databases")) {
				sPrintStream.println(sPaddingForDisplay + "* " + myElementName);
			}
			else {
				sPrintStream.print(sPaddingForDisplay + "* " + myElementName);
			}
			
			sPaddingForDisplay += PADDING_TO_ADVANCE;
			
			for (Iterator myNamesSpacesIterator = aStartElement.getNamespaces(); myNamesSpacesIterator.hasNext(); ) {
				Namespace myNameSpace = (Namespace) myNamesSpacesIterator.next();
				
				String myNameSpaceNamePrefix = myNameSpace.getName().getPrefix();
				String myNameSpaceNameLocalPart = myNameSpace.getName().getLocalPart();
				
				sPrintStream.println(sPaddingForDisplay + "-- " + 
						 ((myNameSpaceNamePrefix.trim() == "") ? myNameSpaceNameLocalPart : (myNameSpaceNamePrefix + ":" + myNameSpaceNameLocalPart))
						 + "='" + 
						 myNameSpace.getValue() + "'");
			}
			
			for (Iterator myAttributeIterator = aStartElement.getAttributes(); myAttributeIterator.hasNext(); ) {
				Attribute myAttribute = (Attribute) myAttributeIterator.next();
				
				String myAttributeNamePrefix = myAttribute.getName().getPrefix();
				String myAttributeNameLocalPart = myAttribute.getName().getLocalPart();
				
				sPrintStream.println(sPaddingForDisplay + "-- " + 
									 ((myAttributeNamePrefix.trim() == "") ? myAttributeNameLocalPart : (myAttributeNamePrefix + ":" + myAttributeNameLocalPart))
									 + "='" + 
									 myAttribute.getValue() + "'");
			}
			break;
		
		default:
			break;
		} 
	}
	
	public void processEndElement(EndElement anEndElement) {
		QName myQName = anEndElement.getName();
		String myEndElement = myQName.getLocalPart();
		
		switch (sFileType) {
		case ONE:
			sTagNameStack.pop();
			sXmlFileTypeOne.setFlag(myEndElement, false);
			sPaddingForDisplay = sPaddingForDisplay.substring(0, sPaddingForDisplay.length() - PADDING_TO_ADVANCE.length());
			break;

		case TWO:
			sTagNameStack.pop();
			sXmlFileTypeTwo.setFlag(myEndElement, false);
			sPaddingForDisplay = sPaddingForDisplay.substring(0, sPaddingForDisplay.length() - PADDING_TO_ADVANCE.length());
			break;
		
		default:
			break;
		}
	}
	
	public void processCharacters(Characters aCharacters) {
		String myData = aCharacters.getData();
		
		switch (sFileType) {
		case ONE:
			break;
			
		case TWO:
			if (!myData.trim().isEmpty()) {
				sPrintStream.println("='" + myData + "'");
			}
			break;
			
		default:
			break;
		}
	}
}
