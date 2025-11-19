class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        int cnt = 0;

        int[] nxt = new int[n];
        nxt[n-1] = n;

        for(int i = n-2; i>=0; i--) {
            if(s.charAt(i+1) == '0') nxt[i] = i+1;
            else nxt[i] = nxt[i+1];
        }

        for(int l = 0; l<n; l++) {
            int zero = 0;
            int one = 0;
            if(s.charAt(l) == '0') zero = 1;
            int r = l;

            while(zero*zero <= n) {
                int nx = nxt[r];
                one = nx-l-zero;
                if(one >= zero*zero) {
                    cnt += Math.min(nx-r, one-(zero*zero)+1);
                }

                r = nx;
                zero++;
                if(r == n) break;
            }
        }

        return cnt;
    }
}