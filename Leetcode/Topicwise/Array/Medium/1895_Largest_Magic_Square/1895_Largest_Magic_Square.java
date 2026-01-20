class Solution {
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int maxSide = Math.min(m, n);

        for (int side = maxSide; side >= 1; side--) {
            for (int r = 0; r + side <= m; r++) {
                for (int c = 0; c + side <= n; c++) {
                    if (isMagic(grid, r, c, side)) {
                        return side;
                    }
                }
            }
        }
        return 0;
    }

    private boolean isMagic(int[][] grid, int r, int c, int side) {
        int target = 0;

        // Check rows
        for (int i = 0; i < side; i++) {
            int rowSum = 0;
            for (int j = 0; j < side; j++) {
                rowSum += grid[r + i][c + j];
            }
            if (i == 0) target = rowSum;
            else if (rowSum != target) return false;
        }

        // Check columns
        for (int j = 0; j < side; j++) {
            int colSum = 0;
            for (int i = 0; i < side; i++) {
                colSum += grid[r + i][c + j];
            }
            if (colSum != target) return false;
        }

        // Check main diagonal
        int diag = 0;
        for (int k = 0; k < side; k++) {
            diag += grid[r + k][c + k];
        }
        if (diag != target) return false;

        // Check anti diagonal
        diag = 0;
        for (int k = 0; k < side; k++) {
            diag += grid[r + k][c + side - 1 - k];
        }
        return diag == target;
    }
}