package com.coursera.week1;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SAP {
    private static class NodePairs {
        private int v;
        private int w;
        
        public NodePairs(int v, int w) {
            this.v = v;
            this.w = w;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (!(obj instanceof NodePairs)) return false;
            NodePairs that = (NodePairs) obj;
            return this.v == that.v && this.w == that.w;
        }
        
        @Override
        public int hashCode() {
            int hash = Integer.hashCode(this.v) + Integer.hashCode(this.w);
            return hash;
        }
    }
    
    private Digraph digraph; 
    // Cached routes between two nodes.
    private Map<NodePairs, List<Integer>[]> calculatedPaths = new HashMap<NodePairs, List<Integer>[]>();
    // Cached traversal routes from a source to all possible moves.
    // private Map<Integer, List<List<Integer>>> cachedTraversalPaths = new HashMap<Integer, List<List<Integer>>>();
    
    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        this.digraph = new Digraph(G);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        List<Integer>[] paths = findShortestCommonPaths(v, w);
        if (paths == null) return -1;
        int length = (paths[0].size() > 0) ? paths[0].size() - 1 : 0;
        length    += (paths[1].size() > 0) ? paths[1].size() - 1 : 0;
        return length;
    }

    // TEST
    // public List<Integer>[] testPaths(Iterable<Integer> v, Iterable<Integer> w) {
    //    return findShortestCommonPaths(v, w);
    // }
    
    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        List<Integer>[] paths = findShortestCommonPaths(v, w);
        if (paths == null) return -1;
        if (paths[0].size() == 0 && paths[1].size() != 0) return paths[1].get(paths[1].size() - 1);
        return paths[0].get(paths[0].size() - 1);
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        return lengthImpl1(v, w);
    }

    private int lengthImpl1(Iterable<Integer> v, Iterable<Integer> w) {
        List<Integer>[] paths = findShortestCommonPaths(v, w);
        if (paths == null) return -1;
        int length = (paths[0].size() > 0) ? paths[0].size() - 1 : 0;
        length    += (paths[1].size() > 0) ? paths[1].size() - 1 : 0;
        return length;
    }
    
    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        return ancestorImpl1(v, w);
    }
    
    private int ancestorImpl1(Iterable<Integer> v, Iterable<Integer> w) {
        List<Integer>[] paths = findShortestCommonPaths(v, w);
        if (paths == null) return -1;
        if (paths[0].size() == 0 && paths[1].size() != 0) return paths[1].get(paths[1].size() - 1);
        return paths[0].get(paths[0].size() - 1);
    }
    
    /*
    private List<List<Integer>> getCachedPaths(int curN) {
        return cachedTraversalPaths.containsKey(curN) ? cachedTraversalPaths.get(curN) : null;
    }
    
    private List<List<Integer>> getPaths(int n) {
        List<List<Integer>> result = getCachedPaths(n);
        if (result != null) return result;
        result = new ArrayList<List<Integer>>();
        
        // for (int cur = 0; cur < digraph.V(); cur++) {
            // int node = cur;
            int node = n; 
            // List to store nodes which are already visited
            List<Integer> visited = new ArrayList<Integer>(); 
            // Stack to store current node in depth first traversal
            Deque<Integer> nodeDequeue = new ArrayDeque<Integer>(); 
            // Store the iterators for adjacent nodes
            Deque<Iterator<Integer>> adjIteratorDequeue = new ArrayDeque<Iterator<Integer>>(); 
            nodeDequeue.addLast(node);
            visited.add(node);
            adjIteratorDequeue.addLast(digraph.adj(node).iterator());
            while (!adjIteratorDequeue.isEmpty()) {
                Iterator<Integer> itr = adjIteratorDequeue.removeLast();
                if (!itr.hasNext()) {
                    int node1 = nodeDequeue.removeLast();
                    visited.remove(Integer.valueOf(node1));
                    continue;
                }
                
                int next = itr.next();
                adjIteratorDequeue.addLast(itr);
                
                if (visited.contains(next)) {
                    if (itr.hasNext()) continue;
                    else {
                        List<Integer> path = new ArrayList<Integer>(nodeDequeue);
                        addToResultPathsIfItIsNotCovered(result, path);
                        int removed = nodeDequeue.removeLast();
                        visited.remove(Integer.valueOf(removed));
                        adjIteratorDequeue.removeLast();
                        continue;
                    }
                }
                else {
                    Iterator<Integer> itr1 = digraph.adj(next).iterator();
                    visited.add(next);
                    nodeDequeue.addLast(next);
                    if (!itr1.hasNext()) {
                        List<Integer> path = new ArrayList<Integer>(nodeDequeue);
                        addToResultPathsIfItIsNotCovered(result, path);
                        int removed = nodeDequeue.removeLast();
                        visited.remove(Integer.valueOf(removed));
                        continue;
                        
                    }
                    adjIteratorDequeue.addLast(itr1);
                }
            }
        // }
        
        result = new ArrayList<List<Integer>>(result); 
        cachedTraversalPaths.put(n, result);
        return result;
    }
    
    private void addToResultPathsIfItIsNotCovered(List<List<Integer>> result, List<Integer> path) {
        // TODO
        result.add(new ArrayList<Integer>(path));
    }
    */
    
    /*
    private List<Integer>[] findShortestCommonPaths(int v, int w) {
        List<Integer>[] path1 = findShortestCommonPathsImpl(v, w);
        List<Integer>[] path2 = findShortestCommonPathsImpl(w, v);
        if (path1 == null && path2 == null) return null;
        if (path1 != null && path2 == null) return path1;
        if (path1 == null && path2 != null) return path2;
        
        int length1 = path1[0].size() + path1[1].size();
        int length2 = path2[0].size() + path2[1].size();
        return (length1 < length2) ? path1 : path2;
    }
    */
   
    private List<Integer>[] findShortestCommonPathsBFS(int v, int w) {
        if (v == w) {
            List<Integer> pathV = new ArrayList<Integer>();
            pathV.add(v);
            return new List[] {pathV, pathV};
        }
       
        // Result caching
        NodePairs curNodePairs = new NodePairs(v, w); 
        if (calculatedPaths.containsKey(curNodePairs)) return calculatedPaths.get(curNodePairs);
        
        List<Integer>[] shortestCommonPaths = (List<Integer>[]) new List[2];
        int lengthShortestCommonPath = Integer.MAX_VALUE;
        
        Deque<List<Integer>> pathsV = new ArrayDeque<List<Integer>>();
        Deque<List<Integer>> pathsW = new ArrayDeque<List<Integer>>();
        
        pathsV.addLast(new ArrayList<Integer>());
        pathsV.peekLast().add(v);
        pathsW.addLast(new ArrayList<Integer>());
        pathsW.peekLast().add(w);
        int lastpathsVSize = -1;
        int lastpathsWSize = -1;
        boolean breakWhileLoop = false;
        while (!breakWhileLoop) {
            // Check the exit condition.
            int newpathsVSize = 0;
            for (List<Integer> path : pathsV) newpathsVSize += path.size();
            int newpathsWSize = 0;
            for (List<Integer> path : pathsW) newpathsWSize += path.size();
            if (lastpathsVSize == newpathsVSize && lastpathsWSize == newpathsWSize) break;
            else {
                lastpathsVSize = newpathsVSize;
                lastpathsWSize = newpathsWSize;
            }
            
            
            // Get new paths.
            List<List<Integer>> tempPathsV = new ArrayList<List<Integer>>(pathsV);
            pathsV.clear();
            for (List<Integer> curBFSPathV : tempPathsV) {
                boolean atLeastOneNewPathV = false;
                int lastNodeV = curBFSPathV.get(curBFSPathV.size() - 1);
                for (int n : digraph.adj(lastNodeV)) {
                    if (!curBFSPathV.contains(n)) {
                        atLeastOneNewPathV = true;
                        List<Integer> newPath = new ArrayList<Integer>(curBFSPathV);
                        newPath.add(n);
                        pathsV.addLast(newPath);
                    }
                }
                if (!atLeastOneNewPathV) pathsV.addLast(curBFSPathV);
            }
            
            List<List<Integer>> tempPathsW = new ArrayList<List<Integer>>(pathsW);
            pathsW.clear();
            for (List<Integer> curBFSPathW : tempPathsW) {
                boolean atLeastOneNewPathW = false;
                int lastNodeW = curBFSPathW.get(curBFSPathW.size() - 1);
                for (int n : digraph.adj(lastNodeW)) {
                    if (!curBFSPathW.contains(n)) {
                        atLeastOneNewPathW = true;
                        List<Integer> newPath = new ArrayList<Integer>(curBFSPathW);
                        newPath.add(n);
                        pathsW.addLast(newPath);
                    }
                }
                if (!atLeastOneNewPathW) pathsW.addLast(curBFSPathW);
            }
            
            
            // Checking common points between two paths.
            if (pathsV.size() != 0 && pathsW.size() == 0) {
                for (List<Integer> pathV : pathsV) {
                    if (pathV.contains(w)) {
                        int curLength = pathV.indexOf(w);
                        if (curLength < lengthShortestCommonPath) {
                            List<Integer> curPathV = new ArrayList<Integer>();
                            for (int j = 0; j <= pathV.indexOf(w); j++) curPathV.add(pathV.get(j));
                            List<Integer> curPathW = new ArrayList<Integer>();
                            // int curLength = curPathV.size() + curPathW.size() - 1;
                            
                            lengthShortestCommonPath = curLength;
                            shortestCommonPaths = (List<Integer>[]) new List[] {curPathV, curPathW};
                        }
                        else breakWhileLoop = true;
                    }
                }
            }
            else if (pathsV.size() == 0 && pathsW.size() != 0) {
                for (List<Integer> pathW : pathsW) {
                    if (pathW.contains(v)) {
                        int curLength = pathW.indexOf(v);
                        if (curLength < lengthShortestCommonPath) {
                            List<Integer> curPathW = new ArrayList<Integer>();
                            for (int j = 0; j <= pathW.indexOf(v); j++) curPathW.add(pathW.get(j));
                            List<Integer> curPathV = new ArrayList<Integer>();
                            // int curLength = curPathW.size() + curPathV.size() - 1;
                            
                            lengthShortestCommonPath = curLength;
                            shortestCommonPaths = (List<Integer>[]) new List[] {curPathV, curPathW};
                        }
                        else breakWhileLoop = true;
                    }
                }
            }
            else {
                for (List<Integer> pathV : pathsV) {
                    int iV = pathV.size() - 1;
                    int curV = pathV.get(iV);
                    for (List<Integer> pathW : pathsW) {
                        // Checking correct break condition.
                        int lengthShortestCommonPathOld = lengthShortestCommonPath;
                        
                        if (pathW.contains(curV)) {
                            int curLength = iV + pathW.indexOf(curV);
                            if (curLength < lengthShortestCommonPath) {
                                List<Integer> curPathV = new ArrayList<Integer>();
                                for (int j = 0; j <= iV; j++) curPathV.add(pathV.get(j));
                                List<Integer> curPathW = new ArrayList<Integer>();
                                for (int j = 0; j <= pathW.indexOf(curV); j++) curPathW.add(pathW.get(j));
                                
                                lengthShortestCommonPath = curLength;
                                shortestCommonPaths = (List<Integer>[]) new List[] {curPathV, curPathW};
                            }
                            else if (lengthShortestCommonPathOld < Integer.MAX_VALUE) {
                                breakWhileLoop = true; // Checking correct break condition.
                            }
                        }
                        
                        int iW = pathW.size() - 1;
                        int curW = pathW.get(iW);
                        if (pathV.contains(curW)) {
                            int curLength = iW + pathV.indexOf(curW);
                            if (curLength < lengthShortestCommonPath) {
                                List<Integer> curPathW = new ArrayList<Integer>();
                                for (int j = 0; j <= iW; j++) curPathW.add(pathW.get(j));
                                List<Integer> curPathV = new ArrayList<Integer>();
                                for (int j = 0; j <= pathV.indexOf(curW); j++) curPathV.add(pathV.get(j));
                                
                                lengthShortestCommonPath = curLength;
                                shortestCommonPaths = (List<Integer>[]) new List[] {curPathV, curPathW};
                            }
                            else if (lengthShortestCommonPathOld < Integer.MAX_VALUE) {
                                breakWhileLoop = true; // Checking correct break condition.
                            }
                        }
                    }
                }
                
                /*
                for (List<Integer> pathV : pathsV) {
                    for (int i = 0; i < pathV.size(); i++) {
                        // List<Integer> visited = new ArrayList<Integer>();
                        int cur = pathV.get(i);
                        // if (!visited.contains(cur)) {
                            // visited.add(cur);
                            for (List<Integer> pathW : pathsW) {
                                if (pathW.contains(cur)) {
                                    int curLength = i + pathW.indexOf(cur);
                                    if (curLength < lengthShortestCommonPath) {
                                        List<Integer> curPathV = new ArrayList<Integer>();
                                        for (int j = 0; j <= i; j++) curPathV.add(pathV.get(j));
                                        List<Integer> curPathW = new ArrayList<Integer>();
                                        for (int j = 0; j <= pathW.indexOf(cur); j++) curPathW.add(pathW.get(j));
                                        // int curLength = curPathV.size() + curPathW.size() - 2;
                                        
                                        lengthShortestCommonPath = curLength;
                                        shortestCommonPaths = (List<Integer>[]) new List[] {curPathV, curPathW};
                                    }
                                }
                            }
                        // }
                    }
                }
                */
            }
            
        }
        
        // Result caching
        List<Integer>[] curPaths = (lengthShortestCommonPath == Integer.MAX_VALUE) ? null : shortestCommonPaths;
        calculatedPaths.put(curNodePairs, curPaths);
        return curPaths;
    }
    
    // Will return null if there is no common path, else will return the path to common ancestor for
    // both v and W in the array result.
    private List<Integer>[] findShortestCommonPaths(int v, int w) {
        // return findShortestCommonPathsNonBFS(v, w);
        return findShortestCommonPathsBFS(v, w);
    }
    
    /*
    private List<Integer>[] findShortestCommonPathsNonBFS(int v, int w) {
        if (v == w) {
            List<Integer> pathV = new ArrayList<Integer>();
            pathV.add(v);
            return new List[] {pathV, pathV};
        }
       
        // Result caching
        NodePairs curNodePairs = new NodePairs(v, w); 
        if (calculatedPaths.containsKey(curNodePairs)) return calculatedPaths.get(curNodePairs);
        
        List<Integer>[] shortestCommonPaths = (List<Integer>[]) new List[2];
        int lengthShortestCommonPath = Integer.MAX_VALUE;
        
        List<List<Integer>> pathsV = getPaths(v);
        List<List<Integer>> pathsW = getPaths(w);
        if (pathsV.size() != 0 && pathsW.size() == 0) {
            for (List<Integer> pathV : pathsV) {
                if (pathV.contains(w)) {
                    int curLength = pathV.indexOf(w);
                    if (curLength < lengthShortestCommonPath) {
                        List<Integer> curPathV = new ArrayList<Integer>();
                        for (int j = 0; j <= pathV.indexOf(w); j++) curPathV.add(pathV.get(j));
                        List<Integer> curPathW = new ArrayList<Integer>();
                        // int curLength = curPathV.size() + curPathW.size() - 1;
                        
                        lengthShortestCommonPath = curLength;
                        shortestCommonPaths = (List<Integer>[]) new List[] {curPathV, curPathW};
                    }
                }
            }
        }
        else if (pathsV.size() == 0 && pathsW.size() != 0) {
            for (List<Integer> pathW : pathsW) {
                if (pathW.contains(v)) {
                    int curLength = pathW.indexOf(v);
                    if (curLength < lengthShortestCommonPath) {
                        List<Integer> curPathW = new ArrayList<Integer>();
                        for (int j = 0; j <= pathW.indexOf(v); j++) curPathW.add(pathW.get(j));
                        List<Integer> curPathV = new ArrayList<Integer>();
                        // int curLength = curPathW.size() + curPathV.size() - 1;
                        
                        lengthShortestCommonPath = curLength;
                        shortestCommonPaths = (List<Integer>[]) new List[] {curPathV, curPathW};
                    }
                }
            }
        }
        else {
            for (List<Integer> pathV : pathsV) {
                for (int i = 0; i < pathV.size(); i++) {
                    List<Integer> visited = new ArrayList<Integer>();
                    int cur = pathV.get(i);
                    if (!visited.contains(cur)) {
                        visited.add(cur);
                        for (List<Integer> pathW : pathsW) {
                            if (pathW.contains(cur)) {
                                int curLength = i + pathW.indexOf(cur);
                                if (curLength < lengthShortestCommonPath) {
                                    List<Integer> curPathV = new ArrayList<Integer>();
                                    for (int j = 0; j <= i; j++) curPathV.add(pathV.get(j));
                                    List<Integer> curPathW = new ArrayList<Integer>();
                                    for (int j = 0; j <= pathW.indexOf(cur); j++) curPathW.add(pathW.get(j));
                                    // int curLength = curPathV.size() + curPathW.size() - 2;
                                    
                                    lengthShortestCommonPath = curLength;
                                    shortestCommonPaths = (List<Integer>[]) new List[] {curPathV, curPathW};
                                }
                            }
                        }
                    }
                }
            }
        }
        
        // Result caching
        List<Integer>[] curPaths = (lengthShortestCommonPath == Integer.MAX_VALUE) ? null : shortestCommonPaths;
        calculatedPaths.put(curNodePairs, curPaths);
        return curPaths;
    }
    */
    
    /*
    private List<Integer>[] findShortestCommonPaths(Iterable<Integer> v, Iterable<Integer> w) {
        List<Integer>[] path1 = findShortestCommonPathsImpl(v, w);
        List<Integer>[] path2 = findShortestCommonPathsImpl(w, v);
        if (path1 == null && path2 == null) return null;
        if (path1 != null && path2 == null) return path1;
        if (path1 == null && path2 != null) return path2;
        
        int length1 = path1[0].size() + path1[1].size();
        int length2 = path2[0].size() + path2[1].size();
        return (length1 < length2) ? path1 : path2;
    }
    */
    
    // Will return null if there is no common path, else will return the path to common ancestor for
    // both v and W in the array result.
    private List<Integer>[] findShortestCommonPaths(Iterable<Integer> v, Iterable<Integer> w) {
        List<Integer>[] shortestCommonPaths = (List<Integer>[]) new List[2];
        int lengthShortestCommonPath = Integer.MAX_VALUE;
        
        for (int intV : v) {
            for (int intW : w) {
                List<Integer>[] curShortestPath = findShortestCommonPaths(intV, intW);
                if (curShortestPath != null) {
                    int curLength = curShortestPath[0].size() + curShortestPath[1].size();
                    if (curLength < lengthShortestCommonPath) {
                        lengthShortestCommonPath = curLength;
                        shortestCommonPaths = curShortestPath;
                    }
                }
            }
        }
        return (lengthShortestCommonPath == Integer.MAX_VALUE) ? null : shortestCommonPaths;
    }
    
    // do unit testing of this class
    public static void main(String[] args) {
        /*
                0
         down  / \ up
              1 -> 2
                    \ down
                     3
         */
        Digraph G1 = new Digraph(4);
        G1.addEdge(0, 1);
        G1.addEdge(2, 0);
        G1.addEdge(1, 2);
        G1.addEdge(2, 3);
        SAP sap1 = new SAP(G1);
        // StdOut.println("Get paths for 0" + sap1.getPaths(0));
        // StdOut.println("Get paths for 1" + sap1.getPaths(1));
        // StdOut.println("Get paths for 2" + sap1.getPaths(2));
        // StdOut.println("Get paths for 3" + sap1.getPaths(3));
        
        List<Integer>[] shortestPath = sap1.findShortestCommonPaths(0, 3); 
        StdOut.println("Get shortest paths between 0 and 3" + shortestPath[0].toString() + " " + shortestPath[1].toString());
        StdOut.println("--------------------------------------------------- \n Enter two nodes: ");
        
        String[] files = new String[] {
                "D:\\Ravi\\My_Programs\\Eclipse_Workspace\\Coursera Project_Algorithms Part 2\\resources\\week1\\wordnet\\digraph1.txt",
                "D:\\Ravi\\My_Programs\\Eclipse_Workspace\\Coursera Project_Algorithms Part 2\\resources\\week1\\wordnet\\digraph2.txt", 
                "D:\\Ravi\\My_Programs\\Eclipse_Workspace\\Coursera Project_Algorithms Part 2\\resources\\week1\\wordnet\\digraph3.txt", 
                "D:\\Ravi\\My_Programs\\Eclipse_Workspace\\Coursera Project_Algorithms Part 2\\resources\\week1\\wordnet\\digraph4.txt",
                "D:\\Ravi\\My_Programs\\Eclipse_Workspace\\Coursera Project_Algorithms Part 2\\resources\\week1\\wordnet\\digraph5.txt",
                "D:\\Ravi\\My_Programs\\Eclipse_Workspace\\Coursera Project_Algorithms Part 2\\resources\\week1\\wordnet\\digraph6.txt",
                "D:\\Ravi\\My_Programs\\Eclipse_Workspace\\Coursera Project_Algorithms Part 2\\resources\\week1\\wordnet\\digraph9.txt"
        };
        
        for (String fileName : files) {
            process(fileName);
        }
    }
    
    private static void process(String fileName) {
        StdOut.println(fileName);
        In in = new In(fileName);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        // while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        // }
    }
}

