package com.coursera.week1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class WordNet {
    private Digraph digraph;
    private SAP sap;
    
    // Contains <id, node>
    private Map<Integer, WordNode> allNodeIds = new HashMap<Integer, WordNode>();
    // Contains <noun, node>, multiple nouns can have same node object
    private Map<String, List<WordNode>> allNodeNouns = new HashMap<String, List<WordNode>>();
    
    // Similar to a tree but relationship direction is leaf to root
    private static class WordNode {
        private Integer id;
        private String[] syns;
        private String description;
        
        private List<WordNode> parents = new ArrayList<WordNode>();
        
        public WordNode(int id, String[] syns, String description) {
            this.id = id;
            this.syns = Arrays.copyOf(syns, syns.length);
            this.description = description;
        }
        
        @Override
        public String toString() {
            return id.toString() + "," + String.join(" ", syns) + "," + description;
        }
    }
    
    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) throw new NullPointerException();
        // Get the Synsets
        readSynset(synsets);
        // Get the Hypernyms
        readHypernyms(hypernyms);
        // Build the digraph and SAP
        buildDigraphAndSAP();
        // check if rooted DAG or not.
        if (countRoots() != 1) throw new IllegalArgumentException();
    }
    
    private void readSynset(String synsets) {
        In synsReader = new In(synsets);
        String line = null;
        while ((line = synsReader.readLine()) != null) {
            String[] words = line.split(",");
            String[] nouns = words[1].split(" ");
            WordNode node = new WordNode(Integer.valueOf(words[0]), nouns, words[2]);
            allNodeIds.put(node.id, node);
            for (String noun : nouns) {
                if (!allNodeNouns.containsKey(noun)) allNodeNouns.put(noun, new LinkedList<WordNode>());
                allNodeNouns.get(noun).add(node);
            }
        }
    }

    private void readHypernyms(String hypernyms) {
        In hypernymReader = new In(hypernyms);
        String line = null;
        while ((line = hypernymReader.readLine()) != null) {
            String[] words = line.split(",");
            Integer curId = Integer.valueOf(words[0]);
            WordNode node = allNodeIds.get(curId);
            for (int i = 1; i < words.length; i++) {
                Integer nextId = Integer.valueOf(words[i]);
                WordNode nextNode = allNodeIds.get(nextId);
                node.parents.add(nextNode);
            }
        }
    }
    
    private void buildDigraphAndSAP() {
        digraph = new Digraph(allNodeIds.size());
        for (Entry<Integer, WordNode> entry : allNodeIds.entrySet()) {
            int curId = entry.getKey();
            for (WordNode node : entry.getValue().parents) {
                int newId = node.id;
                digraph.addEdge(curId, newId);
            }
        }
        sap = new SAP(digraph);
    }
    
    // Nodes where outdegree is zero.
    private int countRoots() {
        List<Integer> roots = new ArrayList<Integer>();
        for (int n = 0; n < digraph.V(); n++) {
            if (digraph.indegree(n) > 0 && digraph.outdegree(n) == 0) roots.add(n);
        }
        // StdOut.println("countroots -> " + roots.size());
        return roots.size();
    }
    
    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return allNodeNouns.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) throw new NullPointerException();
        return allNodeNouns.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (nounA == null || nounB == null) throw new NullPointerException();
        if (!isNoun(nounA) || !isNoun(nounB)) throw new IllegalArgumentException();
        
        List<WordNode> nodeAList = allNodeNouns.get(nounA);
        List<Integer> nodeAIds = new ArrayList<Integer>();
        for (WordNode node : nodeAList) nodeAIds.add(node.id);
        
        List<WordNode> nodeBList = allNodeNouns.get(nounB);
        List<Integer> nodeBIds = new ArrayList<Integer>();
        for (WordNode node : nodeBList) nodeBIds.add(node.id);
        
        return sap.length(nodeAIds, nodeBIds);
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (nounA == null || nounB == null) throw new NullPointerException();
        if (!isNoun(nounA) || !isNoun(nounB)) throw new IllegalArgumentException();
        
        List<WordNode> nodeAList = allNodeNouns.get(nounA);
        List<Integer> nodeAIds = new ArrayList<Integer>();
        for (WordNode node : nodeAList) nodeAIds.add(node.id);
        
        List<WordNode> nodeBList = allNodeNouns.get(nounB);
        List<Integer> nodeBIds = new ArrayList<Integer>();
        for (WordNode node : nodeBList) nodeBIds.add(node.id);
        
        int ancestorId = sap.ancestor(nodeAIds, nodeBIds);
        WordNode ancestorNode = allNodeIds.get(ancestorId);
        return (ancestorNode == null) ? null : String.join(" ", ancestorNode.syns);
    }

    // Test
    /*
    private void printPaths(String nounA, String nounB) {
        List<WordNode> nodeAList = allNodeNouns.get(nounA);
        List<Integer> nodeAIds = new ArrayList<Integer>();
        for (WordNode node : nodeAList) nodeAIds.add(node.id);
        
        List<WordNode> nodeBList = allNodeNouns.get(nounB);
        List<Integer> nodeBIds = new ArrayList<Integer>();
        for (WordNode node : nodeBList) nodeBIds.add(node.id);
        
        List<Integer>[] paths = sap.testPaths(nodeAIds, nodeBIds);
        for (Integer i : paths[0]) {
            StdOut.print(String.join(" ", allNodeIds.get(i).syns) + " , ");
        }
        StdOut.println();
        for (Integer i : paths[1]) {
            StdOut.print(String.join(" ", allNodeIds.get(i).syns) + " , ");
        }
        StdOut.println();
    }
    */
    
    // do unit testing of this class
    public static void main(String[] args) {
        test1();
        test2();
    }
    
    private static void test1() {
        StdOut.println(" ************************** TEST 1 ***********************************");
        String[][] files = new String[][] {
            new String[] {
                    "D:\\Ravi\\My_Programs\\Eclipse_Workspace\\Coursera Project_Algorithms Part 2\\resources\\week1\\wordnet\\synsets.txt",
                    "D:\\Ravi\\My_Programs\\Eclipse_Workspace\\Coursera Project_Algorithms Part 2\\resources\\week1\\wordnet\\hypernyms.txt"
            },
            new String[] {
                    "D:\\Ravi\\My_Programs\\Eclipse_Workspace\\Coursera Project_Algorithms Part 2\\resources\\week1\\wordnet\\synsets100-subgraph.txt",
                    "D:\\Ravi\\My_Programs\\Eclipse_Workspace\\Coursera Project_Algorithms Part 2\\resources\\week1\\wordnet\\hypernyms100-subgraph.txt"
            },
            new String[] {
                    "D:\\Ravi\\My_Programs\\Eclipse_Workspace\\Coursera Project_Algorithms Part 2\\resources\\week1\\wordnet\\synsets500-subgraph.txt",
                    "D:\\Ravi\\My_Programs\\Eclipse_Workspace\\Coursera Project_Algorithms Part 2\\resources\\week1\\wordnet\\hypernyms500-subgraph.txt"
            },
            new String[] {
                    "D:\\Ravi\\My_Programs\\Eclipse_Workspace\\Coursera Project_Algorithms Part 2\\resources\\week1\\wordnet\\synsets1000-subgraph.txt",
                    "D:\\Ravi\\My_Programs\\Eclipse_Workspace\\Coursera Project_Algorithms Part 2\\resources\\week1\\wordnet\\hypernyms1000-subgraph.txt"
            } 
            /* ,
            new String[] {
                    "D:\\Ravi\\My_Programs\\Eclipse_Workspace\\Coursera Project_Algorithms Part 2\\resources\\week1\\wordnet\\synsets3.txt",
                    "D:\\Ravi\\My_Programs\\Eclipse_Workspace\\Coursera Project_Algorithms Part 2\\resources\\week1\\wordnet\\hypernyms3InvalidTwoRoots.txt" // hypernyms3InvalidCycle.txt"
            }
            */
        };
        
        String[][] nouns = new String[][] {
            new String[] {
                    "predicate", "Eragrostis_curvula"
            },
            new String[] {
                    "thing", "pacifier"
            },
            new String[] {
                    "oil", "branched_chain"
            },
            new String[] {
                    "factor_I", "caul"
            }
            /* ,
            new String[] {
                    "dummy", "dummy"
            }
            */
        };
        
        for (int i = 0; i < files.length; i++) {
            String[] fileNames = files[i];
            String[] nounAB = nouns[i];
            StdOut.println(" **** " + fileNames[0] + "\n" + fileNames[1]);
            StdOut.println(" - " + nounAB[0] + " " + nounAB[1]);
            WordNet wordnet = new WordNet(fileNames[0], fileNames[1]);
            StdOut.println(" distance = " + wordnet.distance(nounAB[0], nounAB[1]));
            StdOut.println(" ancestor = " + wordnet.sap(nounAB[0], nounAB[1]));
            // wordnet.printPaths(nounAB[0], nounAB[1]);
        }
    }
    
    private static void test2() {
        StdOut.println(" ************************** TEST 2 ***********************************");
        String[][] files = new String[][] {
            new String[] {
                    "D:\\Ravi\\My_Programs\\Eclipse_Workspace\\Coursera Project_Algorithms Part 2\\resources\\week1\\wordnet\\synsets.txt",
                    "D:\\Ravi\\My_Programs\\Eclipse_Workspace\\Coursera Project_Algorithms Part 2\\resources\\week1\\wordnet\\hypernyms.txt"
            },
            new String[] {
                    "D:\\Ravi\\My_Programs\\Eclipse_Workspace\\Coursera Project_Algorithms Part 2\\resources\\week1\\wordnet\\synsets100-subgraph.txt",
                    "D:\\Ravi\\My_Programs\\Eclipse_Workspace\\Coursera Project_Algorithms Part 2\\resources\\week1\\wordnet\\hypernyms100-subgraph.txt"
            },
            new String[] {
                    "D:\\Ravi\\My_Programs\\Eclipse_Workspace\\Coursera Project_Algorithms Part 2\\resources\\week1\\wordnet\\synsets500-subgraph.txt",
                    "D:\\Ravi\\My_Programs\\Eclipse_Workspace\\Coursera Project_Algorithms Part 2\\resources\\week1\\wordnet\\hypernyms500-subgraph.txt"
            },
            new String[] {
                    "D:\\Ravi\\My_Programs\\Eclipse_Workspace\\Coursera Project_Algorithms Part 2\\resources\\week1\\wordnet\\synsets1000-subgraph.txt",
                    "D:\\Ravi\\My_Programs\\Eclipse_Workspace\\Coursera Project_Algorithms Part 2\\resources\\week1\\wordnet\\hypernyms1000-subgraph.txt"
            }
        };
        
        String[][] nouns = new String[][] {
            new String[] {
                    "size", "sinoatrial_node"
            },
            new String[] {
                    "thing", "whopper"
            },
            new String[] {
                    "oil", "branched_chain"
            },
            new String[] {
                    "short_bone", "fat"
            }
        };
        
        for (int i = 0; i < files.length; i++) {
            String[] fileNames = files[i];
            String[] nounAB = nouns[i];
            WordNet wordnet = new WordNet(fileNames[0], fileNames[1]);
            StdOut.println(" **** " + fileNames[0] + "\n" + fileNames[1]);
            StdOut.println(" - " + nounAB[0] + " " + nounAB[1]);
            StdOut.println(" distance = " + wordnet.distance(nounAB[0], nounAB[1]));
            StdOut.println(" ancestor = " + wordnet.sap(nounAB[0], nounAB[1]));
            // wordnet.printPaths(nounAB[0], nounAB[1]);
        }
    }
}
