import java.util.Arrays;

class Solution {
    private boolean possible(int[] val, int m, int days) {
        int a = 1; 
        int c = 0;
        for (int v : val) 
        {
            c += v;
            if (c > m) 
            {
                a++; 
                c = v;
            }
        }
        return (a <= days);
    }

    public int shipWithinDays(int[] weights, int days) {
        int l = Arrays.stream(weights).max().getAsInt(); 
        int r = Arrays.stream(weights).sum(); 
        while (l <= r) 
        {
            int m = l + (r - l) / 2;
            if (possible(weights, m, days)) r = m - 1;
            else l = m + 1;
        }
        return l;
    }
}
