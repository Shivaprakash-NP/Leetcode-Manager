class Solution {
    public int minMoves(int[] nums) {
        int max = 0;
        for(int v : nums) max = Math.max(max, v);
        int n = 0;

        for(int v : nums) n += max-v;

        return n;
    }
}