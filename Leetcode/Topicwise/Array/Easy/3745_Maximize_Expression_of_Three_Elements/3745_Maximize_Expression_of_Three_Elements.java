class Solution {
    public int maximizeExpressionOfThree(int[] nums) {
        int max = Integer.MIN_VALUE;
        int n = nums.length;
        for(int i = 0; i<n; i++) {
            for(int j = 0; j<n; j++) {
                for(int k = 0; k<n; k++) {
                    if(i!=j && j!= k && i!=k) {
                        max = Math.max(max,nums[i]+nums[j]-nums[k]);
                    }
                }
            }
        }
        return max;
    }
}