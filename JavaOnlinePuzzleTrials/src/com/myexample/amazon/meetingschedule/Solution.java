package com.myexample.amazon.meetingschedule;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {

/*
 Given M busy-time slots of N people, You need to print all the available time slots when all the N people can schedule a meeting for a duration of K minutes.
Event time will be of form HH MM ( where 0 <= HH <= 23 and 0 <= MM <= 59 ), K will be in the form minutes.
 
Input Format:
 
M K [ M number of busy time slots , K is the duration in minutes ]
Followed by M lines with 4 numbers on each line.
Each line will be of form StartHH StartMM EndHH EndMM  [ Example 9Am-11Am time slot will be given as 9 00 11 00 ]
An event time slot is of form [Start Time, End Time ) . Which means it inclusive at start time but doesn’t include the end time.
So an event of form 10 00  11 00 => implies that the meeting start at 10:00 and ends at 11:00, so another meeting can start at 11:00.
 
Sample Input:
5 120
16 00 17 00
10 30 14 30
20 45 22 15
10 00 13 15
09 00 11 00
 
Sample Output:
00 00 09 00
17 00 20 45
 
Sample Input:
8 60
08 00 10 15
22 00 23 15
17 00 19 00
07 00 09 45
09 00 13 00
16 00 17 45
12 00 13 30
11 30 12 30
 
Sample Output:
00 00 07 00
13 30 16 00
19 00 22 00
 
Constraints :
1 <= M <= 100
 
Note: 24 00 has to be presented as 00 00.     */
   
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
       
        int myM = myScanner.nextInt();
        int myK = myScanner.nextInt();
       
        int myStartHH;
        int myStartMM;
        int myEndHH;
        int myEndMM;
       
        int[] myStartTimeInMinutes = new int[myM];
        int[] myEndTimeInMinutes = new int[myM];
       
        for (int i = 0; i < myM; i++) {
            // Meeting blocked times
            myStartHH = myScanner.nextInt();
            myStartMM = myScanner.nextInt();
            myEndHH = myScanner.nextInt();
            myEndMM = myScanner.nextInt();
           
            myStartTimeInMinutes[i] = myStartHH * 60 + myStartMM;
            myEndTimeInMinutes[i] = myEndHH * 60 + myEndMM;
           
            if ((myEndTimeInMinutes[i] == 0) && (myStartTimeInMinutes[i] > 0)) {
                myEndTimeInMinutes[i] = 24 * 60;
            }
        }

        getAvailableMeetingSlot(myStartTimeInMinutes, myEndTimeInMinutes, myK);
       
        myScanner.close();
    }

    private static void getAvailableMeetingSlot(int[] aStartTime, int[] aEndTime, int aK) {
        // 1440 minutes in 24 hours.
        int myMeetingSlots[] = new int[1440];
       
        // Calendar slots
        Arrays.fill(myMeetingSlots, 0);
       
        for (int i = 0; i < aStartTime.length; i++) {
            Arrays.fill(myMeetingSlots, aStartTime[i], aEndTime[i], 1);
        }
       
        int myStartIndex = 0;
        int myEndIndex = 0;
        boolean myCheckForStartFlag = true;
       
        for (int j = 0; j < myMeetingSlots.length; j++) {
            if (myCheckForStartFlag && (myMeetingSlots[j] == 0)) {
                myStartIndex = j;
                myCheckForStartFlag = false;
            }
           
            if (!myCheckForStartFlag) {
                if (myMeetingSlots[j] == 1) {
                    myEndIndex = j;
                    myCheckForStartFlag = true;
                   
                    if ((myEndIndex - myStartIndex) >= aK) {
                        System.out.printf("%02d %02d %02d %02d\n",myStartIndex/60, myStartIndex%60, myEndIndex/60, myEndIndex%60);
                    }
                }
               
                if ((j == (myMeetingSlots.length - 1)) && (myMeetingSlots[j] == 0)) {
                    myEndIndex = j + 1;
                    myCheckForStartFlag = true;
                   
                    if ((myEndIndex - myStartIndex) >= aK) {
                        System.out.printf("%02d %02d %02d %02d\n",myStartIndex/60, myStartIndex%60, ((myEndIndex/60) == 24)? 0:myEndIndex/60, myEndIndex%60);
                    }
                }
            }
        }
    }   
}
