package com.myexample.miscellaneous.lruchache.general;


public class MyMainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		CachePolicyInterface<String> myLRUCachePolicy = new LRUCachePolicy<String>(4);
		CacheInterface<String, String> myLRUCache = new LRUCache<String, String>(myLRUCachePolicy);
		
		myLRUCache.put("A", "A1");
		myLRUCache.put("B", "B2");
		myLRUCache.put("C", "C3");
		myLRUCache.get("B");
		myLRUCache.put("D", "D4");
		
		myLRUCache.printCache(System.out);
		myLRUCache.get("B");
		
		myLRUCache.put("E", "E5");

		myLRUCache.printCache(System.out);
	}

}
