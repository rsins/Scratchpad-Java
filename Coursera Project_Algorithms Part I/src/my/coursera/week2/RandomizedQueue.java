package my.coursera.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private class RandomizedQueueIterator implements Iterator<Item> {
        int[] indexArr = null;
        int curIndex = -1;
        
        public RandomizedQueueIterator() {
            indexArr = new int[size];
            for (int i = 0; i < size; i++) {
                indexArr[i] = i;
            }
            randomizeIndices();
            curIndex = 0;
        }
        
        private void randomizeIndices() {
            for (int i = 1; i < indexArr.length; i++) {
                int j = StdRandom.uniform(i);
                swapValues(i, j);
            }
        }
        
        private void swapValues(int pos1, int pos2) {
            int val = indexArr[pos1];
            indexArr[pos1] = indexArr[pos2];
            indexArr[pos2] = val;
        }
        
        @Override
        public boolean hasNext() {
            return (curIndex < indexArr.length);
        }

        @Override
        public Item next() {
            curIndex++;
            return getAt(indexArr[curIndex-1]);
        }

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
        
    }
    
    private float threshold = 0.75f;
    private int size = 0;
    private Item[] itemArray = null;
    
    @SuppressWarnings("unchecked")
    public RandomizedQueue() {                 // construct an empty randomized queue
         itemArray = (Item[]) new Object[10];
    }
    
    private void setAt(int index, Item item) {
        itemArray[index] = item;
    }
    
    private Item getAt(int index) {
        return itemArray[index];
    }
    
    public boolean isEmpty() {                 // is the queue empty?
        return (size == 0);
    }
     
    public int size() {                        // return the number of items on the queue
        return size;
    }
     
    @SuppressWarnings("unchecked")
    public void enqueue(Item item) {           // add the item
         if (item == null) throw new NullPointerException();
         if (size >= ((int) (itemArray.length * threshold)) ) {
             Item[] newArr = (Item[]) new Object[itemArray.length * 2];
             for (int i = 0; i < size; i++) {
                 newArr[i] = getAt(i);
             }
             itemArray = newArr;
         }
         setAt(size, item);
         size++;
    }
     
    public Item dequeue() {                    // remove and return a random item
        if (size == 0) throw new NoSuchElementException();
        int index = StdRandom.uniform(size);
        Item curValue = getAt(index);
        
        setAt(index, getAt(size - 1));
        setAt(size - 1, null);
        size--;
        return curValue;
    }
     
    public Item sample() {                     // return (but do not remove) a random item
        if (size == 0) throw new NoSuchElementException();
        int index = StdRandom.uniform(size);
        return getAt(index);
    }
     
    @Override
    public Iterator<Item> iterator() {         // return an independent iterator over items in random order
        return new RandomizedQueueIterator();
    }
     
    public static void main(String[] args) {   // unit testing
         RandomizedQueue<String> r = new RandomizedQueue<String>();
         r.enqueue("apple");
         r.enqueue("bed");
         r.enqueue("zebra");
         r.enqueue("lion");
         r.enqueue("tiger");
         
         
         StdOut.println(" - - - - sample");
         StdOut.println(r.sample());
         StdOut.println(r.sample());
         StdOut.println(r.sample());
         StdOut.println(" - - - - none");
         for (String s : r) {
             StdOut.println(s);
         }
         StdOut.println(" - - - - none");
         for (String s : r) {
             StdOut.println(s);
         }
         StdOut.println(" - - - - none");
         for (String s : r) {
             StdOut.println(s);
         }
         
         r.dequeue();
         StdOut.println(" - - - - after deque");
         for (String s : r) {
             StdOut.println(s);
         }
         r.dequeue();
         StdOut.println(" - - - - after deque");
         for (String s : r) {
             StdOut.println(s);
         }
         
    } 
    
}
