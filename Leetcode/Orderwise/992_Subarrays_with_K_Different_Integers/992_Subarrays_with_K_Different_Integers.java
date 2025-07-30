class Solution {
    private int solve(int[] nums , int k) {
        int c = 0;
        int l = 0;
        HashMap<Integer , Integer> set = new HashMap<>();
        for(int r = 0 ; r<nums.length ; r++) {
            set.put(nums[r] , set.getOrDefault(nums[r] , 0)+1);
            while(set.size()>k) {
                set.put(nums[l] , set.get(nums[l])-1);
                if(set.get(nums[l]) == 0) set.remove(nums[l]);
                l++;
            }
            c+=(r-l+1);
        }
        return c;
    }
    public int subarraysWithKDistinct(int[] nums, int k) {
        return solve(nums, k) - solve(nums, k-1);
    }
}