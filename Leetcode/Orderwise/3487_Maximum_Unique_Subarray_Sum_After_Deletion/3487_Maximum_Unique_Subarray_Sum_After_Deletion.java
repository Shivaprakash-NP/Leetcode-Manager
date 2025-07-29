class Solution {
    public int maxSum(int[] nums) {
        int[] map = new int[101];
        int max = Integer.MIN_VALUE;
        int ans = -1;
        for(int v : nums) {
            max = Math.max(max, v);
            if(v > 0 && map[v] == 0) {
                ans+=v;
                map[v] = 1;
            }
        }
        return (ans == -1)?max:ans+1;
    }
}