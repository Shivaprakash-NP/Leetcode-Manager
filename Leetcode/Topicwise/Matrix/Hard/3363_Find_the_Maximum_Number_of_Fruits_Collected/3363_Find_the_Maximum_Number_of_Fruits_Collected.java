class Solution {
    private int ch1(int i, int j, int[][] fruits, int[][] dp) {
        if(i == j && i == fruits.length-1) return 0;
        if(j<=i) return Integer.MIN_VALUE;

        if(dp[i][j] != -1) return dp[i][j];

        int sum1 = 0;
        if(j+1 < fruits.length) sum1 = ch1(i+1, j+1, fruits, dp);
        int sum2 = ch1(i+1, j, fruits, dp);
        int sum3 = ch1(i+1, j-1, fruits, dp);

        return dp[i][j] = Math.max(sum1, Math.max(sum2, sum3))+fruits[i][j];
    }

    private int ch2(int i, int j, int[][] fruits, int[][] dp) {
        if(i == j && i == fruits.length-1) return 0;
        if(i<=j) return Integer.MIN_VALUE;

        if(dp[i][j] != -1) return dp[i][j];

        int sum1 = 0;
        if(i+1 < fruits.length) sum1 = ch2(i+1, j+1, fruits, dp);
        int sum2 = ch2(i, j+1, fruits, dp);
        int sum3 = ch2(i-1, j+1, fruits, dp);

        return dp[i][j] = Math.max(sum1, Math.max(sum2, sum3))+fruits[i][j];
    }

    public int maxCollectedFruits(int[][] fruits) {
        int n = fruits.length;
        int sum = 0;
        int[][] dp1 = new int[n][n];
        int[][] dp2 = new int[n][n];
        for(int i = 0; i<n; i++) {
            Arrays.fill(dp1[i], -1);
            Arrays.fill(dp2[i], -1);
        }

        for(int i = 0; i<n; i++) sum+=fruits[i][i];

        sum+=ch1(0, n-1, fruits, dp1);
        sum+=ch2(n-1, 0, fruits, dp2);

        return sum;
    }
}