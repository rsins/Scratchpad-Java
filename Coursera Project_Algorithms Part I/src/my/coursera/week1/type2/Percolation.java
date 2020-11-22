package my.coursera.week1.type2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private static final int OPEN = 1;
    private static final int CLOSE = 0;
        
    private int n = 0;
    private int[] posStatus = null;
   
    private final int topPosIndex;
    private final int bottomPosIndex;
    
    private WeightedQuickUnionUF weightedQuickUnionUF = null;
    
    public Percolation(int n) {                // create n-by-n grid, with all sites blocked
        if (n <= 0) throw new IllegalArgumentException();
        this.n = n;
        posStatus = new int[n*n+2];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int posIndex = getPosId(i, j);
                posStatus[posIndex] = CLOSE;
            }
        }
        
        topPosIndex = 0;
        bottomPosIndex = n * n + 1;
        
        posStatus[topPosIndex] = OPEN;
        posStatus[bottomPosIndex] = OPEN;
        
        weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2);
    }
    
    public void open(int row, int col) {       // open site (row, col) if it is not open already
        if (row < 1 || row > n || col < 1 || col > n) throw new IndexOutOfBoundsException();
        int posIndex = getPosId(row, col);
        if (posStatus[posIndex] == CLOSE) {
            if (row == 1) weightedQuickUnionUF.union(posIndex, topPosIndex);
            
            posStatus[posIndex] = OPEN;
            
            union(row, col, row-1, col);
            union(row, col, row+1, col);
            union(row, col, row, col-1);
            union(row, col, row, col+1);

            if (row == n && isFull(row, col)) weightedQuickUnionUF.union(posIndex, bottomPosIndex);
        }
    }
    
    public boolean isOpen(int row, int col) {  // is site (row, col) open?
        if (row < 1 || row > n || col < 1 || col > n) throw new IndexOutOfBoundsException();
        int posIndex = getPosId(row, col);
        return (posStatus[posIndex] == OPEN);
    }
    
    public boolean isFull(int row, int col) {  // is site (row, col) full?
        if (row < 1 || row > n || col < 1 || col > n) throw new IndexOutOfBoundsException();
        int posIndex = getPosId(row, col);
        return (posStatus[posIndex] == OPEN && weightedQuickUnionUF.connected(posIndex, topPosIndex));
    }
    
    public boolean percolates() {              // does the system percolate?
        for (int i = 1; i <= n; i++) {
            if (isOpen(n, i) && weightedQuickUnionUF.connected(getPosId(n, i), topPosIndex)) {
                return true;
            }
        }
        return false;
    }

    private int getPosId(int row, int col) {
        return ((row-1) * n + col);
    }
    
    private void union(int row1, int col1, int row2, int col2) {
        if (row1 < 1 || row1 > n || col1 < 1 || col1 > n ||
            row2 < 1 || row2 > n || col2 < 1 || col2 > n) {
            return;
        }
        if (!isOpen(row1, col1) || !isOpen(row2, col2)) {
            return;
        }
        
        int posIndex1 = getPosId(row1, col1);
        int posIndex2 = getPosId(row2, col2);
        weightedQuickUnionUF.union(posIndex1, posIndex2);
        
    }
        
}
    