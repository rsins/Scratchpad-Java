package com.myexample.generictypes;


public class MyMainClass2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyClass<String> myClassString = new MyClass<String> ("One");
		MyClass<Integer> myClassInteger = new MyClass<Integer> (2);
		MyClass<Object> myClassObject = new MyClass<Object> (new Object());

		System.out.println("** instanceof check **");
		System.out.println("myClassString instanceof MyClass --> " + (myClassString instanceof MyClass));
		System.out.println("myClassInteger instanceof MyClass --> " + (myClassInteger instanceof MyClass));
		System.out.println("myClassObject instanceof MyClass --> " + (myClassObject instanceof MyClass));
		System.out.println("myClassObject instanceof MyClass<String> --> " + "Compilation error, must check against the raw type as the type is erased at compilations time.");
		System.out.println("myClassObject instanceof MyClass<Object> --> " + "Compilation error, must check against the raw type as the type is erased at compilations time.");
	}

	private static class MyClass<T> {
		T obj;
		
		MyClass(T aObj) {
			obj = aObj;
		}
	}

}
