package com.myexample.miscellaneous.lruchache.general;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedDeque;

public class LRUCachePolicy<K> implements CachePolicyInterface<K> {

	ConcurrentLinkedDeque<K> mKeys = new ConcurrentLinkedDeque<K>();
	int mCacheSize;
	
	public LRUCachePolicy(int aSize) {
		mCacheSize = aSize;
	}
	
	@Override
	public void keyAccessed(K aKey) {
		mKeys.remove(aKey);
		mKeys.addFirst(aKey);
	}

	@Override
	public void keyRemoved(K aKey) {
		mKeys.remove(aKey);
	}

	@SuppressWarnings("unchecked")
	@Override
	public K[] returnKeysToBeRemovedAfterAddKey(K aKey) {
		ArrayList<K> keysToBeRemoved = new ArrayList<K>(2);
		
		mKeys.remove(aKey);
		mKeys.addFirst(aKey);
		
		if (mKeys.size() > mCacheSize) keysToBeRemoved.add(mKeys.removeLast());
		
		return (K[]) keysToBeRemoved.toArray();
	}

	@Override
	public Iterator<K> getKeyIterator() {
		return mKeys.iterator();
	}

	@Override
	public String getPolicyDescription() {
		return "LRUCache Policy of size " + mCacheSize +".";
	}

}
