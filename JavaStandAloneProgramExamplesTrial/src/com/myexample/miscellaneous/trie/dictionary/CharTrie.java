package com.myexample.miscellaneous.trie.dictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CharTrie<V> {

	final static char STARTCHAR = 'a';
	
	// For words starting with any of the 26 alphabets.
	private Object[] root = new Object[26];
	
	public CharTrie() {
		 
	}
	
	@SuppressWarnings("unchecked")
	public void add(char[] aCharArr, V aValue) {
		if ((aValue != null) && (aCharArr != null) && (aCharArr.length > 0)) {
			char myCurrentChar = aCharArr[0];
			TrieNode<V> myRootTrieNode = (TrieNode<V>) root[myCurrentChar - STARTCHAR];
			
			if (myRootTrieNode == null) {
				myRootTrieNode = new TrieNode<V>(myCurrentChar, null);
				root[myCurrentChar - STARTCHAR] = myRootTrieNode;
			}
			
			addImpl(myRootTrieNode, aCharArr, aValue, 2);
		}
	}
	
	private void addImpl(TrieNode<V> aCurrentNode, char[] aCharArr, V aValue, int aPosition) {
		
		if (aCharArr.length < aPosition) {
			aCurrentNode.mValue = aValue;
			return;
		}
		
		char myCurrentChar = aCharArr[aPosition - 1];
		TrieNode<V> myCheckTrieNode = new TrieNode<V>(myCurrentChar, null);
		
		if (!aCurrentNode.mChildren.contains(myCheckTrieNode)) {
			aCurrentNode.mChildren.add(myCheckTrieNode);
			addImpl(myCheckTrieNode, aCharArr, aValue, aPosition + 1);
		}
		else {
			for (TrieNode<V> myCurrentChildTrieNode : aCurrentNode.mChildren) {
				if (myCurrentChildTrieNode.equals(myCheckTrieNode)) {
					addImpl(myCurrentChildTrieNode, aCharArr, aValue, aPosition + 1);
					break;
				}
			}
		}
	}
	
	public boolean hasWord(char[] aCharArr) {
		return (getValue(aCharArr) != null);
	}
	
	@SuppressWarnings("unchecked")
	public V getValue(char[] aCharArr) {
		V myValue = null;
		
		if ((aCharArr != null) && (aCharArr.length > 0)) {
			char myCurrentChar = aCharArr[0];
			TrieNode<V> myRootTrieNode = (TrieNode<V>) root[myCurrentChar - STARTCHAR];
			
			if (myRootTrieNode != null) {
				myRootTrieNode = getTrieNodeImpl(myRootTrieNode, aCharArr, 2);
				
				if (myRootTrieNode != null) myValue = myRootTrieNode.mValue;
			}
		}
		
		return myValue;
	}
	
	private TrieNode<V> getTrieNodeImpl(TrieNode<V> aCurrentNode, char[] aCharArr, int aPosition) {
		if (aCharArr.length < aPosition) {
			return aCurrentNode;
		}
		
		char myCurrentChar = aCharArr[aPosition - 1];
		TrieNode<V> myCheckTrieNode = new TrieNode<V>(myCurrentChar, null);
		
		if (!aCurrentNode.mChildren.contains(myCheckTrieNode)) {
			return null;
		}
		else {
			for (TrieNode<V> myCurrentChildTrieNode : aCurrentNode.mChildren) {
				if (myCurrentChildTrieNode.equals(myCheckTrieNode)) {
					return getTrieNodeImpl(myCurrentChildTrieNode, aCharArr, aPosition + 1);
				}
			}
		}

		return null;
	}
	
	public HashMap<String, V> getAllWordFromTrie() {
		HashMap<String, V> myWordsList = new HashMap<String, V> ();
		
		myWordsList.putAll(getAllWordsWithPrefix(new char[] {'a'}));
		myWordsList.putAll(getAllWordsWithPrefix(new char[] {'b'}));
		myWordsList.putAll(getAllWordsWithPrefix(new char[] {'c'}));
		myWordsList.putAll(getAllWordsWithPrefix(new char[] {'d'}));
		myWordsList.putAll(getAllWordsWithPrefix(new char[] {'e'}));
		myWordsList.putAll(getAllWordsWithPrefix(new char[] {'f'}));
		myWordsList.putAll(getAllWordsWithPrefix(new char[] {'g'}));
		myWordsList.putAll(getAllWordsWithPrefix(new char[] {'h'}));
		myWordsList.putAll(getAllWordsWithPrefix(new char[] {'i'}));
		myWordsList.putAll(getAllWordsWithPrefix(new char[] {'j'}));
		myWordsList.putAll(getAllWordsWithPrefix(new char[] {'k'}));
		myWordsList.putAll(getAllWordsWithPrefix(new char[] {'l'}));
		myWordsList.putAll(getAllWordsWithPrefix(new char[] {'m'}));
		myWordsList.putAll(getAllWordsWithPrefix(new char[] {'n'}));
		myWordsList.putAll(getAllWordsWithPrefix(new char[] {'o'}));
		myWordsList.putAll(getAllWordsWithPrefix(new char[] {'p'}));
		myWordsList.putAll(getAllWordsWithPrefix(new char[] {'q'}));
		myWordsList.putAll(getAllWordsWithPrefix(new char[] {'r'}));
		myWordsList.putAll(getAllWordsWithPrefix(new char[] {'s'}));
		myWordsList.putAll(getAllWordsWithPrefix(new char[] {'t'}));
		myWordsList.putAll(getAllWordsWithPrefix(new char[] {'u'}));
		myWordsList.putAll(getAllWordsWithPrefix(new char[] {'v'}));
		myWordsList.putAll(getAllWordsWithPrefix(new char[] {'w'}));
		myWordsList.putAll(getAllWordsWithPrefix(new char[] {'x'}));
		myWordsList.putAll(getAllWordsWithPrefix(new char[] {'y'}));
		
		return myWordsList;
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, V> getAllWordsWithPrefix(char[] aCharArr) {
		HashMap<String, V> myWordsList = new HashMap<String, V> ();
		
		if ((aCharArr != null) && (aCharArr.length > 0)) {
			char myCurrentChar = aCharArr[0];
			TrieNode<V> myCurrentTrieNode = (TrieNode<V>) root[myCurrentChar - STARTCHAR];
			
			if (myCurrentTrieNode != null) {
				myCurrentTrieNode = getTrieNodeImpl(myCurrentTrieNode, aCharArr, 2);
				
				/* Word list is part of input parameter. */
				StringBuilder myStringBuilder = new StringBuilder();
				myStringBuilder.append(aCharArr);
				
				if (myCurrentTrieNode.mValue != null) {
					myWordsList.put(myStringBuilder.toString(), myCurrentTrieNode.mValue);
				}

				getAllWordsWithPrefixImpl(myWordsList, myCurrentTrieNode, myStringBuilder);
			}
		}
		
		return myWordsList;
	}
	
	private void getAllWordsWithPrefixImpl(HashMap<String, V> aWordsList, TrieNode<V> aCurrentTrieNode, StringBuilder aStringBuilder) {
		if (aCurrentTrieNode.mChildren.size() == 0) {
			return;
		}
		
		for (TrieNode<V> myCurrentChildTrieNode : aCurrentTrieNode.mChildren) {
			aStringBuilder.append(myCurrentChildTrieNode.mChar);
			
			if (myCurrentChildTrieNode.mValue != null) {
				aWordsList.put(aStringBuilder.toString(), myCurrentChildTrieNode.mValue);
			}
			
			getAllWordsWithPrefixImpl(aWordsList, myCurrentChildTrieNode, aStringBuilder);
			aStringBuilder.setLength(aStringBuilder.length() - 1);
		}
	}
	
	@SuppressWarnings("unchecked")
	public V delete(char[] aCharArr) {
		V myValue = null;
		char[] myNewCharArr = Arrays.copyOf(aCharArr, aCharArr.length - 1);
		
		
		if ((aCharArr != null) && (aCharArr.length > 0)) {
			char myCurrentChar = aCharArr[0];
			TrieNode<V> myParentTrieNode = (TrieNode<V>) root[myCurrentChar - STARTCHAR];
			TrieNode<V> myCurrentTrieNode = null;
			
			if (myParentTrieNode != null) {
				myCurrentTrieNode = getTrieNodeImpl(myParentTrieNode, aCharArr, 2);
				myParentTrieNode = getTrieNodeImpl(myParentTrieNode, myNewCharArr, 2);
				
				/* Make the value to null to delete the node. */
				myValue = myCurrentTrieNode.mValue;
				myCurrentTrieNode.mValue = null;
				
				/* See if there is no child node left then delete the node. */
				if (myCurrentTrieNode.mChildren.size() == 0) {
					myParentTrieNode.mChildren.remove(myCurrentTrieNode);
				}
			}
		}
		
		return myValue;
	}
	
	
	
	static final class TrieNode<V> implements Comparable<TrieNode<V>>{
		char mChar;
		V mValue;
		ArrayList<TrieNode<V>> mChildren = new ArrayList<TrieNode<V>>();
		
		public TrieNode(char aChar, V aValue) {
			mChar = aChar;
			mValue = aValue;
		}
		
		@Override
		public int compareTo(TrieNode<V> aCompareTo) {
			return (mChar - aCompareTo.mChar);
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object aCompareTo) {
			if (aCompareTo == null) return false;
			if ((aCompareTo instanceof TrieNode) == false) return false;
			
			return (mChar == ((TrieNode<V>)aCompareTo).mChar);
		}
	}
}
