package com.myexample.miscellaneous.lruchache.general;

import java.io.PrintStream;

public interface CacheInterface <K, V> {

	public CachePolicyInterface<K> getCachePolicy();
	
	public void put(K aKey, V aValue);
	public V get(K aKey);
	public V remove(K aKey);

	public boolean contains(K aKey);
	
	public void printCache(PrintStream aPrintStream);
}
