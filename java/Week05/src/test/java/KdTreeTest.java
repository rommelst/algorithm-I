import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class KdTreeTest {

    private <T> T[] toArray(Iterable<T> elements)
    {
        ArrayList<T> arrayElements = new ArrayList<T>();
        for(T element : elements)
        {
            arrayElements.add(element);
        }

        return (T[])arrayElements.toArray();
    }


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void isEmptyWhenNoDataWereSuppliedExpectsTrue() {
        // Arrange
        KdTree ps = new KdTree();

        // Action

        // Assert
        assertTrue(ps.isEmpty());
    }

    @Test
    public void isEmptyWhenOneDataIsSuppliedExpectsFalse() {
        // Arrange
        KdTree ps = new KdTree();

        // Action
        ps.insert(new Point2D(2, 0));

        // Assert
        assertFalse(ps.isEmpty());
    }

    @Test
    public void sizeWhenNoDataWereSuppliedExpectsZero() {
        // Arrange
        KdTree ps = new KdTree();

        // Action

        // Assert
        assertEquals(0, ps.size());
    }

    @Test
    public void insertWhenNullParameterIsSuppliedExpectsIllegalArgumentException() {
        // Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {

            // Arrange
            KdTree ps = new KdTree();

            // Act
            ps.insert(null);


        });
        assertEquals("Null is not accepted.", thrown.getMessage());
    }

    @Test
    public void insertWhenOnePointIsSuppliedExpectsNotEmptyAndSize01() {
        // Arrange
        KdTree ps = new KdTree();
        Point2D point = new Point2D(2, 0);

        // Action
        ps.insert(point);

        // Assert
        assertFalse(ps.isEmpty());
        assertEquals(1, ps.size());
    }

    @Test
    public void insertWhenTwoPointsAreSuppliedExpectsNotEmptyAndSize02() {
        // Arrange
        KdTree ps = new KdTree();

        // Action
        ps.insert(new Point2D(0.2, 0));
        ps.insert(new Point2D(0.2, 1));

        // Assert
        assertFalse(ps.isEmpty());
        assertEquals(2, ps.size());
    }

    @Test
    public void insertWhenTwoPointsWithSameCoordinatesAreSuppliedExpectsNotEmptyAndSize01() {
        // Arrange
        KdTree ps = new KdTree();

        // Action
        ps.insert(new Point2D(0.2, 1));
        ps.insert(new Point2D(0.2, 1));

        // Assert
        assertFalse(ps.isEmpty());
        assertEquals(1, ps.size());
    }

    @Test
    public void containsWhenNullParameterIsSuppliedExpectsIllegalArgumentException() {
        // Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {

            // Arrange
            KdTree ps = new KdTree();

            // Act
            ps.contains(null);


        });
        assertEquals("Null is not accepted.", thrown.getMessage());
    }

    @Test
    public void containsWhenSuppliedPointExistsExpectsTrue() {
        // Arrange
        KdTree ps = new KdTree();
        ps.insert(new Point2D(2, 1));

        // Action
        boolean b = ps.contains(new Point2D(2, 1));

        // Assert
        assertTrue(b);
    }

    @Test
    public void containsWhenSuppliedPointDoesNotExistsExpectsFalse() {
        // Arrange
        KdTree ps = new KdTree();
        ps.insert(new Point2D(2, 1));

        // Action
        boolean b = ps.contains(new Point2D(2, 0));

        // Assert
        assertFalse(b);
    }

    @Test
    public void rangeWhenNullParameterIsSuppliedExpectsIllegalArgumentException() {
        // Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {

            // Arrange
            KdTree ps = new KdTree();

            // Act
            ps.range(null);


        });
        assertEquals("Null is not accepted.", thrown.getMessage());
    }

    @Test
    public void rangeWhenSuppliedPointsAreLikeSpecsAndRectangleIsLikeSpecExpectsSpecificPoint() {
        // Arrange
        KdTree ps = new KdTree();
        ps.insert(new Point2D(0.0, 0.0));
        ps.insert(new Point2D(0.1, 0.4));
        ps.insert(new Point2D(0.6, 0.5));
        RectHV rect = new RectHV(0.4, 0.3, 0.8, 0.6);


        // Action
        Iterable<Point2D> list = ps.range(rect);

        // Assert
        assertEquals(1, list.spliterator().getExactSizeIfKnown());
        assertEquals(new Point2D(0.6, 0.5), list.iterator().next());
    }

    @Test
    public void nearestWhenKdTreeIsEmptyExpectNullPoint() {
        // Arrange
        KdTree ps = new KdTree();
        Point2D basePoint = new Point2D(0.0, 0.0);

        // Action
        Point2D nearestPoint = ps.nearest(basePoint);

        // Assert
        assertNull(nearestPoint);
    }

    @Test
    public void nearestWhenSuppliedPointsAreLikeSpecExpectSpecificPoint() {
        // Arrange
        KdTree ps = new KdTree();
        ps.insert(new Point2D(0.0, 0.0));
        ps.insert(new Point2D(0.1, 0.4));
        ps.insert(new Point2D(0.6, 0.5));
        Point2D basePoint = new Point2D(0.6, 0.6);

        // Action
        Point2D nearestPoint = ps.nearest(basePoint);

        // Assert
        assertNotNull(nearestPoint);
        assertEquals(new Point2D(0.6, 0.5), nearestPoint);
    }

    @Test
    public void nearestWhenSuppliedPointsAreFromInput10FileExpectEqualPointFromBruteForceAndKdTree() {

        // Arrange
        KdTree kdTree = new KdTree();
        PointSET brute = new PointSET();
        In in = new In("input10K.txt");
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdTree.insert(p);
            brute.insert(p);
        }
        Point2D query = new Point2D(0.0390625, 0.34765625);

        // Action
        // StdDraw.enableDoubleBuffering();
        // StdDraw.clear();
        // kdTree.draw();
        // query.draw();
        // StdDraw.show();
        // StdDraw.pause(20);
        Point2D kdTreeNearest = kdTree.nearest(query);
        Point2D BruteForceNearest = brute.nearest(query);

        // Assert       
        assertNotNull(kdTreeNearest);
        assertEquals(BruteForceNearest, kdTreeNearest);

    }

    @Test
    public void nearestWhenSuppliedPointsAndRectAreLikeSpecExpectSpecificPointF() {

        // Arrange
        KdTree kdTree = new KdTree();
        In in = new In("circle10.txt");
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdTree.insert(p);
        }
        Point2D expected = new Point2D(0.975528, 0.345492);
        Point2D query = new Point2D(0.81, 0.30);

        // Action
        // StdDraw.enableDoubleBuffering();
        // StdDraw.clear();
        // kdTree.draw();
        // expected.draw();
        // query.draw();
        // StdDraw.show();
        // StdDraw.pause(20);
        Point2D nearest = kdTree.nearest(query);

        // Assert       
        assertEquals(expected, nearest);
    }

    @Test
    public void nearestWhenSuppliedPointExistsExpectItSelf() {
        // Arrange
        KdTree ps = new KdTree();
        ps.insert(new Point2D(0.0, 0.0));
        ps.insert(new Point2D(0.1, 0.4));
        ps.insert(new Point2D(0.6, 0.5));
        Point2D basePoint = new Point2D(0.6, 0.5);

        // Action
        Point2D nearestPoint = ps.nearest(basePoint);

        // Assert
        assertNotNull(nearestPoint);
        assertEquals(new Point2D(0.6, 0.5), nearestPoint);
    }

    @Test
    public void drawWhenPointsAreFromCircle10FileLikeSpecExpectsDrawnBoardLikeSpec() {

        KdTree kdTree = new KdTree();
        In in = new In("circle10.txt");
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdTree.insert(p);
        }
        
        StdDraw.enableDoubleBuffering();
        StdDraw.clear();
        kdTree.draw();
        StdDraw.show();
        StdDraw.pause(20);

        assertTrue(true);
    }

    @Test
    public void drawWhenPointsAreLikeSpecExpectsDrawnBoardLikeSpec() {

        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0.7, 0.2));
        kdTree.insert(new Point2D(0.5, 0.4));
        kdTree.insert(new Point2D(0.2, 0.3));
        kdTree.insert(new Point2D(0.4, 0.7));
        kdTree.insert(new Point2D(0.9, 0.6));
        
        StdDraw.enableDoubleBuffering();
        StdDraw.clear();
        kdTree.draw();
        StdDraw.show();
        StdDraw.pause(20);
        assertTrue(true);
    }

    @Test
    public void assessmentGuideCorrectnessSectionOfBruteForceTest3() {

        // Arrange
        Point2D[] points = { 
            new Point2D(0.0 , 0.0),
            new Point2D(0.75, 1.0),
            new Point2D(0.5 , 1.0),
            new Point2D(0.25, 0.5),
            new Point2D(0.75, 0.75),
            new Point2D(0.75, 0.0),
            new Point2D(0.1 , 1.0),
            new Point2D(0.25, 1.0),
            new Point2D(0.0 , 1.0),
            new Point2D(0.75, 0.5),
        };
        KdTree kdTree = new KdTree();
        for ( Point2D point : points) kdTree.insert(point);
        Point2D queryPoint = new Point2D(1.0, 0.25);
        Point2D expectedPoint = new Point2D(0.75, 0.0);
        
        // Action
        // StdDraw.enableDoubleBuffering();
        // StdDraw.clear();
        // kdTree.draw();
        // StdDraw.show();
        Point2D nearest = kdTree.nearest(queryPoint);

        // Assert
        assertEquals( expectedPoint, nearest);
    }

    @Test
    public void assessmentGuideCorrectnessSectionOfKdTreeTest4aInput5() {

        // Arrange
        Point2D[] points = { 
            new Point2D(0.7, 0.2),
            new Point2D(0.5, 0.4),
            new Point2D(0.2, 0.3),
            new Point2D(0.4, 0.7),
            new Point2D(0.9, 0.6),
        };
        KdTree kdTree = new KdTree();
        for ( Point2D point : points) kdTree.insert(point);
        RectHV query = new RectHV(0.35, 0.77, 0.55, 0.99);
               
        // Action
        // StdDraw.enableDoubleBuffering();
        // StdDraw.clear();
        // kdTree.draw();
        // StdDraw.show();
        // query.draw();
        // StdDraw.show();
        Iterable<Point2D> list = kdTree.range(query);

        // Assert
        assertEquals(0, list.spliterator().getExactSizeIfKnown());
    }

    @Test
    public void assessmentGuideCorrectnessSectionOfKdTreeTest4aInput10() {

        // Arrange
        Point2D A = new Point2D(0.372, 0.497);
        Point2D B = new Point2D(0.564, 0.413);
        Point2D C = new Point2D(0.226, 0.577);
        Point2D D = new Point2D(0.144, 0.179);
        Point2D E = new Point2D(0.083, 0.51);
        Point2D F = new Point2D(0.32, 0.708);
        Point2D G = new Point2D(0.417, 0.362);
        Point2D H = new Point2D(0.862, 0.825);
        Point2D I = new Point2D(0.785, 0.725);
        Point2D J = new Point2D(0.499, 0.208);
        Point2D[] points = {A, B, C ,D ,E ,F ,G, H ,I ,J};
        Point2D[] expected = {A, C ,D ,E ,F ,G};
        KdTree kdTree = new KdTree();
        for ( Point2D point : points) kdTree.insert(point);
        RectHV query = new RectHV(0.0, 0.08, 0.43, 0.88);

        // Action
        // StdDraw.enableDoubleBuffering();
        // StdDraw.clear();
        // kdTree.draw();
        // StdDraw.show();
        // query.draw();
        // StdDraw.show();
        Iterable<Point2D> list = kdTree.range(query);

        // Assert
        assertEquals(6, list.spliterator().getExactSizeIfKnown());
        assertArrayEquals(expected, toArray(list));
    }


}