class Solution {
    public int singleNumber(int[] nums) {
        int flag = 0;
        for(int num : nums)
        {
            flag ^=num;
        }
        return flag;
    }
}