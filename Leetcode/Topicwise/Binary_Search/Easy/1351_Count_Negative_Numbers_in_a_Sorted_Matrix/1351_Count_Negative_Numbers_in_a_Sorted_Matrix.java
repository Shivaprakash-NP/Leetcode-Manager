class Solution {
    public int countNegatives(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int cnt = 0;

        int i = 0;
        int j = m-1;
        while(i<n && j >= 0) {
            while(i<n && grid[i][j] >= 0) i++;
            cnt += n-i;
            j--;
        }

        return cnt;
    }
}