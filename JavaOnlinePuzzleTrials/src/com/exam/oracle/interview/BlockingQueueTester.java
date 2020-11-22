package com.exam.oracle.interview;

import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Queue;

public class BlockingQueueTester {

	public static void main(String[] args) {
		test1();
		test2();
		test3();
		System.out.println(" -- Done --");
	}

	public static void test1() {
		System.out.println("* Testing in sequence add and remove.");
		Queue<Integer> queue = new ArrayDeque<>();
		BlockingQueue<Integer> blockingQueue = new BlockingQueue<Integer>(queue);
		blockingQueue.push(1);
		blockingQueue.push(2);
		blockingQueue.push(3);
		blockingQueue.push(4);
		System.out.println("Removing - " + blockingQueue.pull());
		System.out.println("Removing - " + blockingQueue.pull());
		System.out.println("Removing - " + blockingQueue.pull());
		System.out.println("Removing - " + blockingQueue.pull());
	}
	
	public static void test2() {
		System.out.println("* Testing one adder thread and multiple remover thread");
		Queue<Integer> queue = new ArrayDeque<>();
		BlockingQueue<Integer> blockingQueue = new BlockingQueue<Integer>(queue);
		new Thread(new QueueRemoveTask<Integer>(blockingQueue, "Test1-QueueRemover1")).start();
		new Thread(new QueueRemoveTask<Integer>(blockingQueue, "Test1-QueueRemover2")).start();
		new Thread(new QueueRemoveTask<Integer>(blockingQueue, "Test1-QueueRemover3")).start();
		new Thread(new QueueAddTask<Integer>(blockingQueue, new Integer[] {1, 2, 3, 4, 5, 6, 7, 8}, "Test1-QueueAdder")).start();
		new Thread(new QueueRemoveTask<Integer>(blockingQueue, "Test1-QueueRemover4")).start();
		new Thread(new QueueRemoveTask<Integer>(blockingQueue, "Test1-QueueRemover5")).start();
		sleep(10000);
	}
	
	public static void test3() {
		System.out.println("* Testing multiple adder thread and multiple remover thread");
		Queue<Integer> queue = new ArrayDeque<>();
		BlockingQueue<Integer> blockingQueue = new BlockingQueue<Integer>(queue);
		new Thread(new QueueRemoveTask<Integer>(blockingQueue, "Test2-QueueRemover1")).start();
		new Thread(new QueueAddTask<Integer>(blockingQueue, new Integer[] {1, 2, 3, 4, 5}, "Test2-QueueAdder1")).start();
		new Thread(new QueueRemoveTask<Integer>(blockingQueue, "Test2-QueueRemover2")).start();
		new Thread(new QueueRemoveTask<Integer>(blockingQueue, "Test2-QueueRemover3")).start();
		new Thread(new QueueAddTask<Integer>(blockingQueue, new Integer[] {10, 20, 30, 40, 50}, "Test2-QueueAdder2")).start();
		new Thread(new QueueRemoveTask<Integer>(blockingQueue, "Test2-QueueRemover4")).start();
		new Thread(new QueueRemoveTask<Integer>(blockingQueue, "Test2-QueueRemover5")).start();
		sleep(6000);
	}
	
	private static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static class QueueAddTask<E> implements Runnable {
		BlockingQueue<E> queue;
		E[] values;
		String name;
		
		public QueueAddTask(BlockingQueue<E> queue, E[] values, String name) {
			this.queue = queue;
			this.values = values;
			this.name = name;
		}
		
		@Override
		public void run() {
			for (E e : values) {
				System.out.println(new SimpleDateFormat().format(new Date()) + " " + name + " adding " + e.toString());
				queue.push(e);
				sleep(1000);
			}
			
		}
	}
	
	private static class QueueRemoveTask<E> implements Runnable {
		BlockingQueue<E> queue;
		String name;
		
		public QueueRemoveTask(BlockingQueue<E> queue, String name) {
			this.queue = queue;
			this.name = name;
		}
		
		@Override
		public void run() {
			E e = queue.pull();
			System.out.println(new SimpleDateFormat().format(new Date()) + " " + name + " removing " + e.toString());
		}
		
	}
}
