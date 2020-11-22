package com.myexample.collection;

public class Employee implements Comparable<Employee> {
	private int mEmployeeID;
	private String mFirstName;
	private String mMiddleName;
	private String mLastName;
	
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
		return mFirstName;
	}
	public void setFirstName(String aFirstName) {
		mFirstName = aFirstName;
	}
	public String getMiddleName() {
		return mMiddleName;
	}
	public void setMiddleName(String aMiddleName) {
		mMiddleName = aMiddleName;
	}
	public String getLastName() {
		return mLastName;
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
}
