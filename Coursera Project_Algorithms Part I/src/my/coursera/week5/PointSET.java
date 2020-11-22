package my.coursera.week5;

import java.util.ArrayList;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class PointSET {

    private SET<Point2D> set = new SET<Point2D>();
    
    public         PointSET() {                               // construct an empty set of points
        
    }
    
    public           boolean isEmpty() {                      // is the set empty?
        return (set.size() == 0);
    }
    
    public               int size() {                         // number of points in the set
        return set.size();
    }
    
    public              void insert(Point2D p) {              // add the point to the set (if it is not already in the set)
        if (p == null) throw new NullPointerException();
        set.add(p);
    }
    
    public           boolean contains(Point2D p) {            // does the set contain point p?
        if (p == null) throw new NullPointerException();
        return set.contains(p);
    }
    
    public              void draw() {                         // draw all points to standard draw
        for (Point2D p : set) {
            p.draw();
        }
    }
    
    public Iterable<Point2D> range(RectHV rect) {             // all points that are inside the rectangle
        if (rect == null) throw new NullPointerException();
        ArrayList<Point2D> points = new ArrayList<Point2D>();
        for (Point2D p : set) {
            if ((p.x() >= rect.xmin()) && (p.x() <= rect.xmax())
                    && (p.y() >= rect.ymin()) && (p.y() <= rect.ymax())) {
                points.add(p);
            }
        }
        return points;
    }
    
    public           Point2D nearest(Point2D p) {             // a nearest neighbor in the set to point p; null if the set is empty
        if (p == null) throw new NullPointerException();
        if (isEmpty()) return null;
        Point2D closestPoint = null;
        double closestDistance = Double.POSITIVE_INFINITY;
        for (Point2D point : set) {
            double distance = p.distanceSquaredTo(point);
            if (distance < closestDistance) {
                closestDistance = distance;
                closestPoint = point;
            }
        }
        return closestPoint;
    }

    public static void main(String[] args) {                  // unit testing of the methods (optional)
        
    }
}
