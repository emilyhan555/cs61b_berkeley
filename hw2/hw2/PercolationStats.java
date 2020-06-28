package hw2;

import edu.princeton.cs.introcs.StdStats;
import edu.princeton.cs.introcs.StdRandom;

public class PercolationStats {
    private double[] f;
    private int T;

    public PercolationStats(int N, int T, PercolationFactory pf) {   // perform T independent experiments on an N-by-N grid
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        f = new double[T];
        T = T;
        double fraction;
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int row = StdRandom.uniform(0, N);
                int col = StdRandom.uniform(0, N);
                p.open(row, col);
            }
            fraction = p.numberOfOpenSites() / (N * N);
            f[i] = fraction;
        }
    }
    public double mean() {                                           // sample mean of percolation threshold
        return StdStats.mean(f);
    }

    public double stddev() {                                         // sample standard deviation of percolation threshold
        return StdStats.stddev(f);
    }

    public double confidenceLow() {                                  // low endpoint of 95% confidence interval
        double mean = mean();
        double stddev = stddev();
        return mean - (1.96 * stddev) / Math.sqrt(T);
    }

    public double confidenceHigh() {                                 // high endpoint of 95% confidence interval
        double mean = mean();
        double stddev = stddev();
        return mean + (1.96 * stddev) / Math.sqrt(T);
    }
}
