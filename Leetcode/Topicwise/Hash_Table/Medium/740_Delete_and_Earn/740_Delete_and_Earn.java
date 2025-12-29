class Solution {
    public int deleteAndEarn(int[] nums) {
        int max = 0;
        for(int v : nums) max = Math.max(max, v);

        int[] point = new int[max+1];
        for(int v : nums) point[v] += v;

        int cur = point[0];
        int pre = 0;

        for(int i = 1; i<=max; i++) {
            int val = Math.max(point[i]+pre, cur);
            pre = cur;
            cur = val;
        }

        return cur;
    }
}