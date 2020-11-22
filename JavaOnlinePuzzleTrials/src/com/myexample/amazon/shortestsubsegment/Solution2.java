package com.myexample.amazon.shortestsubsegment;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution2 {

/*
 Given a paragraph of text, write a program to find the first shortest sub-segment that contains each of the given k words at least once. A segment is said to be shorter than other if it contains less number of words.

Ignore characters other than [a-z][A-Z] in the text. Comparison between the strings should be case-insensitive.

If no sub-segment is found then the program should output “NO SUBSEGMENT FOUND”.
 
Input format :
 
First line of the input contains the text.
Next line contains k , the number of  words given to be searched.
Each of the next k lines contains a word.
 
 
Output format :
 
Print first shortest sub-segment that contains given k words , ignore special characters, numbers.If no sub-segment is found it should return “NO SUBSEGMENT FOUND”
 
Sample Input :
 
This is a test. This is a programming test. This is a programming test in any language.
4
this
a
test
programming
 
Sample Output :
 
a programming test This
 
Explanation :
In this test case segment "a programming test. This" contains given four words. You have to print without special characters, numbers so output is "a programming test This".  Another segment "This is a programming test." also contains given  four words but have more number of words.
 

Constraint :

Total number of character in a paragraph will not be more than 200,000.
0 < k <= no. of words in paragraph.
0 < Each word length < 15
 */
   
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        StringBuilder myParagraphStringBuilder = new StringBuilder(myScanner.nextLine().replaceAll("[^a-z A-Z]", " ").replaceAll("  ", " "));
       
        int myK = Integer.valueOf(myScanner.nextLine());
       
       
        String myWords[] = new String[myK];
       
        for (int i = 0; i < myK; i++) {
            myWords[i] = myScanner.nextLine();
        }
       
        LeastSizeString myLeastSizeString = new LeastSizeString();
        myLeastSizeString.size = -1;
       
        findShortestSubSegment(myParagraphStringBuilder, myWords, myLeastSizeString);
       
        if (myLeastSizeString.size == -1) {
            System.out.println("NO SUBSEGMENT FOUND");
        }
        else {
            System.out.println(myParagraphStringBuilder.substring(myLeastSizeString.startPos, myLeastSizeString.endPos).trim());
        }
        
        myScanner.close();
    }

    private static void findShortestSubSegment(StringBuilder aParagraphStringBuilder, String aWords[],
                                               LeastSizeString aLeastSizeString) {
       
        StringBuilder myPatternStringBuilder = new StringBuilder();
        StringBuilder myWordsForPatternStringBuilder = new StringBuilder();
       
        for (int i = 0; i < aWords.length; i++) {
            if (i == 0) {
                if (aWords.length == 1) {
                    myWordsForPatternStringBuilder.append("(");
                    myWordsForPatternStringBuilder.append(aWords[i]);
                    myWordsForPatternStringBuilder.append(")");
                }
                else {
                    myWordsForPatternStringBuilder.append("(");
                    myWordsForPatternStringBuilder.append(aWords[i]);
                    myWordsForPatternStringBuilder.append("|");
                }
            }
            else if (i == (aWords.length - 1)) {
                myWordsForPatternStringBuilder.append(aWords[i]);
                myWordsForPatternStringBuilder.append(")");
            }
            else {
                myWordsForPatternStringBuilder.append(aWords[i]);
                myWordsForPatternStringBuilder.append("|");
            }
        }
       
        for (int i = 0; i < aWords.length; i++) {
            if (i == 0) {
                myPatternStringBuilder.append("\\b");
            }
            else {
                myPatternStringBuilder.append(".*?\\b");
            }

            for (int j = 1 ; j <= i; j++) {
                myPatternStringBuilder.append("(?!\\" + j + ")");
            }
           
            myPatternStringBuilder.append(myWordsForPatternStringBuilder.toString());
            myPatternStringBuilder.append("\\b");
        }
       
        String myMatchedSubString = "";
        int myMatchedStringFirstWordEnd = 0;
           
        Pattern myPattern = Pattern.compile(myPatternStringBuilder.toString(), Pattern.CASE_INSENSITIVE);
        Matcher myMatcher = myPattern.matcher(aParagraphStringBuilder.toString());
       
        while (myMatcher.find(myMatchedStringFirstWordEnd)) {
            myMatchedSubString = myMatcher.group();
            myMatchedStringFirstWordEnd = myMatchedSubString.indexOf(' ');
               
            if (myMatchedStringFirstWordEnd != -1) {
                myMatchedStringFirstWordEnd = myMatcher.start() + myMatchedStringFirstWordEnd;
            }
            else {
                myMatchedStringFirstWordEnd = aParagraphStringBuilder.length();
            }
           
            int myCurrentStringWordCount = myMatchedSubString.split(" ").length;
           
            if ((aLeastSizeString.size == -1) || (aLeastSizeString.size > myCurrentStringWordCount)) {
                aLeastSizeString.size = myCurrentStringWordCount;
                aLeastSizeString.startPos = myMatcher.start();
                aLeastSizeString.endPos = myMatcher.end();
            }
        }
    }
   
    private static class LeastSizeString {
        public int startPos = 0;
        public int endPos = 0;
        public int size = -1;
    }
}