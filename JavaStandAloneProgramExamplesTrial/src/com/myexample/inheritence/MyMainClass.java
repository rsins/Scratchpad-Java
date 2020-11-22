package com.myexample.inheritence;

public class MyMainClass {

	public static void main(String[] args) {
		
		Base base = new Extension();
		base.add(8);
		System.out.println(base.i);
	}

}

class Base {
	int i;
	 
	public Base() {
		add(1);
	}
	 
	public void add(int j) {
		i += j;
	}
}

class Extension extends Base {

	public Extension() {
		add(2);
	}
	  
	public void add(int j) {
		i += j*2;
	}
}

