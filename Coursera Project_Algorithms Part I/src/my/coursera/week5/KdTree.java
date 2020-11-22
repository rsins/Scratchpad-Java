package my.coursera.week5;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {

    private static final int HORIZONTAL = 0;
    private static final int VERTICAL = 1;
    
    private static class Node {
        private Point2D p;
        private RectHV rect;
        private Node lb;
        private Node rt;
        private int orientation = -1; 
        
        public Node(Point2D point, int orientation, RectHV rectHV) {
            this.p = point;
            this.orientation = orientation;
            this.rect = rectHV;
        }
        
        public void draw() {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.01);
            p.draw();
            
            StdDraw.setPenRadius();
            switch (orientation) {
                case HORIZONTAL:
                    StdDraw.setPenColor(StdDraw.BLUE);
                    StdDraw.line(rect.xmin(), p.y(), rect.xmax(), p.y());
                    break;    
                case VERTICAL:
                    StdDraw.setPenColor(StdDraw.RED);
                    StdDraw.line(p.x(), rect.ymin(), p.x(), rect.ymax());
                    break;
            }
        }
    }
    
    private Node root = null;
    private int size = 0;
    
    public         KdTree() {                                 // construct an empty set of points
    }
    
    public           boolean isEmpty() {                      // is the set empty?
        return (size == 0);
    }
    
    public               int size() {                         // number of points in the set
        return size;
    }
    
    public              void insert(Point2D p) {              // add the point to the set (if it is not already in the set)
        if (p == null) throw new NullPointerException();
        if (root == null) {
            root = new Node(p, VERTICAL, new RectHV(0, 0, 1, 1));
            size++;
        }
        else {
            insertImpl(root, p);
        }
    }
    
    private void insertImpl(Node node, Point2D p) {
        if (node.p.equals(p)) return;
        if (node.orientation == HORIZONTAL) {
            if (node.p.y() > p.y()) {
                if (node.lb == null) {
                    node.lb = new Node(p, VERTICAL, new RectHV(node.rect.xmin(), node.rect.ymin(), node.rect.xmax(), node.p.y()));
                    size++;
                }
                else
                    insertImpl(node.lb, p);
            }
            else {
                if (node.rt == null) {
                    node.rt = new Node(p, VERTICAL, new RectHV(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.rect.ymax()));
                    size++;
                }
                else
                    insertImpl(node.rt, p);
            }
        }
        else if (node.orientation == VERTICAL) {
            if (node.p.x() > p.x()) {
                if (node.lb == null) {
                    node.lb = new Node(p, HORIZONTAL, new RectHV(node.rect.xmin(), node.rect.ymin(), node.p.x(), node.rect.ymax()));
                    size++;
                }
                else
                    insertImpl(node.lb, p);
            }
            else {
                if (node.rt == null) {
                    node.rt = new Node(p, HORIZONTAL, new RectHV(node.p.x(), node.rect.ymin(), node.rect.xmax(), node.rect.ymax()));
                    size++;
                }
                else
                    insertImpl(node.rt, p);
            }
        }
    }
    
    public           boolean contains(Point2D p) {            // does the set contain point p?
        if (p == null) throw new NullPointerException();
        return containsImpl(root, p);
    }
    
    private boolean containsImpl(Node node, Point2D p) {
        if (node == null) return false;
        if (node.p.equals(p)) return true;
        if (node.orientation == HORIZONTAL) {
            if (node.p.y() > p.y()) {
                return containsImpl(node.lb, p);
            }
            else {
                return containsImpl(node.rt, p);
            }
        }
        else if (node.orientation == VERTICAL) {
            if (node.p.x() > p.x()) {
                return containsImpl(node.lb, p);
            }
            else {
                return containsImpl(node.rt, p);
            }
        }
        return false;
    }
    
    public              void draw() {                         // draw all points to standard draw
        drawImpl(root);
    }
    
    private void drawImpl(Node node) {
        if (node != null) {
            node.draw();
            drawImpl(node.lb);
            drawImpl(node.rt);
        }
    }
    
    public Iterable<Point2D> range(RectHV rect) {             // all points that are inside the rectangle
        if (rect == null) throw new NullPointerException();
        List<Point2D> result = new ArrayList<Point2D>();
        rangeImpl(root, rect, result);
        return result;
    }
    
    private void rangeImpl(Node node, RectHV rect, List<Point2D> result) {
        if (node == null) return;
        if (isNodeInRect(node, rect)) {
            result.add(node.p);
        }
        if (node.orientation == HORIZONTAL) {
            if (node.p.y() < rect.ymin())
                rangeImpl(node.rt, rect, result);
            else if (node.p.y() > rect.ymax())
                rangeImpl(node.lb, rect, result);
            else {
                rangeImpl(node.rt, rect, result);
                rangeImpl(node.lb, rect, result);
            }
        }
        else if (node.orientation == VERTICAL) {
            if (node.p.x() < rect.xmin())
                rangeImpl(node.rt, rect, result);
            else if (node.p.x() > rect.xmax())
                rangeImpl(node.lb, rect, result);
            else {
                rangeImpl(node.rt, rect, result);
                rangeImpl(node.lb, rect, result);
            }
        }
    }
    
    private boolean isNodeInRect(Node node, RectHV re) {
        return isPointInRect(node.p, re);
    }
    
    private boolean isPointInRect(Point2D p, RectHV re) {
        if ((p == null) || (re == null)) return false;
        if ((p.x() >= re.xmin()) && (p.x() <= re.xmax()) 
                && (p.y() >= re.ymin()) && (p.y() <= re.ymax())) return true;
        return false;
    }
    
    public           Point2D nearest(Point2D p) {             // a nearest neighbor in the set to point p; null if the set is empty
        if (p == null) throw new NullPointerException();
        if (isEmpty()) return null;
        Node result = nearestImpl(root,  null, p);
        
        // tempDebug(null, result, p);
        return result.p;
    }
    
    /*
    private void tempDebug(Node node, Node resultNode, Point2D p) {
        while (!StdDraw.hasNextKeyTyped()) {}
        StdDraw.nextKeyTyped();
        
        StdDraw.clear();
        
        if (node != null) {
            StdDraw.setPenColor(StdDraw.GREEN);
            StdDraw.setPenRadius(0.01);
            node.rect.draw();
        }
        
        this.draw();
        
        StdDraw.setPenColor(StdDraw.MAGENTA);
        StdDraw.setPenRadius(0.01);
        p.draw();
        
        if (node != null) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius(0.02);
            node.p.draw();
        }
        
        if (resultNode != null) {
            StdDraw.setPenColor(StdDraw.PINK);
            StdDraw.setPenRadius(0.02);
            resultNode.p.draw();
        }
        
        StdDraw.show();
    }
    */
    
    private Node nearestImpl(Node node, Node resultNode, Point2D p) {
        // tempDebug(node, resultNode, p);
        
        Node result = resultNode;
        if (node == null) return result;
        result = nearestNodeImpl(node, result, p);
        
        if ((node.lb == null) && (node.rt == null)) return result;
        if (node.lb == null) return nearestImpl(node.rt, result, p);
        if (node.rt == null) return nearestImpl(node.lb, result, p);
        
        if (result == null) {
            result = node;
        }
        
        if (result.p.distanceSquaredTo(p) < node.rect.distanceSquaredTo(p)) return result;
        
        Node lbNearest = nearestImpl(node.lb, result, p);
        Node rtNearest = nearestImpl(node.rt, result, p);
        
        result = nearestNodeImpl(result, lbNearest, p);
        result = nearestNodeImpl(result, rtNearest, p);
        
        return result;
    }

    private Node nearestNodeImpl(Node node1, Node node2, Point2D p) {
        if ((node1 == null) && (node2 != null)) return node2;
        if ((node1 != null) && (node2 == null)) return node1;
        if ((node1 == null) && (node2 == null)) return null;
        double d1 = p.distanceSquaredTo(node1.p);
        double d2 = p.distanceSquaredTo(node2.p);
        if (d1 <= d2) return node1;
        return node2;
    }
    
    public static void main(String[] args) {                  // unit testing of the methods (optional)
        String filename = "D:\\Ravi\\My_Programs\\Eclipse_Workspace\\Coursera Project_Algorithms Part I\\resources\\week5\\kdtree\\circle10.txt";
        In in = new In(filename);

        StdDraw.enableDoubleBuffering();

        // initialize the data structures with point from standard input
        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
            
            while (!StdDraw.hasNextKeyTyped()) { }
            StdDraw.nextKeyTyped();
            StdDraw.clear();
            kdtree.draw();
            StdDraw.show();
        }

        kdtree.draw();
        StdDraw.show();
        
        while (!StdDraw.mousePressed()) { }
        
        // the location (x, y) of the mouse
        double x = StdDraw.mouseX();
        double y = StdDraw.mouseY();
        Point2D query = new Point2D(x, y);
        StdDraw.setPenColor(StdDraw.MAGENTA);
        StdDraw.setPenRadius(0.01);
        query.draw();
        StdDraw.show();
        
        kdtree.nearest(query);
    }
}
