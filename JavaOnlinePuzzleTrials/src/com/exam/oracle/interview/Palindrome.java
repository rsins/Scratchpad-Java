package com.exam.oracle.interview;

public class Palindrome {

	/**
	 * Checks whether passed String is a palindrome or not.
	 * Palindrome: A word, phrase or sentence that reads the same backward
	 * or forward.
	 * The following string is a palindrome: "Madam, I'm Adam."
	 * Important Note: Limit the amount of additional memory to O(1).
	 *
	 * @returns true if passed string is palindrome, false - otherwise
	 */

	 public static boolean isPalindrome(String str) {
		 char[] strChars = str.toCharArray();
		 int start = 0;
		 int end = strChars.length - 1;
		 boolean hasAlpha = false; // To check if string has at least one alphabet.
		 while (start < end) {
			 char ch1 = getLowerFromUpperAlphabet(strChars[start]);
			 char ch2 = getLowerFromUpperAlphabet(strChars[end]);
			 
			 if (ch1 != 0 || ch2 != 0) hasAlpha = true;
			 
			 if (ch1 == 0 && ch2 != 0) start++;
			 else if (ch1 != 0 && ch2 == 0) end--;
			 else if (ch1 == 0 && ch2 == 0) {
				 start++;
				 end--;
			 }
			 else if (ch1 != ch2) {
				 System.out.println(start + " " + end + " " + ch1 + " " + ch2);
				 return false;
			 }
			 else {
				 start++;
				 end--;
			 }
		 }
		 
		 return hasAlpha ? true : false;
	 }
	 
	 // If not alphabet then returns 0 else returns the lower case value of input character.
	 private static char getLowerFromUpperAlphabet(char ch) {
		 if (ch >= 'a' && ch <= 'z') return ch;
		 if (ch < 'A' || ch > 'Z') return 0;
		 int offset = ((int) ch) - ((int) 'A');
		 char newChar = (char) (((int) 'a') + offset);
		 return newChar;
	 }
	 
	 // To run unit tests.
	 public static void main(String[] args) {
		 System.out.println("Is Palindrome check:");
		 
		 testPalindrome("Madam, I'm Adam.");
		 testPalindrome("");
		 testPalindrome(" ");
		 testPalindrome("racecar");
		 testPalindrome("race-car");
		 testPalindrome("I did, did I?");
		 testPalindrome("sator arepo tenet opera rotas");
		 testPalindrome("a   a .?");
		 testPalindrome("Al lets Della call Ed \"Stella\".");
	 }
	 
	// To run unit tests.
	 private static void testPalindrome(String str) {
		 System.out.println(isPalindrome(str) + " : '" + str + "'");
	 }
}
