class Solution {
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int n = fruits.length;
        int maxPos = startPos + k;

        for (int[] fru : fruits) maxPos = Math.max(maxPos, fru[0]);

        int ans = Integer.MIN_VALUE;
        int[] arr = new int[maxPos+1];
        int[] pre = new int[maxPos+1];
        
        for(int[] fru : fruits) arr[fru[0]] = fru[1];

        pre[0] = arr[0];
        for(int i = 1; i<=maxPos; i++) pre[i]=pre[i-1]+arr[i];

        //left-right
        for(int l = 0; l<=k; l++) {
            int r = k-l*2;
            if(r<0) continue;
            int ls = Math.max(0, startPos-l);
            int le = startPos;
            int rs = startPos;
            int re = Math.min(maxPos, startPos+r);
            int lval = pre[le]-(ls>0?pre[ls-1]:0);
            int rval = pre[re]-(rs>0?pre[rs-1]:0);
            ans = Math.max(ans, rval+lval-arr[startPos]);
        }

        //right-left
        for(int r = 0; r<=k; r++) {
            int l = k-r*2;
            if(l<0) continue;
            int ls = Math.max(0, startPos-l);
            int le = startPos;
            int rs = startPos;
            int re = Math.min(maxPos, startPos+r);
            int lval = pre[le]-(ls>0?pre[ls-1]:0);
            int rval = pre[re]-(rs>0?pre[rs-1]:0);
            ans = Math.max(ans, rval+lval-arr[startPos]);
        }

        return ans;
    }
}