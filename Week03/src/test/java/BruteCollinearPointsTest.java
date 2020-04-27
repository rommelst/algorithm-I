import edu.princeton.cs.algs4.In;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BruteCollinearPointsTest {

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

    @BeforeEach
    public void setUp() throws Exception {
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    @Test
    public void callingConstructorWhenParameterIsNullExepctsIllegalArguemntException() {

        // Arrange
        Point[] list = null;

        // Assert
        IllegalArgumentException thrown = assertThrows( IllegalArgumentException.class, () -> {

            // Act
            BruteCollinearPoints bcp = new BruteCollinearPoints(list);

        });

    }

    @Test
    public void callingConstructorWhenParameterHasNullExepctsIllegalArguemntException() {

        // Arrange
        Point[] list = new Point[10];

        // Assert
        IllegalArgumentException thrown = assertThrows( IllegalArgumentException.class, () -> {

            // Act
            BruteCollinearPoints bcp = new BruteCollinearPoints(list);

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
            BruteCollinearPoints bcp = new BruteCollinearPoints(list);

        });

    }

    @Test
    public void bcpWithOnlyOnePointExepctsZeroSize() {
        Point[] list = new Point[1];
        list[0] = new Point(0,0);
        BruteCollinearPoints bcp = new BruteCollinearPoints(list);
        assertEquals(0, bcp.numberOfSegments());
    }

    @Test
    public void fourPointsThreeSegments() {
        Point[] list = new Point[4];
        list[0] = new Point(1,1);
        list[1] = new Point(2,2);
        list[2] = new Point(3,3);
        list[3] = new Point(4,4);
        BruteCollinearPoints bcp = new BruteCollinearPoints(list);
        assertEquals(1, bcp.numberOfSegments());
    }

    @Test
    public void fourPointsNoSegment() {
        Point[] list = new Point[4];
        list[0] = new Point(1,1);
        list[1] = new Point(2,2);
        list[2] = new Point(3,3);
        list[3] = new Point(4,5);
        BruteCollinearPoints bcp = new BruteCollinearPoints(list);
        assertEquals(0, bcp.numberOfSegments());
    }

    @Test
    public void tenPointsSixSegments() {
        int i=0;

        Point[] list = new Point[10];
        list[i++] = new Point(1,1);
        list[i++] = new Point(2,2);
        list[i++] = new Point(3,3);
        list[i++] = new Point(4,4);

        list[i++] = new Point(1,2);
        list[i++] = new Point(2,4);
        list[i++] = new Point(3,6);
        list[i++] = new Point(4,8);

        list[i++] = new Point(1,8);
        list[i++] = new Point(2,12);
        BruteCollinearPoints bcp = new BruteCollinearPoints(list);
        assertEquals(2, bcp.numberOfSegments());
    }

    @Test
    public void checkSegmentsAndNumberOfSegments() {
        Point[] points = readFile("input8.txt");
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        assertEquals(collinear.segments().length, collinear.numberOfSegments());

    }

    @Test
    public void fileInput1() {
        Point[] points = readFile("input1.txt");
        BruteCollinearPoints bcp = new BruteCollinearPoints(points);
        assertEquals(0, bcp.numberOfSegments());
    }

    @Test
    public void fileInput20() {
        Point[] points = readFile("input20.txt");
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        assertEquals(21, collinear.numberOfSegments());
    }

    @Test
    public void fileEquidistante_txt() {
        Point[] points = readFile("equidistant.txt");
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        assertEquals(4, collinear.numberOfSegments());
    }

}