package com.neustar.programming.contest.three;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

/*
 Alice and Bob are playing a variation of the card game War called Canadian Peacekeepers.

In Canadian Peacekeepers, the two players are given two equal-sized decks of 3,329 playing cards.

Canadian Peacekeepers is played in several rounds. Each round starts with the two players playing the card at the top of their decks (First character in the attached file).

The player who plays the highest value card of the two (Ace is lowest, King is highest) wins the hand, and the two cards played are added to the bottom of the winner’s deck in the order high card, low card.

If the two cards are the same value, then the two cards are removed from the game, and nobody wins the hand. The game is over when one player (the loser) runs out of cards.

Card   A 2 3 4 5 6 7 8 9 T  J  Q  K
Value  1 2 3 4 5 6 7 8 9 10 11 12 13

Given the two decks in the files programmingContest3Alice.txt and programmingContest2Bob.txt, you have to determine 3 things:

a) Who wins the game? "Bob" or "Alice"?
b) What is the size of the winner’s deck at the end of the game?
c) What is the very last pair of cards played in the game (order from highest to lowest)?

Append the three answers together and then append to http://www.duocin.com/programmingContest to get the url for the next question.

So for example, if Bob won the game and he had 500 cards in his deck at the end of the game and the very last matchup of cards was a '2' from Alice and a 'K' from Bob, then the url would be:

http://www.duocin.com/programmingContestBob500K2
 */
public class MyMainClass1 {

	public static void main(String[] args) throws IOException {
		Map<String, Integer> mapping = new HashMap<String, Integer> ();
		mapping.put("A",1);
		mapping.put("2",2);
		mapping.put("3",3);
		mapping.put("4",4);
		mapping.put("5",5);
		mapping.put("6",6);
		mapping.put("7",7);
		mapping.put("8",8);
		mapping.put("9",9);
		mapping.put("T",10);
		mapping.put("J",11);
		mapping.put("Q",12);
		mapping.put("K",13);


		BufferedReader file1 = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\com\\neustar\\programming\\contest\\three\\Alice.txt"));
		BufferedReader file2 = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\com\\neustar\\programming\\contest\\three\\Bob.txt"));
		
		ArrayDeque<String> first = new ArrayDeque<String>();
		ArrayDeque<String> second = new ArrayDeque<String>();
		
		//int one = 0;
		//int two = 0;
		
		// Get the intial deck.
		for (int i = 0; i < 3328; i++) {
			String read1 = new String(new char[] {(char) file1.read()});
			String read2 = new String(new char[] {(char) file2.read()});
			int winner = checkWinner(mapping.get(read1), mapping.get(read2));
			if (winner == 1) {
				first.addLast(read1);
				first.addLast(read2);
			}
			else if (winner == 2) {
				second.addLast(read2);
				second.addLast(read1);
			}
		}

		String last1 = null;
		String last2 = null;
		while ((first.size() > 0) && (second.size() > 0)) {
			String read1 = first.pop();
			String read2 = second.pop();
			int winner = checkWinner(mapping.get(read1), mapping.get(read2));
			if (winner == 1) {
				first.addLast(read1);
				first.addLast(read2);
				last1 = read1;
				last2 = read2;
			}
			else if (winner == 2) {
				second.addLast(read2);
				second.addLast(read1);
				last1 = read2;
				last2 = read1;
			}
		}
		
		String winnerName = null;
		int winnerDeckSize = 0;
		if (first.size() == 0) {
			winnerName = "Bob";
			winnerDeckSize = second.size();
		}
		if (second.size() == 0) {
			winnerName = "Alice";
			winnerDeckSize = first.size();
		}
		
		System.out.println(winnerName + winnerDeckSize + last1 + last2);
		file1.close();
		file2.close();
	}
	
	private static int checkWinner(int one, int two) {
		if (one > two) return 1;
		if (one < two) return 2;
		if (one == two) return 0;
		return 0;
	}

}
