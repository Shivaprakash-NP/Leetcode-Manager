class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        for(int i = 0; i<n-1; i++) {
            if(nums[i] != nums[i+1]) return 1;
        }

        return 0;
        
    }
}