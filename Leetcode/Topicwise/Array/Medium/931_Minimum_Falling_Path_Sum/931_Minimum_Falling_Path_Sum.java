class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;

        int[] pre = new int[n];
        for(int i = 0; i<n; i++) pre[i] = matrix[0][i];

        for(int i = 1; i<n; i++) {
            int[] cur = new int[n];
            cur[0] = Math.min(pre[0], pre[1]) + matrix[i][0];
            for(int j = 1; j<n-1; j++) cur[j] = Math.min(pre[j-1], Math.min(pre[j], pre[j+1]))+matrix[i][j];
            cur[n-1] = Math.min(pre[n-1], pre[n-2])+matrix[i][n-1];
            pre = cur;
        }

        int ans = Integer.MAX_VALUE;
        for(int v : pre) ans = Math.min(ans, v);

        return ans;
    }
}