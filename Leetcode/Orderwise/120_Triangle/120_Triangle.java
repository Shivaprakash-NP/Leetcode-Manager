class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();

        if(n == 1) return triangle.get(0).get(0);

        int[] pre = new int[n];
        pre[0] = triangle.get(0).get(0);


        for(int i = 1; i<n; i++) {
            int[] cur = new int[n];
            cur[0] = pre[0]+triangle.get(i).get(0);
            for(int j = 1; j<triangle.get(i).size()-1; j++) {
                cur[j] = Math.min(pre[j], pre[j-1])+triangle.get(i).get(j);
            }
            cur[triangle.get(i).size()-1] = pre[triangle.get(i).size()-2]+triangle.get(i).get(triangle.get(i).size()-1);
            pre = cur;
        }

        int ans = pre[0];
        for(int v : pre) ans = Math.min(ans, v);

        return ans;
    }
}