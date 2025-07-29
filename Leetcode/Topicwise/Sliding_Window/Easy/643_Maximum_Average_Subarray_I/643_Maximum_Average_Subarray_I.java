class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        double ans = Integer.MIN_VALUE;
        int l = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (i - l + 1 > k) {
                sum -= nums[l++];
            }

            if (i - l + 1 == k) { 
                ans = Math.max(ans, (double) sum / k);
            }
        }

        return ans;
    }
}
