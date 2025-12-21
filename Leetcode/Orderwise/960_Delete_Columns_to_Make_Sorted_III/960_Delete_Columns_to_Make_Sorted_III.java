class Solution {
    private int rec(char[][] a, int cur, int pre, int[][] dp) {
        int n = a.length;
        int m = a[0].length;

        if(cur == m) return 0;
        boolean f = true;

        if(dp[cur+1][pre+1] != -1) return dp[cur+1][pre+1];
        
        if(pre != -1) {
            for(int i = 0; i<n; i++) {
                if(a[i][pre] > a[i][cur]) {
                    f = false;
                    break;
                }
            }
        }

        int op1 = 1+rec(a, cur+1, pre, dp);
        int op2 = Integer.MAX_VALUE;

        if(f) op2 = rec(a, cur+1, cur, dp);
        return dp[cur+1][pre+1] = Math.min(op1, op2);
    }

    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        char[][] a = new char[n][m];
        int[][] dp = new int[102][102];

        for(int[] d : dp) Arrays.fill(d, -1);

        for(int i = 0; i<n; i++) a[i] = strs[i].toCharArray();

        return rec(a, 0, -1, dp);
    }
}