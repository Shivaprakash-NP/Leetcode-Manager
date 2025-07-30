class Solution {
    public long maximumHappinessSum(int[] happiness, int k) {
        int n = happiness.length;
        long ans = 0;
        Arrays.sort(happiness);
        for(int i = n-1 ; n-i <= k  ; i--) {
            int val = happiness[i]-(n-1-i);
            if(val<=0) break;
            ans+=val;
        }
        return ans;
    }
}