package com.coursera.week5;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

public class JavaRDDExample {

	public static void main(String[] args) {
		SparkConf conf = new SparkConf();
		conf.setAppName("MySparkTrialProgramUsingJavaRDD");
		conf.setMaster("local");
		JavaSparkContext sc = new JavaSparkContext(conf);
		//System.out.println(sc.getSparkHome());
		process(sc);
		sc.close();
	}
	
	public static void process(JavaSparkContext sc) {
		JavaRDD<String> showViewFile = sc.textFile(System.getProperty("user.dir") + "\\src\\main\\java\\com\\coursera\\week4\\join2_gennum?.txt");
		JavaPairRDD<String, String> showViews = showViewFile.mapToPair(new PairFunction<String, String, String>() {

			public Tuple2<String, String> call(String line) throws Exception {
				String[] words = line.split(",");
				return new Tuple2<String, String>(words[0], words[1]);
			}
		});
		//System.out.println(showViews.collect().toString());
		
		JavaRDD<String> showChannelFile = sc.textFile(System.getProperty("user.dir") + "\\src\\main\\java\\com\\coursera\\week4\\join2_genchan?.txt");
		JavaPairRDD<String, String> showChannels = showChannelFile.mapToPair(new PairFunction<String, String, String>() {
			public Tuple2<String, String> call(String line) throws Exception {
				String[] words = line.split(",");
				return new Tuple2<String, String>(words[0], words[1]);
			}
		});
		//System.out.println(showChannels.collect().toString());
		
		JavaPairRDD<String,Tuple2<String,String>> joinedDataSet = showChannels.join(showViews);
		JavaPairRDD<String,String> channelViews = joinedDataSet.mapToPair(new PairFunction<Tuple2<String,Tuple2<String,String>>, String, String>() {
			public Tuple2<String, String> call(Tuple2<String, Tuple2<String, String>> input) throws Exception {
				return input._2();
			}
		});
		//System.out.println(joinedDataSet.collect().toString());
		
		JavaPairRDD<String, String> channelViewCounts = channelViews.reduceByKey(new Function2<String, String, String>() {
			public String call(String paramT1, String paramT2) throws Exception {
				Integer sum = Integer.valueOf(paramT1) + Integer.valueOf(paramT2);
				//System.out.println(paramT1);
				//System.out.println(paramT2);
				//System.out.println(sum);
				return sum.toString();
			}
		});
		
		System.out.println(channelViewCounts.collect().toString());
	}
}
