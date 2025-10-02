class Solution {
    public int maxBottlesDrunk(int n, int e) {
        int ans = n;
        int sum = 0;
        while(n >= e) {
            ans++;
            n-=(e-1);
            e++;
        }
        return ans;
    }
}