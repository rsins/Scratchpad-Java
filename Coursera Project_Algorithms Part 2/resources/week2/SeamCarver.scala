package com.my.example

import java.awt.Color;
import java.lang.Double;
import org.apache.spark.sql.SparkSession;
import scala.util.Random

// import scala.util.Try


//class SeamCarver(spark: SparkSession, inputPic: Picture) extends java.io.Serializable {
class SeamCarver(var width: Int, var height: Int, private var _colors: Array[Array[Color]]) extends java.io.Serializable {
   
  // var height: Int = 0;
  // var width: Int = 0;
  // private var colors: Array[Array[Color]] = null;
  private var energies: Array[Array[Double]] = Array.tabulate[Double](width, height)( (i, j) => energyImpl(i, j) );

  def colors(): Array[Array[Color]] = {
    var colors = Array.tabulate[Color](width, height)( (i, j) => _colors(i)(j));
    return colors;
  }
  
  /*
  def this(inputPic: Picture) = {
    this();
    if (inputPic == null) throw new NullPointerException();
    this.height = inputPic.height();
    this.width = inputPic.width();
    this.colors = Array.tabulate[Color](width, height) { (i, j) => inputPic.get(i, j)};
    this.energies = Array.tabulate[Double](width, height) { (i, j) => energyImpl(i, j)};;
  }
  
  def picture: Picture = {
    var outputPic: Picture = new Picture(width, height);
    for (i <- 0 until width) for (j <- 0 until height) outputPic.set(i, j, colors(i)(j));
    return outputPic;
  }
  */
  
  def energy(x : Int, y : Int) : Double = {
    if (x < 0 || x >= width || y < 0 || y >= height) throw new IndexOutOfBoundsException();
    return energies(x)(y);
  }
  
  private def energyImpl(x : Int, y : Int) : Double = {
    if (x < 0 || x >= width || y < 0 || y >= height) {
      var result = Double.valueOf(2000); // To handle corner/border cases.
      return result;
    }
    else if (x == 0 || x == (width - 1) || y == 0 || y == (height - 1)) {
      var result = Double.valueOf(1000);
      return result;
    }
    
    var cx1: Color = _colors(x + 1)(y);
    var cx2: Color = _colors(x - 1)(y);
        
    var cy1: Color = _colors(x)(y + 1);
    var cy2: Color = _colors(x)(y - 1);
        
    var cxr12: Int = cx1.getRed() - cx2.getRed();
    var cxg12: Int = cx1.getGreen() - cx2.getGreen();
    var cxb12: Int = cx1.getBlue() - cx2.getBlue();
        
    var cyr12: Int = cy1.getRed() - cy2.getRed();
    var cyg12: Int = cy1.getGreen() - cy2.getGreen();
    var cyb12: Int = cy1.getBlue() - cy2.getBlue();
        
    var energy: Double = cxr12 * cxr12 + cxg12 * cxg12 + cxb12 * cxb12 +
                        cyr12 * cyr12 + cyg12 * cyg12 + cyb12 * cyb12;
    energy = Math.sqrt(energy);
    return energy;
  }
  
  def removeHorizontalSeam(seam: Array[Int]) : Unit = {
    if (seam == null) throw new NullPointerException();
    if (seam.length != width) throw new IllegalArgumentException();
    if (height <= 1) throw new IllegalArgumentException();
    
    for (i <- 0 until seam.length) {
      var j: Int = seam(i);
      if (j < 0 || j >= height) throw new IllegalArgumentException();
      if (i > 0) {
        var prevI = i - 1;
        var prevJ = seam(prevI);
        if (Math.abs(prevJ - j) > 1) throw new IllegalArgumentException();                
      }
            
      for (indexJ <- j until (height - 1)) {
        _colors(i)(indexJ) = _colors(i)(indexJ + 1);
        energies(i)(indexJ) = energies(i)(indexJ + 1);
      }
    }
    
    this.height -= 1;
        
    // recalculate the energies
    for (i <- 0 until width) {
        var j = seam(i);
        if (j >= 0 && j < height) energies(i)(j) = energyImpl(i, j);
        if (i > 0) energies(i - 1)(j) = energyImpl(i - 1, j);
        if (j > 0) energies(i)(j - 1) = energyImpl(i, j - 1);
        if (i < (width - 1)) energies(i + 1)(j) = energyImpl(i + 1, j);
        if (j < (height - 1)) energies(i)(j + 1) = energyImpl(i, j + 1);
    }  
  }
  
