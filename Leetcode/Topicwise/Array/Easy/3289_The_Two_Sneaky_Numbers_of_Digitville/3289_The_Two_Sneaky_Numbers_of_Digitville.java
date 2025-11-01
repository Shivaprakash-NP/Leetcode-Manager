class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        int n = nums.length-2;
        int[] map = new int[n];
        
        int[] ans = new int[2];
        int ind = 0;

        for(int v : nums) map[v]++;

        for(int i = 0; i<n; i++) {
            if(map[i] == 2) ans[ind++] = i;
        }

        return ans;
    }
}