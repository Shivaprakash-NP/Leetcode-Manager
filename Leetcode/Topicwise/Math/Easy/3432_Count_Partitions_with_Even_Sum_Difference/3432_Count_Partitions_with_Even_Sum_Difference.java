class Solution {
    public int countPartitions(int[] nums) {
        int totSum = 0;
        int cnt = 0;
        for(int v : nums) totSum += v;

        int curSum = 0;
        for(int i = 0; i<nums.length-1; i++) {
            curSum += nums[i];
            if((totSum-2*curSum)%2 == 0) cnt++;
        }

        return cnt;
    }
}