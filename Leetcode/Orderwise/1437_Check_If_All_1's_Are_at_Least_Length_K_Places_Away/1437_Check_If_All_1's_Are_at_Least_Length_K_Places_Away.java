class Solution {
    public boolean kLengthApart(int[] nums, int k) {
        List<Integer> ind = new ArrayList<>();
        for(int i = 0; i<nums.length; i++) {
            if(nums[i] == 1) ind.add(i);
        }
        for(int i = 0; i<ind.size()-1; i++) {
            if(ind.get(i+1)-ind.get(i)-1 < k) return false;
        }
        return true;
    }
}