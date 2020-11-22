package com.myexample.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Tree<T extends Comparable<T>> {
	private static class Node<T> {
		private Node<T> left;
		private Node<T> right;
		private T value;
		
		public Node(T value) {
			this(null, null, value);
		}
		
		public Node (Node<T> left, Node<T> right, T value) {
			this.left = left;
			this.right = right;
			this.value = value;
		}
		
		@Override
		public String toString() {
			return value.toString();
		}
	}
	
	private Node<T> root;
	
	public void add(T item) {
		root = addImpl(root, item);
	}
	
	private Node<T> addImpl(Node<T> node, T item) {
		if (node == null) return new Node<T>(item);
		int cmp = item.compareTo(node.value);
		if (cmp > 0) node.right = addImpl(node.right, item);
		else if (cmp < 0) node.left = addImpl(node.left, item);
		return node;
	}
	
	public void remove(T item) {
		root = removeImpl(root, item);
	}
	
	public Node<T> removeImpl(Node<T> node, T item) {
		if (node == null) return null;
		int cmp = item.compareTo(node.value);
		if (cmp > 0) node.right = removeImpl(node.right, item);
		else if (cmp < 0) node.left = removeImpl(node.left, item);
		else {
			// Current node is what needs to be removed.
			if (node.left == null && node.right == null) node = null;
			else if (node.left == null && node.right != null) node = node.right;
			else if (node.left != null && node.right == null) node = node.left;
			else {
				Node<T> lNode = node.right.left;
				while (lNode.left != null) lNode = lNode.left;
				lNode.left = node.left;
				node = node.right;
			}
		}
		return node;
	}
	
	public List<T> inOrder() {
		List<T> list = new ArrayList<T>();
		inOrderImpl(root, list);
		return list;
	}
	
	private void inOrderImpl(Node<T> node, List<T> list) {
		if (node == null) return;
		if (node.left != null) inOrderImpl(node.left, list);
		list.add(node.value);
		if (node.right != null) inOrderImpl(node.right, list);
	}
	
	public List<T> preOrder() {
		List<T> list = new ArrayList<T>();
		preOrderImpl(root, list);
		return list;
	}
	
	private void preOrderImpl(Node<T> node, List<T> list) {
		if (node == null) return;
		list.add(node.value);
		if (node.left != null) preOrderImpl(node.left, list);
		if (node.right != null) preOrderImpl(node.right, list);
	}
	
	public List<T> postOrder() {
		List<T> list = new ArrayList<T>();
		postOrderImpl(root, list);
		return list;
	}
	
	private void postOrderImpl(Node<T> node, List<T> list) {
		if (node == null) return;
		if (node.left != null) postOrderImpl(node.left, list);
		if (node.right != null) postOrderImpl(node.right, list);
		list.add(node.value);
	}
	
	public List<T> breadthFirstTraversal() {
		List<T> list = new ArrayList<T>();
		Deque<Node<T>> queue = new ArrayDeque<Node<T>>();
		if (root != null) queue.addLast(root);
		while (!queue.isEmpty()) {
			Node<T> node = queue.removeFirst();
			list.add(node.value);
			if (node.left != null) queue.addLast(node.left);
			if (node.right != null) queue.addLast(node.right);
		}
		return list;
	}
	
	public List<T> depthFirstTraversal() {
		List<T> list = new ArrayList<T>();
		Deque<Node<T>> stack = new ArrayDeque<Node<T>>();
		if (root != null) stack.push(root);
		while (!stack.isEmpty()) {
			Node<T> node = stack.pop();
			list.add(node.value);
			if (node.right != null) stack.push(node.right);
			if (node.left != null) stack.push(node.left);
		}
		return list;
	}
	
}
