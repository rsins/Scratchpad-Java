package com.my.LRUModule;


public interface Cache<K, V> {
	public V 		get(K aKey);
	public void 	put(K aKey, V aValue);
	public boolean 	exists(K aKey);
	public void 	remove(K aKey);
	public void 	printKeyAndValues();
}
