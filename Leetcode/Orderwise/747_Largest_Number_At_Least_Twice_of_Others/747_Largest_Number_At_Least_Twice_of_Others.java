class Solution {
    public int dominantIndex(int[] nums) {
        int m1 = 0;
        int m2 = 0;
        int ans = -1;
        for(int i = 0 ; i<nums.length ; i++) {
            if(nums[i] > m1) {
                m2 = m1;
                m1 = nums[i];
                ans = i;
            }
            if(nums[i] > m2 && nums[i]!=m1) m2 = nums[i];
        }
        return (m1 >= m2*2)?ans:-1;
    }
}