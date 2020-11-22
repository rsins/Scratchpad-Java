package com.myexample.tree;

import java.util.Comparator;

public class MyMainClass1 {

	private static class IntegerComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer myInteger1, Integer myInteger2) {
			return (myInteger1.intValue() - myInteger2.intValue());
		}

	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tree1<Integer> myTree = new Tree1<>(new IntegerComparator(), true);

		myTree.add(new Integer(5));
		myTree.add(new Integer(9));
		myTree.add(new Integer(2));
		myTree.add(new Integer(1));
		myTree.add(new Integer(15));
		
		System.out.println("Tree Size: " + myTree.size());
		myTree.print(System.out);
	}

}
