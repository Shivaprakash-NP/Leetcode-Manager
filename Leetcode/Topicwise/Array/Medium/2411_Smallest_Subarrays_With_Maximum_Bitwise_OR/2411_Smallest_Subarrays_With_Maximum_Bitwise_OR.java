class Solution {
    public int[] smallestSubarrays(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int[] map = new int[32];
        Arrays.fill(map, -1);

        for(int i = n-1; i>=0; i--) {
            int tem = nums[i];
            int r = i;
            for(int j = 0; j<32; j++) {
                if((tem&1) == 1) {
                    map[j] = i; 
                }
                tem = tem>>1;
                if(map[j] != -1) r = Math.max(r, map[j]);
            }
            ans[i] = r-i+1;
        }

        return ans;
    }
}