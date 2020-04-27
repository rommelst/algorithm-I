import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineSegmentTest {


    @Test
    public void callConstructorWithNull(){

        // Arrange

        // Assert
        NullPointerException thrown = assertThrows( NullPointerException.class, () -> {

            // Act
            LineSegment segment = new LineSegment(null, null);

        });
        assertEquals("argument is null", thrown.getMessage());

    }

    @Test
    public void callDraw() {

        // Arrange
        Point me = new Point(5,2);
        Point him = new Point(7,3);
        LineSegment segment = new LineSegment(me, him);

        // Assert
        assertDoesNotThrow( () -> {

            // Act
            segment.draw();

        });
    }

    @Test
    public void callToString() {
        // Arrange
        Point me = new Point(5,2);
        Point him = new Point(7,3);
        LineSegment segment = new LineSegment(me, him);

        // Act
        String s = segment.toString();

        // Assert
        assertEquals("(5, 2) -> (7, 3)", s);
    }

    @Test
    public void callHashCode(){

        // Arrange
        Point me = new Point(5,2);
        Point him = new Point(7,3);
        LineSegment segment = new LineSegment(me, him);


        // Assert
        UnsupportedOperationException thrown = assertThrows( UnsupportedOperationException.class, () -> {

            // Act
            segment.hashCode();

        });

    }
}