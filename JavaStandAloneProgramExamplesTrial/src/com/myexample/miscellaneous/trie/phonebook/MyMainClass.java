package com.myexample.miscellaneous.trie.phonebook;

import java.io.PrintStream;

public class MyMainClass {

	public static void main(String[] args) {
		PrintStream ps = System.out;
		ContactManager cm = ContactManager.getInstance();

		cm.addUpdateContactDetailsForName("jessica alba", ContactManager.MOBILE_NUMBER, "9528745675");
		cm.addUpdateContactDetailsForName("jessica alba", ContactManager.HOME_NUMBER, "+18734920398");
		cm.addUpdateContactDetailsForName("jessica alba", ContactManager.HOME_ADDRESS, "Somewhere in US, May be.");
		cm.addUpdateContactDetailsForName("jessica alba", ContactManager.NOTES, "oooooooOOO");
		cm.addUpdateContactDetailsForName("jessica silverstone", ContactManager.MOBILE_NUMBER, "9176345679");
		cm.addUpdateContactDetailsForName("love hewitt", ContactManager.MOBILE_NUMBER, "9176298672");
		
		ps.println("**** Contact for love hewitt:");
		cm.printContactDetailsBasedOnName("love hewitt", ps);
		
		ps.println("\n**** Contacts starting with jess:");
		cm.printContactDetailsforNameStartingWithPrefix("jess", ps);
		
		ps.println("\n**** Contacts starting with number 9176:");
		cm.printContactDetailsforNumberStartingWithPrefix("9176", ps);
	}

}
