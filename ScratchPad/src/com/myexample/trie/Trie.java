package com.myexample.trie;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Trie<T> {
	
	private static class Node<T> {
		private char ch;
		private T value;
		
		public Node(char ch) {
			this(ch, null);
		}
		
		public Node(char ch, T value) {
			this.ch = ch;
			this.value = value;
		}
		
		private Map<Character, Node<T>> children = new HashMap<Character, Node<T>>();
	}
	
	private Node<T> root = new Node<T>((char) 0);
	
	public void add(String str, T value) {
		root = addImpl(root, str, value, 0);
	}
	
	private Node<T> addImpl(Node<T> node, String str, T value, int level) {
		Character curChar = str.charAt(level);
		Node<T> childNode = node.children.get(curChar);
		if (childNode == null) childNode = new Node<T>(curChar);
		if ((level + 1) == str.length()) childNode.value = value;
		else addImpl(childNode, str, value, level + 1);
		node.children.put(curChar, childNode);
		return node;
	}
	
	public T remove(String str) {
		if (root == null) return null;
		T value = null;
		Deque<Node<T>> stack = new ArrayDeque<Node<T>>();
		Node<T> node = root;
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			node = node.children.get(ch);
			if (node == null) break;
			stack.push(node);
		}
		value = (node == null) ? null : node.value;
		if (node != null) {
			Node<T> prev = (stack.isEmpty()) ? null : stack.pop();
			while (!stack.isEmpty()) {
				Node<T> next = stack.pop();
				if (prev.children.size() == 0 && prev.value == null) {
					next.children.remove(prev.ch);
				}
				prev = next;
			}
		}
		return value;
	}
	
	public Map<String, T> getAllValues() {
		return getAllValuesStartingWith("");
	}
	
	public T getValue(String str) {
		Node<T> node = getNodeImpl(str);
		return (node == null) ? null: node.value;
	}
	
	public Map<String, T> getAllValuesStartingWith(String str) {
		Map<String, T> result = new TreeMap<String, T>();
		if (str == null) return result;
		Node<T> node = getNodeImpl(str);
		getAllValuesStartingWithNode(node, new StringBuilder(str), result);
		return result;
	}
	
	private void getAllValuesStartingWithNode(Node<T> node, StringBuilder sb, Map<String, T> result) {
		if (node == null) return;
		if (node.value != null) result.put(sb.toString(), node.value);
		for (Node<T> child : node.children.values()) {
			sb.append(child.ch);
			getAllValuesStartingWithNode(child, sb, result);
			sb.setLength(sb.length() - 1);
		}
	}
	
	private Node<T> getNodeImpl(String str) {
		if (root == null) return null;
		Node<T> node = root;
		for (char ch : str.toCharArray()) {
			if (! node.children.containsKey(ch)) return null;
			node = node.children.get(ch);
		}
		return node;
	}
	
}
