package com.myexample.miscellaneous.trie.phonebook;

import java.util.ArrayList;
import java.util.HashMap;

public class CharTrie <V> {
    // Root node of the trie.
	private final LinkedTrieNode<V> ROOT = new LinkedTrieNode<V>(null, (char) 0, null);
	
	public void add(char[] aCharArr, V aValue) {
		if ((aValue != null) && (aCharArr != null) && (aCharArr.length > 0)) {
			addImpl(ROOT, aCharArr, aValue, 0);
		}
	}
	
	LinkedTrieNode<V> addAndGetLeafNode(char[] aCharArr, V aValue) {
		if ((aValue != null) && (aCharArr != null) && (aCharArr.length > 0)) {
			return addImpl(ROOT, aCharArr, aValue, 0);
		}
		return null;
	}
	
	private LinkedTrieNode<V> addImpl(LinkedTrieNode<V> aCurrentNode, char[] aCharArr, V aValue, int aPos) {
		if (aCharArr.length == aPos) {
			aCurrentNode.value = aValue;
			return aCurrentNode;
		}
		
		LinkedTrieNode<V> newCurrentNode = new LinkedTrieNode<V>(aCurrentNode, aCharArr[aPos], null);
		
		if (aCurrentNode.children == null) {
			aCurrentNode.children = new ArrayList<LinkedTrieNode<V>>();
			aCurrentNode.children.add(newCurrentNode);
		}
		else {
			if (!aCurrentNode.children.contains(newCurrentNode)) {
				aCurrentNode.children.add(newCurrentNode);
			}
			else {
				for (LinkedTrieNode<V> aNode: aCurrentNode.children) {
					if (aNode.equals(newCurrentNode)) {
						newCurrentNode = aNode;
						break;
					}
				}
			}
		}

		return addImpl(newCurrentNode, aCharArr, aValue, aPos + 1);
	}
	
	public boolean hasWord(char[] aChar) {
		return (getLeafTrieNode(aChar) != null);
	}
	
	public V getValue(char[] aChar) {
		LinkedTrieNode<V> leafNode = getLeafTrieNode(aChar);
		return ((leafNode == null)? null : leafNode.value);
	}
	
	LinkedTrieNode<V> getLeafTrieNode(char[] aChar) {
		LinkedTrieNode<V> myCurrentNode = ROOT;
		
		for (char myChar : aChar) {
			myCurrentNode = getChildTrieNodeImpl(myCurrentNode, myChar);
			if (myCurrentNode == null) return null;
		}
		
		return myCurrentNode;
	}
	
	private LinkedTrieNode<V> getChildTrieNodeImpl(LinkedTrieNode<V> aCurrentNode, char aChar) {
		LinkedTrieNode<V> myNode = new LinkedTrieNode<V>(aCurrentNode, aChar, null);
		
		if (aCurrentNode.children == null) return null;
		
		if (aCurrentNode.children.contains(myNode)) {
			for (LinkedTrieNode<V> childNode : aCurrentNode.children) {
				if (childNode.equals(myNode)) {
					return childNode;
				}
			}
		}
		
		return null;
	}
	
	public V delete(char[] aChar) {
		LinkedTrieNode<V> currentNode = getLeafTrieNode(aChar);
		if (currentNode == null) return null;

		V value = currentNode.value;
		// prepare to delete the value node.
		currentNode.value = null;
		LinkedTrieNode<V> childNode = null;
		while (true) {
			if (!currentNode.hasChildren() && !currentNode.hasValue()) {
				// Delete the node as it is a leaf node and is not required in the trie anymore.
				childNode = currentNode;
				currentNode = childNode.parent;
				currentNode.children.remove(childNode);
			}
			else {
				break;
			}
		}
		
		return value;
	}
	
	public HashMap<char[], V> getAllWordsWithPrefix(char[] aCharArr) {
		HashMap<char[], V> allWordsHashMap = new HashMap<char[], V>();
		StringBuilder sb = new StringBuilder();
		
		if ((aCharArr != null) && (aCharArr.length > 0)) {
			sb.append(aCharArr);
			getAllWordsWithPrefixImpl(getLeafTrieNode(aCharArr), sb, allWordsHashMap);
		}
		
		return allWordsHashMap;
	}

	char[] getKeyBasedOnLeafNode(LinkedTrieNode<V> leafNode) {
		StringBuilder sb = new StringBuilder();
		
		LinkedTrieNode<V> currentNode = leafNode;
		while (currentNode != ROOT) {
			sb.append(currentNode.mChar);
			currentNode = currentNode.parent;
		}
		
		sb.reverse();
		char[] key = new char[sb.length()];
		sb.getChars(0, sb.length(), key, 0);
		return key;
	}
	
	private void getAllWordsWithPrefixImpl(LinkedTrieNode<V> aNode, StringBuilder sb, HashMap<char[], V> aWordsList) {
		if (!aNode.hasChildren() && aNode.hasValue()) {
			char[] word = new char[sb.length()];
			sb.getChars(0, sb.length(), word, 0);
			aWordsList.put(word, aNode.value);
			return;
		}
		
		if (aNode.children == null) return;
		
		for (LinkedTrieNode<V> childNode : aNode.children) {
			sb.append(childNode.mChar);
			getAllWordsWithPrefixImpl(childNode, sb, aWordsList);
			sb.setLength(sb.length() - 1);
		}
	}
	
	static final class LinkedTrieNode<V> {
		private char mChar;
		private V value;
		private LinkedTrieNode<V> parent;
		private ArrayList<LinkedTrieNode<V>> children;
		
		LinkedTrieNode(LinkedTrieNode<V> aParentNode, char aChar, V aValue) {
			this.parent = aParentNode;
			this.mChar = aChar;
			this.value = aValue;
			this.children = null;
		}
		
		boolean hasValue() {
			return (this.value != null);
		}
		
		V getValue() {
			return value;
		}
		
		boolean hasChildren() {
			return !((children == null) || (children.size() == 0));
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object aCompareTo) {
			if (aCompareTo == null) return false;
			if ((aCompareTo instanceof LinkedTrieNode) == false) return false;
			return (this.mChar == ((LinkedTrieNode<V>) aCompareTo).mChar);
		}
	}
}
