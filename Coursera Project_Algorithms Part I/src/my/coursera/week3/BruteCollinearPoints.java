package my.coursera.week3;

import java.util.Arrays;

public class BruteCollinearPoints {
    
    private Point[] allPoints;
    private Point[][] linePoints = new Point[1][2];
    private LineSegment[] lineSegments = null;
    private int linesCount = 0;
    
    public BruteCollinearPoints(Point[] points) {    // finds all line segments containing 4 points
        if (points == null) throw new NullPointerException();
        for (Point p : points) {
            if (p == null) throw new NullPointerException();
        }
        allPoints = Arrays.copyOf(points, points.length);
        Arrays.sort(allPoints);
        for (int i = 0; i < (allPoints.length-1); i++) {
            if (allPoints[i].compareTo(allPoints[i+1]) == 0) throw new IllegalArgumentException();
        }
        
        findFourCollinearPoints();
        generateSegments();
    }
    
    private void findFourCollinearPoints() {
        for (int i = 0; i < allPoints.length; i++) {
            for (int j = (i + 1); j < allPoints.length; j++) {
                for (int k = (j + 1); k < allPoints.length; k++) {
                    for (int l = (k + 1); l < allPoints.length; l++) {
                        checkIfFourCollinearPoint(i, j, k, l);
                    }
                }
            }
        }
    }
    
    private void checkIfFourCollinearPoint(int i, int j, int k, int l) {
        Point[] fourPoints = new Point[] {allPoints[i], allPoints[j], allPoints[k], allPoints[l]};
        double[] slopes = new double[] {fourPoints[0].slopeTo(fourPoints[1]), fourPoints[0].slopeTo(fourPoints[2]),
                                        fourPoints[0].slopeTo(fourPoints[3])};
        if (slopes[0] == slopes[1] && slopes[1] == slopes[2]) {
            // Only to get first and last point. No need to sort all points.
            Point min = null, max = null;
            for (Point point : fourPoints) {
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
