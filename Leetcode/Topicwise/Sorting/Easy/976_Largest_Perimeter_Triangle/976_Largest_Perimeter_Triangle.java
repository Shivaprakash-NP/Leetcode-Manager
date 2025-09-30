class Solution {
    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for(int i = n-2; i>0; i--) {
            int a = nums[i+1];
            int b = nums[i];
            int c = nums[i-1];
            if(a+b>c && a+c>b && b+c>a) return a+b+c;
        }
        return 0;
    }
}