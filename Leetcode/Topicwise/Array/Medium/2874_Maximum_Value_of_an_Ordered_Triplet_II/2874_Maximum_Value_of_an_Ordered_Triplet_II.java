class Solution {
    public long maximumTripletValue(int[] nums) {
        long maxele = nums[0];
        long max = Long.MIN_VALUE;
        long maxdif = Long.MIN_VALUE;
        for(int i = 1 ; i<nums.length-1 ; i++)
        {
            maxdif = Math.max(maxdif , maxele - nums[i]);
            max = Math.max(max , maxdif*nums[i+1]);
            maxele = Math.max(maxele , nums[i]);
        }
        return (max<0)?0:max;
    }
}