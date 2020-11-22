package com.neustar.programming.contest.three;

/*
Simon likes to run, but very slowly and only in metric.
Every day he runs, he runs 1 mm more than the day he last ran, starting with 1 mm on the first day.

On every 7th day, he doesn't run at all because he rests. He is also superstitious so on any day that contains both the number 1 and the number 3, he doesn't run.

Given a distance of 110 km, 54 m, 3 cm and 2 mm, how many days will it take before Simon has run that far cumulatively?

Email simon.nicoud@neustar.biz with your answer as well as your code for both questions.
The first team to respond correctly wins!

 */
public class MyMainClass2 {

	public static void main(String[] args) {
		//getDays(110000000); // 100 km
		//getDays(54000); // 54 m
		//getDays(30); // 3cm
		//getDays(2); // 2mm
		getDays(110054032); // Total
	}
	
	private static void getDays(long d) {
		int seventhDay = 0;
		long totalDays = 0L;
		long lastDayRun = 0L; // Last Day Run.
		long totalDistanceConvered = 0L;
		while (totalDistanceConvered < d) {
			seventhDay++;
			totalDays++;
			long curDayRun = lastDayRun + 1;

			if (seventhDay == 7) {
				seventhDay = 0;
				continue;
			}
			if (totalDays != 1) {
				String totalDayInString = new String(totalDays + "");
				if ( totalDayInString.contains("1") 
						&& totalDayInString.contains("3")) {
					continue;
				}
			}
			lastDayRun = curDayRun;
			totalDistanceConvered += curDayRun;
		}
		
		System.out.println(totalDays);
	}
}
