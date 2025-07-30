class Solution {
    public int jump(int[] nums) {
        int j = 0;
        int l = 0;
        int r = 0;
        int far = 0;
        while(r < nums.length-1) {
            for(int i = l ; i<=r ; i++) far = Math.max(far , i+nums[i]);
            l = r+1;
            r = far;
            j++;
        }
        return j;
    }
}