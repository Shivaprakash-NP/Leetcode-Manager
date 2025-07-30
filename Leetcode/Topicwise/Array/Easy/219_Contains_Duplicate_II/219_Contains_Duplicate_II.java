class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> val = new HashSet<>();
        for(int i = 0 ; i<nums.length ; i++)
        {
            if(val.contains(nums[i])) return true;
            val.add(nums[i]);
            if(i >= k) val.remove(nums[i-k]);
        }
        return false;
    }
}