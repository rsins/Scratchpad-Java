package com.myexample.collection.queue;

public class Employee implements Comparable<Employee> {
	private int id;
	private String name;
	
	public Employee(int aId, String aName) {
		this.id = aId;
		this.name = aName;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Employee o) {
		
		return 0;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if ((o instanceof Employee) == false) return false;
		return (this.id == ((Employee) o).id);
	}
	
	@Override
	public int hashCode() {
		return this.id;
	}
	
	@Override
	public String toString() {
		return "ID: " + id + ", Name: " + name + "'";
	}
}

