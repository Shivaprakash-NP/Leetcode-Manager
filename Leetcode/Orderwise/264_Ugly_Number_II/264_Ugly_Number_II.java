class Solution {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];

        dp[0] = 1;
        int pa = 0;
        int pb = 0;
        int pc = 0;

        for(int i = 1; i<n; i++) {
            int nxt_a = dp[pa]*2;
            int nxt_b = dp[pb]*3;
            int nxt_c = dp[pc]*5;

            int min = Math.min(Math.min(nxt_a, nxt_b), nxt_c);
            dp[i] = min;

            if(min == nxt_a) pa++;
            if(min == nxt_b) pb++;
            if(min == nxt_c) pc++;
        }

        return dp[n-1];
    }
}