class Solution {
    public int minimumBoxes(int[] apple, int[] capacity) {
        int sum = 0;
        for(int v : apple) sum += v;

        int n = capacity.length;
        
        Arrays.sort(capacity); 
        int l = 0;
        int r = n-1;
        while(l<r) {
            int t = capacity[l];
            capacity[l] = capacity[r];
            capacity[r] = t;
            l++;
            r--;
        }

        for(int i = 0; i<n; i++) {
            if(capacity[i] >= sum) return i+1;
            sum -= capacity[i];
        }

        return n;
    }
}