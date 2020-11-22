package com.myexample.recursion;

import java.io.PrintStream;

public class Tree {
	private final static int myMaximumNodeValue = 100;
	private double mValue = 0;
	Tree mLeftNode = null;
	Tree mRightNode = null;
	
	public Tree() {
		mValue = Math.random() * myMaximumNodeValue;
	}
	
	public double getValue() {
		return mValue;
	}
	public void setValue(double aValue) {
		mValue = aValue;
	}
	public Tree getLeftNode() {
		return mLeftNode;
	}
	public void setLeftNode(Tree aNode) {
		mLeftNode = aNode;
	}
	public Tree getRightNode() {
		return mRightNode;
	}
	public void setRightNode(Tree aNode) {
		mRightNode = aNode;
	}
	
	public static Tree generateDefaultTree() {
		Tree myTree = new Tree();
		
		Tree myTempLeftTree = new Tree();
		Tree myTempRightTree = new Tree();
		
		myTree.setLeftNode(myTempLeftTree);
		myTree.setRightNode(myTempRightTree);

		Tree myTempTree1 = myTempLeftTree;
		Tree myTempTree2 = myTempRightTree;
		
		myTempLeftTree = new Tree();
		myTempRightTree = new Tree();
		myTempTree1.setLeftNode(myTempLeftTree);
		myTempTree1.setRightNode(myTempRightTree);
		
		myTempLeftTree = new Tree();
		myTempRightTree = new Tree();
		myTempTree2.setLeftNode(myTempLeftTree);
		myTempTree2.setRightNode(myTempRightTree);
		
		return myTree;
	}
	
	public void printTree(PrintStream aPrintStream) {
		print(aPrintStream, 0, 0, 0);
	}
	
	private void print(PrintStream aPrintStream, int aLevel, int aLeftCount, int aRightCount) {
		
		aPrintStream.println("(Level, Left Count, Right Count):(" + aLevel + "," + aLeftCount + "," + aRightCount + ") = " + mValue);
		
		if (mLeftNode != null) {
			mLeftNode.print(aPrintStream, aLevel + 1, aLeftCount + 1, aRightCount);
		}
		
		if (mRightNode != null) {
			mRightNode.print(aPrintStream, aLevel + 1, aLeftCount, aRightCount + 1);
		}
	}
}
