class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        Map<Integer, List<Integer>> map = new TreeMap<>();
        for(int i = 0; i<n; i++) {
            for(int j = 0; j<m; j++) {
                int k = i+j;
                map.computeIfAbsent(k, x -> new ArrayList<>()).add(mat[i][j]);
            }
        }

        int[] ans = new int[n*m];
        int ptr = 0;
        for(int key : map.keySet()) {
            if((key&1) == 0) {
                Collections.reverse(map.get(key));
            }

            for(int val : map.get(key)) ans[ptr++] = val;
        }

        return ans;
    }
}