package com.myexample.amazon.shortestsubsegment;

import java.util.Arrays;
import java.util.Scanner;

public class Solution1 {

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
        StringBuilder myUCaseParagraphStringBuilder = new StringBuilder(myParagraphStringBuilder.toString().toUpperCase());
       
        int myK = Integer.valueOf(myScanner.nextLine());
       
       
        String myWords[] = new String[myK];
       
        for (int i = 0; i < myK; i++) {
            myWords[i] = myScanner.nextLine().toUpperCase();
        }
       
       
        boolean myWordsUsed[] = new boolean[myK];
        Arrays.fill(myWordsUsed, false);
       
        LeastSizeString myLeastSizeString = new LeastSizeString();
        myLeastSizeString.size = -1;
       
        findShortestSubSegment(myUCaseParagraphStringBuilder, myWords, 0, myUCaseParagraphStringBuilder.length(), myWordsUsed, 0, 0, myLeastSizeString);
       
        if (myLeastSizeString.size == -1) {
            System.out.println("NO SUBSEGMENT FOUND");
        }
        else {
            System.out.println(myParagraphStringBuilder.substring(myLeastSizeString.startPos, myLeastSizeString.endPos).trim());
        }
        
        myScanner.close();
    }

    private static void findShortestSubSegment(StringBuilder aParagraphStringBuilder, String aWords[],
                                               int wordStartInParagraph, int wordEndInParagraph, boolean aWordsUsed[], int currentLevel,
                                               int largestLastPosition, LeastSizeString aLeastSizeString) {
        int myWordStartPosition = wordStartInParagraph;
        int myWordNextPosition = wordStartInParagraph;
        int myWordEndInParagraph = wordEndInParagraph;
        int myWordStartInParagraph = wordStartInParagraph;
        int myLargestLastPosition = largestLastPosition;
       
        boolean wordLeftSpace = false;
        boolean wordRightSpace = false;
       
        if (currentLevel == aWordsUsed.length) {
            if (aLeastSizeString.size == -1) {
                aLeastSizeString.startPos = myWordStartInParagraph;
                aLeastSizeString.endPos = largestLastPosition;
                aLeastSizeString.size = largestLastPosition - myWordStartInParagraph;
            }
            else {
                if (aLeastSizeString.size > (largestLastPosition - myWordStartInParagraph)) {
                    aLeastSizeString.startPos = myWordStartInParagraph;
                    aLeastSizeString.endPos = largestLastPosition;
                    aLeastSizeString.size = largestLastPosition - myWordStartInParagraph;
                }
            }
           
            return;
        }
       
        for (int i = 0; i < aWords.length; i++) {
            if (aWordsUsed[i]) {
                continue;
            }
           
            if (currentLevel == 0) {
                myWordStartPosition = wordStartInParagraph;
                myWordNextPosition = wordStartInParagraph;
                myWordEndInParagraph = wordEndInParagraph;
                myWordStartInParagraph = wordStartInParagraph;
                myLargestLastPosition = largestLastPosition;
            }
           
            while (myWordStartPosition != -1) {
                myWordEndInParagraph = wordEndInParagraph;
                myLargestLastPosition = largestLastPosition;
               
                while (myWordStartPosition != -1) {
                    myWordStartPosition = aParagraphStringBuilder.indexOf(aWords[i], myWordNextPosition);
                   
                    if (myWordStartPosition == -1) {
                        break;
                    }
                   
                    wordLeftSpace = true;
                    wordRightSpace = true;
                    if (myWordStartPosition > myWordStartInParagraph) {
                        if (aParagraphStringBuilder.charAt(myWordStartPosition - 1) != ' ') {
                            wordLeftSpace = false;
                        }
                    }
                    if ((myWordStartPosition + aWords[i].length()) < myWordEndInParagraph) {
                        if (aParagraphStringBuilder.charAt(myWordStartPosition + aWords[i].length()) != ' ') {
                            wordRightSpace = false;
                        }
                    }
                    else {
                        return; // Word not found within the given boundary.
                    }
                   
                    if (!wordLeftSpace || !wordRightSpace) {
                        myWordNextPosition = myWordStartPosition + 1;
                        continue;
                    }
                    else {
                        if (currentLevel == 0) {
                            myWordStartInParagraph = myWordStartPosition;
                        }
                       
                        break;
                    }
                }
               
                if (myWordStartPosition == -1) {
                    break;
                }
               
                myWordNextPosition = aParagraphStringBuilder.indexOf(aWords[i], myWordStartPosition + 1);
               
                while (myWordNextPosition != -1) {
                    myWordNextPosition = aParagraphStringBuilder.indexOf(aWords[i], myWordNextPosition);
                    if (myWordNextPosition == -1) {
                        myWordNextPosition = aParagraphStringBuilder.length();
                    }
                   
                    wordLeftSpace = true;
                    wordRightSpace = true;
                    if (myWordNextPosition > myWordStartInParagraph) {
                        if (aParagraphStringBuilder.charAt(myWordNextPosition - 1) != ' ') {
                            wordLeftSpace = false;
                        }
                    }
                    if ((myWordNextPosition + aWords[i].length()) < myWordEndInParagraph) {
                        if (aParagraphStringBuilder.charAt(myWordNextPosition + aWords[i].length()) != ' ') {
                            wordRightSpace = false;
                        }
                    }
                   
                    if (wordLeftSpace && wordRightSpace ) {
                        if (myWordNextPosition < myWordEndInParagraph) {
                            myWordEndInParagraph = myWordNextPosition;
                        }
                       
                        break;
                    }
                    else {
                        myWordNextPosition++;
                       
                        continue;
                    }
                }
               
                if (myWordNextPosition == -1) {
                    myWordNextPosition = aParagraphStringBuilder.length();
                }
               
                myLargestLastPosition = myWordStartPosition + aWords[i].length();
                if (myLargestLastPosition > myWordEndInParagraph) {
                    myLargestLastPosition = myWordEndInParagraph;
                }
                if (myLargestLastPosition < largestLastPosition) {
                    myLargestLastPosition = largestLastPosition;
                }
               
                aWordsUsed[i] = true;
                findShortestSubSegment(aParagraphStringBuilder, aWords, myWordStartInParagraph, myWordEndInParagraph, aWordsUsed, currentLevel + 1, myLargestLastPosition, aLeastSizeString);
                aWordsUsed[i] = false;
            }
        }
    }
   
    private static class LeastSizeString {
        public int startPos = 0;
        public int endPos = 0;
        public int size = -1;
    }
}
