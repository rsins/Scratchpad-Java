package com.myexample.miscellaneous.tree;

public class MyMainClass {

	public static void main(String[] args) {
		Tree myTree = new Tree();

		myTree.generateDefaultTree();
		
		System.out.println(myTree.getDepthWithoutRecursion());
		System.out.println(myTree.getDepthWithRecursion());
	}

}
