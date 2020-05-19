import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.LinkedList;
import java.util.List;


public class KdTree {

    private static final String ERR_NULL_MSG = "Null is not accepted.";
    private enum Side { LEFT_BOTTOM, RIGHT_TOP }
    private Node root;
    private char letter = 'A';

    private static class Node {
        private final Point2D data;
        private final boolean vertical;
        private final char letter;
        private Node leftBottom;
        private Node rightTop;
        private int size;          // subtree count

        Node(Point2D data, boolean vertical, char letter) {
            this.data = data;
            this.vertical = vertical;
            this.letter = letter;
            this.size = 1;
        }

        private RectHV rectanglePart(RectHV parentRectangle, Side side) {
            if ( side == Side.LEFT_BOTTOM ) {
                if (vertical) return new RectHV(parentRectangle.xmin(), parentRectangle.ymin(), data.x(), parentRectangle.ymax());
                return new RectHV(parentRectangle.xmin(), parentRectangle.ymin(), parentRectangle.xmax(), data.y());
            }
            else {
                if (vertical) return new RectHV(data.x(), parentRectangle.ymin(), parentRectangle.xmax(), parentRectangle.ymax());
                return new RectHV(parentRectangle.xmin(), data.y(), parentRectangle.xmax(), parentRectangle.ymax());
            }
        }  
    
        public boolean isRightOrTopOf(final Point2D point) {
            if (vertical) return data.x() > point.x();
            return data.y() > point.y();
        }
    }

    public KdTree() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    private int size(Node node) {
        if (node == null) return 0;
        return node.size;
    }
    public int size() {
        return size(root);
    }

    private Node insert(Node node, Point2D data, boolean vertical) {
        if (node == null) return new Node(data, vertical, letter++);
        if (node.data.equals(data)) return node;
        if (node.isRightOrTopOf(data)) node.leftBottom = insert(node.leftBottom, data, !vertical);
        else node.rightTop = insert(node.rightTop, data, !vertical);
        node.size = size(node.leftBottom) + size(node.rightTop) + 1;
        return node;
    }

    public void insert(final Point2D p) {
        if (p == null) throw  new IllegalArgumentException(ERR_NULL_MSG);
        root = insert(root, p, true);
        if (letter > 'Z') letter = 'A';
    }

    public boolean contains(final Point2D p) {
        if (p == null) throw  new IllegalArgumentException(ERR_NULL_MSG);
        Node node = root;
        while (node != null) {
            if (node.data.equals(p)) return true;
            if (node.isRightOrTopOf(p)) node = node.leftBottom;
            else node = node.rightTop;
        }
        return false;
    }

    public void draw() {
        drawTraversePreOrder(root, new RectHV(0, 0, 1, 1));
    }

    private void drawTraversePreOrder(final Node node, RectHV rect) {
        if (node == null) return;
        drawNode(node, rect);
        drawTraversePreOrder(node.leftBottom, node.rectanglePart(rect, Side.LEFT_BOTTOM));
        drawTraversePreOrder(node.rightTop, node.rectanglePart(rect, Side.RIGHT_TOP));
    }

    private void drawNode(final Node node, RectHV rect) {
        // Rect
        StdDraw.setPenColor(StdDraw.RED);
        if (!node.vertical) StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setPenRadius(0.001);
        if (node.vertical) StdDraw.line(node.data.x(), rect.ymin(), node.data.x(), rect.ymax());
        else StdDraw.line(rect.xmin(), node.data.y(), rect.xmax(), node.data.y());
        
        // Point
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        node.data.draw();
        
        // Letter
        StdDraw.setPenColor(StdDraw.RED);
        if (!node.vertical) StdDraw.setPenColor(StdDraw.BLUE);
        drawCaption(node, Character.toString(node.letter));
        
        // Show
        StdDraw.show();
    }

    private void drawCaption(final Node node, String caption) {
        final StringBuilder sb = new StringBuilder();
        sb.append(caption);
        double shiftX = 0.02;
        double shiftY = 0.02;
        if (node.data.x() > 0.8) shiftX = -0.02; 
        if (node.data.y() > 0.8) shiftY = -0.02; 
        StdDraw.text(node.data.x() + shiftX, node.data.y() + shiftY, sb.toString());
    }

    public Iterable<Point2D> range(final RectHV rect) {
        if (rect == null) throw  new IllegalArgumentException(ERR_NULL_MSG);
        final List<Point2D> results = new LinkedList<>();
        range(root, rect, new RectHV(0, 0, 1, 1), results);
        return results;
    }

    private void range(final Node node, final RectHV query, RectHV rect, final List<Point2D> results) {
        if (node == null) return;
        if (!rect.intersects(query)) return;
        if (query.contains(node.data)) results.add(node.data);
        if (node.leftBottom != null) range(node.leftBottom, query, node.rectanglePart(rect, Side.LEFT_BOTTOM), results);
        if (node.rightTop != null) range(node.rightTop, query, node.rectanglePart(rect, Side.RIGHT_TOP), results);
    }

    public Point2D nearest(final Point2D p) {
        if (p == null) throw  new IllegalArgumentException(ERR_NULL_MSG);
        if (isEmpty()) return null;
        return nearest(root, p, root.data, new RectHV(0, 0, 1, 1));
    }

    private Point2D nearest(final Node node, final Point2D target, Point2D closest, RectHV rect) {
        if (node == null) return closest;
        final double closestDist = closest.distanceSquaredTo(target);
        if (closestDist <= rect.distanceSquaredTo(target) ) return closest;
        if (node.data.distanceSquaredTo(target) < closestDist) closest = node.data;

        // First goes to target direction
        if (node.isRightOrTopOf(target)) {
            closest = nearest( node.leftBottom  , target, closest, node.rectanglePart(rect, Side.LEFT_BOTTOM));
            closest = nearest( node.rightTop    , target, closest, node.rectanglePart(rect, Side.RIGHT_TOP));
        } 
        else {
            closest = nearest( node.rightTop    , target, closest, node.rectanglePart(rect, Side.RIGHT_TOP));
            closest = nearest( node.leftBottom  , target, closest, node.rectanglePart(rect, Side.LEFT_BOTTOM));
        }
        return closest;
    }

    /**
     * Unit testing of the methods (optional)
     */
    public static void main(final String[] args) {

        final String[] fileList = {
            "input1M.txt",
            "input10.txt",
            "input20K.txt",
            "input40K.txt",
            "input80K.txt",
            "input100K.txt",
            "input200K.txt",
            "input400K.txt",
            "input800K.txt",
        };
        for (final String filename : fileList) {


            final KdTree kdTree = new KdTree();
            int qty = 0;

            final In in = new In(filename);
            while (!in.isEmpty()) {
                qty++;
                final double x = in.readDouble();
                final double y = in.readDouble();
                final Point2D p = new Point2D(x, y);
                kdTree.insert(p);
            }

            double time;
            final long startTime = System.nanoTime();
            kdTree.nearest(new Point2D(0.5, 0.5));
            time = (System.nanoTime() - startTime)/1000.0;

            System.out.println(qty + ": Elapsed time: [" + time + "μs]");

        }

    }
}