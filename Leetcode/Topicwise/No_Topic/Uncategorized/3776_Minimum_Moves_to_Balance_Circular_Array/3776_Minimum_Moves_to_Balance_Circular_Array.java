class Solution {
    public long minMoves(int[] balance) {
        int n = balance.length;
        
        long sum = 0L;
        for(int v : balance) sum += (long)v;
        if(sum < 0) return -1L;

        int ind = -1;
        long need = -1;
        for(int i = 0; i<n; i++) {
            if(balance[i] < 0) {
                ind = i;
                need = (long)Math.abs(balance[i]);
                break;
            }
        }

        if(ind == -1) return 0;
        int l = (ind-1+n)%n;
        int r = (ind+1)%n;

        long cnt = 0L;
        long steps = 0L;
        
        while(need > 0 && steps < n) {
            if(balance[l] > 0 && need > 0) {
                long take = Math.min(balance[l], need);
                long dis = Math.min(Math.abs(l-ind), n-Math.abs(l-ind));

                cnt += take*dis;
                need -= take;
                balance[l] -= take;
            }

            if(balance[r] > 0 && need > 0) {
                long take = Math.min(balance[r], need);
                long dis = Math.min(Math.abs(r-ind), n-Math.abs(r-ind));
                
                cnt += take*dis;
                need -= take;
                balance[r] -= take;
            }
            
            l = (l-1+n)%n;
            r = (r+1)%n;
            steps++;
        }

        return need == 0 ? cnt : -1L;
    }
}