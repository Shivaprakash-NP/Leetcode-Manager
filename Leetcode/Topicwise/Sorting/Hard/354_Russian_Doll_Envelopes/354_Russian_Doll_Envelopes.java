class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = Integer.MIN_VALUE;

        Arrays.sort(envelopes, (a, b) -> {
            if(a[0] != b[0]) return a[0] - b[0];
            return b[1] - a[1];
        });

        int len = 0;

        for(int[] env : envelopes) {
            int h = env[1];

            int l = 0;
            int r = len;

            while(l < r) {
                int m = l + (r-l)/2;
                if(dp[m] < h) l = m+1;
                else r = m;
            }

            dp[l] = h;
            if(l == len) len++;
        }

        return len;
    }
}