import java.util.Arrays;

class Solution {
    private long time(int[] val, int m) {
        long t = 0; 
        for (int v : val) t += (v + m - 1L) / m; 
        return t;
    }

    public int minEatingSpeed(int[] piles, int h) {
        int l = 1;
        int r = Arrays.stream(piles).max().getAsInt(); 
        while (l <= r) 
        {
            int m = l + (r - l) / 2; 
            if (time(piles, m) <= h) r = m - 1;
            else l = m + 1;
        }
        return l;
    }
}
