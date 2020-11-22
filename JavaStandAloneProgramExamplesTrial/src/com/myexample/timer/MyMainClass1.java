package com.myexample.timer;

import java.util.Timer;
import java.util.TimerTask;

public class MyMainClass1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Timer myTimer = new Timer();
		
		myTimer.schedule(new SomeTask(), 0, 100);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		myTimer.cancel();
	}

	private static class SomeTask extends TimerTask {
		private int mCount = 0;
		
		@Override
		public void run() {
			System.out.println("Count: " + ++mCount);
			
		}
	}
}
