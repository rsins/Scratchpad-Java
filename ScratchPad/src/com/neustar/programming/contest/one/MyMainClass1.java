package com.neustar.programming.contest.one;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class MyMainClass1 {

	public static void main(String[] args) throws IOException {
		final String inputFile = "D:\\Work\\Repositories\\programmingContest\\programmingContest1Input1.txt";
		//final String inputFile = "D:\\a.txt";
		
		Set<Position> positionsCovered = new TreeSet<Position> ();
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		
		// Starting position.
		long curHorz = 0;
		long curVert = 0;
		Position p = new Position(curVert, curHorz);
		positionsCovered.add(p);
		
		int ch = reader.read();
		while (ch != -1) {
			if (ch == '<') {
				curHorz -= 1;
			}
			else if (ch == 'v') {
				curVert += 1;
			}
			else if (ch == '>') {
				curHorz += 1;
			}
			else if (ch == '^') {
				curVert -= 1;
			}
			p = new Position(curVert, curHorz);
			positionsCovered.add(p);
			ch = reader.read();
		}
		
		reader.close();
		
		System.out.println("Number of unique positions covered = " + positionsCovered.size());
	}

	private static class Position implements Comparable<Position> {
		long horiz = 0;
		long vert = 0;
		
		public Position(long vert, long horz) {
			this.vert = vert; 
			this.horiz = horz;
		}
		
		// Not great but will do for now.
		public int hashCode() {
			return (int) (horiz + vert);
		}
		
		@SuppressWarnings("unused")
		public boolean equals(Position position) {
			if (position == null) return false;
			if (this == position) return true;
			if ((this.horiz == position.horiz) && (this.vert == position.vert)) { return true; }
			return false;
		}

		@Override
		public int compareTo(Position param) {
			return (this.horiz == param.horiz) ? ((int) (this.vert - param.vert)) : ((int) (this.horiz - param.horiz));
		}
	}
}
