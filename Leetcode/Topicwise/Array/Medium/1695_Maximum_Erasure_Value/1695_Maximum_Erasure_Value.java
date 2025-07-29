class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        int sum = 0;
        int ans = 0;
        int l = 0;

        for(int r = 0; r<nums.length; r++) {
            sum+=nums[r];
            if(map.containsKey(nums[r])) 
                while(l<=map.get(nums[r])) sum-=nums[l++];
            ans = Math.max(ans, sum);
            map.put(nums[r], r);
        }

        return ans;
    }
}