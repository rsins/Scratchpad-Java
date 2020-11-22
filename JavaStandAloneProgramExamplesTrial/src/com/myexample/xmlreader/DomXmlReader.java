package com.myexample.xmlreader;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomXmlReader implements XmlReader {
	private final static String PADDING_TO_ADVANCE = "    ";
	
	@Override
	public void readAndDisplayXmlContent(String aFilePath, PrintStream aPrintStream) {
		try {
			DocumentBuilderFactory myDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder myDocumentBuilder = myDocumentBuilderFactory.newDocumentBuilder();
			Document myDocument = myDocumentBuilder.parse(new File(aFilePath));
			Node myRootNode = myDocument.getDocumentElement();
			
			aPrintStream.print("# ");
			printNode(myRootNode, aPrintStream, "");
		} catch (ParserConfigurationException myParserConfigurationException) {
			myParserConfigurationException.printStackTrace();
		} catch (SAXException mySAXException) {
			mySAXException.printStackTrace();
		} catch (IOException myIOException) {
			myIOException.printStackTrace();
		}
	}
	
	/*
	 * This function uses recursive calls to print the xml document.
	 */
	private void printNode(Node aNode, PrintStream aPrintStream, String aPadding) {
		NodeList myChildNodeList = aNode.getChildNodes();
		NamedNodeMap myNodeAttributesNamedNodeMap = aNode.getAttributes();

		aPadding += PADDING_TO_ADVANCE;
		if ((aNode.getChildNodes().getLength() == 1) && (!aNode.getNodeName().equals("databases"))) {
			aPrintStream.println(aNode.getNodeName() + "='" + aNode.getTextContent() + "'");
		}
		else {
			aPrintStream.println(aNode.getNodeName());
		}
		
		for (int iNodeAttributeIndex = 0; iNodeAttributeIndex < myNodeAttributesNamedNodeMap.getLength(); iNodeAttributeIndex++) {
			Node mySingleAttributeNode = myNodeAttributesNamedNodeMap.item(iNodeAttributeIndex);
			aPrintStream.println(aPadding + "-- " + mySingleAttributeNode.getNodeName() + "='" + mySingleAttributeNode.getNodeValue() + "'");
		}

		for (int iChildNodeIndex = 0; iChildNodeIndex < myChildNodeList.getLength(); iChildNodeIndex++) {
			Node mySingleElementNode = myChildNodeList.item(iChildNodeIndex);
			
			if (mySingleElementNode instanceof Element) {
				aPrintStream.print(aPadding + "* ");
				printNode(mySingleElementNode, aPrintStream, aPadding);
			}
		}
	}
}
