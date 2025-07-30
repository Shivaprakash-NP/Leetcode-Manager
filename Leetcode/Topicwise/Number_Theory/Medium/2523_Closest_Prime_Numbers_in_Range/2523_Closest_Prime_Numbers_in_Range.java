class Solution {
    public int[] closestPrimes(int left, int right) {
        int[] ans = new int[]{-1 , -1};
        int l = left;
        int r = right;
        boolean[] is = new boolean[r+1];
        Arrays.fill(is , true);
        is[0] = false;
        is[1] = false;

        for(int i = 2 ; i*i<=r ; i++)
            if(is[i])
                for(int j = i*i ; j<=r ; j+=i) 
                    is[j] = false;

        int min = Integer.MAX_VALUE;
        int last = -1;
        for(int i = l ; i<=r ; i++) {
            if(!is[i]) continue;
            if(last != -1 && i-last < min) {
                min = i-last;
                ans[0] = last;
                ans[1] = i;
            }
            last = i;
        }

        return ans;
    }
}