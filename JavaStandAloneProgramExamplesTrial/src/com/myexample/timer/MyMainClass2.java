package com.myexample.timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class MyMainClass2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Timer myTimer = new Timer(100, new SomeActionEvenListener());
		
		myTimer.setInitialDelay(0);
		myTimer.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		myTimer.stop();
	}

	private static class SomeActionEvenListener implements ActionListener {
		private int mCount= 0;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Count: " + ++mCount);
		}
	}
}
