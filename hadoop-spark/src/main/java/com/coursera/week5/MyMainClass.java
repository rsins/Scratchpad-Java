package com.coursera.week5;

import java.util.concurrent.TimeUnit;

import org.apache.hadoop.util.StopWatch;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

public class MyMainClass {

	public static void main(String[] args) {
		SparkConf conf = new SparkConf();
		conf.setAppName("MySparkTrialProgramUsingJavaDataSet");
		conf.setMaster("local");
		final JavaSparkContext sc = new JavaSparkContext(conf);
		
		StopWatch sw1 = new StopWatch();
		sw1.start();
		JavaRDDExample.process(sc);
		sw1.close();
		
		StopWatch sw2 = new StopWatch();
		sw2.start();
		JavaDatasetExample.process(sc);
		sw2.close();
		
		System.out.println("Using RDD     = " + timings(sw1));
		System.out.println("Using Dataset = " + timings(sw2));
		
		sc.close();
	}

	private static String timings(StopWatch sw) {
		long nanos = sw.now(TimeUnit.SECONDS);
		return nanos + " seconds";
	}
}
