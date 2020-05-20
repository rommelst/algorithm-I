import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;

    private final int gridSideSize;
    private final double[] results;
    private final int trials;
    private int total = 0;
    private double mean = 0.0;
    private double stddev = 0.0;


    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        this.trials = trials;
        this.gridSideSize = n;
        results = new double[trials];
        run();
    }

    private void run() {
        for (int i = 0; i < trials; i++) {
            int c = monteCarloSimulation();
//            StdOut.println("PercolationStats(int n{" + gridSideSize + "}, int trials{"+trials+"}) try:"+(i + 1) + "  c:"+c);
            results[i] = (double) c/gridSideSize/gridSideSize;
            total += c;
        }
    }

    private int monteCarloSimulation() {
        int qty = 0;
        Percolation percolation = new Percolation(gridSideSize);
        while (!percolation.percolates()) {
            int index = StdRandom.uniform(gridSideSize * gridSideSize);
            int row = index / gridSideSize + 1;
            int col = index % gridSideSize + 1;
            if (percolation.isOpen(row, col)) continue;
            qty++;
            percolation.open(row, col);
        }
        return qty;
    }

    public  double mean() {
        if (mean == 0) {
            mean = StdStats.mean(results);
        }
        return mean;
    }

    public double stddev() {
        if (1 == trials) return Double.NaN;
        if (stddev == 0) {
            stddev = StdStats.stddev(results);
        }
        return stddev;
    }

    public double confidenceLo() {
        return (mean() - (CONFIDENCE_95 * stddev()) / Math.sqrt(trials));
    }

    public double confidenceHi() {
        return (mean() + (CONFIDENCE_95 * stddev()) / Math.sqrt(trials));
    }

    public static void main(String[] args) {

        Stopwatch stopwatch = new Stopwatch();

        int n = StdIn.readInt();
        int t = StdIn.readInt();
        PercolationStats stat = new PercolationStats(n, t);

        // Print out the results
        StdOut.println("mean                    = "+ stat.mean());
        StdOut.println("stddev                  = "+ stat.stddev());
        StdOut.println("95% confidence interval = "+ stat.confidenceLo() +", "+ stat.confidenceHi());
        StdOut.println("time = "+ stopwatch.elapsedTime());

    }

}
