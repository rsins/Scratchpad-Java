package com.myexample.miscellaneous.trie.dictionary;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map.Entry;


public class MyMainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CharTrie<String> myTrie = new CharTrie<String>();

		/*----------------------------------------------------------*/
		myTrie.add("apple".toCharArray(), "It is a fruit.");
		myTrie.add("carrot".toCharArray(), "It is a vegetable.");
		myTrie.add("ape".toCharArray(), "Human like.");
		prepareInputData(myTrie);
		
		/*----------------------------------------------------------*/
		System.out.println("------------------------------------------");
		for (Entry<String, String> myEntry : myTrie.getAllWordFromTrie().entrySet()) {
			System.out.println(myEntry.getKey() + " - " + myEntry.getValue());
		}
		
		/*----------------------------------------------------------*/

		System.out.println("------------------------------------------");
		System.out.println(myTrie.hasWord("app".toCharArray()));
		System.out.println(myTrie.getValue("carrot".toCharArray()));
		System.out.println(myTrie.getValue("bpcrajcnk".toCharArray()));

		/*----------------------------------------------------------*/
		System.out.println("------------------------------------------");
		for (Entry<String, String> myEntry : myTrie.getAllWordsWithPrefix("app".toCharArray()).entrySet()) {
			System.out.println(myEntry.getKey() + " - " + myEntry.getValue());
		}

		/*----------------------------------------------------------*/
		myTrie.delete("apple".toCharArray());

		System.out.println("------------------------------------------");
		for (Entry<String, String> myEntry : myTrie.getAllWordsWithPrefix("app".toCharArray()).entrySet()) {
			System.out.println(myEntry.getKey() + " - " + myEntry.getValue());
		}
	}

	private static void prepareInputData(CharTrie<String> aTrie) {
			BufferedReader myFileReader;
			String myLine = null;
			String[] myWordMeaning = null;
			
			try {
				myFileReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/JavaStandAloneProgramExamplesTrial/src/com/myexample/miscellaneous/trie/dictionary/Input.txt"));
				
				myLine = myFileReader.readLine();
				
				while (myLine != null) {
					myWordMeaning = myLine.split(";");
				
					aTrie.add(myWordMeaning[0].toCharArray(), myWordMeaning[1]);
					
					myLine = myFileReader.readLine();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
