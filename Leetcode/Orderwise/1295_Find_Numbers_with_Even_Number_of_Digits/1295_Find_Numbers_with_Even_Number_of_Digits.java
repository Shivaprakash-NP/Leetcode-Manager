class Solution {
    public int findNumbers(int[] nums) {
        int ans = 0;
        for(int v : nums) {
            int n = 0;
            while(v != 0) {
                v/=10;
                n++;
            }
            if(n%2 == 0) ans++;
        }
        return ans;
    }
}