  def removeVerticalSeam(seam: Array[Int]) : Unit = {
    if (seam == null) throw new NullPointerException();
    if (seam.length != height) throw new IllegalArgumentException();
    if (width <= 1) throw new IllegalArgumentException();
    
    // remove seams
    for (j <- 0 until seam.length) {
        var i = seam(j);
        if (i < 0 || i >= width) throw new IllegalArgumentException();
        if (j > 0) {
            var prevJ = j - 1;
            var prevI = seam(prevJ);
            if (Math.abs(prevI - i) > 1) throw new IllegalArgumentException();                
        }
            
        for (indexI <- i until (width - 1)) {
            _colors(indexI)(j) = _colors(indexI + 1)(j);
            energies(indexI)(j) = energies(indexI + 1)(j);
        }
    }
        
    this.width -= 1;
        
    // recalculate the energies
    for (j <- 0 until height) {
        var i = seam(j);
        if (i >= 0 && i < width) energies(i)(j) = energyImpl(i, j);
        if (i > 0) energies(i - 1)(j) = energyImpl(i - 1, j);
        if (j > 0) energies(i)(j - 1) = energyImpl(i, j - 1);
        if (i < (width - 1)) energies(i + 1)(j) = energyImpl(i + 1, j);
        if (j < (height - 1)) energies(i)(j + 1) = energyImpl(i, j + 1);
    }
  }
  
  /*
  def findHorizontalSeamSpark(): Array[Int] = {               // sequence of indices for horizontal seam
    var dt = spark.sparkContext.parallelize((0 until height), 10).map { curJ =>
      findHorizontalSeamSparkImpl(curJ)
    }
    var resultSeam = dt.reduce((s1: (Double, Array[Int]), s2: (Double, Array[Int])) => if (s1._1 < s2._1) s1 else s2)
    return resultSeam._2;
  }
  */
  
  // Spark Map
  def findHorizontalSeamSparkImpl(curJ: Int): (Double, Array[Int]) = {               // sequence of indices for horizontal seam
    var resultSeam: Array[Int] = Array.fill[Int](width)(0);
    var cachedEnergySums: Array[Array[Double]] = Array.tabulate[Double](width, height) { (i, j) => Double.valueOf(0); };
    var bestSeams: Array[Array[Array[Int]]] = Array.tabulate[Array[Int]](width, height) { (i, j) => null; };
    findHorizontalSeam1Impl(0, curJ, Array.fill[Int](width)(0), resultSeam, cachedEnergySums, bestSeams);
    var cachedSeamSum = cachedEnergySums(0)(curJ);
    return (cachedSeamSum, resultSeam);
  }
  
  def findHorizontalSeam(): Array[Int] = {               // sequence of indices for horizontal seam
    var resultSeam: Array[Int] = Array.fill[Int](width)(0);
    var cachedEnergySums: Array[Array[Double]] = Array.tabulate[Double](width, height) { (i, j) => Double.valueOf(0); };
    var bestSeams: Array[Array[Array[Int]]] = Array.tabulate[Array[Int]](width, height) { (i, j) => null; };
    for (j <- 0 until height) {
        // findHorizontalSeamImpl(0, j, new int[width], resultSeam);
        findHorizontalSeamImpl(0, j, Array.fill[Int](width)(0), resultSeam, cachedEnergySums, bestSeams);
    }
    return resultSeam;
  }
  
