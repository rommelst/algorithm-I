import java.util.LinkedList;
import java.util.List;

public class Board {

    private final int dimension;
    private final int[][] squares;
    private final int noSquareRow;
    private final int noSquareCol;
    private int manhattan = -1;
    private int hamming = -1;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        if (tiles == null) throw  new IllegalArgumentException("Null is not accepted.");
        this.squares = copySquare(tiles);
        this.dimension = tiles.length;

        int r = -1;
        int c = -1;
        boolean found = false;
        for (int row = 0; !found && row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                if (squares[row][col] == 0) {
                    r = row;
                    c = col;
                    found = true;
                    break;
                }
            }
        }
        noSquareRow = r;
        noSquareCol = c;
    }

    private int[][] copySquare(int[][] array) {
        int[][] copy = new int[array.length][];
        for (int row = 0; row < array.length; row++) {
            copy[row] = array[row].clone();
        }
        return copy;
    }

    private int manhattan(int row, int col) {
        int destVal = squares[row][col] - 1;
        int targetRow = destVal / dimension;
        int targetCol = destVal % dimension;
        return Math.abs(targetRow - row) + Math.abs(targetCol - col);
    }

    private void swap(int[][] array, int r1, int c1, int r2, int c2) {
        int aux = array[r1][c1];
        array[r1][c1] = array[r2][c2];
        array[r2][c2] = aux;
    }

    // string representation of this board
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(dimension).append("\n");
        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                builder.append(String.format("%2d ", squares[row][col]));
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    // board dimension n
    public int dimension() {
        return this.dimension;
    }

    // number of tiles out of place
    public int hamming() {
        if (hamming != -1) return hamming;
        hamming = 0;
        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                if (row == noSquareRow && col == noSquareCol) continue;
                if (manhattan(row, col) != 0) {
                    hamming++;
                }
            }
        }
        return hamming;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        if (manhattan != -1) return  manhattan;
        manhattan = 0;
        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                if (row == noSquareRow && col == noSquareCol) continue;
                manhattan += manhattan(row, col);
            }
        }
        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return manhattan() == 0;
    }

    // does this board equal y?
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (this.getClass() != other.getClass()) return false;
        Board that = (Board) other;
        if (this.noSquareRow != that.noSquareRow) return false;
        if (this.noSquareCol != that.noSquareCol) return false;
        if (this.dimension != that.dimension) return false;
        for (int r = 0; r < dimension; r++)
            for (int y = 0; y < dimension; y++)
                if (this.squares[r][y] != that.squares[r][y])
                    return false;
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {

        List<Board> neighbors = new LinkedList<>();

        if (noSquareRow > 0) {
            int[][] above = copySquare(squares);
            swap(above, noSquareRow, noSquareCol, noSquareRow - 1, noSquareCol);
            neighbors.add(new Board(above));
        }
        if (noSquareRow < dimension - 1) {
            int[][] below = copySquare(squares);
            swap(below, noSquareRow, noSquareCol, noSquareRow + 1, noSquareCol);
            neighbors.add(new Board(below));
        }
        if (noSquareCol > 0) {
            int[][] left = copySquare(squares);
            swap(left, noSquareRow, noSquareCol, noSquareRow, noSquareCol - 1);
            neighbors.add(new Board(left));
        }
        if (noSquareCol < dimension - 1) {
            int[][] right = copySquare(squares);
            swap(right, noSquareRow, noSquareCol, noSquareRow, noSquareCol + 1);
            neighbors.add(new Board(right));
        }
        return neighbors;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] copy = copySquare(squares);
        int row1, row2, col1, col2;
        if (noSquareRow != dimension - 2) {
            row1 = dimension - 2;
        }
        else {
            row1 = dimension - 1;
        }
        col1 = dimension - 2;
        row2 = row1;
        col2 = col1 + 1;
        swap(copy, row1, col1, row2, col2);
        return new Board(copy);
    }

}