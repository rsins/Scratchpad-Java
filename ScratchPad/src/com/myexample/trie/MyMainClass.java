package com.myexample.trie;

import java.util.Map.Entry;

public class MyMainClass {

	public static void main(String[] args) {
		Trie<String> trie = new Trie<String>();
		trie.add("apple", "It is a fruit");
		trie.add("application", "A request in the printed paper form.");
		System.out.println(trie.getValue("apple"));
		for (Entry<String, String> e : trie.getAllValues().entrySet()) {
			System.out.println(e.getKey() + " " + e.getValue());
		}
		for (Entry<String, String> e : trie.getAllValuesStartingWith("z").entrySet()) {
			System.out.println(e.getKey() + " " + e.getValue());
		}
	}

}
