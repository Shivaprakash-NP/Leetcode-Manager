class Solution {
    public int mirrorDistance(int n) {
        StringBuilder sb = new StringBuilder(n+"");
        sb.reverse();

        int v = Integer.parseInt(sb.toString());

        return Math.abs(n-v);
    }
}