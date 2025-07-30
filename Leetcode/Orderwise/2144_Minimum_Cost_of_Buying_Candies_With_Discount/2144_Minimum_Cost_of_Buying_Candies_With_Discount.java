class Solution {
    public int minimumCost(int[] cost) {
        int sum = 0;
        for(int v : cost) sum+=v;
        Arrays.sort(cost);
        int n = cost.length;
        for(int i = n-3 ; i>=0 ; i-=3) sum-=cost[i];
        return sum;
    }
}