package com.myexample.miscellaneous.trie.phonebook;

import java.util.Map;
import java.util.Map.Entry;

public class MyMainClass1 {

	public static void main(String[] args) {
		CharTrie<String> ct = new CharTrie<String>();

		ct.add("monica".toCharArray(), "Full Name: Monica Belluci");
		ct.add("aishwarya".toCharArray(), "Full Name: Aishwarya Rai");
		ct.add("apple".toCharArray(), "It is a fruit.");
		
		System.out.println(ct.getValue("apple".toCharArray()));
		System.out.println(ct.getValue("monica".toCharArray()));
		System.out.println(ct.getValue("aishwarya".toCharArray()));
		
		System.out.println("---------------------------");
		
		Map<char[], String> words = ct.getAllWordsWithPrefix("ap".toCharArray());
		for (Entry<char[], String> e : words.entrySet()) {
			System.out.println(new String(e.getKey()) + " - " + e.getValue());
		}
		
		System.out.println("---------------------------");
		
		ct.delete("apple".toCharArray());
		
		System.out.println(ct.getValue("apple".toCharArray()));
		System.out.println(ct.getValue("monica".toCharArray()));
		System.out.println(ct.getValue("aishwarya".toCharArray()));
	}

}
