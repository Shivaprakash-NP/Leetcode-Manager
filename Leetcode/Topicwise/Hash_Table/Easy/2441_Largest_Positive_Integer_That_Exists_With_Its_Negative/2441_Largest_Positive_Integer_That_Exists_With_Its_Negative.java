class Solution {
    public int findMaxK(int[] nums) {
        Arrays.sort(nums);
        int l = 0;
        int r = nums.length-1;
        while(l<r) {
            if(nums[r] == -1*(nums[l])) return nums[r];
            if(nums[r] < Math.abs(nums[l])) l++;
            else r--;
        }
        return -1;
    }
}