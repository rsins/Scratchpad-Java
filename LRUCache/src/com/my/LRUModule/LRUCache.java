package com.my.LRUModule;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class LRUCache<K, V> implements Cache<K, V> {
	ConcurrentHashMap<K, V> mCacheHashMap = new ConcurrentHashMap<K, V> ();
	CachePolicy<K> mCachePolicy;
	int mSize;
	
	public LRUCache(CachePolicy<K> aChachePolicy, int aSize) {
		mSize = aSize;
		mCachePolicy = aChachePolicy;
	}
	
	public V get(K aKey) {
		if (aKey == null) {
			return null;
		}
		
		V myValue = (V) mCacheHashMap.get(aKey);
		mCachePolicy.keyAccessed(aKey);

		return myValue;
	}
	
	/*
	 * To not allow null as key.
	 * 
	 */
	public void put(K aKey, V aValue) {
		if (aKey == null) {
			return;
		}
		
		/*
		 * Just in case the same key is passed as parameter more than once.
		 */
		this.remove(aKey);
		
		mCacheHashMap.put(aKey, aValue);
		K myKeyToRemove = mCachePolicy.removeKeyAfterAKeyAdded(aKey);
		
		if (myKeyToRemove != null) {
			mCacheHashMap.remove(myKeyToRemove);
		}
	}
	
	public boolean exists(K aKey) {
		return (aKey == null) ? false : mCacheHashMap.contains(aKey);
	}
	
	public void remove(K aKey) {
		if (aKey == null) {
			return;
		}
		
		mCacheHashMap.remove(aKey);
		mCachePolicy.keyRemoved(aKey);
	}

	@Override
	public void printKeyAndValues() {
		Iterator<K> myKeyIterator = mCachePolicy.getKeyIterator();
		
		System.out.println();
		
		while (myKeyIterator.hasNext()) {
			K myKey = myKeyIterator.next();
			System.out.println(myKey + " - " + mCacheHashMap.get(myKey));
		}
	}
}
