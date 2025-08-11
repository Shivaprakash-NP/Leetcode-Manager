class Solution {
    public int[] productQueries(int n, int[][] queries) {
        int mod = 1_000_000_007;     
        int size = queries.length;
        List<Long> list = new ArrayList<>();
        int tem = n;
        int i = 0;

        while(tem != 0) {
            if((tem&1) == 1) list.add((long)Math.pow(2, i)%mod);
            tem>>=1;
            i++;
        }

        int[] ans = new int[size];

        for(int k = 0; k<size; k++) {
            int st = queries[k][0];
            int ed = queries[k][1];
            long pro = 1;
            for(int v = st; v<=ed; v++) {
                pro = (pro * (list.get(v)%mod))%mod;
            }
            ans[k] = (int)pro;
        }

        return ans;
    }
}