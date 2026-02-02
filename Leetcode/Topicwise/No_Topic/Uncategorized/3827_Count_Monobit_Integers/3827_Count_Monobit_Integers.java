class Solution {
    public int countMonobit(int n) {
        int ans = 1;
        int pow = 2;
        while(pow-1 <= n) {
            ans ++;
            pow *= 2;
        }

        return ans;
    }
}