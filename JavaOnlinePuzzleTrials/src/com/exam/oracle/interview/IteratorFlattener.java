package com.exam.oracle.interview;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorFlattener<E> implements Iterator<E> {
	private Iterator<Iterator<E>> iterators;
	private Iterator<E> curIterator;
	
    public IteratorFlattener(Iterator<Iterator<E>> root) {
       this.iterators = root;
    }

	@Override
	public boolean hasNext() {
		if (iterators == null) return false;
		
		// Get an iterator which is not null and has next element.
		while (curIterator == null || (curIterator != null && !curIterator.hasNext())) {
			// Get the next iterator instance.
			if (iterators.hasNext()) curIterator = iterators.next();
			else break;
		}
		
		if (curIterator != null && curIterator.hasNext()) return true;
		return false;
	}

	@Override
	public E next() {
		if (!hasNext()) throw new NoSuchElementException();
		return curIterator.next();
	}

	@Override
	public void remove() {
		if (iterators == null || curIterator == null) 
			throw new IllegalStateException();
		curIterator.remove();
	}

}

