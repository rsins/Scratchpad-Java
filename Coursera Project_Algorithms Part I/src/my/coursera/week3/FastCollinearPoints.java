package my.coursera.week3;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;


public class FastCollinearPoints {
    
    private Point[] allPoints;
    private Point[][] linePoints = new Point[1][2];
    private LineSegment[] lineSegments = null;
    private int linesCount = 0;
    
    public FastCollinearPoints(Point[] points) {    // finds all line segments containing 4 points
        if (points == null) throw new NullPointerException();
        for (Point p : points) {
            if (p == null) throw new NullPointerException();
        }
        allPoints = Arrays.copyOf(points, points.length);
        Arrays.sort(allPoints);
        for (int i = 0; i < (allPoints.length-1); i++) {
            if (allPoints[i].compareTo(allPoints[i+1]) == 0) throw new IllegalArgumentException();
        }
        
        findCollinearPoints();
        generateSegments();
    }
    
    private void findCollinearPoints() {
        for (int i = 0; i < allPoints.length; i++) {
            Point[] newPoints = new Point[allPoints.length-1];
            System.arraycopy(allPoints, 0, newPoints, 0, i);
            System.arraycopy(allPoints, i+1, newPoints, i, allPoints.length - i - 1);
            Arrays.sort(newPoints, allPoints[i].slopeOrder());
            
            ArrayDeque<Point> stack = new ArrayDeque<Point>();
            double lastSlope = 0;
            for (int j = 0; j < newPoints.length; j++) {
                double slope = allPoints[i].slopeTo(newPoints[j]);
                if (stack.size() == 0) {
                    stack.push(newPoints[j]);
                    lastSlope = slope;
                }
                else if (lastSlope == slope) {
                    stack.push(newPoints[j]);
                }
                else {
                    stack.push(allPoints[i]);
                    processStackOfPoints(stack);
                    stack.push(newPoints[j]);
                    lastSlope = slope;
                }
            }
            
            // Check for any remaining items in the stack.
            stack.push(allPoints[i]);
            processStackOfPoints(stack);
        }
    }
    
    private void processStackOfPoints(Deque<Point> stack) {
        Point[] sameSlopePoints = new Point[stack.size()];
        stack.toArray(sameSlopePoints);
        
        if (sameSlopePoints.length >= 4) {
            // Only to get first and last point. No need to sort all points.
            Point min = null, max = null;
            for (Point point : sameSlopePoints) {
                if (min == null) {
                    min = point;
                }
                else if (point.compareTo(min) < 0) {
                    if (max == null) max = min;
                    min = point;
                }
                else if (max == null) {
                    max = point;
                }
                else if (point.compareTo(max) > 0) {
                    max = point;
                }
            }
            addLine(min, max);
        }
        
        stack.clear();
    }
    
    private void addLine(Point p1, Point p2) {
        if (linesCount > 0) {
            for (Point[] points : linePoints) {
                if ((points[0] != null) && (points[0].compareTo(p1) == 0) && (points[1].compareTo(p2) == 0)) return;
            }
        }
        if (linesCount == linePoints.length) {
            Point[][] newLinePoints = new Point[linesCount * 2][2];
            System.arraycopy(linePoints, 0, newLinePoints, 0, linesCount);
            linePoints = newLinePoints;
        }
        linePoints[linesCount] = new Point[] {p1, p2};
        linesCount++;
    }
    
    public           int numberOfSegments() {        // the number of line segments
        return linesCount;
    }
    
    private void generateSegments() {                // the line segments
        LineSegment[] lines = new LineSegment[linesCount];
        for (int i = 0; i < linesCount; i++) {
            Point[] points = linePoints[i];
            lines[i] = new LineSegment(points[0], points[1]);
        }
        lineSegments = lines;
    }
    
    public LineSegment[] segments() {                // the line segments
        return Arrays.copyOf(lineSegments, linesCount);
    }
}
