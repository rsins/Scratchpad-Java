package com.coursera.week5;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

public class JavaDatasetExample {

	public static void main(String[] args) {
		SparkConf conf = new SparkConf();
		conf.setAppName("MySparkTrialProgramUsingJavaDataSet");
		conf.setMaster("local");
		JavaSparkContext sc = new JavaSparkContext(conf);
		process(sc);
		sc.close();
	}
	
	public static void process(JavaSparkContext sc) {
		SQLContext sqlContext = SQLContext.getOrCreate(sc.sc());
		
		JavaRDD<String> showViewFile = sc.textFile(System.getProperty("user.dir") + "\\src\\main\\java\\com\\coursera\\week4\\join2_gennum?.txt");
		JavaRDD<Row> showViews = showViewFile.map(new Function<String, Row>() {
			public Row call(String line) throws Exception {
				String[] words = line.split(",");
				return RowFactory.create(words[0], Integer.valueOf(words[1]));
			}
		});
		StructType showViewschema = DataTypes.createStructType(
				new StructField[] {
						DataTypes.createStructField("ShowName", DataTypes.StringType, false),
						DataTypes.createStructField("ViewCount", DataTypes.IntegerType, false)
				}
		);
		Dataset<Row> shows = sqlContext.createDataFrame(showViews.rdd(), showViewschema);
		//shows.show();
		
		JavaRDD<String> showChannelFile = sc.textFile(System.getProperty("user.dir") + "\\src\\main\\java\\com\\coursera\\week4\\join2_genchan?.txt");
		JavaRDD<Row> showChannels = showChannelFile.map(new Function<String, Row>() {
			public Row call(String line) throws Exception {
				String[] words = line.split(",");
				return RowFactory.create(words[0], words[1]);
			}
		});
		StructType showChannelSchema = DataTypes.createStructType(
				new StructField[] {
						DataTypes.createStructField("ShowName", DataTypes.StringType, false),
						DataTypes.createStructField("ChannelName", DataTypes.StringType, false)
				}
		);
		Dataset<Row> channels = sqlContext.createDataFrame(showChannels.rdd(), showChannelSchema);
		//channels.show();
		
		Dataset<Row> joinedDataset = shows.join(channels, "ShowName");
		//joinedDataset.show();
		Dataset<Row> channelViewCounts = joinedDataset.groupBy("ChannelName").sum("ViewCount");
		channelViewCounts.show();
	}
}
