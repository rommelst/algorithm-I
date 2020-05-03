import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    public void constructorWhenParameterIsValidExpectsNothing(){
        // Arrange
        int n = 3;
        int[][] array = new int[n][n];

        // Assert
        assertDoesNotThrow( () -> {

            // Act
            Board board = new Board(array);

        });
    }

    @Test
    public void constructorWhenParameterIsInvalidExpectsIllegalArgumentException(){
        // Assert
        IllegalArgumentException thrown = assertThrows( IllegalArgumentException.class, () -> {

            // Arrange
            // Act
            Board board = new Board(null);

        });
        assertEquals("Null is not accepted.", thrown.getMessage());
    }

    @Test
    public void toStringWhenBoardIsMinimalExpectsMinimalStringRepresentation(){

        // Arrange
        int[][] array = {{0}};

        // Act
        Board board = new Board(array);

        // Assert
        assertEquals("1\n 0 \n", board.toString());
    }

    @Test
    public void toStringWhenBoardIs2x2Expects2x2StringRepresentation(){

        // Arrange
        int[][] array = {
            {0,1},
            {2,3}
        };

        // Act
        Board board = new Board(array);

        // Assert
        assertEquals("2\n 0  1 \n 2  3 \n", board.toString());
    }

    @Test
    public void dimensionWhenBoardIs1x1Expects1(){

        // Arrange
        int n = 1;
        int[][] array = new int[n][n];

        // Act
        Board board = new Board(array);

        // Assert
        assertEquals(1, board.dimension());
    }

    @Test
    public void dimensionWhenBoardIs2x2Expects2(){

        // Arrange
        int n = 2;
        int[][] array = new int[n][n];

        // Act
        Board board = new Board(array);

        // Assert
        assertEquals(2, board.dimension());
    }

    @Test
    public void dimensionWhenBoardIs128x128Expects128(){

        // Arrange
        int n = 128;
        int[][] array = new int[n][n];

        // Act
        Board board = new Board(array);

        // Assert
        assertEquals(128, board.dimension());
    }

    @Test
    public void equalsWhenBoard1x1IsEqualExpectsTrue(){

        // Arrange
        int[][] array = {{0}};

        // Act
        Board me = new Board(array);
        Board him = new Board(array);

        // Assert
        assertTrue(me.equals(him));
    }

    @Test
    public void equalsWhenOtherIsNullExpectsFalse(){

        // Arrange
        int[][] array = {{0}};

        // Act
        Board me = new Board(array);

        // Assert
        assertFalse(me.equals(null));
    }

    @Test
    public void equalsWhenOtherIsMeExpectsTrue(){

        // Arrange
        int[][] array = {{0}};

        // Act
        Board me = new Board(array);

        // Assert
        assertTrue(me.equals(me));
    }

    @Test
    public void equalsWhenOtherIsNotSameClassExpectsFalse(){

        // Arrange
        int[][] array = {{0}};

        // Act
        Board me = new Board(array);

        // Assert
        assertFalse(me.equals(""));
    }

    @Test
    public void equalsWhenOtherHasDifferentDimensionExpectsFalse(){

        // Arrange
        int[][] array = new int[2][2];
        int[][] array2 = new int[3][3];

        // Act
        Board me = new Board(array);
        Board him = new Board(array2);

        // Assert
        assertFalse(me.equals(him));
    }

    @Test
    public void equalsWhenOtherHasDifferentValuesExpectsFalse(){

        // Arrange
        int[][] array1 = { {0,1}, {2,3} };
        int[][] array2 = { {0,2}, {1,3} };

        // Act
        Board me = new Board(array1);
        Board him = new Board(array2);

        // Assert
        assertFalse(me.equals(him));
    }

    @Test
    public void equalsWhenOtherHasEqualValuesExpectsTrue(){

        // Arrange
        int[][] array1 = { {0,1}, {2,3} };

        // Act
        Board me = new Board(array1);
        Board him = new Board(array1);

        // Assert
        assertTrue(me.equals(him));
    }

    @Test
    public void neighborsWhenIn3x3BlankIsTopLeftExpects2(){

        // Arrange
        int[][] array1 = { {0,1,2}, {3,4,5}, {6,7,8}};

        // Act
        Board board = new Board(array1);
        Iterable<Board> neighbors = board.neighbors();

        // Assert
        assertEquals(2, ((Collection<?>) neighbors).size() );
    }

    @Test
    public void neighborsWhenIn3x3BlankIsTopCenterExpects3(){

        // Arrange
        int[][] array1 = { {1,0,2}, {3,4,5}, {6,7,8}};

        // Act
        Board board = new Board(array1);
        Iterable<Board> neighbors = board.neighbors();

        // Assert
        assertEquals(3, ((Collection<?>) neighbors).size() );
    }

    @Test
    public void neighborsWhenIn3x3BlankIsTopRightExpects2(){

        // Arrange
        int[][] array1 = { {1,2,0}, {3,4,5}, {6,7,8}};

        // Act
        Board board = new Board(array1);
        Iterable<Board> neighbors = board.neighbors();

        // Assert
        assertEquals(2, ((Collection<?>) neighbors).size() );
    }

    @Test
    public void neighborsWhenIn3x3BlankIsMiddleLeftExpects3(){

        // Arrange
        int[][] array1 = { {1,2,3}, {0,4,5}, {6,7,8}};

        // Act
        Board board = new Board(array1);
        Iterable<Board> neighbors = board.neighbors();

        // Assert
        assertEquals(3, ((Collection<?>) neighbors).size() );
    }

    @Test
    public void neighborsWhenIn3x3BlankIsMiddleCenterExpects4(){

        // Arrange
        int[][] array1 = { {1,2,3}, {4,0,5}, {6,7,8}};

        // Act
        Board board = new Board(array1);
        Iterable<Board> neighbors = board.neighbors();

        // Assert
        assertEquals(4, ((Collection<?>) neighbors).size() );
    }

    @Test
    public void neighborsWhenIn3x3BlankIsMiddleRightExpects3(){

        // Arrange
        int[][] array1 = { {1,2,3}, {4,5,0}, {6,7,8}};

        // Act
        Board board = new Board(array1);
        Iterable<Board> neighbors = board.neighbors();

        // Assert
        assertEquals(3, ((Collection<?>) neighbors).size() );
    }

    @Test
    public void neighborsWhenIn3x3BlankIsBottomLeftExpects3(){

        // Arrange
        int[][] array1 = { {1,2,3}, {4,5,6}, {0,7,8}};

        // Act
        Board board = new Board(array1);
        Iterable<Board> neighbors = board.neighbors();

        // Assert
        assertEquals(2, ((Collection<?>) neighbors).size() );
    }

    @Test
    public void neighborsWhenIn3x3BlankIsBottomCenterExpects3(){

        // Arrange
        int[][] array1 = { {1,2,3}, {4,5,6}, {7,0,8}};

        // Act
        Board board = new Board(array1);
        Iterable<Board> neighbors = board.neighbors();

        // Assert
        assertEquals(3, ((Collection<?>) neighbors).size() );
    }

    @Test
    public void neighborsWhenIn3x3BlankIsBottomRightExpects2(){

        // Arrange
        int[][] array1 = { {1,2,3}, {4,5,6}, {7,8,0}};

        // Act
        Board board = new Board(array1);
        Iterable<Board> neighbors = board.neighbors();

        // Assert
        assertEquals(2, ((Collection<?>) neighbors).size() );
    }

    @Test
    public void neighborsWhenAreInSpecExpectsNeighborsLikeSpec(){

        // Arrange
        int[][] array1 = { {1,0,3}, {4,2,5}, {7,8,6}};

        // Act
        Board board = new Board(array1);
        Iterable<Board> neighbors = board.neighbors();

        // Assert
        assertEquals("[3\n" +
                " 1  2  3 \n" +
                " 4  0  5 \n" +
                " 7  8  6 \n" +
                ", 3\n" +
                " 0  1  3 \n" +
                " 4  2  5 \n" +
                " 7  8  6 \n" +
                ", 3\n" +
                " 1  3  0 \n" +
                " 4  2  5 \n" +
                " 7  8  6 \n" +
                "]", neighbors.toString() );
    }

    @Test
    public void manhattanDistanceWhenNoSquareDisplacedExpects0(){

        // Arrange
        int[][] array1 = { {1,2,3}, {4,5,6}, {7,8,0}};

        // Act
        Board board = new Board(array1);

        // Assert
        assertEquals(0, board.manhattan() );
    }

    @Test
    public void manhattanDistanceWhenLastIsMovedRightExpects1(){

        // Arrange
        int[][] array1 = { {1,2,3}, {4,5,6}, {7,0,8}};

        // Act
        Board board = new Board(array1);

        // Assert
        assertEquals(1, board.manhattan() );
    }

    @Test
    public void manhattanDistanceWhenBoardIsLikeSpecExpectsLikeSpec(){

        // Arrange
        int[][] array1 = { {8,1,3}, {4,0,2}, {7,6,5}};

        // Act
        Board board = new Board(array1);

        // Assert
        assertEquals(10, board.manhattan() );
    }

    @Test
    public void hammingDistanceWhenNoSquareDisplacedExpects0(){

        // Arrange
        int[][] array1 = { {1,2,3}, {4,5,6}, {7,8,0}};

        // Act
        Board board = new Board(array1);

        // Assert
        assertEquals(0, board.hamming() );
    }

    @Test
    public void hammingDistanceWhenLastIsMovedRightExpects1(){

        // Arrange
        int[][] array1 = { {1,2,3}, {4,5,6}, {7,0,8}};

        // Act
        Board board = new Board(array1);

        // Assert
        assertEquals(1, board.hamming() );
    }

    @Test
    public void hammingDistanceWhenAllSquaresAreDisplacedExpects8(){

        // Arrange
        int[][] array1 = { {7,0,8}, {1,2,3}, {4,5,6} };

        // Act
        Board board = new Board(array1);

        // Assert
        assertEquals(8, board.hamming() );
    }

    @Test
    public void isGoalWhenAllSquaresAreCorrectExpectsTrue(){

        // Arrange
        int[][] array1 = { {1,2,3}, {4,5,6}, {7,8, 0}};

        // Act
        Board board = new Board(array1);

        // Assert
        assertTrue(board.isGoal());
    }

    @Test
    public void twinExpectsDifferentBoard(){

        // Arrange
        int[][] array1 = { {1,2,3}, {4,5,6}, {7,8,0} };

        // Act
        Board board = new Board(array1);
        Board twinBoard = board.twin();

        // Assert
        assertFalse(board.equals(twinBoard));
    }

}