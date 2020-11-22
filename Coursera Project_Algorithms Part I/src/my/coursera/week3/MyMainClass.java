package my.coursera.week3;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class MyMainClass {

	public static void main(String[] args) {
		//type1("D:\\Ravi\\My_Programs\\Eclipse_Workspace\\Coursera Project_Algorithms Part I\\resources\\week3\\collinear-testing\\input299.txt");
		
		//while(! StdDraw.hasNextKeyTyped()) {
		//}
		//StdDraw.nextKeyTyped();
		
		//type2("D:\\Ravi\\My_Programs\\Eclipse_Workspace\\Coursera Project_Algorithms Part I\\resources\\week3\\collinear-testing\\input299.txt");
		type2(System.getProperty("user.dir") + File.separator + "resources" + File.separator + "week3" + File.separator + "collinear-testing" + File.separator + "input299.txt");
		StdOut.println("Done.");
	}

	private static void type1(String fileName) {
		// read the n points from a file
	    In in = new In(fileName);
	    int n = in.readInt();
	    Point[] points = new Point[n];
	    for (int i = 0; i < n; i++) {
	        int x = in.readInt();
	        int y = in.readInt();
	        points[i] = new Point(x, y);
	    }

	    // draw the points
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : points) {
	        p.draw();
	    }
	    StdDraw.show();

	    // print and draw the line segments
	    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show();
	}
	
	private static void type2(String fileName) {
		// read the n points from a file
	    In in = new In(fileName);
	    int n = in.readInt();
	    Point[] points = new Point[n];
	    for (int i = 0; i < n; i++) {
	        int x = in.readInt();
	        int y = in.readInt();
	        points[i] = new Point(x, y);
	    }

	    // draw the points
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : points) {
	        p.draw();
	    }
	    StdDraw.show();

	    // print and draw the line segments
	    FastCollinearPoints collinear = new FastCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show();
	}
}
