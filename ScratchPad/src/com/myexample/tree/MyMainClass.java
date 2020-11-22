package com.myexample.tree;

public class MyMainClass {

	public static void main(String[] args) {
		Tree<Integer> tree = new Tree<Integer>();
		/*
		           45
		      1         100
		        35    75
		     20     50   88
		                    
		 */
		tree.add(45);
		tree.add(1);
		tree.add(100);
		tree.add(35);
		tree.add(75);
		tree.add(88);
		tree.add(50);
		tree.add(20);
		System.out.println("In Order : " + tree.inOrder());
		System.out.println("Pre Order : " + tree.preOrder());
		System.out.println("Post Order : " + tree.postOrder());
		System.out.println("BreadthFirst : " + tree.breadthFirstTraversal());
		System.out.println("DepthFirst : " + tree.depthFirstTraversal());
		
		System.out.println("Remove 40.");
		tree.remove(40);
		System.out.println("In Order : " + tree.inOrder());
		System.out.println("Pre Order : " + tree.preOrder());
		System.out.println("Post Order : " + tree.postOrder());
		System.out.println("BreadthFirst : " + tree.breadthFirstTraversal());
		System.out.println("DepthFirst : " + tree.depthFirstTraversal());
		
		System.out.println("Remove 50.");
		tree.remove(50);
		System.out.println("In Order : " + tree.inOrder());
		System.out.println("Pre Order : " + tree.preOrder());
		System.out.println("Post Order : " + tree.postOrder());
		System.out.println("BreadthFirst : " + tree.breadthFirstTraversal());
		System.out.println("DepthFirst : " + tree.depthFirstTraversal());
	}

}
