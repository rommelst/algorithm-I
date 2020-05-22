import edu.princeton.cs.algs4.In;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PercolationTest {


    @Test
    public void constructorWhenArgumentIsNotPositiveExpectsIllegalArgumentException() {

        // Assert
        IllegalArgumentException thrown01 = assertThrows( IllegalArgumentException.class, () -> {
            // Arrange
            // Action
            Percolation percolation = new Percolation(0);
        });

        IllegalArgumentException thrown02 = assertThrows( IllegalArgumentException.class, () -> {
            // Arrange
            // Action
            Percolation percolation = new Percolation(-1);
        });

        assertEquals("Argument must be positive.", thrown01.getMessage());
        assertEquals("Argument must be positive.", thrown02.getMessage());

    }

    @Test
    public void openWhenArgumentIsNotPositiveExpectsIllegalArgumentException() {


        // Arrange
        final int NEGATIVE_VALUE = -1;
        final int NORMAL_VALUE = 1;
        final int ZERO = 0;
        Percolation percolation = new Percolation(2);

        // Assert
        IllegalArgumentException thrown01 = assertThrows( IllegalArgumentException.class, () -> {
            // Action
            percolation.open(ZERO, NORMAL_VALUE);
        });

        // Assert
        IllegalArgumentException thrown02 = assertThrows( IllegalArgumentException.class, () -> {
            // Action
            percolation.open(NORMAL_VALUE, ZERO);
        });

        // Assert
        IllegalArgumentException thrown03 = assertThrows( IllegalArgumentException.class, () -> {
            // Action
            percolation.open(NEGATIVE_VALUE, NORMAL_VALUE);
        });

        // Assert
        IllegalArgumentException thrown04 = assertThrows( IllegalArgumentException.class, () -> {
            // Action
            percolation.open(NORMAL_VALUE, NEGATIVE_VALUE);
        });

        assertEquals("Argument must be positive.", thrown01.getMessage());
        assertEquals("Argument must be positive.", thrown02.getMessage());
        assertEquals("Argument must be positive.", thrown03.getMessage());
        assertEquals("Argument must be positive.", thrown04.getMessage());

    }

    @Test
    public void openWhenArgumentIsBiggerThanDimensionExpectsIllegalArgumentException() {


        // Arrange
        final int DIMENSION_VALUE = 10;
        final int OUTNUMBERED_VALUE = DIMENSION_VALUE + 1;
        final int NORMAL_VALUE = 1;
        Percolation percolation = new Percolation(DIMENSION_VALUE);

        // Assert
        IllegalArgumentException thrown01 = assertThrows( IllegalArgumentException.class, () -> {
            // Action
            percolation.open(OUTNUMBERED_VALUE, NORMAL_VALUE);
        });

        // Assert
        IllegalArgumentException thrown02 = assertThrows( IllegalArgumentException.class, () -> {
            // Action
            percolation.open( NORMAL_VALUE, OUTNUMBERED_VALUE);
        });

        assertEquals("Argument must be smaller then defined dimension.", thrown01.getMessage());
        assertEquals("Argument must be smaller then defined dimension.", thrown02.getMessage());

    }

    @Test
    public void isOpenWhenArgumentIsNotPositiveExpectsIllegalArgumentException() {


        // Arrange
        final int NEGATIVE_VALUE = -1;
        final int NORMAL_VALUE = 1;
        final int ZERO = 0;
        Percolation percolation = new Percolation(2);

        // Assert
        IllegalArgumentException thrown01 = assertThrows( IllegalArgumentException.class, () -> {
            // Action
            percolation.isOpen(ZERO, NORMAL_VALUE);
        });

        // Assert
        IllegalArgumentException thrown02 = assertThrows( IllegalArgumentException.class, () -> {
            // Action
            percolation.isOpen(NORMAL_VALUE, ZERO);
        });

        // Assert
        IllegalArgumentException thrown03 = assertThrows( IllegalArgumentException.class, () -> {
            // Action
            percolation.isOpen(NEGATIVE_VALUE, NORMAL_VALUE);
        });

        // Assert
        IllegalArgumentException thrown04 = assertThrows( IllegalArgumentException.class, () -> {
            // Action
            percolation.isOpen(NORMAL_VALUE, NEGATIVE_VALUE);
        });

        assertEquals("Argument must be positive.", thrown01.getMessage());
        assertEquals("Argument must be positive.", thrown02.getMessage());
        assertEquals("Argument must be positive.", thrown03.getMessage());
        assertEquals("Argument must be positive.", thrown04.getMessage());

    }

    @Test
    public void isOpenWhenArgumentIsNotOpenedExpectsFalse() {

        // Arrange
        Percolation percolation = new Percolation(2);

        // Action
        boolean value = percolation.isOpen(1,1);

        // Assert
        assertFalse(value);

    }

    @Test
    public void isOpenWhenArgumentIsOpenedExpectsTrue() {

        // Arrange
        Percolation percolation = new Percolation(2);
        percolation.open(1,1);

        // Action
        boolean value = percolation.isOpen(1,1);

        // Assert
        assertTrue(value);

    }

    @Test
    public void isOpenWhenArgumentIsBiggerThanDimensionExpectsIllegalArgumentException() {


        // Arrange
        final int DIMENSION_VALUE = 10;
        final int OUTNUMBERED_VALUE = DIMENSION_VALUE + 1;
        final int NORMAL_VALUE = 1;
        Percolation percolation = new Percolation(DIMENSION_VALUE);

        // Assert
        IllegalArgumentException thrown01 = assertThrows( IllegalArgumentException.class, () -> {
            // Action
            percolation.isOpen(OUTNUMBERED_VALUE, NORMAL_VALUE);
        });

        // Assert
        IllegalArgumentException thrown02 = assertThrows( IllegalArgumentException.class, () -> {
            // Action
            percolation.isOpen( NORMAL_VALUE, OUTNUMBERED_VALUE);
        });

        assertEquals("Argument must be smaller then defined dimension.", thrown01.getMessage());
        assertEquals("Argument must be smaller then defined dimension.", thrown02.getMessage());

    }

    @Test
    public void isOpenWhenArgumentIsOnEdgeExpectsTrue() {


        // Arrange
        final int DIMENSION_VALUE = 10;
        final int NORMAL_VALUE = 10;
        Percolation percolation = new Percolation(DIMENSION_VALUE);

        // Action
        percolation.open(NORMAL_VALUE, NORMAL_VALUE);
        percolation.open(NORMAL_VALUE - 1, NORMAL_VALUE);
        percolation.open(NORMAL_VALUE, NORMAL_VALUE - 1);

        // Assert
        assertTrue(percolation.isOpen(NORMAL_VALUE, NORMAL_VALUE));
        assertTrue(percolation.isOpen(NORMAL_VALUE - 1, NORMAL_VALUE));
        assertTrue(percolation.isOpen(NORMAL_VALUE, NORMAL_VALUE - 1));

    }

    @Test
    public void isFullWhenArgumentIsNotPositiveExpectsIllegalArgumentException() {

        // Arrange
        final int NEGATIVE_VALUE = -1;
        final int NORMAL_VALUE = 1;
        final int ZERO = 0;
        Percolation percolation = new Percolation(2);

        // Assert
        IllegalArgumentException thrown01 = assertThrows( IllegalArgumentException.class, () -> {
            // Action
            percolation.isFull(ZERO, NORMAL_VALUE);
        });

        // Assert
        IllegalArgumentException thrown02 = assertThrows( IllegalArgumentException.class, () -> {
            // Action
            percolation.isFull(NORMAL_VALUE, ZERO);
        });

        // Assert
        IllegalArgumentException thrown03 = assertThrows( IllegalArgumentException.class, () -> {
            // Action
            percolation.isFull(NEGATIVE_VALUE, NORMAL_VALUE);
        });

        // Assert
        IllegalArgumentException thrown04 = assertThrows( IllegalArgumentException.class, () -> {
            // Action
            percolation.isFull(NORMAL_VALUE, NEGATIVE_VALUE);
        });

        assertEquals("Argument must be positive.", thrown01.getMessage());
        assertEquals("Argument must be positive.", thrown02.getMessage());
        assertEquals("Argument must be positive.", thrown03.getMessage());
        assertEquals("Argument must be positive.", thrown04.getMessage());

    }

    @Test
    public void isFullWhenArgumentIsBiggerThanDimensionExpectsIllegalArgumentException() {

        // Arrange
        final int DIMENSION_VALUE = 10;
        final int OUTNUMBERED_VALUE = DIMENSION_VALUE + 1;
        final int NORMAL_VALUE = 1;
        Percolation percolation = new Percolation(DIMENSION_VALUE);

        // Assert
        IllegalArgumentException thrown01 = assertThrows( IllegalArgumentException.class, () -> {
            // Action
            percolation.isFull(OUTNUMBERED_VALUE, NORMAL_VALUE);
        });

        // Assert
        IllegalArgumentException thrown02 = assertThrows( IllegalArgumentException.class, () -> {
            // Action
            percolation.isFull( NORMAL_VALUE, OUTNUMBERED_VALUE);
        });

        assertEquals("Argument must be smaller then defined dimension.", thrown01.getMessage());
        assertEquals("Argument must be smaller then defined dimension.", thrown02.getMessage());

    }

    @Test
    public void isFullWhenNoSiteIsOpenedExpectsFalse() {

        // Arrange
        final int DIMENSION_VALUE = 10;
        final int NORMAL_VALUE = 1;
        Percolation percolation = new Percolation(DIMENSION_VALUE);

        // Action
        boolean value = percolation.isFull(NORMAL_VALUE, NORMAL_VALUE);

        // Assert
        assertFalse(value);
    }

    @Test
    public void isFullWhenSiteIsOnTopExpectsTrue() {

        // Arrange
        final int DIMENSION_VALUE = 10;
        final int TOP = 1;
        Percolation percolation = new Percolation(DIMENSION_VALUE);
        percolation.open(TOP, TOP);

        // Action
        boolean value = percolation.isFull(TOP, TOP);

        // Assert
        assertTrue(value);
    }

    @Test
    public void isFullWhenSiteIsOnSecondRowExpectsFalse() {

        // Arrange
        final int DIMENSION_VALUE = 10;
        final int SECOND = 2;
        Percolation percolation = new Percolation(DIMENSION_VALUE);
        percolation.open(SECOND, SECOND);

        // Action
        boolean value = percolation.isFull(SECOND, SECOND);

        // Assert
        assertFalse(value);
    }

    @Test
    public void isFullWhenSiteIsOnThirdRowConnectedToTopExpectsTrue() {

        // Arrange
        final int DIMENSION_VALUE = 10;
        Percolation percolation = new Percolation(DIMENSION_VALUE);

        // Action
        percolation.open(3, 1);
        boolean value01 = percolation.isFull(3, 1);
        percolation.open(2, 1);
        boolean value02 = percolation.isFull(2, 1);
        percolation.open(1, 1);
        boolean value03 = percolation.isFull(1, 1);

        // Assert
        assertFalse(value01);
        assertFalse(value02);
        assertTrue(value03);

    }

    @Test
    public void isFullWhenTwoStringAreConnectedToBottomAndOnlyOneConnectedToTopExpectsNoBackWash() {

        // Arrange
        final int DIMENSION_VALUE = 5;
        final int ROW_ON_BOTTOM_NOT_CONNECTED_TOP = 5;
        final int COL_ON_BOTTOM_NOT_CONNECTED_TOP = 1;
        Percolation percolation = new Percolation(DIMENSION_VALUE);
        // String that does not percolates
        percolation.open(5, 1);
        // String that percolates
        {
            percolation.open(1, 4);
            percolation.open(2, 4);
            percolation.open(3, 4);
            percolation.open(4, 4);
            percolation.open(5, 4);
        }

        // Action
        boolean value = percolation.isFull(ROW_ON_BOTTOM_NOT_CONNECTED_TOP, COL_ON_BOTTOM_NOT_CONNECTED_TOP);

        // Assert
        assertFalse(value);
    }

    @Test
    public void isFullWhenMiddleSiteIsConnectedToTopSiteExpectsTrue() {

        // Arrange
        final int DIMENSION_VALUE = 10;
        final int COLUMN_TO_OPEN = DIMENSION_VALUE / 2 + 1;
        Percolation percolation = new Percolation(DIMENSION_VALUE);

        for ( int row = 1; row <= DIMENSION_VALUE - 2; row++) {

            // Arrange
            percolation.open(row, COLUMN_TO_OPEN);

            // Action
            boolean value = percolation.isFull(row,COLUMN_TO_OPEN);

            // Assert
            assertTrue(value, "When processing row["+row+"] col["+COLUMN_TO_OPEN+"]");
        }



    }

    @Test
    public void numberOfOpenSitesWhenNoSiteIsOpenedExpectsZero() {

        // Arrange
        Percolation percolation = new Percolation(2);

        // Action
        int value = percolation.numberOfOpenSites();

        // Assert
        assertEquals(0, value);

    }

    @Test
    public void numberOfOpenSitesWhenManySitesAreOpenedExpectsNumberOfOpenedSites() {

        // Arrange
        final int DIMENSION_VALUE = 10;
        Percolation percolation = new Percolation(DIMENSION_VALUE);
        int opened = 0;
        for ( int row = 1; row <= DIMENSION_VALUE/2; row++)
        {
            for (int col = 1; col <= DIMENSION_VALUE/2; col++) {

                // Action
                percolation.open(row,col);
                opened++;
                int value = percolation.numberOfOpenSites();

                // Assert
                assertEquals(opened, value, "When processing row["+row+"] col["+col+"]");
            }
        }
    }

    @Test
    public void numberOfOpenSitesWhenTheSameSiteIsOpenedTwiceExpects1() {

        // Arrange
        final int DIMENSION_VALUE = 10;
        Percolation percolation = new Percolation(DIMENSION_VALUE);
        percolation.open(2,2);
        percolation.open(2,2);

        // Act
        int value = percolation.numberOfOpenSites();

        // Assert
        assertEquals(1, value);

    }

    @Test
    public void percolatesWhenNoSiteIsOpenedExpectsFalse() {

        // Arrange
        final int DIMENSION_VALUE = 10;
        Percolation percolation = new Percolation(DIMENSION_VALUE);

        // Action
        boolean value = percolation.percolates();

        // Assert
        assertFalse(value);

    }

    @Test
    public void percolatesWhenAllVerticalSitesAreOpenedExpectsTrue() {

        // Arrange
        final int DIMENSION_VALUE = 10;
        final int COLUMN_TO_OPEN = DIMENSION_VALUE / 2 + 1;
        Percolation percolation = new Percolation(DIMENSION_VALUE);
        for ( int row = 1; row <= DIMENSION_VALUE; row++) {
            percolation.open(row,COLUMN_TO_OPEN);
        }

        // Action
        Boolean value = percolation.percolates();

        // Assert
        assertTrue(value);
    }

    @Test
    public void assessmentGuideCorrectnessSectionOfPercolateTest1Input6() {

        // Arrange
        In in = new In("input6.txt");
        int n = in.readInt();
        boolean value = false;

        // Act
        Percolation percolate = new Percolation(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            percolate.open(i, j);
            if ( percolate.numberOfOpenSites() == 18 )
                value = percolate.isFull(1,6);
        }

        // Assert
        assertTrue(percolate.percolates());
        assertTrue(value);
        assertEquals(18, percolate.numberOfOpenSites());

    }

    @Test
    public void assessmentGuideCorrectnessSectionOfPercolateTest1Input8() {

        // Arrange
        In in = new In("input8.txt");
        int n = in.readInt();

        // Act
        Percolation percolate = new Percolation(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            percolate.open(i, j);
        }

        // Assert
        assertTrue(percolate.percolates());
        assertEquals(34, percolate.numberOfOpenSites());

    }

    @Test
    public void assessmentGuideCorrectnessSectionOfPercolateTest1Input8No() {

        // Arrange
        In in = new In("input8-no.txt");
        int n = in.readInt();
        boolean value = false;

        // Act
        Percolation percolate = new Percolation(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            percolate.open(i, j);
            if ( percolate.numberOfOpenSites() == 32 )
                value = percolate.isFull(1,3);
        }

        // Assert
        assertFalse(percolate.percolates());
        assertTrue(value);
        assertTrue(percolate.isFull(1,3));
        assertEquals(33, percolate.numberOfOpenSites());


    }

    @Test
    public void assessmentGuideCorrectnessSectionOfPercolateTest1Input10No() {

        // Arrange
        In in = new In("input10-no.txt");
        int n = in.readInt();
        boolean value = false;

        // Act
        Percolation percolate = new Percolation(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            percolate.open(i, j);
            if ( percolate.numberOfOpenSites() == 25 )
                value = percolate.isFull(1,10);
        }

        // Assert
        assertFalse(percolate.percolates());
        assertTrue(value);
        assertTrue(percolate.isFull(1,10));
        assertEquals(55, percolate.numberOfOpenSites());
    }

    @Test
    public void assessmentGuideCorrectnessSectionOfPercolateTest3Input1() {

        // Arrange
        In in = new In("input1.txt");
        int n = in.readInt();

        // Act
        Percolation percolate = new Percolation(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            percolate.open(i, j);
        }

        // Assert
        assertTrue(percolate.percolates());
        assertEquals(1, percolate.numberOfOpenSites());
    }

    @Test
    public void assessmentGuideCorrectnessSectionOfPercolateTest4Snake13() {

        // Arrange
        In in = new In("snake13.txt");
        int n = in.readInt();
        boolean value = false;

        // Act
        Percolation percolate = new Percolation(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            percolate.open(i, j);
            if ( percolate.numberOfOpenSites() == 85 )
                value = percolate.percolates();
        }

        // Assert
        assertTrue(value);
        assertEquals(85, percolate.numberOfOpenSites());
    }

    @Test
    public void assessmentGuideCorrectnessSectionOfPercolateTest4Snake101() {

        // Arrange
        In in = new In("snake101.txt");
        int n = in.readInt();
        boolean value = false;

        // Act
        Percolation percolate = new Percolation(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            percolate.open(i, j);
            if ( percolate.numberOfOpenSites() == 5101 )
                value = percolate.percolates();
        }

        // Assert
        assertTrue(value);
        assertEquals(5101, percolate.numberOfOpenSites());
    }

    @Test
    public void assessmentGuideCorrectnessSectionOfPercolateTest16Input20() {

        // Arrange
        In in = new In("input20.txt");
        int n = in.readInt();
        boolean value01 = false;
        boolean value02 = false;

        // Act
        Percolation percolate = new Percolation(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            percolate.open(i, j);
            if ( percolate.numberOfOpenSites() == 155 )
                value01 = percolate.isFull(1,5);
            if ( percolate.numberOfOpenSites() == 205 )
                value02 = percolate.percolates();
        }

        // Assert
        assertTrue(value01);
        assertTrue(value02);
        assertEquals(250, percolate.numberOfOpenSites());
    }

    @Test
    public void assessmentGuideCorrectnessSectionOfPercolateTest16Input10() {

        // Arrange
        In in = new In("input10.txt");
        int n = in.readInt();
        boolean value = false;

        // Act
        Percolation percolate = new Percolation(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            percolate.open(i, j);
            if ( percolate.numberOfOpenSites() == 25 )
                value = percolate.isFull(1,10);
        }

        // Assert
        assertTrue(value);
        assertEquals(56, percolate.numberOfOpenSites());
    }

    @Test
    public void assessmentGuideCorrectnessSectionOfPercolateTest16Input50() {

        // Arrange
        In in = new In("input50.txt");
        int n = in.readInt();
        boolean value01 = false;
        boolean value02 = false;

        // Act
        Percolation percolate = new Percolation(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            percolate.open(i, j);
            if ( percolate.numberOfOpenSites() == 694 )
                value01 = percolate.isFull(1,46);
            if ( percolate.numberOfOpenSites() == 1089 )
                value02 = percolate.isFull(1,9);
        }

        // Assert
        assertTrue(value01);
        assertTrue(value02);
        assertEquals(1412, percolate.numberOfOpenSites());
    }

    @Test
    public void assessmentGuideCorrectnessSectionOfPercolateTest16Jerry47() {

        // Arrange
        In in = new In("jerry47.txt");
        int n = in.readInt();
        boolean value01 = false;
        boolean value02 = false;

        // Act
        Percolation percolate = new Percolation(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            percolate.open(i, j);
            if ( percolate.numberOfOpenSites() == 622 )
                value01 = percolate.isFull(1,12);
            if ( percolate.numberOfOpenSites() == 1076 )
                value02 = percolate.percolates();
        }

        // Assert
        assertTrue(value01);
        assertTrue(value02);
        assertEquals(1476, percolate.numberOfOpenSites());
    }

    @Test
    public void assessmentGuideCorrectnessSectionOfPercolateTest16Sedgewick60() {

        // Arrange
        In in = new In("sedgewick60.txt");
        int n = in.readInt();
        boolean value01 = false;
        boolean value02 = false;
        boolean value03 = false;

        // Act
        Percolation percolate = new Percolation(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            percolate.open(i, j);
            if ( percolate.numberOfOpenSites() == 381 )
                value01 = percolate.isFull(1,12);
            if ( percolate.numberOfOpenSites() == 851 )
                value02 = percolate.isFull(1,46);
            if ( percolate.numberOfOpenSites() == 1578 )
                value03 = percolate.percolates();
        }

        // Assert
        assertTrue(value01);
        assertTrue(value02);
        assertTrue(value03);
        assertEquals(2408, percolate.numberOfOpenSites());
    }

    @Test
    public void assessmentGuideCorrectnessSectionOfPercolateTest16Wayne98() {

        // Arrange
        In in = new In("wayne98.txt");
        int n = in.readInt();
        boolean value01 = false;
        boolean value02 = false;

        // Act
        Percolation percolate = new Percolation(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            percolate.open(i, j);
            if ( percolate.numberOfOpenSites() == 760 )
                value01 = percolate.isFull(1,7);
            if ( percolate.numberOfOpenSites() == 3852 )
                value02 = percolate.percolates();
        }

        // Assert
        assertTrue(value01);
        assertTrue(value02);
        assertEquals(5079, percolate.numberOfOpenSites());
    }

    @Test
    public void assessmentGuideCorrectnessSectionOfPercolateTest17Input3() {

        // Arrange
        In in = new In("input3.txt");
        int n = in.readInt();
        boolean value = false;

        // Act
        Percolation percolate = new Percolation(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            percolate.open(i, j);
            if ( percolate.numberOfOpenSites() == 4 )
                value = percolate.percolates();
        }

        // Assert
        assertTrue(value);
        assertEquals(6, percolate.numberOfOpenSites());
    }

    @Test
    public void assessmentGuideCorrectnessSectionOfPercolateTest17Input4() {

        // Arrange
        In in = new In("input4.txt");
        int n = in.readInt();
        boolean value01 = false;
        boolean value02 = false;

        // Act
        Percolation percolate = new Percolation(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            percolate.open(i, j);
            if ( percolate.numberOfOpenSites() == 4 )
                value01 = percolate.isFull(1,1);
            if ( percolate.numberOfOpenSites() == 5 )
                value02 = percolate.percolates();
        }

        // Assert
        assertTrue(value01);
        assertTrue(value02);
        assertEquals(8, percolate.numberOfOpenSites());
    }

    @Test
    public void assessmentGuideCorrectnessSectionOfPercolateTest17Input7() {

        // Arrange
        In in = new In("input7.txt");
        int n = in.readInt();
        boolean value01 = false;
        boolean value02 = false;

        // Act
        Percolation percolate = new Percolation(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            percolate.open(i, j);
            if ( percolate.numberOfOpenSites() == 13 )
                value01 = percolate.percolates();
            if ( percolate.numberOfOpenSites() == 16 )
                value02 = percolate.isFull(1,1);
        }

        // Assert
        assertTrue(value01);
        assertTrue(value02);
        assertEquals(16, percolate.numberOfOpenSites());
    }


}