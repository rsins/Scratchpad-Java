package com.exam.morganstanley.movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;


/*
 * Two integer arrays. One is movie start time array and second is movie end time array. 
 * Find out the maximum no of movies which can be seen for the given timings.

4
10 12
16 18
12 14
13 15

 */
public class Solution {

	public static void main(String[] args)
	{
		Scanner myScanner = new Scanner(System.in);
		
		Map<Integer,Integer> myMovieTimings = new TreeMap<Integer,Integer>();
		
		int n = myScanner.nextInt(); // Number of entries.
		for (int i = 0; i < n; i++) {
			int s = myScanner.nextInt();
			int e = myScanner.nextInt();
			myMovieTimings.put(Integer.valueOf(s), Integer.valueOf(e));
		}
		
		List<int[]> myMT = getMaxNumberOfMovies(myMovieTimings);
		
		System.out.println("Length: " + myMT.size());
		for(int[] mt : myMT) {
			System.out.println(mt[0] + " - " + mt[1]);
		}
		
		myScanner.close();
	}
		
	private static List<int[]> getMaxNumberOfMovies(Map<Integer,Integer> aMovieTimings) {
		List<int[]> myMT = new ArrayList<int[]> ();
		List<int[]> myCurrMT = new ArrayList<int[]> ();
		
		int[] myMovieStart = new int[aMovieTimings.size()];
		int[] myMovieEnd = new int[aMovieTimings.size()];
		
		int i = 0;
		for (Entry<Integer, Integer> mt : aMovieTimings.entrySet()) {
			myMovieStart[i] = mt.getKey();
			myMovieEnd[i] = mt.getValue();
			i++;
		}
		
		getMaxNoMoviedImpl(myMovieStart, myMovieEnd, myCurrMT, myMT, 0);
		
		return myMT;
	}
	
	private static void getMaxNoMoviedImpl(int[] aMovieStart, int[] aMovieEnd, List<int[]> aCurrMT, List<int[]> aResultMT, int aCurrLevel) {
		if (aCurrLevel >= aMovieStart.length) {
			if (aCurrLevel > aResultMT.size()) {
				aResultMT.clear();
				aResultMT.addAll(aCurrMT);
			}
			
			return;
		}
		
		if (aCurrLevel == 0) {
			aCurrMT.add(new int [] {aMovieStart[aCurrLevel], aMovieEnd[aCurrLevel]});
		}
		else if (aMovieEnd[aCurrLevel - 1] <= aMovieStart[aCurrLevel]) {
				aCurrMT.add(new int [] {aMovieStart[aCurrLevel], aMovieEnd[aCurrLevel]});
		}
		
		getMaxNoMoviedImpl(aMovieStart, aMovieEnd, aCurrMT, aResultMT, aCurrLevel + 1);
	}
}
