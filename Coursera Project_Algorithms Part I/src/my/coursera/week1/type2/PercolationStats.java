package my.coursera.week1.type2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int n = 0;
    private int trials = 0;
    
    private double[] thresholds = null;
    
    public PercolationStats(int n, int trials) {    // perform trials independent experiments on an n-by-n grid
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        this.n = n;
        this.trials = trials;
        
        thresholds = new double[trials];
        runComputationalExperiments();
    }
    
    public double mean() {                          // sample mean of percolation threshold
        return StdStats.mean(thresholds);
    }
    
    public double stddev() {                        // sample standard deviation of percolation threshold
        return StdStats.stddev(thresholds);
    }
    
    public double confidenceLo() {                  // low  endpoint of 95% confidence interval
        return mean() - 1.96 * stddev() / Math.sqrt(trials);
    }
    
    public double confidenceHi() {                  // high endpoint of 95% confidence interval
        return mean() + 1.96 * stddev() / Math.sqrt(trials);
    }
    
    private void runComputationalExperiments() {
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            
            int openCount = 0;
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(1, n+1);
                int col = StdRandom.uniform(1, n+1);
                
                if (!percolation.isOpen(row, col)) {
                    openCount++;
                    percolation.open(row, col);
                }
            }
            
            thresholds[i] = ((double) openCount) / ((double) (n * n));
        }
    }

    public static void main(String[] args) {        // test client (described below)
        int n = Integer.valueOf(args[0]).intValue();
        int trials = Integer.valueOf(args[1]).intValue();

        PercolationStats percolationStats = new PercolationStats(n, trials);
        
        StdOut.println("mean\t\t\t= " + percolationStats.mean());
        StdOut.println("stddev\t\t\t= " + percolationStats.stddev());
        StdOut.println("95% confidence interval\t= " + 
                        percolationStats.confidenceLo() + ", " + 
                        percolationStats.confidenceHi());
        
    }

}
