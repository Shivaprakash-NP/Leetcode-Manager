class Solution {
    public int uniquePaths(int m, int n) {
        int N = m + n - 2;                 
        int k = Math.min(m, n) - 1;          
        long ans = 1;                      
        for (int i = 1; i <= k; i++) 
            ans = ans * (N - i + 1) / i;
        return (int)ans;
    }
}
