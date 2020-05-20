import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private static final String ERR_POSITIVE_VALUE = "Argument must be positive.";
    private static final String ERR_DIMENSION_VALUE = "Argument must be smaller then defined dimension.";

    private static final byte CLOSED = 0;
    private static final byte OPEN = 1;
    private static final byte LINKED_TOP = 2;
    private static final byte LINKED_BOTTOM = 4;

    private final WeightedQuickUnionUF wqu;
    private final byte[] grid;
    private final int dimension;
    private int opened = 0;
    private boolean percolate = false;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException(ERR_POSITIVE_VALUE);
        dimension = n;
        wqu = new WeightedQuickUnionUF(n * n);
        grid = new byte[n * n];
        for (int i = 0; i < dimension; i++) grid[i] = CLOSED;
    }

    private boolean isValidRowCol(final int row, final int col) {
        if (row > 0 && row <= dimension && col > 0 && col <= dimension) return true;
        return false;
    }

    private int index(final int row, final int col) {
        return ((row - 1) * dimension + col - 1);
    }

    private byte makeStatus(int row) {
        byte status = OPEN;
        if (row == 1) status |= LINKED_TOP;
        if (row == dimension) status |= LINKED_BOTTOM;
        if (status != OPEN) status ^= OPEN;
        return  status;
    }

    private boolean percolateStatus(int rootIndex) {
        if (percolate) return true;
        if (grid[rootIndex] == (LINKED_TOP | LINKED_BOTTOM)) return true;
        return false;
    }

    private void updateStatus(int rootIndex01, int rootIndex02) {
        byte newStatus = (byte) (grid[rootIndex01] | grid[rootIndex02]); // Merge both status
        newStatus |= OPEN;
        newStatus ^= OPEN;                                          // Remove open status
        if (newStatus != CLOSED) {
            grid[rootIndex01] = newStatus;
            grid[rootIndex02] = newStatus;
        }
    }

    /*
     * update status must to occur before union
     * since it will get status from both canonical
     * element which will be only one after the union.
     * index01 -> First site is the openning one then Root and site are the same
     * */
    private int connect(final int index01, final int index02) {
        if (grid[index01] == CLOSED) return index01;
        if (grid[index02] == CLOSED) return index01;
        int rootIndex02 = wqu.find(index02);
        updateStatus(index01, rootIndex02);
        wqu.union(index01, rootIndex02);
        return  wqu.find(index01);
    }

    private int connectNeighbors(int row, int col) {

        // Connect with neighbors
        int index = index(row, col);

        // TOP
        if (isValidRowCol(row - 1, col)) index = connect(index, index(row - 1, col));

        // LEFT
        if (isValidRowCol(row, col - 1)) index = connect(index, index(row, col - 1));

        // RIGHT
        if (isValidRowCol(row, col + 1)) index = connect(index, index(row, col + 1));

        // BOTTOM
        if (isValidRowCol(row + 1, col)) index = connect(index, index(row + 1, col));

        return index;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0) throw new IllegalArgumentException(ERR_POSITIVE_VALUE);
        if (col <= 0) throw new IllegalArgumentException(ERR_POSITIVE_VALUE);
        if (row > dimension) throw new IllegalArgumentException(ERR_DIMENSION_VALUE);
        if (col > dimension) throw new IllegalArgumentException(ERR_DIMENSION_VALUE);

        int index = index(row, col);
        if (grid[index] != CLOSED) return;

        // Some status
        opened++;
        grid[index] = makeStatus(row);

        // Dynamic connection
        int rootIndex = connectNeighbors(row, col);
        percolate = percolateStatus(rootIndex);

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0) throw new IllegalArgumentException(ERR_POSITIVE_VALUE);
        if (col <= 0) throw new IllegalArgumentException(ERR_POSITIVE_VALUE);
        if (row > dimension) throw new IllegalArgumentException(ERR_DIMENSION_VALUE);
        if (col > dimension) throw new IllegalArgumentException(ERR_DIMENSION_VALUE);

        int index = index(row, col);
        return grid[index] != CLOSED;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0) throw new IllegalArgumentException(ERR_POSITIVE_VALUE);
        if (col <= 0) throw new IllegalArgumentException(ERR_POSITIVE_VALUE);
        if (row > dimension) throw new IllegalArgumentException(ERR_DIMENSION_VALUE);
        if (col > dimension) throw new IllegalArgumentException(ERR_DIMENSION_VALUE);
        int rootIndex = wqu.find(index(row, col));
        return (grid[rootIndex] & LINKED_TOP) == LINKED_TOP;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return opened;
    }
   
    // does the system percolate?
    public boolean percolates() {
        return percolate;
    }
   
    //    // test client (optional)
    //    public static void main(String[] args)
}
