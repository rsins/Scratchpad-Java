package com.myexample.miscellaneous.lruchache;

import java.util.Map.Entry;



public class MyMainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 LRUCache<Integer, String> myLRUCache = new LRUCache<Integer, String> (2); 
		 
		 myLRUCache.put(1, "A");
		 myLRUCache.put(2, "B");
		 myLRUCache.put(3, "C");
		 myLRUCache.get(2);
		 myLRUCache.put(4, "D");
		 
		 for (Entry<Integer, String> myEntry : myLRUCache.entrySet()) {
			 System.out.println(myEntry.getKey() + " : " + myEntry.getValue());
		 }
		 
	}

}