  private def findHorizontalSeamImpl(curI: Int, curJ: Int, curSeam: Array[Int], resultSeam: Array[Int], cachedEnergySums: Array[Array[Double]], bestSeams: Array[Array[Array[Int]]]): Unit = {
    if (curJ < 0 || curJ >= height || curI < 0) return;
    if (curI >= width) {
        var resultSeamSum: Double = 0L;
        var curSeamSum: Double = 0L;
        for (idx <- (width - 1) to 0 by -1) {
          resultSeamSum += energies(idx)(resultSeam(idx));
          curSeamSum += energies(idx)(curSeam(idx));
            
          // Caching
          if (bestSeams(idx)(curSeam(idx)) == null) {
            var cachedSeam = Array.fill[Int](width - idx)(0);
            System.arraycopy(curSeam, idx, cachedSeam, 0, cachedSeam.length);
            bestSeams(idx)(curSeam(idx)) = cachedSeam;
            cachedEnergySums(idx)(curSeam(idx)) = curSeamSum;
          }
          else {
            var cachedSeamSum = cachedEnergySums(idx)(curSeam(idx));
            if (curSeamSum < cachedSeamSum) {
              var cachedSeam = Array.fill[Int](width - idx)(0);
              System.arraycopy(curSeam, idx, cachedSeam, 0, cachedSeam.length);
              bestSeams(idx)(curSeam(idx)) = cachedSeam;
              cachedEnergySums(idx)(curSeam(idx)) = curSeamSum;
            }
          }
        }
        if (curSeamSum < resultSeamSum) {
          System.arraycopy(curSeam, 0, resultSeam, 0, width);
        }
        return;
    }
    
    curSeam(curI) = curJ;
    if (bestSeams(curI)(curJ) != null) {
        var bestSeamAtCurPos: Array[Int] = bestSeams(curI)(curJ);
        System.arraycopy(bestSeamAtCurPos, 0, curSeam, curI, bestSeamAtCurPos.length);
        findHorizontalSeamImpl(width, curSeam(curSeam.length - 1), curSeam, resultSeam, cachedEnergySums, bestSeams);
    }
    else {
        findHorizontalSeamImpl(curI + 1, curJ - 1, curSeam, resultSeam, cachedEnergySums, bestSeams);
        findHorizontalSeamImpl(curI + 1, curJ, curSeam, resultSeam, cachedEnergySums, bestSeams);
        findHorizontalSeamImpl(curI + 1, curJ + 1, curSeam, resultSeam, cachedEnergySums, bestSeams);
    }
  }
  
  
  def findHorizontalSeam1(): Array[Int] = {               // sequence of indices for horizontal seam
    var resultSeam: Array[Int] = Array.fill[Int](width)(0);
    var cachedEnergySums: Array[Array[Double]] = Array.tabulate[Double](width, height) { (i, j) => Double.valueOf(0); };
    var bestSeams: Array[Array[Array[Int]]] = Array.tabulate[Array[Int]](width, height) { (i, j) => null; };
    var r = new Random();
    var times = 50;
    times = if (times > height) height else times;
    for (j <- 0 until times) {
        var curJ = r.nextInt(height);
        findHorizontalSeam1Impl(0, curJ, Array.fill[Int](width)(0), resultSeam, cachedEnergySums, bestSeams);
    }
    return resultSeam;
  }
  
  private def findHorizontalSeam1Impl(curI: Int, curJ: Int, curSeam: Array[Int], resultSeam: Array[Int], cachedEnergySums: Array[Array[Double]], bestSeams: Array[Array[Array[Int]]]): Unit = {
    if (curJ < 0 || curJ >= height || curI < 0) return;
    if (curI >= width) {
        var resultSeamSum: Double = 0L;
        var curSeamSum: Double = 0L;
        for (idx <- (width - 1) to 0 by -1) {
          resultSeamSum += energies(idx)(resultSeam(idx));
          curSeamSum += energies(idx)(curSeam(idx));
            
          // Caching
          if (bestSeams(idx)(curSeam(idx)) == null) {
            var cachedSeam = Array.fill[Int](width - idx)(0);
            System.arraycopy(curSeam, idx, cachedSeam, 0, cachedSeam.length);
            bestSeams(idx)(curSeam(idx)) = cachedSeam;
            cachedEnergySums(idx)(curSeam(idx)) = curSeamSum;
          }
          else {
            var cachedSeamSum = cachedEnergySums(idx)(curSeam(idx));
            if (curSeamSum < cachedSeamSum) {
              var cachedSeam = Array.fill[Int](width - idx)(0);
              System.arraycopy(curSeam, idx, cachedSeam, 0, cachedSeam.length);
              bestSeams(idx)(curSeam(idx)) = cachedSeam;
              cachedEnergySums(idx)(curSeam(idx)) = curSeamSum;
            }
          }
        }
        if (curSeamSum < resultSeamSum) {
          System.arraycopy(curSeam, 0, resultSeam, 0, width);
        }
        return;
    }
    
    curSeam(curI) = curJ;
    if (bestSeams(curI)(curJ) != null) {
        var bestSeamAtCurPos: Array[Int] = bestSeams(curI)(curJ);
        System.arraycopy(bestSeamAtCurPos, 0, curSeam, curI, bestSeamAtCurPos.length);
        findHorizontalSeamImpl(width, curSeam(curSeam.length - 1), curSeam, resultSeam, cachedEnergySums, bestSeams);
    }
    else {
        var r = new Random();
        r.nextInt(3) match {
          case 0 => findHorizontalSeam1Impl(curI + 1, curJ - 1, curSeam, resultSeam, cachedEnergySums, bestSeams);
          case 1 => findHorizontalSeam1Impl(curI + 1, curJ, curSeam, resultSeam, cachedEnergySums, bestSeams);
          case 2 => findHorizontalSeam1Impl(curI + 1, curJ + 1, curSeam, resultSeam, cachedEnergySums, bestSeams);
        }
    }
  }
  
