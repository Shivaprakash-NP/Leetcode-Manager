class Solution {
    public long maxMatrixSum(int[][] matrix) {
        long sum = 0;
        int negcnt = 0;
        long low = Long.MAX_VALUE;

        for(int[] row : matrix) {
            for(int v : row) {
                if(v < 0)negcnt++;
                v = Math.abs(v);
                sum += Math.abs(v);
                low = Math.min(low, v);
            }
        }

        return (negcnt%2 == 0) ? sum : sum - 2*low;
    }
}