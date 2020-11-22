package com.my.LRUModule;

import java.util.Iterator;

public interface CachePolicy<K> {
	public void 		keyAccessed(K aKey);
	public void 		keyRemoved(K aKey);
	public K 			removeKeyAfterAKeyAdded(K aKey);
	public Iterator<K> 	getKeyIterator ();
}
