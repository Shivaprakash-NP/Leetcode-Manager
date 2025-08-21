class Solution {
    public int numSubmat(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int ans = 0;
        
        int[][] hei = new int[n][m];
        for(int i = 0; i<m; i++) hei[0][i] = mat[0][i];

        for(int i = 1; i<n; i++) {
            for(int j = 0; j<m; j++) {
                hei[i][j] = mat[i][j]*(hei[i-1][j] + 1);
            }
        }

        for(int i = 0; i<n; i++) {
            for(int j = m-1; j>=0; j--) {
                int min = hei[i][j];
                for(int k = j; k>=0 && min>0; k--) {
                    min = Math.min(min, hei[i][k]);
                    ans+=min;
                }
            }
        }

        return ans;
    }
}