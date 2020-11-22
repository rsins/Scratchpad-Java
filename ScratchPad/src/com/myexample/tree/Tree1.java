package com.myexample.tree;

import java.io.PrintStream;
import java.util.Comparator;

public class Tree1 <T> {
	Node myRoot = null;
	
	Comparator<T> myComparator = null;
	boolean myOrderAscending = true;
	int mySize = 0;
	
	public Tree1(Comparator<T> aComparator, boolean aOrderAscending) {
		myComparator = aComparator;
		myOrderAscending = aOrderAscending;
	}
	
	public int size() {
		return mySize;
	}
	
	public void add(T aValue) {
		Node myCurrentNode = myRoot;
		Node myChildNode = null;
		
		if (mySize == 0) {
			myRoot = new Node <T> (aValue);
			mySize++;
			return;
		}
		
		int myCurrentComparision = 0;
		
		while (myCurrentNode != null) {
			myCurrentComparision = myComparator.compare(aValue, (T) myCurrentNode.getValue());
			if (myCurrentComparision == 0) {
				return;
			}
			if (myOrderAscending) {
				if (myCurrentComparision < 0) {
					myChildNode = myCurrentNode.getLeft();
					
					if (myChildNode == null) {
						myCurrentNode.setLeft(new Node<T> (aValue));
						mySize++;
						return;
					}
					else {
						if ((myComparator.compare(aValue, (T) myChildNode.getValue()) > 0) &&
							(myChildNode.getRight() == null)) {
							myCurrentNode.setLeft(new Node<T> (aValue));
							myCurrentNode.getLeft().setLeft(myChildNode);
							
							mySize++;
							return;
						}
						else {
							myCurrentNode = myChildNode;
						}
					}
				}
				if (myCurrentComparision > 0) {
					myChildNode = myCurrentNode.getRight();
					
					if (myChildNode == null) {
						myCurrentNode.setRight(new Node<T> (aValue));
						mySize++;
						return;
					}
					else {
						if ((myComparator.compare(aValue, (T) myChildNode.getValue()) < 0) &&
								(myChildNode.getLeft() == null)) {
								myCurrentNode.setRight(new Node<T> (aValue));
								myCurrentNode.getRight().setRight(myChildNode);
								
								mySize++;
								return;
							}
							else {
								myCurrentNode = myChildNode;
							}
					}
				}
			}
			else {
				if (myCurrentComparision > 0) {
					myChildNode = myCurrentNode.getLeft();
					
					if (myChildNode == null) {
						myCurrentNode.setLeft(new Node<T> (aValue));
						mySize++;
						return;
					}
					else {
						if ((myComparator.compare(aValue, (T) myChildNode.getValue()) < 0) &&
							(myChildNode.getRight() == null)) {
							myCurrentNode.setLeft(new Node<T> (aValue));
							myCurrentNode.getLeft().setLeft(myChildNode);
							
							mySize++;
							return;
						}
						else {
							myCurrentNode = myChildNode;
						}
					}
				}
				if (myCurrentComparision < 0) {
					myChildNode = myCurrentNode.getRight();
					
					if (myChildNode == null) {
						myCurrentNode.setRight(new Node<T> (aValue));
						mySize++;
						return;
					}
					else {
						if ((myComparator.compare(aValue, (T) myChildNode.getValue()) > 0) &&
								(myChildNode.getLeft() == null)) {
								myCurrentNode.setRight(new Node<T> (aValue));
								myCurrentNode.getRight().setRight(myChildNode);
								
								mySize++;
								return;
							}
							else {
								myCurrentNode = myChildNode;
							}
					}
				}
			}
		}
			
	}
	
	public void print(PrintStream aPrintStream) {
		myRoot.printInOrder(aPrintStream);
	}

	
	private class Node <E> {
		E myValue;
		Node myLeft = null;
		Node myRight = null;
		
		public Node(E aValue) {
			myValue = aValue;
		}
		
		public Node(E aValue, Node aLeft, Node aRight) {
			myValue = aValue;
			myLeft = aLeft;
			myRight = aRight;
		}
		
		public E getValue() { 
			return myValue;
		}
		
		public Node getLeft() {
			return myLeft;
		}
		
		public void setLeft(Node aLeft) {
			myLeft = aLeft;
		}
		
		public Node getRight() {
			return myRight;
		}
		
		public void setRight(Node aRight) {
			myRight = aRight;
		}
		
		public void printInOrder(PrintStream aPrintStream) {
			if (myLeft != null) {
				myLeft.printInOrder(aPrintStream);
			}
			
			aPrintStream.print(myValue + " ");
			
			if (myRight != null) {
				myRight.printInOrder(aPrintStream);
			}
		}
		
	} // Node
	
} // Tree
