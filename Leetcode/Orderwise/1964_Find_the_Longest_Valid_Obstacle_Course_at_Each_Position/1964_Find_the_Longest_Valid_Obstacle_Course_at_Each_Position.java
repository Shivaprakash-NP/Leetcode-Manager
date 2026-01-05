class Solution {
    private int bs(int[] arr, int v) {
        int l = 0;
        int r = arr.length;

        while(l<r) {
            int m = (l+r)/2;
            if(arr[m] <= v) {
                l = m+1;
            } else r = m;
        }

        return l;
    }

    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int n = obstacles.length;

        int[] dp = new int[n+1];
        int[] ans = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = Integer.MIN_VALUE;

        for(int i = 0; i<n; i++) {
            int ind = bs(dp, obstacles[i]);
            ans[i] = ind;
            dp[ind] = obstacles[i];
        }

        return ans;
    }
}