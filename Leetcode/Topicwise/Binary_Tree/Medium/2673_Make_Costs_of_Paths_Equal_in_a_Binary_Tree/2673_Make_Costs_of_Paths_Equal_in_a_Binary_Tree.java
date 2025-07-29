class Solution {
    int ans;
    private int dfs(int[] arr, int ind) {
        if(ind>=arr.length) return 0;

        int l = dfs(arr, ind*2+1);
        int r = dfs(arr, ind*2+2);

        ans+=Math.abs(r-l);

        return arr[ind]+Math.max(l , r);
    }
    public int minIncrements(int n, int[] cost) {
        dfs(cost, 0);
        return ans;
    }
}