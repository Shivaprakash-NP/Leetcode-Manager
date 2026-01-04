class Solution {
    private int dfs(int i, int cnt, int k, int[] arr, int max, int[][] dp) {
        // base case
        if(i == arr.length) return 0;
        max = Math.max(max, arr[i]);

        if(dp[i][cnt] != -1) return dp[i][cnt];

        int nocut = 0;
        if(cnt < k) nocut = dfs(i+1, cnt+1, k, arr, max, dp);
        int cut = dfs(i+1, 1, k, arr, 0, dp) + cnt*max;

        return dp[i][cnt] = Math.max(cut, nocut);
    } 

    public int maxSumAfterPartitioning(int[] arr, int k) {
        int[][] dp = new int[arr.length+1][k+1];
        
        for(int[] indp : dp) Arrays.fill(indp, -1);
        return dfs(0, 1, k, arr, arr[0], dp);
    }
}