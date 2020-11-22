package com.myexample.miscellaneous.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Random;


public class Tree {
   public static class TreeNode {
	    int mValue;
	   
	    TreeNode mLeft = null;
	    TreeNode mRight = null;
	   
	    TreeNode (int aValue) {
		    mValue = aValue;
	    }

	    TreeNode (int aValue, TreeNode aLeftNode, TreeNode aRightNode) {
	    	mLeft = aLeftNode;
	    	mRight = aRightNode;
	    }
	    
		public TreeNode getmLeft() {
			return mLeft;
		}
	
		public void setmLeft(TreeNode mLeft) {
			this.mLeft = mLeft;
		}
	
		public TreeNode getmRight() {
			return mRight;
		}
	
		public void setmRight(TreeNode mRight) {
			this.mRight = mRight;
		}
	
		public int getmValue() {
			return mValue;
		}
   }
   
   TreeNode root = null;
   
   public void generateDefaultTree() {
	   final int MAX_VAL = 10000;
	   Random r = new Random();
	   
	   root = new TreeNode(r.nextInt(MAX_VAL));
	   
	   TreeNode curN = root;
	   TreeNode newN = curN;
	   TreeNode ln = new TreeNode(r.nextInt(MAX_VAL));
	   TreeNode rn = new TreeNode(r.nextInt(MAX_VAL));
	   newN.setmLeft(ln);
	   newN.setmRight(rn);
	   
	   newN = curN.getmLeft();
	   ln = new TreeNode(r.nextInt(MAX_VAL));
	   rn = new TreeNode(r.nextInt(MAX_VAL));
	   newN.setmLeft(ln);
	   newN.setmRight(rn);
	   
	   newN = curN.getmRight();
	   ln = new TreeNode(r.nextInt(MAX_VAL));
	   newN.setmLeft(ln);
	   
	   newN = curN.getmLeft().getmLeft();
	   ln = new TreeNode(r.nextInt(MAX_VAL));
	   rn = new TreeNode(r.nextInt(MAX_VAL));
	   newN.setmLeft(ln);
	   newN.setmRight(rn);
	   
	   /*
	    *           *
	    *         /   \
	    *        *     *
	    *       /\    /
	    *      *  *  *
	    *     /
	    *    *
	    */
   }
   
   public int getDepthWithoutRecursion() {
	   int level = 0;
	   
	   ArrayDeque<TreeNode> dq = new ArrayDeque<TreeNode>();
	   if (root != null) dq.addLast(root);
	   
	   while (true) {
		   if (dq.isEmpty()) {
			   break;
		   }
		   
		   level++;
		   int size = dq.size();
		   for (int i = 0; i < size; i++) {
			   TreeNode tn = (TreeNode) dq.removeFirst();
			   if (tn.getmLeft() != null) dq.addLast(tn.getmLeft());
			   if (tn.getmRight() != null) dq.addLast(tn.getmRight());
		   }
	   }
	   
	   return level;
   }
   
   public int getDepthWithRecursion() {
	   Deque<TreeNode> dq = new ArrayDeque<TreeNode>();
	   dq.addLast(root);
	   return (root == null) ? 0 : getDepthRecurseImpl(dq, 1);
   }
   
   private int getDepthRecurseImpl(Deque adq, int aLevel) {
	   Deque<TreeNode> dq = new ArrayDeque<TreeNode>();
	   
	   if (adq.isEmpty()) return (aLevel - 1);
	   
	   Iterator<TreeNode> itn = adq.iterator();
	   while(itn.hasNext()) {
		   TreeNode tn = itn.next();
		   if (tn.getmLeft() != null) dq.addLast(tn.getmLeft());
		   if (tn.getmRight() != null) dq.addLast(tn.getmRight());
	   }
	   
	   return getDepthRecurseImpl(dq, aLevel + 1);
   }
}
