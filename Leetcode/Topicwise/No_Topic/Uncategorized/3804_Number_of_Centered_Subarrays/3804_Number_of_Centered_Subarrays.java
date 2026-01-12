class Solution {
    public int centeredSubarrays(int[] nums) {
        int n = nums.length;

        int cnt = 0;

        for(int i = 0; i<n; i++) {
            int sum = 0;
            Set<Integer> set = new HashSet<>();
            for(int j = i; j<n; j++) {
                set.add(nums[j]);
                sum += nums[j];
                if(set.contains(sum)) cnt++;
            }
        }

        return cnt;
    }
}