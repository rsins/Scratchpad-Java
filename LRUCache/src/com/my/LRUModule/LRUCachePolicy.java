package com.my.LRUModule;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedDeque;

public class LRUCachePolicy<K> implements CachePolicy<K> {
	ConcurrentLinkedDeque<K> mDeque = new ConcurrentLinkedDeque<K>();
	int mSize;
	
	public LRUCachePolicy(int aSize) {
		mSize = aSize;
	}

	/*
	 * Assumption is that this method will never be called when a Key is added.
	 * 
	 */
	@Override
	public void keyAccessed(K aKey) {
		if (mDeque.contains(aKey)) {
			mDeque.remove(aKey);
			mDeque.addFirst(aKey);
		}
	}

	@Override
	public void keyRemoved(K aKey) {
		if (mDeque.contains(aKey)) {
			mDeque.remove(aKey);
		}
		
	}

	/*
	 * Assumption is that this method will be called only when a Key is added.
	 * 
	 */
	@Override
	public K removeKeyAfterAKeyAdded(K aKey) {
		K myKeyToRemove = null;
		
		mDeque.addFirst(aKey);
		
		if (mSize < mDeque.size()) {
			myKeyToRemove = mDeque.removeLast();
		}
		
		return myKeyToRemove;
	}

	@Override
	public Iterator<K> getKeyIterator() {
		return mDeque.iterator();
	}

	
	

}
