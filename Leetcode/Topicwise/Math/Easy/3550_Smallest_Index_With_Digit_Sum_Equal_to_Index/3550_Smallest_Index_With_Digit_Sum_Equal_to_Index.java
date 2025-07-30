class Solution {
    private int find(int n) {
        int sum = 0;
        while(n != 0) {
            sum+=n%10;
            n/=10;
        }
        return sum;
    }
    public int smallestIndex(int[] nums) {
        for(int i = 0 ; i<nums.length ; i++) {
            if(find(nums[i]) == i) return i;
        }
        return -1;
    }
}