import edu.princeton.cs.algs4.Stack;

import java.util.Arrays;

public class BruteCollinearPoints {

    private LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null || hasNull(points)) throw new IllegalArgumentException();
        Point[] sortedPoints = points.clone();
        Arrays.sort(sortedPoints);
        checkIfHasDuplicate(sortedPoints);
        findSegments(sortedPoints);
    }

    private static boolean hasNull(Point[] points) {
        for (Point item : points) {
            if (item == null) return true;
        }
        return false;
    }

    private static void checkIfHasDuplicate(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].<Point>compareTo(points[i+1]) == 0) throw new IllegalArgumentException();
        }
        return;
    }

    private void findSegments(Point[] points) {

        Stack<LineSegment> segmentstack = new Stack<>();
        int s = points.length;

        for (int a = 0; a < s - 3; a++) {
            for (int b = a + 1; b < s -2; b++) {
                double slopeAB = points[a].slopeTo(points[b]);

                for (int c = b + 1; c < s - 1; c++) {
                    double slopeAC = points[a].slopeTo(points[c]);
                    if (slopeAB  !=  slopeAC) continue;

                    for (int d = c + 1; d < s; d++) {
                        double slopeAD = points[a].slopeTo(points[d]);
                        if (slopeAB != slopeAD) continue;
                        segmentstack.push(new LineSegment(points[a], points[d]));
                    }
                }
            }
        }

        int size = segmentstack.size();
        segments = new LineSegment[size];
        for (int i = 0; i < size; i++) segments[i] = segmentstack.pop();
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