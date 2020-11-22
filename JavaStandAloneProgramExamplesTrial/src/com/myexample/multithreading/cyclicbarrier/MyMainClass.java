package com.myexample.multithreading.cyclicbarrier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MyMainClass {
	private static final int MAX_ITEMS_TO_PROCESS = 1000;
	private static final int NO_OF_PROCESSORS = 10;
	
	public static void main(String[] args) {
		MergeDataProcessor mdp = new MergeDataProcessor();
		CyclicBarrier cb = new CyclicBarrier(NO_OF_PROCESSORS, mdp);

		int[] myArr = new int[MAX_ITEMS_TO_PROCESS];
		for (int i=0; i<myArr.length; i++) {
			myArr[i] = (int) (10000 * Math.random());
		}
		
		List<PartDataProcessor> lpb = new ArrayList<PartDataProcessor>();
		for (int i=0,end=-1; i < NO_OF_PROCESSORS; i++) {
			PartDataProcessor dp = new PartDataProcessor(returnPartOfArray(myArr, 
					 													   end + 1, 
					 													   -1+(MAX_ITEMS_TO_PROCESS*(i+1))/NO_OF_PROCESSORS), 
													     cb);
			end = -1+(MAX_ITEMS_TO_PROCESS*(i+1))/NO_OF_PROCESSORS;
			lpb.add(dp);
		}
		
		mdp.setRunnables(lpb);
		mdp.startProcessing();
		
		while (!mdp.isProcessComplete()) {
			
		}
		
		printArr(myArr, "Before processing the array:");
		printArr(mdp.getData(), "After processing the array:");
	}

	/** 
	 * Includes both start and end in the returning array.
	 * 
	 * @param aArr
	 * @param start
	 * @param end
	 * @return
	 */
	private static int[] returnPartOfArray(int[] aArr, int start, int end) {
		int[] myArr = new int[end - start + 1];
		System.arraycopy(aArr, start, myArr, 0, end - start + 1);
		return myArr;
	}
	
	private static void printArr(int[] aArr, String msg) {
		System.out.println(msg);
		System.out.print('{');
		for (int i=0; i<aArr.length; i++) {
			System.out.print(aArr[i]);
			if ((aArr.length - i) != 1)	{
				System.out.print(',');
				System.out.print('\t');
			}
		}
		System.out.println('}');
	}
}

class PartDataProcessor implements Runnable {

	private static final int MAX_SLEEP_TIME = 1000;
	
	private int[] mArr;
	private float mFact = 0.5f;
	private CyclicBarrier mCb;
	
	public PartDataProcessor(int[] anArr, CyclicBarrier aCb) {
		mCb = aCb;
		mArr = new int[anArr.length];
		System.arraycopy(anArr, 0, mArr, 0, anArr.length);
	}
	
	public PartDataProcessor(float aFact, int[] anArr, CyclicBarrier aCb) {
		this(anArr, aCb);
		mFact = aFact;
	}
	
	@Override
	public void run() {
		System.out.println("Started ** " + Thread.currentThread().getName());
		
		try {
			Thread.sleep((long) (MAX_SLEEP_TIME * Math.random()));
		} catch (InterruptedException e) {
		}
		
		for (int i=0; i< mArr.length; i++) {
			mArr[i] *= mFact;
		}
		
		System.out.println("Completed ** " + Thread.currentThread().getName());
		
		try {
			mCb.await();
		} catch (InterruptedException e) {
		}
		catch (BrokenBarrierException e) {
			
		}
	}
	
	public int[] getData() {
		return mArr;
	}
}

class MergeDataProcessor implements Runnable {
	int[] mArr = null;
	List<PartDataProcessor> mRn;
	boolean processComplete = false;
	
	public MergeDataProcessor() {
		
	}
	
	public MergeDataProcessor(List<PartDataProcessor> aRn) {
		mRn = aRn;
	}
	
	@Override
	public void run() {
		if (mRn == null) return;
		int length = 0;
		
		for (PartDataProcessor rn : mRn) {
			length += rn.getData().length;
		}
		
		int curPos = 0;
		mArr = new int[length];
		for (PartDataProcessor rn : mRn) {
			System.arraycopy(rn.getData(), 0, mArr, curPos, rn.getData().length);
			curPos += rn.getData().length;
		}
		
		processComplete = true;
	}
	
	public boolean isProcessComplete() {
		return processComplete;
	}
	
	public int[] getData() {
		return (mArr == null) ? new int[0] : mArr;
	}
	
	public void startProcessing() {
		if (mRn == null) return;
		for (PartDataProcessor rn : mRn) {
			Thread st = new Thread(rn);
			st.start();
		}
	}
	
	public void setRunnables(List<PartDataProcessor> aRn) {
		mRn = aRn;
	}
	
	public void reset() {
		processComplete = false;
		mArr = null;
	}
}
