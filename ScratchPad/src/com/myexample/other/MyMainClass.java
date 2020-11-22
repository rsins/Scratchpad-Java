package com.myexample.other;

import java.io.PrintStream;
import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;


public class MyMainClass {
	private static PrintStream ps = System.out;
	
	public static void main(String[] args) {
		ManagementFactory.getThreadMXBean().findDeadlockedThreads();
		//Proxy.newProxyInstance(null, null, null);
		
		// ---- String repeatation -----
		try1();
		// ---- Callable and Runnable ----
		try2();
		// ---- Binary Operators ----
		try3();
		// ---- Bitwise Operators ----
		try4();
		// ---- char manipulation ----
		try5();
		// ----- Randomizing array ----
		try6();
	}
	
	private static void try1() {
		ps.println("---- String repeatation -----");
		int CONST = 5;
		for (int i = 0; i < CONST; i++) {
			ps.println(new String(new char[i]).replace("\0", " ") + new String(new char[CONST-i]).replace("\0", "* ") + new String(new char[i]).replace("\0", " "));
		}
		for (int i = (CONST-2); i >= 0; i--) {
			ps.println(new String(new char[i]).replace("\0", " ") + new String(new char[CONST-i]).replace("\0", "* ") + new String(new char[i]).replace("\0", " "));
		}
		
	}

	private static void try2() {
		ps.println("---- Callable and Runnable ----");
		Callable<String> c = new Callable<String>() {

			@Override
			public String call() throws Exception {
				return "An executed callable";
			}
			
		};
		ThreadPoolExecutor tpe = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
		Future<String> fe = tpe.submit(c);
		try {
			ps.println(fe.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		tpe.shutdown();
		
	}
	
	private static void try3() {
		ps.println("---- Binary Operators ----");
		int a = 0;
		int b = 0;
		int f = 0;
		a = 1; b = 0; f = 0;
		ps.println("" + a + " " + b + " " + f + " = " + ((a & b) | (b & f) | (a & f)) + " - " + (a ^ b ^ f));
		a = 1; b = 0; f = 1;
		ps.println("" + a + " " + b + " " + f + " = " + ((a & b) | (b & f) | (a & f)) + " - " + (a ^ b ^ f));
		a = 0; b = 1; f = 0;
		ps.println("" + a + " " + b + " " + f + " = " + ((a & b) | (b & f) | (a & f)) + " - " + (a ^ b ^ f));
		a = 0; b = 1; f = 1;
		ps.println("" + a + " " + b + " " + f + " = " + ((a & b) | (b & f) | (a & f)) + " - " + (a ^ b ^ f));
		a = 0; b = 0; f = 1;
		ps.println("" + a + " " + b + " " + f + " = " + ((a & b) | (b & f) | (a & f)) + " - " + (a ^ b ^ f));
		a = 0; b = 0; f = 0;
		ps.println("" + a + " " + b + " " + f + " = " + ((a & b) | (b & f) | (a & f)) + " - " + (a ^ b ^ f));
		a = 1; b = 1; f = 0;
		ps.println("" + a + " " + b + " " + f + " = " + ((a & b) | (b & f) | (a & f)) + " - " + (a ^ b ^ f));
		a = 1; b = 1; f = 1;
		ps.println("" + a + " " + b + " " + f + " = " + ((a & b) | (b & f) | (a & f)) + " - " + (a ^ b ^ f));
	}
	
	private static void try4() {
		ps.println("---- Bitwise Operators ----");
		int i = 4;
		int I = i << 1;
		System.out.println("i     \t" + i + "\t" + Integer.toBinaryString(i) + "\t" + Integer.toUnsignedString(i, 2));
		System.out.println("i <<  \t" + I + "\t" + Integer.toBinaryString(I) + "\t" + Integer.toUnsignedString(I, 2));
		I = i >> 1;
		System.out.println("i     \t" + i + "\t" + Integer.toBinaryString(i) + "\t" + Integer.toUnsignedString(i, 2));
		System.out.println("i >>  \t" + I + "\t" + Integer.toBinaryString(I) + "\t" + Integer.toUnsignedString(I, 2));
		I = i >>> 1;
		System.out.println("i     \t" + i + "\t" + Integer.toBinaryString(i) + "\t" + Integer.toUnsignedString(i, 2));
		System.out.println("i >>> \t" + I + "\t" + Integer.toBinaryString(I) + "\t" + Integer.toUnsignedString(I, 2));
		
		i = -4;
		I = i << 1;
		System.out.println("i     \t" + i + "\t" + Integer.toBinaryString(i) + "\t" + Integer.toUnsignedString(i, 2));
		System.out.println("i <<  \t" + I + "\t" + Integer.toBinaryString(I) + "\t" + Integer.toUnsignedString(I, 2));
		I = i >> 1;
		System.out.println("i     \t" + i + "\t" + Integer.toBinaryString(i) + "\t" + Integer.toUnsignedString(i, 2));
		System.out.println("i >>  \t" + I + "\t" + Integer.toBinaryString(I) + "\t" + Integer.toUnsignedString(I, 2));
		I = i >>> 1;
		System.out.println("i     \t" + i + "\t" + Integer.toBinaryString(i) + "\t" + Integer.toUnsignedString(i, 2));
		System.out.println("i >>> \t" + I + "\t" + Integer.toBinaryString(I) + "\t" + Integer.toUnsignedString(I, 2));
	}
	
	private static void try5() {
		ps.println("---- char manipulation ----");
		char ch = 'M';
		ps.println(ch + " - " + getLowerFromUpperAlphabet1(ch) + " " + getLowerFromUpperAlphabet2(ch));
		
		ch = 'Z';
		ps.println(ch + " - " + getLowerFromUpperAlphabet1(ch) + " " + getLowerFromUpperAlphabet2(ch));
	}
	
	private static char getLowerFromUpperAlphabet1(char ch) {
		 if (ch >= 'a' && ch <= 'z') return ch;
		 if (ch < 'A' || ch > 'Z') return 0;
		 int offset = ((int) ch) - ((int) 'A');
		 char newChar = (char) (((int) 'a') + offset);
		 return newChar;
	 }
	
	private static char getLowerFromUpperAlphabet2(char ch) {
		 if (ch >= 'a' && ch <= 'z') return ch;
		 if (ch < 'A' || ch > 'Z') return 0;
		 return (char) ((int) ch + (int) 'a' - (int) 'A');
	 }
	
	private static void try6() {
		ps.println("---- Randomizing array -----");
		int[] arr = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
		for (int e : arr) System.out.print(e + "\t");
		System.out.println();
		for (int e : randomize(arr)) System.out.print(e + "\t");
		System.out.println();
		for (int e : randomize(arr)) System.out.print(e + "\t");
		System.out.println();
		for (int e : randomize(arr)) System.out.print(e + "\t");
		System.out.println();
		for (int e : randomize(arr)) System.out.print(e + "\t");
		System.out.println();
	}
	
	private static int[] randomize(int[] arrIn) {
		int arr[] = Arrays.copyOf(arrIn, arrIn.length);
		Random r = new Random();
		for (int i = 1; i < arr.length; i++) {
			int idx = r.nextInt(i);
			int tmp = arr[i];
			arr[i] = arr[idx];
			arr[idx] = tmp;
		}
		return arr;
	}
	
}
