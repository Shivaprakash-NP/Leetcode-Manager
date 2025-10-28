class Solution {
    public int countValidSelections(int[] nums) {
        int sum = 0;
        for(int v : nums) sum+=v;

        int n = nums.length;
        int cur = 0;
        int ans = 0;
        for(int i = 0; i<n; i++) {
            if(nums[i] == 0) {
                int rem = sum-cur;
                if(rem == cur) ans += 2;
                else if(Math.abs(rem-cur) == 1) ans++;
            } else cur += nums[i];
        }

        return ans;
    }
}