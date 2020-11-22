package com.coursera.week2;

import java.awt.Color;
import java.util.concurrent.CountDownLatch;

import edu.princeton.cs.algs4.Picture;

public class SeamCarver2 {
    private int height = -1;
    private int width = -1;
    private Color[][] colors; 
    private double[][] energies;
    
    private final Object sharedLockV = new Object();
    private final Object sharedLockH = new Object();
    
    public SeamCarver2(Picture picture) {                // create a seam carver object based on the given picture
        if (picture == null) throw new NullPointerException();
        this.height = picture.height();
        this.width = picture.width();

        // Get colors.
        this.colors = new Color[this.width][this.height];
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.colors[i][j] = picture.get(i, j);
            }
        }
        
        // Calculate energy for each pixel.
        this.energies = new double[this.width][this.height];
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.energies[i][j] = energyImpl(i, j);
            }
        }
    }
    
    public Picture picture() {                          // current picture
        Picture picture = new Picture(width, height);
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                picture.set(i, j, colors[i][j]);
            }
        }
        return picture;
    }
    
    public     int width() {                            // width of current picture
        return this.width;
    }
    
    public     int height() {                           // height of current picture
        return this.height;
    }

    // http://coursera.cs.princeton.edu/algs4/assignments/seamCarving.html
    public  double energy(int x, int y) {               // energy of pixel at column x and row y
        if (x < 0 || x >= width || y < 0 || y >= height) throw new IndexOutOfBoundsException();
        return energies[x][y];
    }
    
    private  double energyImpl(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) return 2000; // To handle corner/border cases.
        if (x == 0 || x == (width - 1) || y == 0 || y == (height - 1)) return 1000;
        
        Color cx1 = colors[x + 1][y];
        Color cx2 = colors[x - 1][y];
        
        Color cy1 = colors[x][y + 1];
        Color cy2 = colors[x][y - 1];
        
        int cxr12 = cx1.getRed() - cx2.getRed();
        int cxg12 = cx1.getGreen() - cx2.getGreen();
        int cxb12 = cx1.getBlue() - cx2.getBlue();
        
        int cyr12 = cy1.getRed() - cy2.getRed();
        int cyg12 = cy1.getGreen() - cy2.getGreen();
        int cyb12 = cy1.getBlue() - cy2.getBlue();
        
        double energy = cxr12 * cxr12 + cxg12 * cxg12 + cxb12 * cxb12 +
                        cyr12 * cyr12 + cyg12 * cyg12 + cyb12 * cyb12;
        energy = Math.sqrt(energy);
        return energy;
    }
    
    public   int[] findHorizontalSeam() {               // sequence of indices for horizontal seam
        int[] resultSeam = new int[width];
        double[][] cachedEnergySums = new double[width][height];
        Object[][] bestSeams = new Object[width][height];
        if (width > 100) {
        	int block = 50;
        	int parts = height / block;
        	if (height % block != 0) parts++;
        	CountDownLatch latch = new CountDownLatch(parts);
        	for (int p = 0; p < parts; p++) {
        		int curPartStart = p * block;
        		System.out.println("Starting findHorizontalSeam for curPartStart = " + curPartStart);
        		Thread curThread = new Thread() {
        			@Override
        			public void run() {
        				for (int j = 0; j < height; j++) {
        	                findHorizontalSeamImpl(curPartStart, j, new int[width], resultSeam, cachedEnergySums, bestSeams);
        	                latch.countDown();
        	            }
        			}
        		};
        		curThread.start();
        	}
        	try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        else {
        	for (int j = 0; j < height; j++) {
                findHorizontalSeamImpl(0, j, new int[width], resultSeam, cachedEnergySums, bestSeams);
            }
        }
        return resultSeam;
    }
    
    private void findHorizontalSeamImpl(int curI, int curJ, int[] curSeam, int[] resultSeam, double[][] cachedEnergySums, Object[][] bestSeams) {
        if (curJ < 0 || curJ >= height || curI < 0) return;
        if (curI >= width) {
        	synchronized (sharedLockH) {
        		double resultSeamSum = 0L;
                double curSeamSum = 0L;
                for (int idx = (width - 1); idx >= 0; idx--) {
                    resultSeamSum += energies[idx][resultSeam[idx]];
                    curSeamSum += energies[idx][curSeam[idx]];
                    
                    // Caching
                    if (bestSeams[idx][curSeam[idx]] == null) {
                        int[] cachedSeam = new int[width - idx];
                        System.arraycopy(curSeam, idx, cachedSeam, 0, cachedSeam.length);
                        bestSeams[idx][curSeam[idx]] = cachedSeam;
                        cachedEnergySums[idx][curSeam[idx]] = curSeamSum;
                    }
                    else {
                        double cachedSeamSum = cachedEnergySums[idx][curSeam[idx]];
                        if (curSeamSum < cachedSeamSum) {
                            int[] cachedSeam = new int[width - idx];
                            System.arraycopy(curSeam, idx, cachedSeam, 0, cachedSeam.length);
                            bestSeams[idx][curSeam[idx]] = cachedSeam;
                            cachedEnergySums[idx][curSeam[idx]] = curSeamSum;
                        }
                    }
                }
                if (curSeamSum < resultSeamSum) {
                    System.arraycopy(curSeam, 0, resultSeam, 0, width);
                }
			}
            return;
        }
        
        curSeam[curI] = curJ;
        if (bestSeams[curI][curJ] != null) {
            int[] bestSeamAtCurPos = (int[]) bestSeams[curI][curJ];
            System.arraycopy(bestSeamAtCurPos, 0, curSeam, curI, bestSeamAtCurPos.length);
            findHorizontalSeamImpl(width, curSeam[curSeam.length - 1], curSeam, resultSeam, cachedEnergySums, bestSeams);
        }
        else {
            findHorizontalSeamImpl(curI + 1, curJ - 1, curSeam, resultSeam, cachedEnergySums, bestSeams);
            findHorizontalSeamImpl(curI + 1, curJ, curSeam, resultSeam, cachedEnergySums, bestSeams);
            findHorizontalSeamImpl(curI + 1, curJ + 1, curSeam, resultSeam, cachedEnergySums, bestSeams);
        }
    }
    
    /*
    private void findHorizontalSeamImpl(int curI, int curJ, int[] curSeam, int[] resultSeam) {
        if (curJ < 0 || curJ >= height || curI < 0) return;
        if (curI >= width) {
            double resultSeamSum = 0L;
            double curSeamSum = 0L;
            for (int idx = 0; idx < width; idx++) {
                resultSeamSum += energies[idx][resultSeam[idx]];
                curSeamSum += energies[idx][curSeam[idx]];
            }
            if (curSeamSum < resultSeamSum) {
                System.arraycopy(curSeam, 0, resultSeam, 0, width);
            }
            return;
        }
        
        curSeam[curI] = curJ;
        findHorizontalSeamImpl(curI + 1, curJ - 1, curSeam, resultSeam);
        findHorizontalSeamImpl(curI + 1, curJ, curSeam, resultSeam);
        findHorizontalSeamImpl(curI + 1, curJ + 1, curSeam, resultSeam);
    }
    */
    
    public   int[] findVerticalSeam() {                 // sequence of indices for vertical seam
        int[] resultSeam = new int[height];
        double[][] cachedEnergySums = new double[width][height];
        Object[][] bestSeams = new Object[width][height];
        if (height > 100) {
        	int block = 50;
        	int parts = height / block;
        	if (height % block != 0) parts++;
        	CountDownLatch latch = new CountDownLatch(parts);
        	for (int p = 0; p < parts; p++) {
        		int curPartStart = p * block;
        		System.out.println("Starting findVerticalSeam for curPartStart = " + curPartStart);
        		Thread curThread = new Thread() {
        			@Override
        			public void run() {
        				for (int i = 0; i < width; i++) {
        	                findVerticalSeamImpl(i, 0, new int[height], resultSeam, cachedEnergySums, bestSeams);
        	                latch.countDown();
        	            }
        			}
        		};
        		curThread.start();
        	}
        	try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        else {
        	for (int i = 0; i < width; i++) {
                findVerticalSeamImpl(i, 0, new int[height], resultSeam, cachedEnergySums, bestSeams);
            }
        }
        return resultSeam;
    }

    // Using caching of already calculated best path so far.
    private void findVerticalSeamImpl(int curI, int curJ, int[] curSeam, int[] resultSeam, double[][] cachedEnergySums, Object[][] bestSeams) {
        if (curI < 0 || curI >= width || curJ < 0) return;
        if (curJ >= height) {
        	synchronized (sharedLockV) {
        		double resultSeamSum = 0L;
                double curSeamSum = 0L;
                for (int idx = (height - 1); idx >= 0; idx--) {
                    resultSeamSum += energies[resultSeam[idx]][idx];
                    curSeamSum += energies[curSeam[idx]][idx];
                    
                    // Caching
                    if (bestSeams[curSeam[idx]][idx] == null) {
                        int[] cachedSeam = new int[height - idx];
                        System.arraycopy(curSeam, idx, cachedSeam, 0, cachedSeam.length);
                        bestSeams[curSeam[idx]][idx] = cachedSeam;
                        cachedEnergySums[curSeam[idx]][idx] = curSeamSum;
                    }
                    else {
                        double cachedSeamSum = cachedEnergySums[curSeam[idx]][idx];
                        if (curSeamSum < cachedSeamSum) {
                            int[] cachedSeam = new int[height - idx];
                            System.arraycopy(curSeam, idx, cachedSeam, 0, cachedSeam.length);
                            bestSeams[curSeam[idx]][idx] = cachedSeam;
                            cachedEnergySums[curSeam[idx]][idx] = curSeamSum;
                        }
                    }
                }
                if (curSeamSum < resultSeamSum) {
                    System.arraycopy(curSeam, 0, resultSeam, 0, height);
                }
        	}
            return;
        }
        
        curSeam[curJ] = curI;
        if (bestSeams[curI][curJ] != null) {
            int[] bestSeamAtCurPos = (int[]) bestSeams[curI][curJ];
            System.arraycopy(bestSeamAtCurPos, 0, curSeam, curJ, bestSeamAtCurPos.length);
            findVerticalSeamImpl(curSeam[curSeam.length - 1], height, curSeam, resultSeam, cachedEnergySums, bestSeams);
        }
        else {
            findVerticalSeamImpl(curI - 1, curJ + 1, curSeam, resultSeam, cachedEnergySums, bestSeams);
            findVerticalSeamImpl(curI, curJ + 1, curSeam, resultSeam, cachedEnergySums, bestSeams);
            findVerticalSeamImpl(curI + 1, curJ + 1, curSeam, resultSeam, cachedEnergySums, bestSeams);
        }
    }
    
    /*
    private void findVerticalSeamImpl(int curI, int curJ, int[] curSeam, int[] resultSeam) {
        if (curI < 0 || curI >= width || curJ < 0) return;
        if (curJ >= height) {
            double resultSeamSum = 0L;
            double curSeamSum = 0L;
            for (int idx = 0; idx < height; idx++) {
                resultSeamSum += energies[resultSeam[idx]][idx];
                curSeamSum += energies[curSeam[idx]][idx];
            }
            if (curSeamSum < resultSeamSum) {
                System.arraycopy(curSeam, 0, resultSeam, 0, height);
            }
            return;
        }
        
        curSeam[curJ] = curI;
        findVerticalSeamImpl(curI - 1, curJ + 1, curSeam, resultSeam);
        findVerticalSeamImpl(curI, curJ + 1, curSeam, resultSeam);
        findVerticalSeamImpl(curI + 1, curJ + 1, curSeam, resultSeam);
    }
    */
    
    public    void removeHorizontalSeam(int[] seam) {   // remove horizontal seam from current picture
        if (seam == null) throw new NullPointerException();
        if (seam.length != width) throw new IllegalArgumentException();
        if (height <= 1) throw new IllegalArgumentException();

        // remove seams
        for (int i = 0; i < seam.length; i++) {
            int j = seam[i];
            if (j < 0 || j >= height) throw new IllegalArgumentException();
            if (i > 0) {
                int prevI = i - 1;
                int prevJ = seam[prevI];
                if (Math.abs(prevJ - j) > 1) throw new IllegalArgumentException();                
            }
            
            for (int indexJ = j; indexJ < (height - 1); indexJ++) {
                colors[i][indexJ] = colors[i][indexJ + 1];
                energies[i][indexJ] = energies[i][indexJ + 1];
            }
        }
        
        this.height--;
        
        // recalculate the energies
        for (int i = 0; i < width; i++) {
            int j = seam[i];
            if (j >= 0 && j < height) energies[i][j] = energyImpl(i, j);
            if (i > 0) energies[i - 1][j] = energyImpl(i - 1, j);
            if (j > 0) energies[i][j - 1] = energyImpl(i, j - 1);
            if (i < (width - 1)) energies[i + 1][j] = energyImpl(i + 1, j);
            if (j < (height - 1)) energies[i][j + 1] = energyImpl(i, j + 1);
        }        
    }
    
    public    void removeVerticalSeam(int[] seam) {     // remove vertical seam from current picture
        if (seam == null) throw new NullPointerException();
        if (seam.length != height) throw new IllegalArgumentException();
        if (width <= 1) throw new IllegalArgumentException();
        
        // remove seams
        for (int j = 0; j < seam.length; j++) {
            int i = seam[j];
            if (i < 0 || i >= width) throw new IllegalArgumentException();
            if (j > 0) {
                int prevJ = j - 1;
                int prevI = seam[prevJ];
                if (Math.abs(prevI - i) > 1) throw new IllegalArgumentException();                
            }
            
            for (int indexI = i; indexI < (width - 1); indexI++) {
                colors[indexI][j] = colors[indexI + 1][j];
                energies[indexI][j] = energies[indexI + 1][j];
            }
        }
        
        this.width--;
        
        // recalculate the energies
        for (int j = 0; j < height; j++) {
            int i = seam[j];
            if (i >= 0 && i < width) energies[i][j] = energyImpl(i, j);
            if (i > 0) energies[i - 1][j] = energyImpl(i - 1, j);
            if (j > 0) energies[i][j - 1] = energyImpl(i, j - 1);
            if (i < (width - 1)) energies[i + 1][j] = energyImpl(i + 1, j);
            if (j < (height - 1)) energies[i][j + 1] = energyImpl(i, j + 1);
        }
        
    }
}
