import java.util.Arrays;
class Solution {
    public int missingNumber(int[] nums) {
        int s = 0;
        int n = nums.length+1;
        for(int v : nums) s+=v;
        return (n*(n-1)/2 - s);
    }
}