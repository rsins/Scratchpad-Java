package com.my.LRUModule;


public class MyMainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CachePolicy<String> myCachePolicy = new LRUCachePolicy<> (2);
		Cache<String, String> myLRUCache = new LRUCache<>(myCachePolicy, 2);
		
		myLRUCache.put("A", "A1");
		myLRUCache.put("B", "B2");
		myLRUCache.put("C", "C3");
		myLRUCache.get("B");
		myLRUCache.put("D", "D4");
		
		myLRUCache.printKeyAndValues();
		myLRUCache.get("B");
		
		myLRUCache.put("E", "E5");

		myLRUCache.printKeyAndValues();
	}

}
