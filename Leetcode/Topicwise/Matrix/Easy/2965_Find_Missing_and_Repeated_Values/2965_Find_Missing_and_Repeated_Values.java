class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        long n = grid.length * grid.length;
        long ns = (n * (n + 1)) / 2;
        long nss = (n * (n + 1) * (2 * n + 1)) / 6;
        long as = 0;
        long ass = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int v : grid[i]) {
                as += v;
                ass += (long) v * v;
            }
        }

        long x = as - ns;  
        long y = ass - nss; 

        long s = y / x; 

        int[] ans = new int[2];
        ans[0] = (int) ((s + x) / 2); 
        ans[1] = (int) ((s - x) / 2); 
        return ans;
    }
}
