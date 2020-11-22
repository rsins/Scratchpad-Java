package com.my.example.seamcarver

import java.awt.Color;
import java.lang.Double;
import org.apache.spark.sql.SparkSession;
// import scala.util.Try

import edu.princeton.cs.algs4._
import scala.util.Random

object Main extends App {
  
  def generatePicture(colors: Array[Array[Color]]): Picture = {
    var height = colors(0).length;
    var width = colors.length;
    var outputPic: Picture = new Picture(width, height);
    for (i <- 0 until width) for (j <- 0 until height) outputPic.set(i, j, colors(i)(j));
    return outputPic;
  }
  
  //var picture = new Picture("D:\\Ravi\\My_Programs\\Eclipse_Workspace\\Coursera Project_Algorithms Part 2\\resources\\week2\\seamCarving\\HJocean.png")
  var picture = new Picture("/Users/raviksingh/ravi/programs/Workspace_Eclipse/Coursera Project_Algorithms Part 2/resources/week2/seamCarving/HJocean.png")
  var seamCarver = new SeamCarver(picture.width, picture.height, Array.tabulate[Color](picture.width, picture.height) { (i, j) => picture.get(i, j)})
  generatePicture(seamCarver.colors).show
  
  var ver = 100;
  var hor = 100;
  
  // Without using Spark Session.
  for (i <- 1 to ver) {
    println("Generating vertical seam - " + i);
    var seam = seamCarver.findVerticalSeam1();
    println("Generating vertical seam done - " + i);
    seamCarver.removeVerticalSeam(seam)
    println("Removed vertical seam - " + i); 
  }
  for (i <- 1 to hor) {
    println("Generating horizontal seam - " + i);
    var seam = seamCarver.findHorizontalSeam1();
    println("Generating horizontal seam done - " + i);
    seamCarver.removeHorizontalSeam(seam)
    println("Removed horizontal seam - " + i); 
  }
  generatePicture(seamCarver.colors()).show
  
  
  
  // Using Spark Session.
  println("Creating spark session.");
  val spark = SparkSession.builder()
  .master("local")
  .appName("* Picture Seam Carving")
  .getOrCreate();
  
  var parts = 30;
  //picture = new Picture("D:\\Ravi\\My_Programs\\Eclipse_Workspace\\Coursera Project_Algorithms Part 2\\resources\\week2\\seamCarving\\HJocean.png")
  picture = new Picture("/Users/raviksingh/ravi/programs/Workspace_Eclipse/Coursera Project_Algorithms Part 2/resources/week2/seamCarving/HJocean.png")
  seamCarver = new SeamCarver(picture.width, picture.height, Array.tabulate[Color](picture.width, picture.height) { (i, j) => picture.get(i, j)})
  for (i <- 1 to ver) {
    var times = 50;
    var r = new Random();
    times = if (times > seamCarver.width) seamCarver.width else times;
    println("* Generating vertical seam - " + i);
    var dt = spark.sparkContext.parallelize(Array.fill[Int](times)(r.nextInt(seamCarver.width)), parts).map { curI => seamCarver.findVerticalSeamSparkImpl(curI) }
    var resultSeam = dt.reduce((s1: (Double, Array[Int]), s2: (Double, Array[Int])) => if (s1._1 < s2._1) s1 else s2)
    var seam = resultSeam._2;
    println("* Generating vertical seam done - " + i);
    seamCarver.removeVerticalSeam(seam)
    println("* Removed vertical seam - " + i);
  }
  for (i <- 1 to hor) {
    var times = 50;
    var r = new Random();
    times = if (times > seamCarver.height) seamCarver.height else times;
    println("* Generating horizontal seam - " + i);
    var dt = spark.sparkContext.parallelize(Array.fill[Int](times)(r.nextInt(seamCarver.height)), parts).map { curI => seamCarver.findHorizontalSeamSparkImpl(curI) }
    var resultSeam = dt.reduce((s1: (Double, Array[Int]), s2: (Double, Array[Int])) => if (s1._1 < s2._1) s1 else s2)
    var seam = resultSeam._2;
    println("* Generating horizontal seam done - " + i);
    seamCarver.removeHorizontalSeam(seam)
    println("* Removed horizontal seam - " + i);
  }
  generatePicture(seamCarver.colors()).show
  
  
}
