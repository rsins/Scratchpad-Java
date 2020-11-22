package com.coursera.week1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {
    
    private WordNet wordnet;
    
    public Outcast(WordNet wordnet) {         // constructor takes a WordNet object
        this.wordnet = wordnet;
    }
    
    public String outcast(String[] nouns) {   // given an array of WordNet nouns, return an outcast
        int longestDistance = -1;
        String outcastNoun = null;
        
        for (int i = 0; i < nouns.length; i++) {
            int curDistance = 0;
            String curNoun = nouns[i];
            for (String nounB : nouns) {
                if (nounB.equals(curNoun)) continue;
                curDistance += wordnet.distance(curNoun, nounB);
            }
            if (curDistance > longestDistance) {
                longestDistance = curDistance;
                outcastNoun = curNoun;
            }
        }
        
        return outcastNoun;
    }
    
    public static void main(String[] args) {  // see test client below
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}
