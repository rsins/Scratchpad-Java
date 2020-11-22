/******************************************************************************
 *  Compilation:  javac PercolationVisualizer.java
 *  Execution:    java PercolationVisualizer input.txt
 *  Dependencies: Percolation.java
 *
 *  This program takes the name of a file as a command-line argument.
 *  From that file, it
 *
 *    - Reads the grid size n of the percolation system.
 *    - Creates an n-by-n grid of sites (intially all blocked)
 *    - Reads in a sequence of sites (row i, column j) to open.
 *
 *  After each site is opened, it draws full sites in light blue,
 *  open sites (that aren't full) in white, and blocked sites in black,
 *  with with site (1, 1) in the upper left-hand corner.
 *
 ******************************************************************************/

package my.coursera.week1.type1;

import java.awt.Font;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationVisualizer {

    // draw n-by-n percolation system
    public static void draw(Percolation perc, int n, String filePath) {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setXscale(-0.05*n, 1.05*n);
        StdDraw.setYscale(-0.1*n, 1.05*n);   // leave a border to write text
        StdDraw.filledSquare(n/2.0, n/2.0, n/2.0);

        // draw n-by-n grid
        int opened = 0;
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                if (perc.isFull(row, col)) {
                    StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
                    opened++;
                }
                else if (perc.isOpen(row, col)) {
                    StdDraw.setPenColor(StdDraw.WHITE);
                    opened++;
                }
                else
                    StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.filledSquare(col - 0.5, n - row + 0.5, 0.45);
            }
        }

        // write status text
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 12));
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(0.5*n, -0.065*n, filePath.replace(System.getProperty("user.dir"),""));
        StdDraw.text(0.25*n, -0.025*n, opened + " open sites");
        if (perc.percolates()) StdDraw.text(0.75*n, -0.025*n, "percolates");
        else                   StdDraw.text(0.75*n, -0.025*n, "does not percolate");

    }

    public static void main(String[] args) {
    	// --------------------- Based on input files -------------------------
    	String[] filePaths = {
				"\\resources\\week1\\percolation-testing\\percolation\\greeting57.txt",
				"\\resources\\week1\\percolation-testing\\percolation\\heart25.txt",
				"\\resources\\week1\\percolation-testing\\percolation\\input1-no.txt",
				"\\resources\\week1\\percolation-testing\\percolation\\input1.txt",
				"\\resources\\week1\\percolation-testing\\percolation\\input2-no.txt",
				"\\resources\\week1\\percolation-testing\\percolation\\input2.txt",
				"\\resources\\week1\\percolation-testing\\percolation\\input3.txt",
				"\\resources\\week1\\percolation-testing\\percolation\\input4.txt",
				"\\resources\\week1\\percolation-testing\\percolation\\input5.txt",
				"\\resources\\week1\\percolation-testing\\percolation\\input6.txt",
				"\\resources\\week1\\percolation-testing\\percolation\\input7.txt",
				"\\resources\\week1\\percolation-testing\\percolation\\input8-no.txt",
				"\\resources\\week1\\percolation-testing\\percolation\\input8.txt",
				"\\resources\\week1\\percolation-testing\\percolation\\input10-no.txt",
				"\\resources\\week1\\percolation-testing\\percolation\\input10.txt",
				"\\resources\\week1\\percolation-testing\\percolation\\input20.txt",
				"\\resources\\week1\\percolation-testing\\percolation\\input50.txt",
				"\\resources\\week1\\percolation-testing\\percolation\\jerry47.txt",
				"\\resources\\week1\\percolation-testing\\percolation\\sedgewick60.txt",
				"\\resources\\week1\\percolation-testing\\percolation\\wayne98.txt"
		};
    	
    	// filePaths =  new String[] {"\\resources\\week1\\percolation-testing\\percolation\\jerry47.txt"};
    	for (String filePath : filePaths) {
    		String fullFilePath = System.getProperty("user.dir") + filePath;
            In in = new In(fullFilePath);      // input file
            int n = in.readInt();         // n-by-n percolation system

            // turn on animation mode
            StdDraw.enableDoubleBuffering();

            // repeatedly read in sites to open and draw resulting system
            Percolation perc = new Percolation(n);
            while (!in.isEmpty()) {
                int i = in.readInt();
                int j = in.readInt();
                perc.open(i, j);
            }
            draw(perc, n, fullFilePath);
            StdDraw.show();
            
            while(!StdDraw.hasNextKeyTyped()){
            }
            StdDraw.nextKeyTyped();
    	}
    	
    	// --------------------- Randomly select open box -------------------------
    	int n = 20;
    	String fileName = " -- Random -- ";
    	int delay = 100;
    	Percolation percolation = new Percolation(n);
		
    	draw(percolation, n, fileName);
    	StdDraw.show();
    	StdDraw.pause(delay);
		while (! percolation.percolates()) {
			int row = StdRandom.uniform(1, n+1);
			int col = StdRandom.uniform(1, n+1);
			
			if (! percolation.isOpen(row, col)) {
				percolation.open(row, col);
				
				draw(percolation, n, fileName);
		    	StdDraw.show();
		    	StdDraw.pause(delay);
			}
		}
		
    }
}
