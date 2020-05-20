import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class PointSET {

    private static final String ERR_NULL_MSG = "Null is not accepted.";

    private final TreeSet<Point2D> points = new TreeSet<>();

    public boolean isEmpty() {
        return points.isEmpty();
    }

    public int size() {
        return points.size();
    }

    public void insert(Point2D point) {
        if (point == null) throw  new IllegalArgumentException(ERR_NULL_MSG);
        this.points.add(point);
    }

    public boolean contains(Point2D point2D) {
        if (point2D == null) throw  new IllegalArgumentException(ERR_NULL_MSG);
        return points.contains(point2D);
    }

    public void draw() {
        for (Point2D item : points)
            item.draw();
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw  new IllegalArgumentException(ERR_NULL_MSG);
        List<Point2D> foundPoints = new ArrayList<>();
        for (Point2D point : points) {
            if (!rect.contains(point)) continue;
            foundPoints.add(point);
        }
        return foundPoints;
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException(ERR_NULL_MSG);
        if (size() == 0) return null;

        double minDist = Double.POSITIVE_INFINITY;
        Point2D nearestPoint = null;

        for (Point2D point : points) {
            double curDist = point.distanceSquaredTo(p);
            if (curDist < minDist) {
                minDist = curDist;
                nearestPoint = point;
            }
        }
        return nearestPoint;
    }


    public static void main(String[] args) {
        String[] fileList = {
            "input1M.txt",
            "input10.txt",
            "input20K.txt",
            "input40K.txt",
            "input80K.txt",
            "input100K.txt",
            "input200K.txt",
            "input400K.txt",
            "input800K.txt"
        };
        for (String filename : fileList) {


            PointSET ps = new PointSET();
            int qty = 0;

            In in = new In(filename);
            while (!in.isEmpty()) {
                qty++;
                double x = in.readDouble();
                double y = in.readDouble();
                Point2D p = new Point2D(x, y);
                ps.insert(p);
            }

            double time;
            long startTime = System.nanoTime();
            ps.nearest(new Point2D(0.5, 0.5));
            time = (System.nanoTime() - startTime)/1000.0;

            System.out.println(qty + ": Elapsed time: [" + time + "μs]");

        }
    }
}
