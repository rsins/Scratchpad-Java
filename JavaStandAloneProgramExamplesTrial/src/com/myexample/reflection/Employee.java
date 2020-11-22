package com.myexample.reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Employee implements Comparable<Employee> {
	@MyAnnotation(name="EmployeeID", value="int")
	private int mEmployeeID;
	@MyAnnotation(name="FirstName", value="String")
	private String mFirstName;
	@MyAnnotation(name="MiddleName", value="String")
	private String mMiddleName;
	@MyAnnotation(name="LastName", value="String")
	private String mLastName;
	
	public Employee() {
		
	}
	
	public Employee(int aEmployeeID, String aFirstName, 
				 	String aMiddleName, String aLastName) {
		mEmployeeID = aEmployeeID;
		mFirstName = aFirstName;
		mMiddleName = aMiddleName;
		mLastName = aLastName;
	}
	
	public int getEmployeeID() {
		return mEmployeeID;
	}
	public void setEmployeeID(int aEmployeeID) {
		mEmployeeID = aEmployeeID;
	}
	public String getFirstName() {
		return (mFirstName == null) ? "" : mFirstName;
	}
	public void setFirstName(String aFirstName) {
		mFirstName = aFirstName;
	}
	public String getMiddleName() {
		return (mMiddleName == null) ? "" : mMiddleName;
	}
	public void setMiddleName(String aMiddleName) {
		mMiddleName = aMiddleName;
	}
	public String getLastName() {
		return (mLastName == null) ? "" : mLastName;
	}
	public void setLastName(String aLastName) {
		mLastName = aLastName;
	}

	@Override
	public int hashCode() {
		return getEmployeeID() 
			 + getFirstName().hashCode()
			 + getMiddleName().hashCode()
			 + getLastName().hashCode();
	}
	
	@Override
	public boolean equals(Object aObject) {
		if (aObject == null) {
			return false;
		}
		if (!(aObject instanceof Employee)) {
			return false;
		}
		
		Employee myEmployee2 = (Employee) aObject;
		
		if (getEmployeeID() != myEmployee2.getEmployeeID()) {
			return false;
		} 
		if (! getFirstName().equals(myEmployee2.getFirstName()) ) {
			return false;
		}
		if (! getMiddleName().equals(myEmployee2.getMiddleName()) ) {
			return false;
		}
		if (! getLastName().equals(myEmployee2.getLastName()) ) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public int compareTo(Employee aEmployee) {
		return hashCode() - aEmployee.hashCode();
	}
	
	@Override
	public String toString() {
		return "Employee ID: "
			   + getEmployeeID() + " "
			   + "Employee Name : " 
			   + ((getFirstName().trim() == "") ? "" : (" " + getFirstName()))
			   + ((getMiddleName().trim() == "") ? "" : (" " + getMiddleName()))
			   + ((getLastName().trim() == "") ? "" : (" " + getLastName()));
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface MyAnnotation {
		public String name();
		public String value();
	}
}
