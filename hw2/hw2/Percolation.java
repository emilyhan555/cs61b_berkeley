package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int top;
    private int bottom;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufbw;
    private int bwtop;
    private int gridSize;
    private int numofOpenSites;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        grid = new boolean[N][N];
        top = N * N;
        bottom = N * N + 1;
        uf = new WeightedQuickUnionUF(N * N + 2);
        ufbw = new WeightedQuickUnionUF(N * N + 1);
        bwtop = N * N;
        gridSize = N;
        numofOpenSites = 0;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 0 || row > gridSize - 1 || col < 0 || col > gridSize - 1) {
            throw new IndexOutOfBoundsException();
        }
        if (isOpen(row, col)) {
            return;
        } else {
            grid[row][col] = true;
            numofOpenSites += 1;
        }
        int current = xyTo1D(row, col);
        if (row == 0) {
            uf.union(top, current);
            ufbw.union(bwtop, current);
        }
        if (row == gridSize - 1) {
            uf.union(bottom, current);
        }
        int[][] surround = {{row + 1, col}, {row - 1, col}, {row, col + 1}, {row, col - 1}};
        for (int[] s : surround) {
            if (s[0] < 0 || s[0] > gridSize - 1 || s[1] < 0 || s[1] > gridSize - 1) {
                continue;
            }
            if (isOpen(s[0], s[1])) {
                uf.union(xyTo1D(s[0], s[1]), current);
                ufbw.union(xyTo1D(s[0], s[1]), current);
            }
        }
    }

    private int xyTo1D(int row, int col) {
        return row * gridSize + col;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 0 || row > gridSize - 1 || col < 0 || col > gridSize - 1) {
            throw new IndexOutOfBoundsException();
        }
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 0 || row > gridSize - 1 || col < 0 || col > gridSize - 1) {
            throw new IndexOutOfBoundsException();
        }
        int current = xyTo1D(row, col);
        return ufbw.find(current) == ufbw.find(bwtop);
    }

    public int numberOfOpenSites() {          // number of open sites
        return numofOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(top) == uf.find(bottom);
    }

    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args) {
        Percolation p = new Percolation(4);
        p.open(2, 2);
        p.open(3, 2);
        p.open(2, 3);
        p.open(3, 3);
        p.open(0, 1);
        p.open(1, 1);
        System.out.println(p.numberOfOpenSites());
        System.out.println(p.percolates());
        System.out.println(p.isFull(1, 1));
    }
}
