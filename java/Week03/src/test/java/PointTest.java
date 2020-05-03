import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class PointTest {

    @Test
    public void degenerateLineSegment() {

        // Arrange
        Point me = new Point(1,1);
        Point him = new Point(1,1);

        // Act
        Double slope = me.slopeTo(him);

        // Assert
        assertTrue(slope.isInfinite());
        assertTrue(slope.compareTo(0.0)<0);

    }

    @Test
    public void horizontalLineSlope() {

        // Arrange
        Point me = new Point(1,1);
        Point him = new Point(5,1);

        // Act
        Double slope = me.slopeTo(him);

        // Assert
        assertTrue(Double.compare(slope,0)==0);

    }

    @Test
    public void verticalLineSlope() {

        // Arrange
        Point me = new Point(5,1);
        Point him = new Point(5,5);

        // Act
        Double slope = me.slopeTo(him);

        // Assert
        assertTrue(slope.isInfinite());
        assertTrue(slope.compareTo(0.0)>0);

    }

    @Test
    public void crescentSlope() {

        // Arrange
        Point me = new Point(1,1);
        Point him = new Point(3,2);

        // Act
        Double slope = me.slopeTo(him);

        // Assert
        assertTrue(slope.compareTo(0.5)==0);

    }

    @Test
    public void compareToMinorY() {

        // Arrange
        Point me = new Point(1,1);
        Point him = new Point(3,2);

        // Act
        // Assert
        assertTrue(me.compareTo(him) < 0);

    }

    @Test
    public void compareToMajorY() {

        // Arrange
        Point me = new Point(1,1);
        Point him = new Point(3,0);

        // Act
        // Assert
        assertTrue(me.compareTo(him) > 0);

    }

    @Test
    public void compareToMinorX() {

        // Arrange
        Point me = new Point(1,2);
        Point him = new Point(3,2);

        // Act
        // Assert
        assertTrue(me.compareTo(him) < 0);

    }

    @Test
    public void compareToMajorX() {

        // Arrange
        Point me = new Point(5,2);
        Point him = new Point(3,2);

        // Act
        // Assert
        assertTrue(me.compareTo(him) > 0);

    }

    @Test
    public void compareToEqual() {

        // Arrange
        Point me = new Point(5,2);
        Point him = new Point(5,2);

        // Act
        // Assert
        assertTrue(me.compareTo(him) == 0);

    }

    @Test
    public void checkToString() {

        // Arrange

        // Act
        Point me = new Point(5,2);

        // Assert
        assertTrue(me.toString().compareTo("(5, 2)") == 0);

    }

    @Test
    public void slopeOrder() {

        // Arrange

        // Act
        Point me = new Point(5,2);

        // Assert
        assertNotNull(me.slopeOrder());

    }

    @Test
    public void slopeComparatorCompareMinor() {

        // Arrange
        Point me = new Point(5,2);
        Point other1 = new Point(7,4);
        Point other2 = new Point(6,4);

        // Act
        Comparator<Point> comparator = me.slopeOrder();

        // Assert
        assertTrue( comparator.compare(other1, other2) < 0);

    }

    @Test
    public void slopeComparatorCompareMajor() {

        // Arrange
        Point me = new Point(5,2);
        Point other1 = new Point(6,4);
        Point other2 = new Point(7,4);

        // Act
        Comparator<Point> comparator = me.slopeOrder();

        // Assert
        assertTrue( comparator.compare(other1, other2) > 0);

    }

    @Test
    public void slopeComparatorCompareEqual() {

        // Arrange
        Point me = new Point(5,2);
        Point other1 = new Point(6,4);
        Point other2 = new Point(7,6);

        // Act
        Comparator<Point> comparator = me.slopeOrder();

        // Assert
        assertTrue( comparator.compare(other1, other2) == 0);

    }

    @Test
    public void slopeComparatorWithNull() {

        // Arrange
        Point me = new Point(5,2);
        Comparator<Point> comparator = me.slopeOrder();

        // Assert
        NullPointerException thrown = assertThrows( NullPointerException.class, () -> {

            // Act
            comparator.compare(null, null);

        });

    }

    @Test
    public void callDraw() {

        // Arrange
        Point me = new Point(5,2);

        // Assert
        assertDoesNotThrow( () -> {

            // Act
            me.draw();

        });
    }

    @Test
    public void callDrawTo() {

        // Arrange
        Point me = new Point(5,2);
        Point him = new Point(7,3);

        // Assert
        assertDoesNotThrow( () -> {

            // Act
            me.drawTo(him);

        });
    }

}