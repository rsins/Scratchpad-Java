package com.myexample.sqrt;

public class MyMainClass {

	public static void main(String[] args) {
		System.out.println("Answer = " + sqrt(50d, 8));

	}

	/**
	 * Calculate square root of the given input.
	 * 
	 * @param d input
	 * @param p number of precision points after decimal
	 * @return result
	 */
	private static double sqrt(double d, int p) {
		if (d < 0) throw new IllegalArgumentException("Value = " + d + " is negative.");
		// Above precision = 8 due to rounding off errors it returns wrong value. 
		if (p < 0 || p > 8) throw new IllegalArgumentException("Preceision = " + p + "out of range, only allowed values are from 0 to 8.");
		double[] range = new double[] {0, d};
		double dp = Math.pow(10, (double) p);
		int count = 0;
		while (true) {
			count++;
			range = sqrtImpl(d, range);
			System.out.println(" " + count + ") Ranges -> " + range[0] + " ... " + range[1]);
			if (range[0] == range[1]) return range[0];
			if (((int)(range[1]*dp) - (int)(range[0]*dp)) == 0) return ((int)(range[0]*dp))/dp;
		}
	}
	
	private static double[] sqrtImpl(double d, double[] range) {
		double num = (range[0] + range[1]) / 2;
		double numSq =  num * num;
		if (numSq < d) return new double[] {num, range[1]};
		if (numSq > d) return new double[] {range[0], num};
		return new double[] {num, num};
	}
}
