class Solution {
    private long count(long cur , long nxt , int n) {
        long step = 0;
        while(cur <= n) {
            step += Math.min(n+1L , nxt) - cur;
            cur*=10;
            nxt*=10;
        }
        return step;
    }
    
    public int findKthNumber(int n, int k) {
        long c = 1;
        k--;

        while(k>0) {
            long step = count(c , c+1 , n);
            if(step<=k) {
                c++;
                k-=step;
            } else {
                c*=10;
                k--;
            }
        }

        return (int)c;
    }
}