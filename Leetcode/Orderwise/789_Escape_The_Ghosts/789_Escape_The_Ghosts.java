class Solution {
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        int d_h = Math.abs(target[0]) + Math.abs(target[1]);
        for(int[] v : ghosts)
        {
            if(Math.abs(target[0] - v[0]) + Math.abs(target[1] - v[1]) <= d_h) return false;
        }
        return true;
    }
}