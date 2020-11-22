package com.myexample.miscellaneous.lruchache.general;

import java.util.Iterator;


public interface CachePolicyInterface<K> {
	
	public void keyAccessed(K aKey);
	public void keyRemoved(K aKey);
	public K[] returnKeysToBeRemovedAfterAddKey(K aKey);
	
	public Iterator<K> getKeyIterator();
	
	public String getPolicyDescription();
}
