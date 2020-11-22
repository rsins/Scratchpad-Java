package com.myexample.miscellaneous.lruchache.general;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class LRUCache<K, V> implements CacheInterface<K, V> {

	CachePolicyInterface<K> mCachePolicy;
	ConcurrentHashMap<K, V> mCache = new ConcurrentHashMap<K, V>();
	
	public LRUCache (CachePolicyInterface<K> aCachePolicy) {
		mCachePolicy = aCachePolicy;
	}
	
	@Override
	public CachePolicyInterface<K> getCachePolicy() {
		return mCachePolicy;
	}

	@Override
	public void put(K aKey, V aValue) {
		K[] myKeysToBeRemoved;
		
		if (aKey == null) return;
		
		this.remove(aKey);
		
		mCache.put(aKey, aValue);
		myKeysToBeRemoved = mCachePolicy.returnKeysToBeRemovedAfterAddKey(aKey);
		
		for (K myKey : myKeysToBeRemoved) {
			if (myKey != null) this.remove(myKey);
		}
	}

	@Override
	public V get(K aKey) {
		if (this.contains(aKey) == false) return null;
		
		mCachePolicy.keyAccessed(aKey);
		
		return mCache.get(aKey);
	}

	@Override
	public V remove(K aKey) {
		if (aKey == null) return null;
		
		mCachePolicy.keyRemoved(aKey);
		
		return mCache.remove(aKey);
	}

	@Override
	public boolean contains(K aKey) {
		return (aKey == null)? false : mCache.containsKey(aKey);
	}

	@Override
	public void printCache(PrintStream aPrintStream) {
		Iterator<K> myKeys = mCachePolicy.getKeyIterator();
		K myKey;
		
		aPrintStream.println();
		
		while (myKeys.hasNext()) {
			myKey = myKeys.next();
			
			aPrintStream.println(myKey.toString() + " - " + mCache.get(myKey).toString());
		}
	}

}
