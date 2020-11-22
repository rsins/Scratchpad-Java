package com.hackerrank.mrxandshots;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MyMainClass1 {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
	
		List<ShotRange> t = new ArrayList<ShotRange>();
		t.sort(new ShotRangeComparator());
		
		long s = 0L;
		
		int n = scn.nextInt();
		int m = scn.nextInt();
		
		for (int i = 0 ; i < n ; i++) {
			long one = scn.nextLong();
			long two = scn.nextLong();
			if (one > two) {
				long swap = one;
				one = two;
				two = swap;
			}
			t.add(new ShotRange(one, two));
		}
	
		for (int i = 0 ; i < m ; i++) {
			long c = scn.nextLong();
			long d = scn.nextLong();
			if (c > d) {
				long swap = c;
				c = d;
				d = swap;
			}
			
			for (ShotRange sr : t) {
				if (isOverLapping(sr.a, sr.b, c, d)) {
					s++;
				}
				//if (sr.b > d) break;
			}
		}
		
		System.out.println(s);
		scn.close();
	}

	private static boolean isOverLapping(long a, long b, long c, long d) {
		if ((c <= a && d >= a) ||
			(c <= b && d >= b) ||
			(c >= a && b >= d)
			){
			return true;
		}
		return false;
	}
	
	private static class ShotRange {
		public long a = 0;
		public long b = 0;
		public ShotRange(long a, long b) {
			this.a = a;
			this.b = b;
		}
		@Override
		public boolean equals(Object o) {
			if (o == null) return false;
			if (!(o instanceof ShotRange)) return false;
			ShotRange oo = (ShotRange) o;
			if (this.a == oo.a && this.b == oo.b) return true; 
			return false;
		}
	}
	
	private static class ShotRangeComparator implements Comparator<ShotRange> {

		@Override
		public int compare(ShotRange arg0, ShotRange arg1) {
			if (arg0.a == arg1.a && arg0.b == arg1.b) return 0;
			if (arg0.a >= arg1.b) return 1;
			if (arg0.b <= arg1.a) return -1;
			if (arg0.b <= arg1.b) return -1;
			if (arg0.a <= arg1.a) return -1;
			if (arg0.b >= arg1.b) return 1;
			if (arg0.a >= arg1.a) return 1;
			return 0;
		}
		
	}
}
