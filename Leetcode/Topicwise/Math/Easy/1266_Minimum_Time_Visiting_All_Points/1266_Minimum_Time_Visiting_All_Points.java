class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
        int ans = 0;

        for(int i = 0; i<points.length-1; i++) {
            int[] p1 = points[i];
            int[] p2 = points[i+1];

            int dx = Math.abs(p1[0]-p2[0]);
            int dy = Math.abs(p1[1]-p2[1]);

            ans += Math.max(dx, dy);
        }

        return ans;
    }
}