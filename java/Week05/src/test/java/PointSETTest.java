import edu.princeton.cs.algs4.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointSETTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void isEmptyWhenNoDataWereSuppliedExpectsTrue() {
        // Arrange
        PointSET ps = new PointSET();

        // Action

        // Assert
        assertTrue(ps.isEmpty());
    }

    @Test
    public void sizeWhenNoDataWereSuppliedExpectsZero() {
        // Arrange
        PointSET ps = new PointSET();

        // Action

        // Assert
        assertEquals(0, ps.size());
    }

    @Test
    public void insertWhenNullParameterIsSuppliedExpectsIllegalArgumentException() {
        // Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {

            // Arrange
            PointSET ps = new PointSET();

            // Act
            ps.insert(null);


        });
        assertEquals("Null is not accepted.", thrown.getMessage());
    }

    @Test
    public void insertWhenOnePointIsSuppliedExpectsNotEmptyAndSize01() {
        // Arrange
        PointSET ps = new PointSET();
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
        PointSET ps = new PointSET();

        // Action
        ps.insert(new Point2D(2, 0));
        ps.insert(new Point2D(2, 1));

        // Assert
        assertFalse(ps.isEmpty());
        assertEquals(2, ps.size());
    }

    @Test
    public void insertWhenTwoPointsWithSameCoordinatesAreSuppliedExpectsNotEmptyAndSize01() {
        // Arrange
        PointSET ps = new PointSET();

        // Action
        ps.insert(new Point2D(2, 1));
        ps.insert(new Point2D(2, 1));

        // Assert
        assertFalse(ps.isEmpty());
        assertEquals(1, ps.size());
    }

    @Test
    public void containsWhenNullParameterIsSuppliedExpectsIllegalArgumentException() {
        // Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {

            // Arrange
            PointSET ps = new PointSET();

            // Act
            ps.contains(null);


        });
        assertEquals("Null is not accepted.", thrown.getMessage());
    }

    @Test
    public void containsWhenSuppliedPointExistsExpectsTrue() {
        // Arrange
        PointSET ps = new PointSET();
        ps.insert(new Point2D(2, 1));

        // Action
        boolean b = ps.contains(new Point2D(2, 1));

        // Assert
        assertTrue(b);
    }

    @Test
    public void containsWhenSuppliedPointDoesNotExistsExpectsFalse() {
        // Arrange
        PointSET ps = new PointSET();
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
            PointSET ps = new PointSET();

            // Act
            ps.range(null);


        });
        assertEquals("Null is not accepted.", thrown.getMessage());
    }

    @Test
    public void rangeWhenSuppliedPointsAreLikeSpecsAndRectangleIsLikeSpecExpectsSpecificPoint() {
        // Arrange
        PointSET ps = new PointSET();
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
    public void nearestWhenPointSetIsEmptyExpectNullPoint() {
        // Arrange
        PointSET ps = new PointSET();
        Point2D basePoint = new Point2D(0.0, 0.0);

        // Action
        Point2D nearestPoint = ps.nearest(basePoint);

        // Assert
        assertNull(nearestPoint);
    }

    @Test
    public void nearestWhenSuppliedPointsAndRectAreLikeSpecExpectSpecificPoint() {
        // Arrange
        PointSET ps = new PointSET();
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
    public void nearestWhenSuppliedPointExistsExpectItSelf() {
        // Arrange
        PointSET ps = new PointSET();
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
        for ( Point2D point : points) 
            kdTree.insert(point);
        Point2D queryPoint = new Point2D(1.0, 0.25);
        Point2D expectedPoint = new Point2D(0.75, 0.0);

        // Action
        Point2D nearest = kdTree.nearest(queryPoint);

        // Assert
        assertEquals( expectedPoint, nearest);
    }

}