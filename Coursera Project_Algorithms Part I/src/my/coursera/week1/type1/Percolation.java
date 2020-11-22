package my.coursera.week1.type1;

public class Percolation {

    private static final int OPEN = 1;
    private static final int CLOSE = 0;
        
    private int n = 0;
    private int[] pos = null;
    private int[] posId = null;
    
    private final int topPosId;
    private final int bottomPosId;
    
    public Percolation(int n) {                // create n-by-n grid, with all sites blocked
        if (n <= 0) throw new IllegalArgumentException();
        this.n = n;
        pos = new int[n*n+2];
        posId = new int[n*n+2];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int posIndex = getPosId(i, j);
                pos[posIndex] = CLOSE;
                posId[posIndex] = posIndex;
            }
        }
        
        topPosId = 0;
        bottomPosId = n * n + 1;
        
        pos[topPosId] = OPEN;
        pos[bottomPosId] = OPEN;
        posId[topPosId] = topPosId;
        posId[bottomPosId] = bottomPosId;
    }
    
    public void open(int row, int col) {       // open site (row, col) if it is not open already
        if (row < 1 || row > n || col < 1 || col > n) throw new IndexOutOfBoundsException();
        int posIndex = getPosId(row, col);
        if (pos[posIndex] == CLOSE) {
            if (row == 1) {
                posId[posIndex] = topPosId;
            }
            
            pos[posIndex] = OPEN;
            
            union(row, col, row-1, col);
            union(row, col, row+1, col);
            union(row, col, row, col-1);
            union(row, col, row, col+1);

        }
    }
    
    public boolean isOpen(int row, int col) {  // is site (row, col) open?
        if (row < 1 || row > n || col < 1 || col > n) throw new IndexOutOfBoundsException();
        int posIndex = getPosId(row, col);
        return (pos[posIndex] == OPEN);
    }
    
    public boolean isFull(int row, int col) {  // is site (row, col) full?
        if (row < 1 || row > n || col < 1 || col > n) throw new IndexOutOfBoundsException();
        int posIndex = getPosId(row, col);
        return (pos[posIndex] == OPEN && getRootPosId(row, col) == topPosId);
    }
    
    public boolean percolates() {              // does the system percolate?
        for (int i = 1; i <= n; i++) {
            if (isFull(n, i)) return true;
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
        int rootPosId1 = getRootPosIdImpl(posIndex1);
        int rootPosId2 = getRootPosIdImpl(posIndex2);
        if (rootPosId1 != rootPosId2) {
            if (rootPosId1 < rootPosId2) {
                posId[rootPosId2] = rootPosId1;
                posId[posIndex2] = rootPosId1;
            }
            else {
                posId[rootPosId1] = rootPosId2;
                posId[posIndex1] = rootPosId2;
            }
        }
        
    }
    
    private int getRootPosId(int row, int col) {
        int currentPosId = getPosId(row, col);
        return getRootPosIdImpl(currentPosId);
    }
    
    private int getRootPosIdImpl(int posIndex) {
        int currentPosId = posIndex;
        int rootPosId = posId[currentPosId];
        while (currentPosId != rootPosId) {
            posId[currentPosId] = posId[rootPosId];
            currentPosId = rootPosId;
            rootPosId = posId[currentPosId];
        }
        
        return rootPosId;
    }
    
}
    