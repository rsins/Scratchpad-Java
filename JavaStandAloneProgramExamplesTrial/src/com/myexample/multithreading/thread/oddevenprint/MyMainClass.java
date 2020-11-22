package com.myexample.multithreading.thread.oddevenprint;

import java.util.concurrent.Semaphore;


/*
 * You have two threads one printing even numbers in order and other odd numbers. 
 * Design an algorithm so that it prints numbers in natural order
 */
public class MyMainClass {

	public static void main(String[] args) {
		Semaphore o = new Semaphore(1);
		Semaphore e = new Semaphore(0);
		OddEvenNumbers odd = new OddEvenNumbers(OddEvenNumbers.ODDEVEN.ODD, 20, o, e);
		OddEvenNumbers even = new OddEvenNumbers(OddEvenNumbers.ODDEVEN.EVEN, 20, o, e);

		new Thread(odd).start();
		new Thread(even).start();
	}

}

class OddEvenNumbers implements Runnable {
	int count;
	ODDEVEN type;
	
	Semaphore o;
	Semaphore e;
	public enum ODDEVEN {
		ODD, EVEN;
	}
	
	public OddEvenNumbers(ODDEVEN aType, int aCount, Semaphore aO, Semaphore aE) {
		type = aType;
		count = aCount;
		o = aO;
		e = aE;
	}
	
	@Override
	public void run() {
		try {
			printNumbers();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void printNumbers() throws InterruptedException {
		switch (type) {
		case ODD:
			for (int i = 1; i <= count; i = i + 2) {
				o.acquire();
				System.out.print(i + " , ");
				e.release();
			}
			break;

		case EVEN:
			for (int i = 2; i <= count; i = i + 2) {
				e.acquire();
				System.out.print(i + " , ");
				o.release();
			}
			break;
		}
	}
	
}
