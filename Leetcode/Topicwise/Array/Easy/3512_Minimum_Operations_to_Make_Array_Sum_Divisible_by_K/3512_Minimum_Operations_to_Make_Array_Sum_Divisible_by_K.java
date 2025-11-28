class Solution {
    public int minOperations(int[] nums, int k) {
        int sum = 0;
        for(int v : nums) sum += v;

        return sum%k;
    }
}