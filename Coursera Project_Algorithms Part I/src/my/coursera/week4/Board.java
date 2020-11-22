package my.coursera.week4;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.StdOut;

public class Board {
    
    private int[][] blocks;
    private int dimension;
    
    private int blankRow;
    private int blankCol;
    
    // private boolean isGoal = false;
    // private int hamming = -1;
    // private int manhattan = -1;
    
    public Board(int[][] blocks) {           // construct a board from an n-by-n array of blocks
                                             // (where blocks[i][j] = block in row i, column j)
        this.dimension = blocks.length;
        this.blocks = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                this.blocks[i][j] = blocks[i][j];
                if (blocks[i][j] == 0) {
                    blankRow = i;
                    blankCol = j;
                }
            }
        }
        
        // initValues();
    }
    
    // private void initValues() {
    //    this.isGoal = isGoalImpl();
    //    this.hamming = hammingImpl();
    //    this.manhattan = manhattanImpl();
    // }
    
    public int dimension() {                 // board dimension n
        return dimension;
    }
    
    public int hamming() {                   // number of blocks out of place
        // return this.hamming;
        return hammingImpl();
    }
    
    private int hammingImpl() {
        int count = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if ((i == (dimension -1)) && (j == (dimension - 1)) && (blocks[i][j] != 0)) {
                    count++;
                }
                else {
                    int val = i * dimension + j + 1;
                    if (blocks[i][j] != val) count++;
                }
            }
        }
        // Blank cell should not be counted.
        return (count - 1);
    }
    
    public int manhattan() {                 // sum of Manhattan distances between blocks and goal
        // return this.manhattan;
        return manhattanImpl();
    }
    
    private int manhattanImpl() {
        int count = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                int goalI = -1;
                int goalJ = -1;
                // Blank cell should not be counted.
                if (blocks[i][j] == 0) {
                    continue;
                }
                else {
                    goalI = ((blocks[i][j] - 1) / dimension);
                    goalJ = ((blocks[i][j] - 1) % dimension);
                }
                count = count + Math.abs(goalI - i) + Math.abs(goalJ - j);
            }
        }
        return count;
    }
    
    public boolean isGoal() {                // is this board the goal board?
        // return isGoal;
        return isGoalImpl();
    }
    
    private boolean isGoalImpl() {
        if (blocks[dimension - 1][dimension - 1] != 0) return false;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if ((i == (dimension -1)) && (j == (dimension - 1))) {
                    continue;
                }
                else {
                    int val = i * dimension + j + 1;
                    if (blocks[i][j] != val) return false;
                }
            }
        }
        return true;
    }
    
    public Board twin() {                    // a board that is obtained by exchanging any pair of blocks
        Board b = new Board(this.blocks);

        int i1 = -1, j1 = -1, i2 = -1, j2 = -1;
        for (int i = 0; i < dimension; i++) {
            i1 = -1;
            j1 = -1;
            i2 = -1;
            j2 = -1;
            for (int j = 0; j < (dimension - 1); j++) {
                if (b.blocks[i][j] == 0) continue;
                i1 = i;
                j1 = j;
                for (int k = (j + 1); k < dimension; k++) {
                    if (b.blocks[i][k] == 0) continue;
                    if (b.blocks[i1][j1] > b.blocks[i][k]) {
                        i2 = i;
                        j2 = k;
                        break;
                    }
                }
                if ((i1 != -1) && (i2 != -1)) break;
            }
            if ((i1 != -1) && (i2 != -1)) break;
        }

        if ((i1 == -1) || (i2 == -1)) {
            if ((b.blocks[0][0] != 0) && (b.blocks[0][1] != 0)) {
                i1 = 0;
                j1 = 0;
                i2 = 0;
                j2 = 1;
            }
            else {
                i1 = 1;
                j1 = 0;
                i2 = 1;
                j2 = 1;
            }
        }
        
        int val = b.blocks[i1][j1];
        b.blocks[i1][j1] = b.blocks[i2][j2];
        b.blocks[i2][j2] = val;
        return b;
    }
    
    /*
    public Board twin() {                    // a board that is obtained by exchanging any pair of blocks
        Board b = new Board(this.blocks);

        int i1 = -1, j1 = -1, i2 = -1, j2 = -1;
        for (int i = 0; i < dimension; i++) {
            i1 = j1 = i2 = j2 = -1;
            for (int j = 0; j < dimension; j++) {
                boolean valueInPosition = true;
                if ((i == (dimension -1)) && (j == (dimension - 1) && (b.blocks[dimension - 1][dimension - 1] != 0))) {
                    valueInPosition = false;
                }
                else {
                    int val = i * dimension + j + 1;
                    if (b.blocks[i][j] != val) valueInPosition = false;
                }
                if (!valueInPosition) {
                    if ((i1 == -1) && (b.blocks[i][j] != 0)) {
                        i1 = i;
                        j1 = j;
                    }
                    else if ((i2 == -1) && (b.blocks[i][j] != 0)) {
                        i2 = i;
                        j2 = j;
                        break;
                    }
                }
            }
            if ((i1 != -1) && (i2 != -1)) break;
        }

        if ((i1 == -1) || (i2 == -1)) {
            if ((b.blocks[0][0] != 0) && (b.blocks[0][1] != 0)) {
                i1 = 0;
                j1 = 0;
                i2 = 0;
                j2 = 1;
            }
            else {
                i1 = 1;
                j1 = 0;
                i2 = 1;
                j2 = 1;
            }
        }
        
        int val = b.blocks[i1][j1];
        b.blocks[i1][j1] = b.blocks[i2][j2];
        b.blocks[i2][j2] = val;
        return b;
    }
    */
    
    @Override
    public boolean equals(Object y) {        // does this board equal y?
        if (y == null) return false;
        if (this == y) return true;
        if (!y.getClass().toString().equals(Board.class.toString())) return false;
        Board that = (Board) y;
        if (this.dimension != that.dimension) return false;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (this.blocks[i][j] != that.blocks[i][j]) return false;
            }
        }
        return true;
    }
    
    public Iterable<Board> neighbors() {     // all neighboring boards
        List<Board> neighbors = new ArrayList<Board>();
        
        Board newNeighbor = new Board(this.blocks);
        boolean isValidMove = newNeighbor.move(newNeighbor.blankRow - 1, newNeighbor.blankCol, 
                                               newNeighbor.blankRow, newNeighbor.blankCol);
        if (isValidMove) {
            neighbors.add(newNeighbor);
            newNeighbor = new Board(this.blocks);
        }
        
        isValidMove = newNeighbor.move(newNeighbor.blankRow + 1, newNeighbor.blankCol, 
                                       newNeighbor.blankRow, newNeighbor.blankCol);
        if (isValidMove) {
            neighbors.add(newNeighbor);
            newNeighbor = new Board(this.blocks);
        }
        
        isValidMove = newNeighbor.move(newNeighbor.blankRow, newNeighbor.blankCol - 1, 
                                       newNeighbor.blankRow, newNeighbor.blankCol);
        if (isValidMove) {
            neighbors.add(newNeighbor);
            newNeighbor = new Board(this.blocks);
        }
        
        isValidMove = newNeighbor.move(newNeighbor.blankRow, newNeighbor.blankCol + 1, 
                                       newNeighbor.blankRow, newNeighbor.blankCol);
        if (isValidMove) neighbors.add(newNeighbor);
        
        return neighbors;
    }
    
    // Returns if move was valid.
    private boolean move(int row1, int col1, int row2, int col2) {
        boolean isValidMove = moveImpl(row1, col1, row2, col2);
        // if (isValidMove) initValues();
        return isValidMove;
    }
    
    private boolean moveImpl(int row1, int col1, int row2, int col2) {
        if (row1 < 0 || row1 >= dimension || row2 < 0 || row2 >= dimension ||
            col1 < 0 || col1 >= dimension || col2 < 0 || col2 >= dimension) return false;
        
        if (blocks[row1][col1] == 0) {
            blankRow = row2;
            blankCol = col2;
        }
        else if (blocks[row2][col2] == 0) {
            blankRow = row1;
            blankCol = col1;
        }
        else return false; // If either cells are not 0 then no move permitted.
        
        int val = blocks[row1][col1];
        blocks[row1][col1] = blocks[row2][col2];
        blocks[row2][col2] = val;
        return true;
    }
    
    public String toString() {               // string representation of this board (in the output format specified below)
        StringBuilder sb = new StringBuilder();
        sb.append(dimension + "\n");
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                sb.append(String.format("%2d ", blocks[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    
    public static void main(String[] args) { // unit tests (not graded)
        Board b = new Board(new int[][] {{1, 0}, {2, 3}});
        StdOut.println(b.toString());
        StdOut.println("Hamming : " + b.hamming());
        StdOut.println("Manhattan : " + b.manhattan());
        
        StdOut.println("--------------");
        for (Board nb : b.neighbors()) {
            StdOut.println("--------------");
            StdOut.println(nb.toString());
            StdOut.println("Hamming : " + nb.hamming());
            StdOut.println("Manhattan : " + nb.manhattan());
        }
        
        StdOut.println("---- twin ----");
        StdOut.println(b.twin().toString());
    }
}
