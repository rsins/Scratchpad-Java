//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.02.06 at 12:24:50 PM IST 
//


package com.myexample.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.myexample.jaxb package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Middlename_QNAME = new QName("", "middlename");
    private final static QName _Lastname_QNAME = new QName("", "lastname");
    private final static QName _Emailid_QNAME = new QName("", "emailid");
    private final static QName _Firstname_QNAME = new QName("", "firstname");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.myexample.jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Employees }
     * 
     */
    public Employees createEmployees() {
        return new Employees();
    }

    /**
     * Create an instance of {@link Employee }
     * 
     */
    public Employee createEmployee() {
        return new Employee();
    }

    /**
     * Create an instance of {@link Employee }
     * 
     */
    public Employee createEmployee(int employeeid, String emailID, String firstName, String middleName, String lastName) {
    	Employee myEmployee = new Employee();
    	
    	myEmployee.setEmployeeid(employeeid);
    	myEmployee.setFirstname(firstName);
    	myEmployee.setMiddlename(middleName);
    	myEmployee.setLastname(lastName);
    	myEmployee.setEmailid(emailID);
    	
        return myEmployee;
    }
    
    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "middlename")
    public JAXBElement<String> createMiddlename(String value) {
        return new JAXBElement<String>(_Middlename_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "lastname")
    public JAXBElement<String> createLastname(String value) {
        return new JAXBElement<String>(_Lastname_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "emailid")
    public JAXBElement<String> createEmailid(String value) {
        return new JAXBElement<String>(_Emailid_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "firstname")
    public JAXBElement<String> createFirstname(String value) {
        return new JAXBElement<String>(_Firstname_QNAME, String.class, null, value);
    }

}