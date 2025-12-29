class Solution {
    private boolean isvalid(int[] map, int[] smap, int[] smap2, int diag1, int diag2) {
        if(diag1 != diag2) return false;
        for(int v : smap) if(v != diag1) return false;
        for(int v : smap2) if(v != diag1) return false;

        for(int i = 1; i<=9; i++) {
            if(map[i] != 1) return false;
        }

        return true;
    }

    public int numMagicSquaresInside(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int cnt = 0;

        if(n <= 2 && m <= 2) return 0;

        for(int i = 0; i<n-2; i++) {
            for(int j = 0; j<m-2; j++) {
                if(grid[i+1][j+1] != 5) continue;
                int[] map = new int[10];
                int[] smap = new int[3];
                int[] smap2 = new int[3];
                int diag1 = 0;
                int diag2 = 0;
                for(int k = i; k<i+3; k++) {
                    int sum = 0;
                    for(int l = j; l<j+3; l++) {
                        if(k-i == l-j) diag1 += grid[k][l];
                        if((k-i + l-j) == 2) diag2 += grid[k][l];
                        if(grid[k][l] <= 9) map[grid[k][l]]++;
                        smap[k-i] += grid[k][l];
                        smap2[l-j] += grid[k][l];
                    }
                }
                if(isvalid(map, smap, smap2, diag1, diag2)) cnt++;
            }
        }

        return cnt;
    }
}