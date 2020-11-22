package com.myexample.jaxb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class MyMainClass {

	private final static String XML_FILE_SCHEMA_PATH 
	= System.getProperty("user.dir") + "\\src\\com\\myexample\\jaxb\\EmployeeXML-schema.xsd";
	private final static String XML_FILE1_PATH 
	= System.getProperty("user.dir") + "\\src\\com\\myexample\\jaxb\\Employees_To_Marshal.xml";
	private final static String XML_FILE2_PATH 
	= System.getProperty("user.dir") + "\\src\\com\\myexample\\jaxb\\Employees_To_Marshal.xml";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PrintStream myPrintStream = System.out;
		
		ObjectFactory myObjectFactory = new ObjectFactory();
		Employees myEmployeesForOutput = myObjectFactory.createEmployees();
		
		addEmployeeDetails(myObjectFactory, myEmployeesForOutput);
		
		myPrintStream.println("********************************");
		myPrintStream.println("* ArrayList<Employee>.... To be written in XML.");
		myPrintStream.println("********************************");
		printArrayList(myEmployeesForOutput, myPrintStream);

		try {
			File myOutputFile = new File(XML_FILE1_PATH);
			JAXBContext myJAXBContext = JAXBContext.newInstance(Employees.class);
			Marshaller myMarshaller = myJAXBContext.createMarshaller();
			
			myMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			myMarshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, 
									 XML_FILE_SCHEMA_PATH.substring(XML_FILE_SCHEMA_PATH.lastIndexOf("\\") + 1));
			
			//myMarshaller.setSchema(mySchema);
			myMarshaller.marshal(myEmployeesForOutput, myOutputFile);
			myPrintStream.println("********************************");
			myPrintStream.println("* Generated XML file.");
			myPrintStream.println("********************************");
			myMarshaller.marshal(myEmployeesForOutput, myPrintStream);
			
			File myInputFile = new File(XML_FILE2_PATH);
			Unmarshaller myUnmarshaller = myJAXBContext.createUnmarshaller();
			myPrintStream.println("********************************");
			myPrintStream.println("* File to be read from XML file.");
			myPrintStream.println("********************************");
			try {
				FileInputStream myFileInputStream = new FileInputStream(myInputFile);
				int myInputByte;
				
				while ((myInputByte = myFileInputStream.read()) != -1) {
					myPrintStream.print((char)myInputByte);
				}
				
				myFileInputStream.close();
			} catch (FileNotFoundException myFileNotFoundException) {
				myFileNotFoundException.printStackTrace();
			} catch (IOException myIOException) {
				myIOException.printStackTrace();
			}
			myPrintStream.println("********************************");
			myPrintStream.println("* ArrayList<Employee>.... Generated from XML.");
			myPrintStream.println("********************************");
			Employees myEmployeesForInput = (Employees) myUnmarshaller.unmarshal(myInputFile);
			printArrayList(myEmployeesForInput, myPrintStream);
		} catch (JAXBException myJAXBException) {
			myJAXBException.printStackTrace();
		}
	}

	private static void addEmployeeDetails(ObjectFactory anObjectFactory, Employees aEmployees){
		Collection<Employee> myEmployeeList = aEmployees.getEmployeeList(); 

		myEmployeeList.add(anObjectFactory.createEmployee(1001, "shk@abc.com", "Shahrukh","","Khan"));
		myEmployeeList.add(anObjectFactory.createEmployee(1005, "kak@abc.com", "Katrina","","Khaif"));
		myEmployeeList.add(anObjectFactory.createEmployee(1008, "sak@abc.com", "Salman","","Khan"));
		myEmployeeList.add(anObjectFactory.createEmployee(1006, "prc@abc.com", "Pryanka","","Chopra"));
		myEmployeeList.add(anObjectFactory.createEmployee(1007, "rir@abc.com", "Rithik","","Roshan"));
		myEmployeeList.add(anObjectFactory.createEmployee(1002, "jot@abc.com", "John","P","Travolta"));
		myEmployeeList.add(anObjectFactory.createEmployee(1004, "drm@abc.com", "Drew","Barry","Moore"));
		myEmployeeList.add(anObjectFactory.createEmployee(1003, "jea@abc.com", "Jessica","","Alba"));
		myEmployeeList.add(anObjectFactory.createEmployee(1009, "cat@abc.com", "Charlize","","Theron"));
		myEmployeeList.add(anObjectFactory.createEmployee(1010, "ram@abc.com", "Rani","L","Mukharjee"));
	}
	
	private static void printArrayList(Employees aEmployees, PrintStream aPrintStream) {
		Collection<Employee> myEmployeeList = aEmployees.getEmployeeList();
		
		aPrintStream.println("* -- " 
							 + myEmployeeList.getClass().getName()
							 + "(" + Employee.class.getName() + ")");
		for (Employee myEmployee : myEmployeeList) {
			aPrintStream.println("   |-- " + myEmployee);
		}
	}
}
