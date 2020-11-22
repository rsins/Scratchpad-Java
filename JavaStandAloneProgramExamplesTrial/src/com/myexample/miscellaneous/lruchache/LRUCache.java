package com.myexample.miscellaneous.lruchache;

import java.util.LinkedHashMap;

/* Least Recently Used Cache. */
public class LRUCache<K,V> extends LinkedHashMap<K, V> {
	private static final long serialVersionUID = 1L;
	
	int mSize;
	
	public LRUCache(int aSize) {
		super(aSize, 0.75f, true);
		mSize = aSize;
	}
	
	@Override
	protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
		System.out.println("To remove --> " + eldest.getKey() + " : " + eldest.getValue());
		return size() > mSize;
	}
}
