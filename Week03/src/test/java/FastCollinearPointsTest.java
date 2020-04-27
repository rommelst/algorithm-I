import edu.princeton.cs.algs4.In;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class FastCollinearPointsTest {

    private Point[] readFile(String fileName){
        // read the n points from a file
        In in = new In(fileName);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        return points;
    }


    @Test
    public void callingConstructorWhenParameterIsNullExepctsIllegalArguemntException() {

        // Arrange
        Point[] list = null;

        // Assert
        IllegalArgumentException thrown = assertThrows( IllegalArgumentException.class, () -> {

            // Act
            FastCollinearPoints collinear = new FastCollinearPoints(list);

        });

    }

    @Test
    public void callingConstructorWhenParameterHasNullExepctsIllegalArguemntException() {

        // Arrange
        Point[] list = new Point[10];

        // Assert
        IllegalArgumentException thrown = assertThrows( IllegalArgumentException.class, () -> {

            // Act
            FastCollinearPoints collinear = new FastCollinearPoints(list);

        });

    }

    @Test
    public void callingConstructorWhenParameterHasDuplicatesExepctsIllegalArguemntException() {

        // Arrange
        Point[] list = new Point[2];
        list[0] = new Point(1,1);
        list[1] = new Point(1,1);

        // Assert
        IllegalArgumentException thrown = assertThrows( IllegalArgumentException.class, () -> {

            // Act
            FastCollinearPoints collinear = new FastCollinearPoints(list);

        });

    }
    
    
    @Test
    public void emptyFCPOnePoint() {
        Point[] list = new Point[1];
        list[0] = new Point(0,0);
        FastCollinearPoints collinear = new FastCollinearPoints(list);
        assertEquals(0, collinear.numberOfSegments());
    }

    @Test
    public void fourPointsOneSegments() {
        Point[] list = new Point[6];
        list[0] = new Point(1,1);
        list[1] = new Point(2,2);
        list[2] = new Point(3,3);
        list[3] = new Point(4,4);
        list[4] = new Point(5,5);
        list[5] = new Point(6,6);
        FastCollinearPoints collinear = new FastCollinearPoints(list);
        assertEquals(1, collinear.numberOfSegments());
    }

    @Test
    public void checkSegmentsAndNumberOfSegments() {
        Point[] points = readFile("input8.txt");
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        assertEquals(collinear.segments().length, collinear.numberOfSegments());

    }

    @Test
    public void fileInput20() {
        Point[] points = readFile("input20.txt");
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        assertEquals(5, collinear.numberOfSegments());
    }

    @Test
    public void fileInput40() {
        Point[] points = readFile("input40.txt");
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        assertEquals(4, collinear.numberOfSegments());
    }

    @Test
    public void fileInput48() {
        Point[] points = readFile("input48.txt");
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        assertEquals(6, collinear.numberOfSegments());
    }

    @Test
    public void fileInput299() {
        Point[] points = readFile("input299.txt");
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        assertEquals(6, collinear.numberOfSegments());
    }

    @Test
    public void fileHorizontal5() {
        Point[] points = readFile("horizontal5.txt");
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        assertEquals(5, collinear.numberOfSegments());
    }

    @Test
    public void fileHorizontal25() {
        Point[] points = readFile("horizontal25.txt");
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        assertEquals(25, collinear.numberOfSegments());
    }

    @Test
    public void fileHorizontal50() {
        Point[] points = readFile("horizontal50.txt");
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        assertEquals(50, collinear.numberOfSegments());
    }

    @Test
    public void fileHorizontal75() {
        Point[] points = readFile("horizontal75.txt");
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        assertEquals(75, collinear.numberOfSegments());
    }

    @Test
    public void fileHorizontal100() {
        Point[] points = readFile("horizontal100.txt");
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        assertEquals(100, collinear.numberOfSegments());
    }

    @Test
    public void fileVertical5() {
        Point[] points = readFile("vertical5.txt");
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        assertEquals(5, collinear.numberOfSegments());
    }

    @Test
    public void fileVertical25() {
        Point[] points = readFile("vertical25.txt");
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        assertEquals(25, collinear.numberOfSegments());
    }

    @Test
    public void fileVertical50() {
        Point[] points = readFile("vertical50.txt");
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        assertEquals(50, collinear.numberOfSegments());
    }

    @Test
    public void fileVertical100() {
        Point[] points = readFile("vertical100.txt");
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        assertEquals(100, collinear.numberOfSegments());
    }

}