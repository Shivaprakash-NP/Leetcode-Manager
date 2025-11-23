class Solution {
    public int maxSumDivThree(int[] nums) {
        int sum = 0;
        int INF = Integer.MAX_VALUE;

        int r1a = INF, r1b = INF;
        int r2a = INF, r2b = INF;

        for(int x : nums) {
            sum += x;
            int r = x % 3;

            if(r == 1) {
                if(x < r1a) { r1b = r1a; r1a = x; }
                else if(x < r1b) r1b = x;
            } else if(r == 2) {
                if(x < r2a) { r2b = r2a; r2a = x; }
                else if(x < r2b) r2b = x;
            }
        }

        int rem = sum % 3;

        if(rem == 0) return sum;

        int ans = 0;

        if(rem == 1) {
            int remove1 = r1a;
            int remove2 = (r2a < INF && r2b < INF) ? r2a + r2b : INF;
            ans = Math.min(remove1, remove2);
        } else { 
            int remove1 = r2a;
            int remove2 = (r1a < INF && r1b < INF) ? r1a + r1b : INF;
            ans = Math.min(remove1, remove2);
        }

        if(ans >= INF) return 0;
        return sum - ans;
    }
}
