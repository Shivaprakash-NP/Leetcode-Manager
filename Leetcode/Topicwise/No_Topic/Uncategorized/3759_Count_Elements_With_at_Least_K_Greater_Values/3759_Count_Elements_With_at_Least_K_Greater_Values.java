class Solution {
    public int countElements(int[] nums, int k) {
        int n = nums.length;
        if(k == 0) return n;
        int cnt = 0;
        
        Arrays.sort(nums);

        int kth = nums[n-k];

        for(int v : nums) {
            if(v < kth) cnt++;
        }
        
        return cnt;
    }
}