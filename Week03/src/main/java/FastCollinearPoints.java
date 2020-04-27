import edu.princeton.cs.algs4.Stack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class FastCollinearPoints {

    private final LineSegment[] segments;

    public FastCollinearPoints(final Point[] points) {
        if (points == null || hasNull(points)) throw new IllegalArgumentException();
        Point[] clone = points.clone();
        Arrays.sort(clone);
        checkIfHasDuplicate(clone);
        segments = findSegments(clone);
    }

    private LineSegment[] findSegments(Point[] points) {

        Stack<LineSegment> segmentstack = new Stack<>();
        for (Point base : points) {
            Point[] pointsBySlope = points.clone();
            Arrays.sort(pointsBySlope, base.slopeOrder());
            // x starts with 1 because 0 is the base point.
            int x = 1;
            while (x < pointsBySlope.length) {
                List<Point> colinear = new LinkedList<Point>();
//                if (point.compareTo(base) == 0) continue;
                double slopeReference = base.slopeTo(pointsBySlope[x]);
                do {
                    colinear.add(pointsBySlope[x++]);
                } while (x < pointsBySlope.length && base.slopeTo(pointsBySlope[x]) == slopeReference);
                if (colinear.size() >= 3 && base.compareTo(colinear.get(0)) < 0) {
                    segmentstack.push(new LineSegment(base, colinear.get(colinear.size()-1)));
                }
            }
        }
        int size = segmentstack.size();
        LineSegment[] ret = new LineSegment[size];
        for (int i = 0; i < size; i++) ret[i] = segmentstack.pop();
        return ret;
    }


    private static boolean hasNull(Point[] points) {
        for (Point item : points) {
            if (item == null) return true;
        }
        return false;
    }

    private static void checkIfHasDuplicate(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].<Point>compareTo(points[i+1]) == 0)  throw new IllegalArgumentException();
        }
        return;
    }

    // the number of line segments
    public int numberOfSegments() {
        if (segments == null) return 0;
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return segments.clone();
    }

}
