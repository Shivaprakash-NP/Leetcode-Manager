class Solution {
    private boolean sr(int v) {
        if(v == 1) return false;
        for(int i = 2 ; i*i <= v ; i++) 
            if(v%i == 0) return false;
        return true;
    }

    public int nonSpecialCount(int l, int r) {
        int ans = 0;
        for(int i = (int) Math.sqrt(l) ; i <= (int)Math.sqrt(r) ; i++) {
            if(sr(i) && i*i<=r && i*i>=l) ans++;
        }
        return (r-l+1 - ans);
    }
}