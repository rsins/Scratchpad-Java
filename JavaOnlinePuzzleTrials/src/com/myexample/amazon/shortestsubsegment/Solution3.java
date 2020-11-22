package com.myexample.amazon.shortestsubsegment;

import java.util.Scanner;

public class Solution3 {

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
       
        LeastSizeString myLeastSizeString = new LeastSizeString();
        myLeastSizeString.size = -1;
       
        nonRecursivePermuteFindShortestSubSegment(myUCaseParagraphStringBuilder, myWords, myLeastSizeString);
       
        if (myLeastSizeString.size == -1) {
            System.out.println("NO SUBSEGMENT FOUND");
        }
        else {
            System.out.println(myParagraphStringBuilder.substring(myLeastSizeString.startPos, myLeastSizeString.endPos).trim());
        }
        
        myScanner.close();
    }

    private static void innerFindShortestSubSegment(
            final StringBuilder aParagraphStringBuilder, final String aWords[],
            final int[] aPermutations,
            int wordStartInParagraph, int wordEndInParagraph,
            final int currentLevel, int largestLastPosition,
            LeastSizeString aLeastSizeString) {
       
        int myWordStartPosition = wordStartInParagraph;
        int myWordNextPosition = wordStartInParagraph;
        int myWordEndInParagraph = wordEndInParagraph;
        int myWordStartInParagraph = wordStartInParagraph;
        int myLargestLastPosition = largestLastPosition;

        boolean wordLeftSpace = false;
        boolean wordRightSpace = false;

        if (currentLevel == aPermutations.length) {
            if (aLeastSizeString.size == -1) {
                aLeastSizeString.startPos = myWordStartInParagraph;
                aLeastSizeString.endPos = largestLastPosition;
                aLeastSizeString.size = largestLastPosition - myWordStartInParagraph;
            } else {
                if (aLeastSizeString.size > (largestLastPosition - myWordStartInParagraph)) {
                    aLeastSizeString.startPos = myWordStartInParagraph;
                    aLeastSizeString.endPos = largestLastPosition;
                    aLeastSizeString.size = largestLastPosition - myWordStartInParagraph;
                }
            }

            return;
        }

        while (myWordStartPosition != -1) {
            myWordEndInParagraph = wordEndInParagraph;
            myLargestLastPosition = largestLastPosition;

            while (myWordStartPosition != -1) {
                myWordStartPosition = aParagraphStringBuilder.indexOf(
                        aWords[aPermutations[currentLevel]], myWordNextPosition);

                if (myWordStartPosition == -1) {
                    break;
                }

                wordLeftSpace = true;
                wordRightSpace = true;
                if (myWordStartPosition > myWordStartInParagraph) {
                    if (aParagraphStringBuilder
                            .charAt(myWordStartPosition - 1) != ' ') {
                        wordLeftSpace = false;
                    }
                }
                if ((myWordStartPosition + aWords[aPermutations[currentLevel]].length()) < myWordEndInParagraph) {
                    if (aParagraphStringBuilder.charAt(myWordStartPosition
                            + aWords[aPermutations[currentLevel]].length()) != ' ') {
                        wordRightSpace = false;
                    }
                } else {
                    return; // Word not found within the given boundary.
                }

                if (!wordLeftSpace || !wordRightSpace) {
                    myWordNextPosition = myWordStartPosition + 1;
                    continue;
                } else {
                    if (currentLevel == 0) {
                        myWordStartInParagraph = myWordStartPosition;
                    }

                    break;
                }
            }

            if (myWordStartPosition == -1) {
                return;
            }

            myWordNextPosition = aParagraphStringBuilder.indexOf(aWords[aPermutations[currentLevel]],
                    myWordStartPosition + 1);

            while (myWordNextPosition != -1) {
                myWordNextPosition = aParagraphStringBuilder.indexOf(
                        aWords[aPermutations[currentLevel]], myWordNextPosition);
                if (myWordNextPosition == -1) {
                    myWordNextPosition = aParagraphStringBuilder.length();
                }

                wordLeftSpace = true;
                wordRightSpace = true;
                if (myWordNextPosition > myWordStartInParagraph) {
                    if (aParagraphStringBuilder
                            .charAt(myWordNextPosition - 1) != ' ') {
                        wordLeftSpace = false;
                    }
                }
                if ((myWordNextPosition + aWords[aPermutations[currentLevel]].length()) < myWordEndInParagraph) {
                    if (aParagraphStringBuilder.charAt(myWordNextPosition
                            + aWords[aPermutations[currentLevel]].length()) != ' ') {
                        wordRightSpace = false;
                    }
                }

                if (wordLeftSpace && wordRightSpace) {
                    if (myWordNextPosition < myWordEndInParagraph) {
                        myWordEndInParagraph = myWordNextPosition;
                    }

                    break;
                } else {
                    myWordNextPosition++;

                    continue;
                }
            }

            if (myWordNextPosition == -1) {
                myWordNextPosition = aParagraphStringBuilder.length();
            }

            myLargestLastPosition = myWordStartPosition
                    + aWords[aPermutations[currentLevel]].length();
            if (myLargestLastPosition > myWordEndInParagraph) {
                myLargestLastPosition = myWordEndInParagraph;
            }
            if (myLargestLastPosition < largestLastPosition) {
                myLargestLastPosition = largestLastPosition;
            }

            innerFindShortestSubSegment(aParagraphStringBuilder, aWords,
                    aPermutations,
                    myWordStartInParagraph, myWordEndInParagraph,
                    currentLevel + 1, myLargestLastPosition,
                    aLeastSizeString);
        }

    }

    private static void nonRecursivePermuteFindShortestSubSegment(final StringBuilder aParagraphStringBuilder,
                                                                  final String[] aWords,
                                                                  final LeastSizeString aLeastSizeString) {
        int[] myIndexes = new int[aWords.length];

        for (int i = 0; i < myIndexes.length; i++) {
            myIndexes[i] = i;
        }

        innerFindShortestSubSegment(aParagraphStringBuilder, aWords, myIndexes, 0, aParagraphStringBuilder.length(), 0, 0, aLeastSizeString);

        if (aWords.length == 1) {
            return;
        }
       
        while(true) {
            int i1 = 0;
            int i2 = 0;

            for (i1 = myIndexes.length - 2, i2 = myIndexes.length - 1;
            (i1 > 0) && (myIndexes[i2] <= myIndexes[i1]);
            i1--, i2--) {
            }

            if (myIndexes[i2] <= myIndexes[i1]) {
                break;
            }
            else {
                for (i2 = myIndexes.length - 1;
                (i2 > i1) && (myIndexes[i2] <= myIndexes[i1]);
                i2--) {
                }

                swap(myIndexes, i1, i2);

                reverse(myIndexes, i1 + 1, myIndexes.length - 1);

                innerFindShortestSubSegment(aParagraphStringBuilder, aWords, myIndexes, 0, aParagraphStringBuilder.length(), 0, 0, aLeastSizeString);
            }
        }
       
        return;
    }
   
    private static void swap(int[] aArray, int aFirst, int aSecond) {
        int myTemp = aArray[aFirst];
        aArray[aFirst] = aArray[aSecond];
        aArray[aSecond] = myTemp;
    }

    private static void reverse(int[] aArray, int aFirst, int aLast) {
        int i;
        int j;
       
        for (i = aFirst, j = aLast; i < j; i++, j--) {
            swap(aArray, i, j);
        }
    }
   
    private static class LeastSizeString {
        public int startPos = 0;
        public int endPos = 0;
        public int size = -1;
    }
}