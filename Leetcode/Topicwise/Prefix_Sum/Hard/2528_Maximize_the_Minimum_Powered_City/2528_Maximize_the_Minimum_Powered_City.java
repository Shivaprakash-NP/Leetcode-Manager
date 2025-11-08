class Solution {
    private boolean is(long[] pow, long m, int r, int k) {
        long added = 0;
        int n = pow.length;
        long[] diff = new long[n+1];

        for(int i = 0; i<n; i++) {
            added += diff[i];
            if(pow[i] + added < m) {
                long needed = m-(pow[i]+added);
                if(needed > k) return false;
                k -= needed;
                added += needed;

                if(i+2*r < n) diff[i+2*r+1] -= needed;
            }
        }

        return true;
    }

    public long maxPower(int[] stations, int r, int k) {
        int n = stations.length;
        long[] pre = new long[n+1];
        long[] pow = new long[n];

        for(int i = 0; i<n; i++) pre[i+1] = pre[i]+stations[i];

        for(int i = 0; i<n; i++) pow[i] = pre[Math.min(n, i+1+r)] - pre[Math.max(0, i-r)];

        long l = Long.MAX_VALUE;
        long h = 0L;
        long ans = 0;

        for(long v : pow) {
            l = Math.min(l, v);
            h = Math.max(h, v);
        }

        h += k;

        while(l<=h) {
            long m = (l+h)/2;
            if(is(pow, m, r, k)) {
                ans = m;
                l = m+1;
            } else {
                h = m-1;
            }
        }

        return ans;
    }
}