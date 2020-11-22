package com.myexample.thread;


public class ImageBlurProcessor implements Runnable {
	private Integer[] mArrayToBeProcessed;
	private int mCurrentStatus = 0;
	
	public ImageBlurProcessor(Integer[] aArrayToBeProcessd) {
		super();
		mArrayToBeProcessed = aArrayToBeProcessd;
	}

	public int getCurrentStatus() {
		return mCurrentStatus;
	}
	
	public Integer[] getArrayProcessed() {
		return mArrayToBeProcessed;
	}
	
	@Override
	public void run() {
		System.out.println("Thread " + Thread.currentThread().toString() + " started.");
		mCurrentStatus = 0;
		
		for (int i = 0; i < mArrayToBeProcessed.length; i++) {
			mArrayToBeProcessed[i] = (int) (mArrayToBeProcessed[i].intValue() * 0.5);

			try {
				Thread.sleep((long) (Math.random() * 100));
			} catch (InterruptedException myInterruptedException) {
				myInterruptedException.printStackTrace();
			} finally {
				mCurrentStatus = 100*(i/mArrayToBeProcessed.length);
			}
		}
		
		mCurrentStatus = 100;
		System.out.println("Thread " + Thread.currentThread().toString() + " stopped.");
	}
}
