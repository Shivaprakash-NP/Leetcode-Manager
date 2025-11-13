class Solution {
    public int[] shuffle(int[] nums, int n) {
        int[] ans = new int[2*n];

        for(int i = 0; i<2*n; i++) {
            if(i<n) ans[2*i] = nums[i];
            else ans[2*(i-n)+1] = nums[i];
        }

        return ans;
    }
}