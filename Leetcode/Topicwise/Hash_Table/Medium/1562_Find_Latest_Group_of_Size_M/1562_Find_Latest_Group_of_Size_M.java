class Solution {
    public int findLatestStep(int[] arr, int m) {
        int n = arr.length;
        if (m == n) return n;
        int[] len = new int[n+2];
        int c = 0;
        int ans = -1;
        for(int s = 0 ; s<n ; s++) {
            int i = arr[s];
            int l = len[i-1];
            int r = len[i+1];
            int t = l+r+1;

            len[i-l] = t;
            len[i+r] = t;
            len[i] = t;

            if(l == m) c--;
            if(r == m) c--;
            if(t == m) c++;

            if(c>0) {
                ans = s+1;
            }
        }
        return ans;
    }
}
