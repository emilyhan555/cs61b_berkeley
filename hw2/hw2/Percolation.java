package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int top;
    private int bottom;
    private WeightedQuickUnionUF uf;
    private int gridSize;
    private int numofOpenSites;
    private int[][] surroundings;

    public Percolation(int N) {                // create N-by-N grid, with all sites initially blocked
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        grid = new boolean[N][N];
        top = N * N;
        bottom = N * N + 1;
        uf = new WeightedQuickUnionUF(N * N + 2);
        gridSize = N;
        numofOpenSites = 0;
        surroundings = new int[][]{{1, 0},{-1, 0},{0, 1},{0,-1}};
    }

    public void open(int row, int col) {      // open the site (row, col) if it is not open already
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
        }
        if (row == gridSize - 1) {
            uf.union(bottom, current);
        }
        int[][] currentCell = new int[][]{{row, col}};
        int[][] surround = new int[surroundings.length][2];
        for (int i = 0; i < surroundings.length; i++) {
            for (int j = 0; j < 2; j++) {
                surround[i][j] = surroundings[i][j] + currentCell[0][j];
            }
        }
        for (int[] s : surround) {
            if (s[0] < 0 || s[0] > gridSize - 1 || s[1] < 0 || s[1] > gridSize -1) {
                continue;
            }
            if (isOpen(s[0], s[1])) {
                uf.union(xyTo1D(s[0], s[1]), current);
            }
        }
    }

    private int xyTo1D(int row, int col) {
        return row * gridSize + col;
    }

    public boolean isOpen(int row, int col) {   // is the site (row, col) open?
        if (row < 0 || row > gridSize - 1 || col < 0 || col > gridSize - 1) {
            throw new IndexOutOfBoundsException();
        }
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {  // is the site (row, col) full?
        if (row < 0 || row > gridSize - 1 || col < 0 || col > gridSize - 1) {
            throw new IndexOutOfBoundsException();
        }
        int current = xyTo1D(row, col);
        return uf.connected(current, top);
    }

    public int numberOfOpenSites() {          // number of open sites
        return numofOpenSites;
    }

    public boolean percolates() {             // does the system percolate?
        return uf.connected(top, bottom);
    }

    public static void main(String[] args) {  // use for unit testing (not required, but keep this here for the autograder)
        Percolation p = new Percolation(4);
        p.open(2,2);
        p.open(3,2);
        p.open(2,3);
        //p.open(2,1);
        p.open(3,3);
        p.open(0,1);
        p.open(1,1);
        System.out.println(p.numberOfOpenSites());
        System.out.println(p.percolates());
        System.out.println(p.isFull(1,1));
    }
}