  /*
  def findVerticalSeamSpark(): Array[Int] = {               // sequence of indices for horizontal seam
    var dt = spark.sparkContext.parallelize((0 until width), 10).map { curI =>
      findVerticalSeamSparkImpl(curI)
    }
    var resultSeam = dt.reduce((s1: (Double, Array[Int]), s2: (Double, Array[Int])) => if (s1._1 < s2._1) s1 else s2)
    return resultSeam._2;
  }
  */
  
  // Spark Map
  def findVerticalSeamSparkImpl(curI: Int): (Double, Array[Int]) = {               // sequence of indices for horizontal seam
    var resultSeam: Array[Int] = Array.fill[Int](height)(0);
    var cachedEnergySums: Array[Array[Double]] = Array.tabulate[Double](width, height) { (i, j) => Double.valueOf(0); };
    var bestSeams: Array[Array[Array[Int]]] = Array.tabulate[Array[Int]](width, height) { (i, j) => null; };
    findVerticalSeam1Impl(curI, 0, Array.fill[Int](height)(0), resultSeam, cachedEnergySums, bestSeams);
    var cachedSeamSum = cachedEnergySums(curI)(0);
    return (cachedSeamSum, resultSeam);
  }
  
  
  def findVerticalSeam(): Array[Int] = {                 // sequence of indices for vertical seam
    var resultSeam: Array[Int] = Array.fill[Int](height)(0);
    var cachedEnergySums: Array[Array[Double]] = Array.tabulate[Double](width, height) { (i, j) => Double.valueOf(0); };
    var bestSeams: Array[Array[Array[Int]]] = Array.tabulate[Array[Int]](width, height) { (i, j) => null; };
    for (i <- 0 until width) {
        // findHorizontalSeamImpl(0, j, new int[width], resultSeam);
        findVerticalSeamImpl(i, 0, Array.fill[Int](height)(0), resultSeam, cachedEnergySums, bestSeams);
    }
    return resultSeam;
  }
 
  
  // Using caching of already calculated best path so far.
  private def findVerticalSeamImpl(curI: Int, curJ: Int, curSeam: Array[Int], resultSeam: Array[Int], cachedEnergySums: Array[Array[Double]], bestSeams: Array[Array[Array[Int]]]): Unit = {
    if (curI < 0 || curI >= width || curJ < 0) return;
    if (curJ >= height) {
        var resultSeamSum: Double = 0L;
        var curSeamSum: Double = 0L;
        for (idx <- (height - 1) to 0 by -1) {
            resultSeamSum += energies(resultSeam(idx))(idx);
            curSeamSum += energies(curSeam(idx))(idx);
            
            // Caching
            if (bestSeams(curSeam(idx))(idx) == null) {
                var cachedSeam = Array.fill[Int](height - idx)(0);
                System.arraycopy(curSeam, idx, cachedSeam, 0, cachedSeam.length);
                bestSeams(curSeam(idx))(idx) = cachedSeam;
                cachedEnergySums(curSeam(idx))(idx) = curSeamSum;
            }
            else {
                var cachedSeamSum = cachedEnergySums(curSeam(idx))(idx);
                if (curSeamSum < cachedSeamSum) {
                    var cachedSeam = Array.fill[Int](height - idx)(0);
                    System.arraycopy(curSeam, idx, cachedSeam, 0, cachedSeam.length);
                    bestSeams(curSeam(idx))(idx) = cachedSeam;
                    cachedEnergySums(curSeam(idx))(idx) = curSeamSum;
                }
            }
        }
        if (curSeamSum < resultSeamSum) {
            System.arraycopy(curSeam, 0, resultSeam, 0, height);
        }
        return;
    }
    
    curSeam(curJ) = curI;
    if (bestSeams(curI)(curJ) != null) {
        var bestSeamAtCurPos: Array[Int] = bestSeams(curI)(curJ);
        System.arraycopy(bestSeamAtCurPos, 0, curSeam, curJ, bestSeamAtCurPos.length);
        findVerticalSeamImpl(curSeam(curSeam.length - 1), height, curSeam, resultSeam, cachedEnergySums, bestSeams);
    }
    else {
        findVerticalSeamImpl(curI - 1, curJ + 1, curSeam, resultSeam, cachedEnergySums, bestSeams);
        findVerticalSeamImpl(curI, curJ + 1, curSeam, resultSeam, cachedEnergySums, bestSeams);
        findVerticalSeamImpl(curI + 1, curJ + 1, curSeam, resultSeam, cachedEnergySums, bestSeams);
    }
  }
  
  
  def findVerticalSeam1(): Array[Int] = {                 // sequence of indices for vertical seam
    var resultSeam: Array[Int] = Array.fill[Int](height)(0);
    var cachedEnergySums: Array[Array[Double]] = Array.tabulate[Double](width, height) { (i, j) => Double.valueOf(0); };
    var bestSeams: Array[Array[Array[Int]]] = Array.tabulate[Array[Int]](width, height) { (i, j) => null; };
    var r = new Random();
    var times = 50;
    times = if (times > width) width else times;
    for (i <- 0 until times) {
        var curI = r.nextInt(height);
        // findHorizontalSeamImpl(0, j, new int[width], resultSeam);
        findVerticalSeam1Impl(curI, 0, Array.fill[Int](height)(0), resultSeam, cachedEnergySums, bestSeams);
    }
    return resultSeam;
  }
 
  
  // Using caching of already calculated best path so far.
  private def findVerticalSeam1Impl(curI: Int, curJ: Int, curSeam: Array[Int], resultSeam: Array[Int], cachedEnergySums: Array[Array[Double]], bestSeams: Array[Array[Array[Int]]]): Unit = {
    if (curI < 0 || curI >= width || curJ < 0) return;
    if (curJ >= height) {
        var resultSeamSum: Double = 0L;
        var curSeamSum: Double = 0L;
        for (idx <- (height - 1) to 0 by -1) {
            resultSeamSum += energies(resultSeam(idx))(idx);
            curSeamSum += energies(curSeam(idx))(idx);
            
            // Caching
            if (bestSeams(curSeam(idx))(idx) == null) {
                var cachedSeam = Array.fill[Int](height - idx)(0);
                System.arraycopy(curSeam, idx, cachedSeam, 0, cachedSeam.length);
                bestSeams(curSeam(idx))(idx) = cachedSeam;
                cachedEnergySums(curSeam(idx))(idx) = curSeamSum;
            }
            else {
                var cachedSeamSum = cachedEnergySums(curSeam(idx))(idx);
                if (curSeamSum < cachedSeamSum) {
                    var cachedSeam = Array.fill[Int](height - idx)(0);
                    System.arraycopy(curSeam, idx, cachedSeam, 0, cachedSeam.length);
                    bestSeams(curSeam(idx))(idx) = cachedSeam;
                    cachedEnergySums(curSeam(idx))(idx) = curSeamSum;
                }
            }
        }
        if (curSeamSum < resultSeamSum) {
            System.arraycopy(curSeam, 0, resultSeam, 0, height);
        }
        return;
    }
    
    curSeam(curJ) = curI;
    if (bestSeams(curI)(curJ) != null) {
        var bestSeamAtCurPos: Array[Int] = bestSeams(curI)(curJ);
        System.arraycopy(bestSeamAtCurPos, 0, curSeam, curJ, bestSeamAtCurPos.length);
        findVerticalSeamImpl(curSeam(curSeam.length - 1), height, curSeam, resultSeam, cachedEnergySums, bestSeams);
    }
    else {
      var r = new Random();
        r.nextInt(3) match {
          case 0 => findVerticalSeam1Impl(curI - 1, curJ + 1, curSeam, resultSeam, cachedEnergySums, bestSeams);
          case 1 => findVerticalSeam1Impl(curI, curJ + 1, curSeam, resultSeam, cachedEnergySums, bestSeams);
          case 2 => findVerticalSeam1Impl(curI + 1, curJ + 1, curSeam, resultSeam, cachedEnergySums, bestSeams);
        }
    }
  }
}
