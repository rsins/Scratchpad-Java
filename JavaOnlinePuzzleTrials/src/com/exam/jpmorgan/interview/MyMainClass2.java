package com.exam.jpmorgan.interview;

import java.io.*;
import java.util.Scanner;

public class MyMainClass2 {

	private static class Rectangle {
		private int x1, y1, x2, y2;
		private int width, height;
		
		public Rectangle(int x1, int y1, int width, int height) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x1 + width;
			this.y2 = y1 + height;
			this.width = width;
			this.height = height;
		}
		
		@Override
		public String toString() {
			return "Rectangle(" + x1 + "," + y1 + "," + x2 + "," + y2 +")";
		}
		
		public static boolean checkOverlap(Rectangle r1, Rectangle r2) {
			// Conditions -
			// r1 is bigger and has r2 inside.
			// r2 is bigger and has r1 inside.
			// They do not intersect each other.
			// They intersect each other.
			//
			//               ------------------
			//               | r2             |
			//               |                |
			//               |                |
			//    ------------------          |
			//    | r1       |     |          |
			//    |          ------------------
			//    |                |
			//    ------------------
			//
			
			// System.out.println(r1.toString() + " " + r2.toString());
			if (r1.x2 < r2.x1) return false;
			if (r1.x1 > r2.x2) return false;
			if (r1.y2 < r2.y1) return false;
			if (r1.y1 > r2.y2) return false;
			return true;
		}	
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	    String s;
	    while ((s = in.readLine()) != null) {
	    	if (s.isEmpty()) break;
	    	Scanner scn = new Scanner(s);
	    	int x = scn.nextInt();
	    	int y = scn.nextInt();
	    	int width = scn.nextInt();
	    	int height = scn.nextInt();
	    	Rectangle r1 = new Rectangle(x, y, width, height);
	    	
	    	x = scn.nextInt();
	    	y = scn.nextInt();
	    	width = scn.nextInt();
	    	height = scn.nextInt();
	    	Rectangle r2 = new Rectangle(x, y, width, height);
	    	
	    	boolean doOverlap = Rectangle.checkOverlap(r1, r2);
	    	System.out.println(doOverlap);
	    	scn.close();
	    }
	}

}
