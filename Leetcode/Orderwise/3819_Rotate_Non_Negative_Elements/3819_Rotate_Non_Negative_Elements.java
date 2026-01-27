class Solution {
    public int[] rotateElements(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();

        for(int v : nums) if(v >= 0) list.add(v);
        int len = list.size();
        if(len == 0) return nums;
        k%=len;

        for(int i = 0; i<nums.length; i++) {
            if(nums[i] >= 0) {
                nums[i] = list.get(k);
                k++;
                k%=len;
            }
        }

        return nums;
    }
}