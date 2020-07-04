import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class BubbleGrid {
    private int[][] grid;
    private int row;
    private int col;
    private int stuck;
    private int space;
    private WeightedQuickUnionUF ufstuck;
    private int[][] surroundings;

    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
        row = grid.length;
        col = grid[0].length;
        stuck = row * col;
        ufstuck = new WeightedQuickUnionUF(row * col + 1);
        surroundings = new int[][]{{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

        // A bubble is stuck if it is the topmost row of the grid.
        for (int i = 0; i < col; i++) {
            if (grid[0][i] == 1) {
                ufstuck.union(stuck, xyTo1D(0, i));
            }
        }
    }

    // A bubble is stuck if it is orthogonally adjacent to a bubble that is stuck.
    private void stuck(int row, int col) {
        int current = xyTo1D(row, col);
        int[][] currentCell = new int[][]{{row, col}};
        int[][] surrounds = new int[4][2];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                surrounds[i][j] = surroundings[i][j] + currentCell[0][j];
            }
        }
        for (int[] s : surrounds) {
            if (s[0] < 0 || s[0] >= this.row || s[1] < 0 || s[1] >= this.col) {
                continue;
            }
            if (isStuck(s[0], s[1]) && grid[row][col] == 1
                    && !ufstuck.connected(xyTo1D(row, col), stuck)) {
                ufstuck.union(stuck, xyTo1D(row, col));
            }
        }
    }

    private boolean isStuck(int row, int col) {
        return ufstuck.find(stuck) == ufstuck.find(xyTo1D(row, col));
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        int dartsRow = darts.length;
        int dartsCol = 2;
        int[] pop = new int[dartsRow];

        for (int i = 0; i < dartsRow; i++) {
            int dRow = darts[i][0];
            int dCol = darts[i][1];
            if (grid[dRow][dCol] == 0) {
                pop[i] = 0;
            } else {
                int count = 0;
                grid[dRow][dCol] = 0;
                for (int ith = 1; ith < row; ith++) {
                    for (int j = 0; j < col; j++) {
                        stuck(ith, j);
                        if (!isStuck(ith, j) && grid[ith][j] == 1) {
                            count += 1;
                        }
                    }
                }
                pop[i] = count;
            }
        }
        return pop;
    }

    public int xyTo1D(int row, int col) {
        return grid[0].length * row + col;
    }

    public static void main(String[] args) {
        int[][] g = new int[][]{{1, 1, 0}, {1, 0, 0}, {1, 1, 0}, {1, 1, 1}};
        int[][] darts = new int[][]{{2, 2}, {2, 0}};

        BubbleGrid bg = new BubbleGrid(g);
        int[] p = bg.popBubbles(darts);
        for (int i = 0; i < p.length; i++) {
            System.out.print(p[i] + " ");
        }

    }
}
