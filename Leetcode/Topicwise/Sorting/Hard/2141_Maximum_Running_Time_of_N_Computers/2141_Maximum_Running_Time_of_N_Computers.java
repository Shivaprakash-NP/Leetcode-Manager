class Solution {
    private boolean isGood(long t, int n, int[] batteries) {
        long sum = 0L;
        for(long v : batteries) sum += Math.min(v, t);

        return sum >= n*t;
    }

    public long maxRunTime(int n, int[] batteries) {
        long sum = 0L;
        for(long v : batteries) sum += v;
    
        long l = 0L;
        long r = sum/(long)n + 1;

        while(l+1L < r) {
            long m = (r+l)/2;
            if(isGood(m, n, batteries)) l = m;
            else r = m;
        }    

        return l;
    }
}