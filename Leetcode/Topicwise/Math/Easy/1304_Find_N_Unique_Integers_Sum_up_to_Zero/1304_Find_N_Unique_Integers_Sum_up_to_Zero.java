class Solution {
    public int[] sumZero(int n) {
        int[] ans = new int[n];
        int sum = n*(n-1)/2;
        ans[0] = -1*sum;
        for(int i = 1; i<n; i++) ans[i] = i;
        return ans;
    }
}