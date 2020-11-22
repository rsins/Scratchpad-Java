package my.coursera.week2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {

    public static void main(String[] args) {
        int k = Integer.valueOf(args[0]).intValue();
        RandomizedQueue<String> r = new RandomizedQueue<String>();
        
        try {
            while (true) {
                String word = StdIn.readString();
                r.enqueue(word);
            }
        }
        catch (Exception e) { }
        
        for (int i = 0; i < k; i++) {
            StdOut.println(r.dequeue());
        }
    }
}
