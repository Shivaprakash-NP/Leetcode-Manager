class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] horpre = new int[m+1];
        int[] verpre = new int[n+1];
        for(int i = 1 ; i <=m ; i++) {
            int s = 0;
            for(int j = 0 ; j < n ; j++) s+=grid[i-1][j];
            horpre[i] = s+horpre[i-1];
        }
        for(int i = 1 ; i <=n ; i++) {
            int s = 0;
            for(int j = 0 ; j < m ; j++) s+=grid[j][i-1];
            verpre[i] = s+verpre[i-1];
        }
        int hormax = horpre[m];
        int vermax = verpre[n];
        for(int v : horpre) {
            if(v*2 == hormax) return true;
        }
        for(int v : verpre) {
            if(v*2 == vermax) return true;
        }
        return false;
    }
}