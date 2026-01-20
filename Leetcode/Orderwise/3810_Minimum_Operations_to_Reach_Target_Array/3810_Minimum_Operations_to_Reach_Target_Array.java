class Solution {
    public int minOperations(int[] nums, int[] target) {
        Set<Integer> set = new HashSet<>();

        int n = nums.length;

        for(int i = 0; i<n; i++) {
            if(nums[i] == target[i]) continue;
            set.add(nums[i]);
        }

        return set.size();
    }
}