class Solution {
    private long cost(int x, int t) {
        boolean match = true;
        int y = 0;

        for(int i = 30; i>=0; i--) {
            int bX = (x&(1<<i));
            int bT = (t&(1<<i));

            if(match && bX == 0 && bT != 0) match = false;
            if(match) y |= (bX);
            else y |= (bT);
        }

        return (long)(y-x);
    }

    public int maximumAND(int[] nums, int k, int m) {
        int bit = 0;
        for(int i = 30; i>=0; i--) {
            PriorityQueue<Long> q = new PriorityQueue<>();
            for(int v : nums) {
                long c = cost(v, (bit | (1<<i)));
                q.offer(c);
            }

            long tot = 0;
            for(int j = 0; j<m; j++) tot += q.poll();

            if(tot <= k) {
                bit |= (1<<i);
            }
        }

        return bit;
    }
}