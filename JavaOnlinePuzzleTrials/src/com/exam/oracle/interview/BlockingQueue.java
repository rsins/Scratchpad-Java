package com.exam.oracle.interview;

/**
 * Provides waiting thread-safe access to a java.util.Queue.
 */
public class BlockingQueue<E> {
	
	java.util.Queue<E> queue;
	
	Object queueLock = new Object();
	
    /**
     * @param queue Warning: queue may or may not be empty at the moment of calling this constructor.
     * Warning: queue may or may not be a thread-safe implementation.
     * Assumption: after been passed into this constructor, queue object is not used by any code outside this class.
     */
    public BlockingQueue(java.util.Queue<E> queue) {
    	if (queue == null) throw new IllegalArgumentException("Constructor argument cannot be null.");
    	this.queue = queue;
    }

    /**
     * Inserts the specified element into the underlying queue, waiting if necessary for the underlying queue to be ready
     * to accept new elements.
     */
    public void push(E e) {
    	synchronized (queueLock) {
			queue.add(e);
			queueLock.notifyAll();
		}
    }

    /**
     * Retrieves and removes the head of the underlying queue, waiting if necessary until it is capable of providing an element.
     */
    public E pull() {
    	try {
			synchronized (queueLock) {
				while (queue.size() == 0) {
					queueLock.wait();
				}
				return queue.remove();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
    }
}
