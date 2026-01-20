class Solution {
    private boolean pos(int l, int th, int[][] pre) {
        int m = pre.length;
        int n = pre[0].length;

        for(int i = 0; i<=m-l; i++) {
            for(int j = 0; j<=n-l; j++) {
                int ni = i+l-1;
                int nj = j+l-1;

                int sum = pre[ni][nj]-(j>0 ? pre[ni][j-1] : 0)-(i > 0 ? pre[i-1][nj] : 0)+(i > 0 && j > 0 ? pre[i-1][j-1] : 0);

                if(sum <= th) return true;                
            }
        }

        return false;
    }

    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;

        int[][] pre = new int[m][n];

        pre[0][0] = mat[0][0];

        for(int i = 1; i<n; i++) pre[0][i] = pre[0][i-1]+mat[0][i];
        for(int i = 1; i<m; i++) pre[i][0] = pre[i-1][0]+mat[i][0];

        for(int i = 1; i<m; i++) {
            for(int j = 1; j<n; j++) {
                pre[i][j] = pre[i-1][j]+pre[i][j-1]-pre[i-1][j-1]+mat[i][j];
            }
        }

        int l = 1;
        int r = Math.min(n, m);
        int ans = 0;

        while(l <= r) {
            int mid = l+(r-l)/2;
            if(pos(mid, threshold, pre)) {
                l = mid+1;
                ans = mid;
            } else r = mid-1;
        }

        return ans;
    }
}