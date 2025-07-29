class Solution {
    private int getLCP(String a, String b) {
        int len = Math.min(a.length(), b.length());
        for (int i = 0; i < len; i++)
            if (a.charAt(i) != b.charAt(i)) return i;
        return len;
    }

    public int[] longestCommonPrefix(String[] words) {
        int n = words.length;
        if (n <= 2) return new int[n];

        int[] prefix = new int[n - 1];      
        int[] skipPrefix = new int[n - 2];   
        for (int i = 0; i < n - 1; i++)
            prefix[i] = getLCP(words[i], words[i + 1]);

        for (int i = 0; i < n - 2; i++)
            skipPrefix[i] = getLCP(words[i], words[i + 2]);

        int[] lmax = new int[n - 1];
        int[] rmax = new int[n - 1];
        lmax[0] = prefix[0];
        for (int i = 1; i < n - 1; i++)
            lmax[i] = Math.max(lmax[i - 1], prefix[i]);

        rmax[n - 2] = prefix[n - 2];
        for (int i = n - 3; i >= 0; i--)
            rmax[i] = Math.max(rmax[i + 1], prefix[i]);

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                res[i] = rmax[1];  
            } else if (i == n - 1) {
                res[i] = lmax[n - 3]; 
            } else {
                int skip = skipPrefix[i - 1]; 
                int left = (i >= 2) ? lmax[i - 2] : 0;
                int right = (i <= n - 3) ? rmax[i + 1] : 0;
                res[i] = Math.max(skip, Math.max(left, right));
            }
        }

        return res;
    }
}
