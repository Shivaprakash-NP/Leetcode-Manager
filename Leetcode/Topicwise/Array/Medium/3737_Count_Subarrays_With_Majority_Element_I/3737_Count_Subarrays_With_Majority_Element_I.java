class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int cnt = 0;

        for(int i = 0; i<n; i++) {
            int c = 0;
            for(int j = i; j<n; j++) {
                if(nums[j] == target) c++;
                if(c > (j-i+1)/2) cnt++;
            }
        }

        return cnt;
    }
}