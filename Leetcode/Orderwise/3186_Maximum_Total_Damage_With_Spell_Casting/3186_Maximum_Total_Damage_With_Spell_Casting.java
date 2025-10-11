class Solution {
    private long rec(List<long[]> pow, int i, long l, long[] dp) {
        if(i>=pow.size()) return 0L;

        if(dp[i] != -1) return dp[i];

        long pick = 0L, nopick = 0L;
        long v = pow.get(i)[0];

        int j = i + 1;
        while (j < pow.size() && pow.get(j)[0] - v <= 2L) j++;

        pick = rec(pow, j, v, dp) + pow.get(i)[1];

        nopick = rec(pow, i+1, l, dp);

        long ans = Math.max(pick, nopick);
        
        return dp[i] = ans;
    }

    public long maximumTotalDamage(int[] power) {
        int n = power.length;

        Map<Long, Long> map = new HashMap<>();

        for(int v : power) map.put((long)v, map.getOrDefault((long)v, 0L) + 1L);

        List<long[]> pow = new ArrayList<>();
        
        for(long v : map.keySet()) pow.add(new long[]{v, v*map.get(v)});

        Collections.sort(pow, (a,b) -> Long.compare(a[0], b[0]));

        long[] dp = new long[pow.size()+1];
        Arrays.fill(dp, -1L);

        return rec(pow, 0, 0L, dp);
    }
}