package my.coursera.week4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

    private static class SearchNode implements Comparable<SearchNode> {
        private int hamming = -1;
        private int manhattan = -1;
        private int numMoves = -1;
        
        private Board searchBoard;
        private SearchNode prevSearchNode;
        
        public SearchNode(Board board, SearchNode prev) {
            this.searchBoard = board;
            this.hamming = board.hamming();
            this.manhattan = board.manhattan();
            this.numMoves = (prev == null) ? 0 : (prev.numMoves + 1);
            this.prevSearchNode = prev;
        }
        
        @Override
        public int compareTo(SearchNode o) {
            int first = this.manhattan + this.numMoves;
            int second = o.manhattan + o.numMoves;
            if (first == second) {
                first = this.hamming + this.numMoves;
                second = o.hamming + o.numMoves;
            }
            return (first - second);
        }
        
        /*
        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (this == obj) return true;
            if (!obj.getClass().toString().equals(SearchNode.class.toString())) return false;
            SearchNode that = (SearchNode) obj;
            return this.getBoard().equals(that.getBoard());
        }
        
        @Override
        public int hashCode() {
            return super.hashCode();
        }
        */
        
        public Board getBoard() {
            return this.searchBoard;
        }
        
        public SearchNode getPreviousSearchNode() {
            return this.prevSearchNode;
        }
        
        public int getNumMoves() {
            return numMoves;
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("hamming = " + hamming);
            sb.append("\nmanhattan = " + manhattan);
            sb.append("\nnumMoves = " + numMoves);
            sb.append("\nblocks = \n" + searchBoard.toString());
            return sb.toString();
        }
    }
    
    private List<Board> boardSolverSteps = new ArrayList<Board>();
    private boolean isSolvable = false;
    
    public Solver(Board initial) {           // find a solution to the initial board (using the A* algorithm)
        if (initial == null) throw new NullPointerException();
        SearchNode searchNode1 = new SearchNode(initial, null);
        SearchNode searchNode2 = new SearchNode(initial.twin(), null);

        MinPQ<SearchNode> pq1 = new MinPQ<Solver.SearchNode>();
        MinPQ<SearchNode> pq2 = new MinPQ<Solver.SearchNode>();
        
        pq1.insert(searchNode1);
        pq2.insert(searchNode2);
        
        List<Board> boardsCovered1 = new ArrayList<Board>();
        List<Board> boardsCovered2 = new ArrayList<Board>();
        
        while (true) {
            searchNode1 = pq1.delMin();
            searchNode2 = pq2.delMin();
            
            if (!boardsCovered1.contains(searchNode1.getBoard())) boardsCovered1.add(searchNode1.getBoard());
            if (!boardsCovered2.contains(searchNode2.getBoard())) boardsCovered2.add(searchNode2.getBoard());
            
            // StdOut.println(searchNode1.getBoard());
            // StdOut.println(searchNode2.getBoard());
            if (searchNode1.getBoard().isGoal()) {
                isSolvable = true;
                boardSolverSteps = getSolvedSteps(searchNode1);
                break;
            }
            
            if (searchNode2.getBoard().isGoal()) {
                isSolvable = false;
                break;
            }
            
            // pq1 = new MinPQ<Solver.SearchNode>();
            // int count = 0;
            for (Board neighbor : searchNode1.getBoard().neighbors()) {
                // if ((searchNode1.getPreviousSearchNode() != null) &&
                //        neighbor.equals(searchNode1.getPreviousSearchNode().getBoard())) continue;
                if (boardsCovered1.contains(neighbor)) continue;
                pq1.insert(new SearchNode(neighbor, searchNode1));
                // count++;
            }
            // if (count == 0) {
            //    searchNode1 = searchNode1.getPreviousSearchNode();
            // }
            
            // pq2 = new MinPQ<Solver.SearchNode>();
            // count = 0;
            for (Board neighbor : searchNode2.getBoard().neighbors()) {
                // if ((searchNode2.getPreviousSearchNode() != null) &&
                //        neighbor.equals(searchNode2.getPreviousSearchNode().getBoard())) continue;
                if (boardsCovered2.contains(neighbor)) continue;
                pq2.insert(new SearchNode(neighbor, searchNode2));
                // count++;
            }
            // if (count == 0) {
            //    searchNode2 = searchNode2.getPreviousSearchNode();
            // }
            
            /*
            if (pq1.isEmpty()) {
                if (searchNode1.getPreviousSearchNode() == null) {
                    isSolvable = false;
                    break;
                }
                searchNode1 = searchNode1.getPreviousSearchNode();
                pq1.insert(searchNode1);
            }
            
            if (pq2.isEmpty()) {
                if (searchNode2.getPreviousSearchNode() != null) {
                    searchNode2 = searchNode2.getPreviousSearchNode();
                }
                pq2.insert(searchNode2);
            }
            */
        }
        
    }
    
    private List<Board> getSolvedSteps(SearchNode searchNode) {
        List<Board> steps = new ArrayList<Board>(searchNode.getNumMoves());
        SearchNode curNode = searchNode;
        for (int i = 0; i <= searchNode.getNumMoves(); i++) {
            steps.add(curNode.getBoard());
            curNode = curNode.getPreviousSearchNode();
        }
        Collections.reverse(steps);
        return steps;
    }
    
    public boolean isSolvable() {            // is the initial board solvable?
        return isSolvable;
    }
    
    public int moves() {                     // min number of moves to solve initial board; -1 if unsolvable
        return (isSolvable()) ? (boardSolverSteps.size() - 1) : -1;
    }
    
    public Iterable<Board> solution() {      // sequence of boards in a shortest solution; null if unsolvable
        List<Board> resultBoardSteps = new ArrayList<Board>(boardSolverSteps.size());
        for (Board b : boardSolverSteps) {
            resultBoardSteps.add(b);
        }
        return (isSolvable()) ? resultBoardSteps : null;
    }
    
    
    public static void main(String[] args) { // solve a slider puzzle (given below)
        // create initial board from file
        StdOut.println("* File : " + args[0]);
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
