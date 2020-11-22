package my.coursera.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

    private static class Node<Item> {
        Node<Item> prev = null;
        Node<Item> next = null;
        Item value = null;
        
        public Node(Item value, Node<Item> prev, Node<Item> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
    
    private class DequeIterator implements Iterator<Item> {
        Node<Item> current = null;
        
        public DequeIterator() {
            current = first;
        }
        
        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public Item next() {
            if (current == null) throw new NoSuchElementException();
            Node<Item> next = current;
            current = current.next;
            return next.value;
        }

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
        
    }
    
    private Node<Item> first = null;
    private Node<Item> last = null;
    private int size = 0;
    
    public Deque() {                            // construct an empty deque
    }
    
    public boolean isEmpty() {                  // is the deque empty?
        return (size == 0);
    }
    
    public int size() {                         // return the number of items on the deque
        return size;
    }
    
    public void addFirst(Item item) {           // add the item to the front
        if (item == null) throw new NullPointerException();
        Node<Item> cur = new Node<Item>(item, null, first);
        if (first != null) first.prev = cur;
        first = cur;
        size++;
        if (size == 1 && last == null) last = first;
    }
    
    public void addLast(Item item) {            // add the item to the end
        if (item == null) throw new NullPointerException();
        Node<Item> cur = new Node<Item>(item, last, null);
        if (last != null) last.next = cur;
        last = cur;
        size++;
        if (size == 1 && first == null) first = last;
    }
    
    public Item removeFirst() {                 // remove and return the item from the front
        if (size() == 0) throw new NoSuchElementException();
        Node<Item> cur = first;
        first = first.next;
        if (first != null) first.prev = null;
        size--;
        if (size == 0) last = null;
        return cur.value;
    }
    
    public Item removeLast() {                  // remove and return the item from the end
        if (size() == 0) throw new NoSuchElementException();
        Node<Item> cur = last;
        last = last.prev;
        if (last != null) last.next = null;
        size--;
        if (size == 0) first = null;
        return cur.value;
    }
       
    @Override
    public Iterator<Item> iterator() {            // return an iterator over items in order from front to end
        return new DequeIterator();
    }

    public static void main(String[] args) {    // unit testing
        Deque<String> d = new Deque<String>();
        d.addFirst("Apple");
        d.addFirst("Bed");
        d.addLast("Zebra");
        d.addFirst("Lion");
        for (String a : d) {
            StdOut.println(a);
        }
        StdOut.println(" - - - - - - ");
        d.removeFirst();
        d.removeLast();
        for (String a : d) {
            StdOut.println(a);
        }
        StdOut.println(" - - - - - - ");
        d.removeLast();
        d.removeFirst();
        for (String a : d) {
            StdOut.println(a);
        }
    }
}
